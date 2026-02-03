package org.dam;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new Gson();
        File file = new File("BrandonSanderson.json");

        JsonReader reader = new JsonReader(new FileReader(file));

        JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
        List<Universo> universos = new ArrayList<>();

        for (JsonElement jsonElement : jsonObject.getAsJsonArray("universos")) {
            universos.add(gson.fromJson(jsonElement, Universo.class));
        }

        for (Universo universo : universos) {
            System.out.println(universo);
        }

        // Libros del Cosmere que comienzan por E
        System.out.println("\n\n=== LIBROS DEL COSMERE QUE COMIENZAN POR 'E' ===");
        universos.getFirst().getSagas().forEach(s -> s.getLibros().stream().filter(l -> l.getTitulo().startsWith("E")).forEach(System.out::println));
        System.out.println("=".repeat(50));

        // Número de libros No Cosmere que comienzan por P
        System.out.println("\n\n=== CANTIDAD LIBROS NO COSMERE QUE COMIENZAN POR 'S' ===");
        Long cantidad = universos.getLast().getSagas().stream().mapToLong(s -> s.getLibros().stream().filter(l -> l.getTitulo().startsWith("S")).count()).sum();
        System.out.println(cantidad);
        System.out.println("=".repeat(50));

        // Generar fichero CSV
        System.out.println("\n\n=== GENERAR FICHERO CSV ===");

        File fileCSV = new File("BrandonSanderson.csv");

        if (!fileCSV.exists()) {
            try {
                fileCSV.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        try (Writer writer = new FileWriter("BrandonSanderson.csv")) {
            writer.write("nombreSaga,cantidadLibros,nombreLibro\n");
            for (Universo universo:universos) {
                for (Saga saga : universo.getSagas()) {
                    for (Libro libro : saga.getLibros()) {
                        String nSaga = saga.getTitulo();
                        int cantLibros = saga.getLibros().size();
                        String nLibro = libro.getTitulo();

                        String line = String.format("%s,%d,%s\n",nSaga,cantLibros,nLibro);
                        writer.write(line);
                    }
                }
            }
            System.out.println("Fichero CSV escrito correctamente!!!");
            System.out.println("=".repeat(50));
        } catch (IOException e) {
            System.err.println("Error al escribir los datos en el fichero CSV");
            e.printStackTrace();
        }
    }
}
