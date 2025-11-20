package org.dam;

import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new Gson();
        File file = new File("cvalenciana.json");

        if (!file.exists()) {
            System.err.printf("El archivo %s no existe", file.getName());
            System.exit(1);
        }

        Reader reader = new FileReader(file);

        JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();

        ArrayList<Comunidad> comunidades = new ArrayList<>();
        for  (JsonElement jsonElement : jsonArray) {
            comunidades.add(gson.fromJson(jsonElement.getAsJsonObject(), Comunidad.class));
        }

        for (Comunidad comunidad : comunidades) {
            System.out.println(comunidad);

        }

        System.out.println("\n--- Municipios de Valencia que comiezan por A ---");
        comunidades.getLast().getProvincias().stream().filter(m -> m.getNombre().startsWith("A")).forEach(System.out::println);

        System.out.println("\n--- Número de municipios de Castellón que comienzan por B ---");

    }
}
