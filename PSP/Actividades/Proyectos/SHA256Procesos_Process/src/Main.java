import java.io.*;
import java.security.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Process> procesos = new ArrayList<Process>();

        long inicio = System.nanoTime();
        for (String archivo:args) {
            String classpath =  System.getProperty("java.class.path");
            String[] cmd = {"java", "-cp", classpath, "HashSHA256", archivo};
            ProcessBuilder pb = new ProcessBuilder(cmd);
            try {
                pb.inheritIO();
                Process p = pb.start();
                procesos.add(p);
            } catch (IOException e) {
                System.err.println("Exception: "+e.getMessage());
                System.exit(-1);
            }
        }

        for (Process p : procesos) {
            try {
                p.waitFor();
            } catch (InterruptedException e) {
                System.err.println("Exception: "+e.getMessage());
                System.exit(-1);
            }
        }
        long fin = System.nanoTime();
        System.out.println("Tiempo total del PROGRAMA: " + (fin - inicio)/1_000_000 + " ms");
    }
}