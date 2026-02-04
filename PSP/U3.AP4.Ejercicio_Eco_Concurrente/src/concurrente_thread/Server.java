package concurrente_thread;

import java.net.*;

public class Server {
    static final int Puerto = 5000;

    public Server() {
        try (
            ServerSocket skServer = new ServerSocket(Puerto);
        ) {
            System.out.println("Escuchando el puerto: " + Puerto);

            for (int i = 1; true ; i++) {
                Socket sClient = skServer.accept();
                System.out.printf("Cliente #%d conectado - %s\n", i, sClient.getPort());
                Thread hilo = new Thread(new ManejadorCliente(sClient, i));
                hilo.start();
            }
        } catch (Exception e) {
            System.out.println("Error del servidor: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
	    new Server();
    }
}
