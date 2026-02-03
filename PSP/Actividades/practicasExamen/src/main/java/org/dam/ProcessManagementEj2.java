package org.dam;

import java.io.IOException;

public class ProcessManagementEj2 {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("xed");
        try {
            Process p = pb.start();

            do {
                System.out.println("Process is still alive");
                Thread.sleep(3000);
            } while (p.isAlive());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
