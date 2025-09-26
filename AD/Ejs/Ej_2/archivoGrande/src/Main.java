import java.io.File;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.printf("Argumentos incorrectos.%nArgumentos: <ruta_directorio>");
            System.exit(1);
        }
        String arg = args[0];
        File dir = new File(arg);
        if (!dir.exists()) {
            System.out.println("Error: El directorio no existe.");
            System.exit(0);
        }
        if (!dir.isDirectory()) {
            System.out.println("Error: El argumento no es un directorio.");
            System.exit(0);
        }

        System.out.printf("--- Archivo más grande en '%s' ---%n", dir.getName());

        File[] files = dir.listFiles();
        File fileGrande = new File("");
        for (File file : files) {
            long masGrande = Math.max(file.length(), fileGrande.length());
            if (masGrande == file.length()) {
                fileGrande = file;
            }
        }
        System.out.println(fileGrande.getName());
    }
}