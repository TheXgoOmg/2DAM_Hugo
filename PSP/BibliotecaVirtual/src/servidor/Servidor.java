package servidor;

import cliente.GestorCliente;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor {
    static final int Port = 7777;
    static ExecutorService pool = Executors.newFixedThreadPool(15);

    public Servidor() {
        try (ServerSocket skServer = new ServerSocket(Port)) {
            System.out.println("Servidor iniciado");
            System.out.println("Escuchando el puerto "+Port);

            int id = 0;
            while (true) {
                Socket socket = skServer.accept();
                id++;
                GestorCliente gestor = new GestorCliente(socket, id);
                pool.execute(gestor);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new Servidor();
    }
}
