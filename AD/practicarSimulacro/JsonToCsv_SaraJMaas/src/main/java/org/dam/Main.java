package org.dam;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        File jsonFile = new File("SaraJMaas.json");
        ArrayList<Universo> universos = new ArrayList<>();
        try (Reader reader = new FileReader(jsonFile)) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray jsonArray = jsonObject.getAsJsonArray("universes");

            for (JsonElement jsonElement : jsonArray) {
                universos.add(gson.fromJson(jsonElement, Universo.class));
            }

            universos.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Error al leer el fichero: " + e.getMessage());
        }
        System.out.println("\n-- Saga Trono empieza por T ---\n");
        universos.getFirst().getSagas().getFirst().getLibros().stream().filter(l -> l.getTitulo().startsWith("T")).forEach(System.out::println);

        System.out.println("\n-- Generar CSV ---\n");


        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("SaraJMaas.csv")))) {
            pw.println("NombreSaga,CantidadLibros,NombreLibro");
            int cont=0;
            for (Universo universo : universos)  {
                for (Saga saga : universo.getSagas()) {
                    for (Libro libro : saga.getLibros()) {
                        pw.printf("%s,%s,%s%n",saga.getTitulo(),saga.getLibros().size(),libro.getTitulo());
                        cont++;
                    }
                }
            }
            System.out.println("Total de libros: "+cont);

        } catch (IOException e) {
            System.err.println("Error al escribir el fichero: " + e.getMessage());
        }

    }
}