package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.lang.classfile.Attribute;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        File file = new File("SW.json");
        if (!file.exists()) {
            System.out.printf("El archivo %s no existe.",file.getName());
            System.exit(1);
        }
        String jsonString = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            do {
                if ((line = br.readLine()) != null) {
                    jsonString += line;
                }
            } while (line != null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
        JsonArray arrayResults = jsonObject.get("results").getAsJsonArray();

        // Personajes que no condujeron ningún vehículo
        System.out.println("--- PERSONAJES QUE NO CONDUJERON NINGÚN VEHÍCULO ---");
        for (JsonElement jsonElement : arrayResults) {
            if (jsonElement.getAsJsonObject().get("vehicles").getAsJsonArray().isEmpty()) {
                System.out.println(jsonElement.getAsJsonObject().get("name").getAsString());
            }
        }

        // Ordenar personajes por número de películas
        System.out.printf("%n%n--- PERSONAJES ORDENADOS POR NÚMERO DE PELÍCULAS ---%n");
        ArrayList<JsonElement> array = new ArrayList<>();
        for (JsonElement jsonElement : arrayResults) {
            array.add(jsonElement);
        }

        array.sort((p1,p2)->p2.getAsJsonObject().get("films").getAsJsonArray().size()-p1.getAsJsonObject().get("films").getAsJsonArray().size());
        for (JsonElement jsonElement : array) {
            System.out.printf("%d películas, %s%n",jsonElement.getAsJsonObject().get("films").getAsJsonArray().size(),jsonElement.getAsJsonObject().get("name").getAsString());
        }

        // Crear fichero XML
        File ficheroXML = new File("sw.xml");
        if (!ficheroXML.exists()) {
            try {
                ficheroXML.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Document doc = null;
        try {
            // Crear documento
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        // Crear y añadir elemento root "characters"
        Element root = doc.createElement("characters");
        doc.appendChild(root);

        // Por cada character del JsonArray
        for (JsonElement jsonElement : arrayResults) {
            // Crear elemento "character"

            Element character = doc.createElement("character");
            root.appendChild(character);

            // Añadir Atributos
            character.setAttribute("films", String.valueOf(jsonElement.getAsJsonObject().getAsJsonArray("films").size()));
            character.setAttribute("vehicles", String.valueOf(jsonElement.getAsJsonObject().get("vehicles").getAsJsonArray().size()));

            // Crear y añadir elementos "nombre", "mass" y "url"
            Element name = doc.createElement("name");
            character.appendChild(name);

            Element mass = doc.createElement("mass");
            character.appendChild(mass);

            Element url = doc.createElement("url");
            character.appendChild(url);

            // Añadir TextContents
            name.setTextContent(jsonElement.getAsJsonObject().get("name").getAsString());

            mass.setTextContent(jsonElement.getAsJsonObject().get("mass").getAsString());

            url.setTextContent(jsonElement.getAsJsonObject().get("url").getAsString());
        }
        try {
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileOutputStream(ficheroXML));

            try {
                trans.transform(source, result);
                System.out.printf("%n%n%nFICHERO XML CREADO CORRECTAMENTE%n%n%n");
            } catch (TransformerException e) {
                System.out.println("Error al crear el fichero XML");
                throw new RuntimeException(e);
            }
        } catch (TransformerConfigurationException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}