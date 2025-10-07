import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Ej3 {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Introduzca el comando...");
            System.exit(1);
        }

        ArrayList<String> comandos = new ArrayList<>()["cmd.exe","/c","dir",args]
        ProcessBuilder pb = new ProcessBuilder(comandos);
        try {
            Process p = pb.start();
            try (InputStream is = p.getInputStream();
                 InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader br = new BufferedReader(isr)) {
                int codRet = p.waitFor();
                System.out.println("La ejecución de " + "'dir " + Arrays.toString(args)
                        + "' devuelve " + codRet
                        + " " + (codRet == 0 ? "(ejecución OK)" : "(ERROR!!!)")
                );
                System.out.println("Salida del proceso");
                System.out.println("------------------");
                String linea = null;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
                System.out.println("------------------");
            }
        } catch (IOException e) {
            System.err.println("Error durante ejecución del proceso");
            e.printStackTrace();
            System.exit(2);
        } catch (InterruptedException ex) {
            System.err.println("Proceso interrumpido...");
            System.exit(3);
        }
    }
}