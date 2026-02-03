package org.dam.NeoAnalizer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NeoAnalizer {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("/home/hugo/Documents/2DAM_Hugo/PSP/practicasExamen/src/main/java/org/dam/NeoAnalizer/neos.csv"))) {
            String line;
            List<Process> processes = new ArrayList<>();
            int nProc = Runtime.getRuntime().availableProcessors();
            System.out.println("Número de cores: "+nProc);
            for (int i = 1; i <= nProc; i++) {
                line = br.readLine();

                String[] fields = line.split(",");
                String nombre = fields[0];
                String velocidad = fields[1];
                String posicion = fields[2];

                ProcessBuilder pb = new ProcessBuilder("java","src/main/java/org/dam/NeoAnalizer/calculoProb.java",nombre,velocidad,posicion);
                Process p = pb. start();
                processes.add(p);
            }

            for (Process p : processes) {
                try {
                    try (BufferedReader br2 = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                        String line2;
                        while ((line2 = br2.readLine()) != null) {
                            System.out.println(line2);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (
                FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
