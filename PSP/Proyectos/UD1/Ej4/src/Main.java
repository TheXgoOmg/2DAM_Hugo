import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> comandos = new ArrayList<>(Arrays.asList("cmd.exe","/c","dir"));
        ProcessBuilder pb =  new ProcessBuilder(comandos);
        try {
            Process p = pb.start();
            System.out.printf("Directorio por defecto: %s%n",pb.directory());
            System.out.printf("Environment por defecto: %s%n",pb.environment());

            p.destroy();
        } catch (IOException e) {
            System.err.println("Error al iniciar el programa.");
        }

        File dir = new File("C:\\Users\\usuario\\Documents");
    }
}