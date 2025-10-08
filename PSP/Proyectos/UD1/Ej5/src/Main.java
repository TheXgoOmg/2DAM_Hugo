import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> comandos = new ArrayList<>();
        if (System.getProperty("os.name").equals("windows")) {
            comandos = new ArrayList<>(List.of("find","/c","/v"));
        } else {
            comandos = new ArrayList<>(List.of("wc"));
        }

        ProcessBuilder pb = new ProcessBuilder(comandos);
        pb.inheritIO();

        Scanner sc = new Scanner(System.in);

        try {
            String line;
            do {
                line = sc.nextLine();
                comandos.add(line);
                Process p = pb.start();
                p.destroy();
            } while (!line.equals("0"));

        } catch (IOException e) {
            System.err.printf("Error al ejecutar el proceso: %s\n", e.getMessage());
        }

    }
}