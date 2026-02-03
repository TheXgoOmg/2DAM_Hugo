package org.cipfpcheste.dam2;

import com.google.gson.*;
import org.cipfpcheste.dam2.pojo.Comunidad;
import org.cipfpcheste.dam2.pojo.provinces;
import org.cipfpcheste.dam2.pojo.towns;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class Main {
    private static final Gson gson = new Gson();

    public static void main(String[] args) throws FileNotFoundException {

        Gson gson = new Gson();
        Reader lector = new FileReader("cvalenciana.json");

        // 1º forma de leer este. Es la más rápida, y es mapeando directamente a las clases. Fijaros porque es muy rápida.

        /// ///////////////////////////////////////////////////////////////////////////

//        JsonArray jsonArray = JsonParser.parseReader(lector).getAsJsonArray();
//        List<Comunidad> comunidades = new ArrayList<>();
//        for (int i = 0; i < jsonArray.size(); i++) {
//            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
//            comunidades.add(gson.fromJson(jsonObject, Comunidad.class));
//        }
//        System.out.println(comunidades.get(0).getProvinces().getLast().getTowns());

        // 2º forma de leer este. Accediendo a cada uno de los elementos. Las clases luego les podemos demoninar como queramos.

        JsonArray jsonArray2 = JsonParser.parseReader(lector).getAsJsonArray();
        JsonObject comunidadJson = jsonArray2.get(0).getAsJsonObject();

        Comunidad micomunidad = new Comunidad();
        micomunidad.setParent_code(comunidadJson.get("parent_code").getAsInt());
        micomunidad.setLabel(comunidadJson.get("label").getAsString());
        micomunidad.setCode(comunidadJson.get("code").getAsInt());

        // Obtenemos las provincias
        JsonArray provinciasArray = comunidadJson.getAsJsonArray("provinces");
        List<provinces> provincias = new ArrayList<>();

        for (JsonElement provinciaElement : provinciasArray) {
            JsonObject provinciaJson = provinciaElement.getAsJsonObject();

            provinces provincia = new provinces();
            provincia.setParent_code(provinciaJson.get("parent_code").getAsInt());
            provincia.setCode(provinciaJson.get("code").getAsInt());
            provincia.setLabel(provinciaJson.get("label").getAsString());

            // Procesar los municipios de cada provincia
            JsonArray townsArray = provinciaJson.getAsJsonArray("towns");
            List<towns> municipios = new ArrayList<>();

            for (JsonElement townElement : townsArray) {
                JsonObject townJson = townElement.getAsJsonObject();

                towns municipio = new towns();
                municipio.setParent_code(townJson.get("parent_code").getAsInt());
                municipio.setTown_code(townJson.get("town_code").getAsInt());
                municipio.setLabel(townJson.get("label").getAsString());

                municipios.add(municipio);
            }

            provincia.setTowns(municipios);
            provincias.add(provincia);
        }

        micomunidad.setProvinces(provincias);
        try {
            lector.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(micomunidad.getProvinces().getLast().getTowns());


        List<towns> ciudadesValencia= micomunidad.getProvinces().getLast().getTowns();

        Stream<towns> comienzanporA = ciudadesValencia.stream().filter(towns -> towns.getLabel().startsWith("A"));
        comienzanporA.forEach(System.out::println);

        List<towns> ciudadesCastellon= micomunidad.getProvinces().get(1).getTowns();
        Stream<towns> comienzanporB = ciudadesCastellon.stream().filter(towns -> towns.getLabel().startsWith("B"));
        comienzanporB.forEach(System.out::println);

        // Escribir en el fichero con una cabecera que incluye el nombre de provincia, cantidad de municipios y nombre municipio
        List<provinces> misprovincias = micomunidad.getProvinces();

        try (Writer writer = new FileWriter("datosMunicipiosProvincia.csv")) {
            // Escribir la cabecera
            writer.write("Nombre de provincia;Cantidad de municipios en la provincia;Nombre de municipio\n");
            for (provinces provincia : misprovincias) {
                String nombreProvincia = provincia.getLabel();
                int cantidadMunicipios = provincia.getTowns().size();

                // Para cada municipio en la provincia
                for (towns municipio : provincia.getTowns()) {
                    String nombreMunicipio = municipio.getLabel();

                    // Escribir la línea en el CSV
                    String linea = String.format("%s;%d;%s\n",
                            nombreProvincia,
                            cantidadMunicipios,
                            nombreMunicipio);
                    writer.write(linea);
                }
            }


    } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}