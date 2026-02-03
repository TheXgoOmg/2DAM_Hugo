package org.cipfpcheste.dam2;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import org.cipfpcheste.dam2.pojo.Comunidad;
import org.cipfpcheste.dam2.pojo.Provinces;
import org.cipfpcheste.dam2.pojo.Towns;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class Main {
    private static final Gson gson = new Gson();

    public static void main(String[] args) throws FileNotFoundException {

        Gson gson = new Gson();
        Reader lector = new FileReader("cvalenciana.json");

        JsonArray jsonArray = JsonParser.parseReader(lector).getAsJsonArray();

        ArrayList<Comunidad> comunidades = new ArrayList<>();
        for (JsonElement element : jsonArray) {
            comunidades.add(gson.fromJson(element.getAsJsonObject(), Comunidad.class));
        }

        System.out.println("--- Municipios de Valencia que comienzan por A ---");
        comunidades.getFirst().getProvinces().getLast().getTowns().stream().filter(t -> t.getLabel().startsWith("A")).forEach(System.out::println);

        System.out.println("\n--- Nº de municipios de Castellón que comienzan por B ---");
        long num = comunidades.getFirst().getProvinces().get(1).getTowns().stream().filter(t -> t.getLabel().startsWith("B")).count();
        System.out.println(num);

        System.out.println("\n--- Generar fichero CSV ---");
        try (PrintWriter pw = new PrintWriter("datosProvincias.csv")) {
            pw.println("NombreProvincia;CantidadMunicipios;Municipio");
            for (Provinces province : comunidades.getFirst().getProvinces()) {
                String nombre = province.getLabel();
                int cMunicipios = province.getTowns().size();
                for (Towns town : province.getTowns()) {
                    pw.printf("%s;%d;%s%n",
                            nombre,
                            cMunicipios,
                            town.getLabel());
                }
            }
        }
    }

}