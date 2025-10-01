package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
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

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroXML))) {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element root = doc.createElement("character");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}