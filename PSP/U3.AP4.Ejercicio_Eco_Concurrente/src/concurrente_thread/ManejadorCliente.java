package concurrente_thread;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ManejadorCliente implements Runnable {
    private Socket socket;
    private int idCliente;

    public ManejadorCliente(Socket socket, int  idCliente) {
        this.socket = socket;
        this.idCliente = idCliente;
    }

    @Override
    public void run() {
        try (
             InputStream in = socket.getInputStream();
             OutputStream out = socket.getOutputStream();
        ) {
            byte[] buffer = new byte[1024];
            int bytesLeidos;

            while((bytesLeidos = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesLeidos);
                out.flush();
            }
            System.out.println("Cliente #" + idCliente + " desconectado.");
        } catch (Exception eCliente) {
            System.out.println("Error en el cliente " + idCliente + ": " + eCliente.getMessage());
        }
        System.out.println("Hilo del cliente #" + idCliente + " finalizado.");
    }
}
