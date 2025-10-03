import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    // Valores mágicos que identifican un archivo BMP y PNG.
    // Los "magic numbers" son los primeros bytes que permiten saber el formato del archivo.
    final static byte[] BMP_MAGIC_VALUE = {0x42, 0x4D}; // "BM" en ASCII

    // Cabecera de un PNG: 89 50 4E 47 0D 0A 1A 0A
    final static byte[] PNG_MAGIC_VALUE = { -0x77, 'P', 'N', 'G', '\r', '\n', 0x1A, '\n'};
    final static byte[] IHDR = {'I', 'H', 'D', 'R'}; // Primer chunk de datos en un PNG (contiene ancho y alto)
    final static byte[] IEND = {'I', 'E', 'N', 'D'}; // Último chunk de un PNG

    public static void main(String[] args) {
        // Comprobamos que se ha pasado exactamente un argumento (la ruta del archivo a analizar)
        if (args.length != 1) {
            System.out.println("Argumentos incorrectos");
            return;
        }

        String ruta = args[0]; // Ruta del archivo a leer

        byte[] tipo = new byte[4]; // Para leer los tipos de "chunks" en PNG
        try (DataInputStream dis = new DataInputStream(new FileInputStream(ruta))) {
            byte[] boff = new byte[8]; // Buffer para leer cabeceras de archivo

            // Leemos los dos primeros bytes del archivo
            dis.readFully(boff, 0, 2);

            // --- CASO BMP ---
            // Si los dos primeros bytes son "BM", el archivo es un BMP
            if (Arrays.equals(BMP_MAGIC_VALUE, 0, 2, boff, 0, 2)) {
                // Saltamos 16 bytes hasta llegar al campo de ancho y alto
                dis.skip(16);

                // En los BMP, ancho y alto se almacenan como enteros en "little endian",
                // por eso usamos Integer.reverseBytes() para invertir el orden de los bytes.
                System.out.println("Anchura: " + Integer.reverseBytes(dis.readInt()));
                System.out.println("Altura: " + Integer.reverseBytes(dis.readInt()));

                // --- CASO PNG ---
            } else if (Arrays.equals(PNG_MAGIC_VALUE, 0, 2, boff, 0, 2)) {
                // Ya leímos 2 bytes, ahora completamos los otros 6 para verificar la cabecera completa
                dis.readFully(boff, 2, 6);

                // Comprobamos si los 8 bytes coinciden con el valor mágico de un PNG
                if (Arrays.equals(PNG_MAGIC_VALUE, boff)) {
                    do {
                        // Leemos la longitud del bloque de datos (4 bytes)
                        int longitud = dis.readInt();

                        // Leemos el tipo de chunk (4 bytes)
                        dis.readFully(tipo);

                        // Si el chunk es "IHDR", contiene ancho y alto
                        if (Arrays.equals(tipo, IHDR)) {
                            // OJO: En PNG, los enteros están en big endian, así que no invertimos bytes.
                            System.out.println("Anchura: " + dis.readInt());
                            System.out.println("Altura: " + dis.readInt());
                            return; // Terminamos, ya tenemos la información que queríamos
                        } else {
                            // Si no es IHDR, saltamos el contenido del chunk
                            dis.skip(longitud);
                        }

                        // Después de cada chunk hay 4 bytes de CRC que también hay que saltar
                        dis.skip(4);

                        // Repetimos hasta llegar al chunk IEND (que marca el final del archivo)
                    } while (!Arrays.equals(tipo, IEND));
                }
            }
        } catch (IOException e) {
            // Si ocurre un error al abrir o leer el archivo, lo mostramos en consola
            System.err.println("Error: " + e.getMessage());
        }
    }
}
