package org.dam.Recapitulacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Recapitulacion {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Argumentos: <archivo>");
        }
        String file = args[0];

        for (char c = 'a'; c <= 'z'; c++) {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "sh", "-c",
                    "tr -dc '" + c + "' < '" + file + "' | wc -m"
            );
            processBuilder.inheritIO();
            Process p = processBuilder.start();

            try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(c + ": " + line);
                }
            }
        }

    }
}
