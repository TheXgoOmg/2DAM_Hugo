package concurrente_limite;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class ManejadorCliente_limite implements Runnable {
    private Socket socket;
    private int idCliente;
    private AtomicInteger activos;
    private AtomicInteger espera;

    public ManejadorCliente_limite(Socket socket, int  idCliente, AtomicInteger activos, AtomicInteger espera) {
        this.socket = socket;
        this.idCliente = idCliente;
        this.activos = activos;
        this.espera = espera;
    }

    @Override
    public void run() {
        try (
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
        ) {
            espera.decrementAndGet();
            activos.incrementAndGet();
            System.out.printf("Cliente #%d siendo atendido - %d\n", idCliente, socket.getPort());
            mostrarEstadoPool();

            byte[] buffer = new byte[1024];
            int bytesLeidos;

            while((bytesLeidos = in.read(buffer)) != -1) {
                if (buffer[0] == '.') {
                    break;
                }
                out.write(buffer, 0, bytesLeidos);
                out.flush();
            }
            System.out.printf("Cliente #%d desconectado - %d%n", idCliente, socket.getPort());
            activos.decrementAndGet();
            mostrarEstadoPool();
        } catch (Exception eCliente) {
            System.out.println("Error en el cliente " + idCliente + ": " + eCliente.getMessage());
        }
    }

    public void mostrarEstadoPool() {
        System.out.printf("Activos: %s, En espera: %s%n", activos, espera);
    }
}
