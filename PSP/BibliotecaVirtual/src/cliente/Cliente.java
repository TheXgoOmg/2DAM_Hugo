package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private static final String Host = "localhost";
    private static final int Port = 7777;

    public Cliente() throws IOException {
        try (
                Socket socket = new Socket(Host, Port);
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                Scanner sc = new Scanner(System.in);
        ) {
            String mensaje = "";
            while (true) {
                String mensajeServer = dis.readUTF();
                System.out.println("[Servidor] "+mensajeServer);

                System.out.print("> ");
                mensaje = sc.nextLine();

                if (!mensaje.isEmpty()) {
                    dos.writeUTF(mensaje);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Cliente();
    }
}
