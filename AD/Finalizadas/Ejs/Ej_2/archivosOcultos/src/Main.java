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

        System.out.printf("--- Archivos Ocultos en '%s' ---%n", dir.getName());
        buscarOcultos(dir, true);
    }

    public static void buscarOcultos(File dir, boolean guion) {
        File[] files = dir.listFiles();
        assert files != null;
        for (File file:files) {
            if (file.isDirectory()) {
                if (guion) System.out.print("- ");
                System.out.printf("%s\\", file.getName());
                buscarOcultos(file, false);
            }
            if (file.isHidden()) {
                if (guion) System.out.print("- ");
                System.out.printf("%s%n", file.getName());
            }
        }
    }
}