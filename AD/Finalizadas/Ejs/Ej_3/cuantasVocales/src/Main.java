import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.printf("Argumentos incorrectos.%nArgumentos: <ruta_archivo> <vocal>");
            System.exit(1);
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.printf("El archivo %s no existe.%n",file.getName());
            System.exit(1);
        }
        if (!file.isFile()) {
            System.out.printf("El archivo %s no es un fichero.%n",file.getName());
            System.exit(1);
        }
        if (args[1].length() != 1) {
            System.out.printf("El argumento <vocal> debe ser un solo carácter.%n");
            System.exit(1);
        }

        try (FileInputStream fis = new FileInputStream(file)) {
            int bytes;
            String vocal = args[1];
            int contador = 0;
            do  {
                bytes = fis.read();
                if (bytes == vocal.toLowerCase().charAt(0) || bytes == vocal.toUpperCase().charAt(0)) {
                    contador++;
                }
            } while (bytes != -1);
            System.out.printf("Hay %d vocales '%s' en el archivo '%s'%n", contador, vocal, file.getName());
        } catch (Exception e) {
            System.out.printf("Error: %s%n",e.getMessage());
            e.printStackTrace();
        }
    }
}