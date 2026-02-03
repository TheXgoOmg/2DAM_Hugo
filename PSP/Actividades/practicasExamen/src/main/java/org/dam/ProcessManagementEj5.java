package org.dam;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ProcessManagementEj5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String file;

        do {
            file = sc.nextLine();
            if (!file.strip().equals("0")) {
                ProcessBuilder pb;
                if (System.getProperty("os.name").toLowerCase().contains("win")) {
                    pb = new ProcessBuilder("find","/c","/v", file);
                } else {
                    pb = new ProcessBuilder("wc", file).directory(new File("/home/hugo/Documents/"));
                }
                pb.inheritIO();
                try {
                    pb.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } while (!file.strip().equals("0"));
    }
}
