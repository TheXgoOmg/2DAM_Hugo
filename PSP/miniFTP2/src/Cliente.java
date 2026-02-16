import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    private static final String Host = "localhost";
    private static final int Port = 5000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(Host, Port);
             Scanner sc = new Scanner(System.in);
             DataInputStream dis = new DataInputStream(socket.getInputStream());
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        ) {
            String mensaje = "";
            while (socket.isConnected()) {
                System.out.print("> ");
                mensaje = sc.nextLine();
                if (mensaje.isEmpty()) continue;

                dos.writeUTF(mensaje);
                dos.flush();

                if (mensaje.startsWith("G ")) {
                    Long length = dis.readLong();

                    if (length == 0) {
                        System.out.println("Error: El archivo no existe");;
                    } else {
                        File file = new File("descargas/"+mensaje.split(" ")[1]);
                        if (!file.exists()) {
                            boolean creado = file.createNewFile();
                            if (creado) {
                                System.out.println("Archivo creado correctamente");
                            } else {
                                System.out.println("Error al crear el archivo");
                            }
                        }
                        try (FileOutputStream fos = new FileOutputStream(file, true)) {
                            byte[] buffer = new byte[4096];
                            int totalRead = 0;
                            int bytesRead = 0;
                            while (totalRead < length) {
                                bytesRead = dis.read(buffer);
                                fos.write(buffer, 0, bytesRead);
                                totalRead += bytesRead;
                            }
                        }
                        System.out.println("Archivo enviado correctamente");
                    }
                } else {
                    String mensajeServer = dis.readUTF();
                    if (!mensajeServer.isEmpty()) {
                        System.out.println("[Servidor] " + mensajeServer);
                    }
                }
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Desconectando del servidor...");
        }
    }
}
