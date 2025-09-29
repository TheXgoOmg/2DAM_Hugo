import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1  && args.length != 3) {
            System.out.printf("El número de argumentos no es corecto.%nArguments: <nombre_fichero> [-n [número de líneas]]");
            System.exit(1);
        }
        File file = new File(args[0]);
        if (!file.exists() || !file.isFile()) {
            System.out.printf("El fichero %s no existe o no es un fichero.", file.getName());
            System.exit(1);
        }
        if (args.length == 1) {
            Listar(file,10);
        }
        if (args.length == 3) {
            try {
                int num = Integer.parseInt(args[2]);
                Listar(file,num);
            } catch (NumberFormatException e) {
                System.out.printf("Formato incorrecto del número de líneas.%nError: %s.",e.getMessage());
                System.exit(1);
            }
        }


    }

    public static void Listar(File file, int num) {
        ArrayList<String> lineas = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            do {
                line = br.readLine();
                if (line != null) {
                    lineas.add(line);
                }
            } while (line != null);
        } catch (IOException ex) {
            System.out.printf("No se encuentra el fichero.%nError:",ex.getMessage());
        }

        for (int i = lineas.size()-num; i < lineas.size(); i++) {
            System.out.printf("%s%n", lineas.get(i));
        }
    }
}