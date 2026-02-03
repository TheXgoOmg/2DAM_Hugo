package eco_texto;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientTCP_text {
    static final String Host = "localhost";
    static final int Puerto = 5000;

    public ClientTCP_text() throws IOException {
        try (
            Socket sClient = new Socket(Host, Puerto);
            BufferedReader in = new BufferedReader(new InputStreamReader(sClient.getInputStream()));
            PrintWriter out = new PrintWriter(sClient.getOutputStream(), true);
            Scanner sc = new Scanner(System.in);
        ) {
            while (true) {
                System.out.print("Escribe tu mensaje: ");
                String msg = sc.nextLine();

                if (msg.equals(".")) {
                    System.out.println("Desconectando del servidor...");
                    break;
                }

                out.println(msg);

                String respuesta = in.readLine();
                System.out.println("Servidor: " + respuesta);
            }
        } catch(IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    	
    }

    public static void main(String[] args) throws IOException {
    	new ClientTCP_text();
    }
}
