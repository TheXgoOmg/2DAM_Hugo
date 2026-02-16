import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
    final static String Host = "localhost";
    final static int Port = 1111;
    private int id;

    public Cliente(int idCliente) {
        this.id = id;

        try (
                Socket socket = new Socket(Host, Port);
        ) {

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new Cliente();
    }
}
