import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> comandos = new ArrayList<>();
        if (System.getProperty("os.name").contains("Windows")) {
            comandos = new ArrayList<>(List.of("cmd.exe", "/c", "find","/c","/v", ""));
        } else if (System.getProperty("os.name").contains("Linux")) {
            comandos = new ArrayList<>(List.of("wc", "-l", "<"));
        }

        ProcessBuilder pb = new ProcessBuilder(comandos);

        Scanner sc = new Scanner(System.in);

        try {
            String file;
            do {
                System.out.printf("Introduce el archivo: ");
                file = sc.nextLine();
                if (!file.equals("0")) {
                    if (comandos.size() > 6) {
                        comandos.removeLast();
                    }

                    comandos.add(file);

                    pb.redirectInput(new File(file));

                    pb.inheritIO();

                    Process p = pb.start();
                    p.waitFor();
                    p.destroy();

                }
            } while (!file.equals("0"));
        } catch (IOException | InterruptedException e) {
            System.err.printf("Error al ejecutar el proceso: %s\n\n", e.getMessage());
        }
    }
}