import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MiniFTPCliente {
    private static final int Puerto = 5000;
    private static final String Host = "localhost";

    public static void main(String[] args) {
        try (
                Socket sClient = new Socket(Host, Puerto);
                DataInputStream din = new DataInputStream(sClient.getInputStream());
                DataOutputStream dout = new DataOutputStream(sClient.getOutputStream());
                Scanner sc = new Scanner(System.in);
        ) {
            while (true) {
                String mensaje = "";

                MostrarMenu();
                System.out.print("Escribe el mensaje: ");
                mensaje = sc.nextLine();
                if (!mensaje.isEmpty()) {
                    dout.writeUTF(mensaje);
                }

                if (mensaje.startsWith("G ")) {
                    long size = din.readLong();
                    if (size == 0) {
                        System.out.println("No se ha encontrado el archivo.");
                    } else {
                        String directorio = "archivos_descargados";
                        String nombre = mensaje.substring(2);
                        boolean creado = false;
                        File fileDescargado = new File(directorio, nombre);
                        if (!fileDescargado.exists() || !fileDescargado.isFile()) {
                            creado = fileDescargado.createNewFile();
                            if (creado) {
                                System.out.println("Archivo "+nombre+" creado correctamente.");
                            } else {
                                System.out.println("Error al crear el archivo "+nombre+".");
                            }
                        }

                        try (
                                FileOutputStream fos = new FileOutputStream(fileDescargado);
                        ) {
                            byte[] buffer = new byte[4096];
                            long recibidos = 0;

                            while (recibidos < size) {
                                int bytesLeidos = din.read(buffer);
                                fos.write(buffer, 0, bytesLeidos);
                                recibidos += bytesLeidos;
                            }
                            System.out.println("Archivo "+nombre+" descargado correctamente.");
                        } catch (IOException e) {
                            System.out.println("Error al descargar el archivo.");
                        }
                    }
                } else {
                    mensaje = din.readUTF();
                    if (!mensaje.isEmpty()) {
                        System.out.println(mensaje);
                    }
                }
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("\nSesión cerrada...");
        }

    }

     public static void MostrarMenu() {
        System.out.println("\n=== MENÚ DE COMANDOS ===");
        System.out.println("L - Solicita el listado de archivos disponibles en el servidor.");
        System.out.println("G <nombreArchivo> - Solicita la descarga de un archivo.");
        System.out.println("Q - Cierra la conexión y termina el programa.");
        System.out.println();
    }
}
