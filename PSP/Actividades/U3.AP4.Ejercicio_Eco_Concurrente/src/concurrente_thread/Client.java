package concurrente_thread;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    static final String Host = "localhost";
    static final int Puerto = 5000;

    public Client() throws IOException {
        try (
            Socket sClient = new Socket(Host, Puerto);
            InputStream in = sClient.getInputStream();
            OutputStream out = sClient.getOutputStream();
            Scanner sc = new Scanner(System.in);
        ) {
            while (true) {
                String msg = "";

                System.out.print("Escribe tu mensaje: ");
                msg = sc.nextLine();

                if (msg.equals(".")) {
                    sClient.close();
                    System.out.println("Desconectando del servidor...");
                    break;
                }

                byte[] datos = (msg + "\n").getBytes();

                out.write(datos);
                out.flush();

                byte[] buffer = new byte[1024];
                int bytesLeidos = in.read(buffer);

                String respuesta = new String(buffer, 0, bytesLeidos, StandardCharsets.UTF_8);
                System.out.println("Servidor: " + respuesta);
            }
        } catch(IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    	
    }

    public static void main(String[] args) throws IOException {
    	new Client();
    }
}
