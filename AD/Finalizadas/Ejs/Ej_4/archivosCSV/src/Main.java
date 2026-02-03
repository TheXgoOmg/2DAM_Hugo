import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.printf("Número de argumentos invalido.%nArguments: <nombre_fichero> <separador> <(s/n) encabezado");
            System.exit(0);
        }
        if (!args[2].equals("s") && !args[2].equals("n")) {
            System.out.printf("Segundo argumento inválido.%nArguments: <separador> <(s/n) encabezado");
            System.exit(0);
        }

        boolean inicio = false;
        if (args[2].equals("s")) {
            inicio = true;
        }

        String separador = args[1];

        File file = new File(args[0]);
        if (!file.exists() || !file.isFile() || !file.canRead()) {
            System.out.println("Error al encontrar o no se puede leer.");
            System.exit(1);
        }
        try (FileInputStream fis = new FileInputStream(file)) {
            int bytes;
            do {
                bytes = fis.read();
                if (bytes != -1) {
                    char caracter = (char) bytes;
                    if (caracter == separador.charAt(0)) {
                        System.out.print("\n");
                        inicio = false;
                    } else {
                        if (!inicio) {
                            System.out.print(caracter);
                        }
                    }
                }
            } while (bytes != -1);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
            e.printStackTrace();
            System.exit(1);
        }
    }
}