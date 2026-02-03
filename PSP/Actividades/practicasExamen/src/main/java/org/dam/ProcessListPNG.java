package org.dam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessListPNG {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main.jar <path>");
            System.exit(1);
        }

        try {
            String[] terminal = {"find", args[0], "-name", "*.png"};
            ProcessBuilder pb = new ProcessBuilder(terminal);
            Process p = pb.start();
            p.waitFor();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }

        } catch (IOException e) {
            System.err.println("Exception: " + e.getMessage());
            System.exit(-1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}