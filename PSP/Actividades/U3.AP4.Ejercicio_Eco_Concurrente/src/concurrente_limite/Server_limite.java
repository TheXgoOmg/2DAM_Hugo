package concurrente_limite;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Server_limite {
    static final int Puerto = 5000;
    static final ExecutorService pool = Executors.newFixedThreadPool(2);
    private static final AtomicInteger clientesActivos = new AtomicInteger(0);
    private static final AtomicInteger clientesEnEspera = new AtomicInteger(0);

    public Server_limite() {
        try (
            ServerSocket skServer = new ServerSocket(Puerto);
        ) {
            System.out.println("Escuchando el puerto: " + Puerto);

            for (int i = 1; true ; i++) {
                Socket sClient = skServer.accept();
                System.out.printf("Cliente #%d conectado - %s\n", i, sClient.getPort());

                ManejadorCliente_limite manejador = new ManejadorCliente_limite(sClient, i, clientesActivos, clientesEnEspera);
                Thread hilo = new Thread(manejador);
                clientesEnEspera.incrementAndGet();
                manejador.mostrarEstadoPool();
                pool.execute(hilo);
            }
        } catch (Exception e) {
            System.out.println("Error del servidor: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
	    new Server_limite();
    }
}
