package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class GestorCliente implements Runnable {
    private final Socket socket;
    private int id;
    private final Catalogo catalogo;

    public GestorCliente(Socket socket, int id) {
        this.socket = socket;
        this.id = id;
        this.catalogo = new Catalogo();
    }

    @Override
    public void run() {
        System.out.printf("Cliente %d conectado%n", id);

        try (
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        ) {
            dos.writeUTF("BIENVENIDO:"+id);

            String mensajeCliente = "";
            while(!mensajeCliente.equals("SALIR")){
                mensajeCliente = dis.readUTF();
                if (mensajeCliente.equals("CATALOGO")) {
                    String mensaje;
                    mensaje = catalogo.getCatalogo().toString();
                    dos.writeUTF(mensaje);
                }
                else if (mensajeCliente.equals("SALIR")) {
                    dos.writeUTF("ADIOS");
                    break;
                } else {
                    dos.writeUTF("[ERROR] Comando no reconocido");
                }
            }
            socket.close();
            System.out.printf("Cliente %d desconectado%n", id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
