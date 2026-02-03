package eco_texto;

import java.io.*;
import java.net.*;

public class ServerTCP_text {
    static final int Puerto = 5000;

    public ServerTCP_text() {
        try (
            ServerSocket skServer = new ServerSocket(Puerto);
        ) {
            System.out.println("Escuchando el puerto: " + Puerto);

            try (Socket sClient = skServer.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(sClient.getInputStream()));
                PrintWriter out = new PrintWriter(sClient.getOutputStream(), true);
            ) {
                String mensaje;

                while((mensaje = in.readLine()) != null) {
                    out.println(mensaje);
                }

                System.out.println("Cliente desconectado");

            } catch (Exception eCliente) {
                System.out.println("Error en el cliente: " + eCliente.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error del servidor: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
	    new ServerTCP_text();
    }
}