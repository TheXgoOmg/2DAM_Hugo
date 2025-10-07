import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> comandos = new ArrayList<>(Arrays.asList("cmd.exe","/c","dir"));
        ProcessBuilder pb =  new ProcessBuilder(comandos);
        try {
            // DEFECTO
            Process p = pb.start();
            System.out.printf("Directorio por defecto: %s%n",pb.directory());
            System.out.printf("Environment por defecto:%n");
            System.out.printf("------------------------%n");
            Map<String,String> env = pb.environment();
            for (String key : env.keySet()) {
                System.out.printf("| %s= %s%n",key,env.get(key));
            }
            System.out.printf("------------------------%n");

            p.destroy();

            // PRUEBA 1
            p = pb.start();
            File dir = new File("C:\\Users\\usuario\\Documents\\2DAM_Hugo\\PSP");
            pb.directory(dir);
            System.out.printf("--- PRUEBA 1 ---%n%n");
            System.out.printf("Directorio: %s%n%n",pb.directory());
            System.out.printf("------------------------%n");

            p.destroy();

            // PRUEBA 2
            p = pb.start();
            System.out.printf("--- PRUEBA 2 ---%n%n");
            System.out.printf("Directorio: %s%n%n",pb.directory());
            System.out.printf("------------------------%n");

            p.destroy();

            // PRUEBA 3
            p = pb.start();
            System.out.printf("--- PRUEBA 3 ---%n%n");
            System.out.printf("Directorio: %s%n%n",pb.directory());
            System.out.printf("------------------------%n");

            p.destroy();
        } catch (IOException e) {
            System.err.println("Error al iniciar el programa.");
        }

        File dir = new File("C:\\Users\\usuario\\Documents");
    }
}