package org.dam;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProcessManagementEj4 {
    public static void main(String[] args) throws IOException {
        List<ProcessBuilder> pbs = new ArrayList<ProcessBuilder>();
        pbs.add(new ProcessBuilder("ls"));
        pbs.add(new ProcessBuilder("ls").directory(new File("/home/hugo/Documents")));

        int cont = 1;
        for (ProcessBuilder pb : pbs) {
            Process p = pb.start();
            System.out.println(cont + ". Directory: " + (pb.directory() != null ? pb.directory() : new File("").getAbsolutePath()));
            System.out.println("-".repeat(20));
            try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
            System.out.println("-".repeat(20));
        }
    }
}