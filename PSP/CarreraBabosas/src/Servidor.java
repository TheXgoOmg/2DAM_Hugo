import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Servidor {
    final static int Port = 1111;
    ExecutorService pool = Executors.newFixedThreadPool(5);
    private static AtomicInteger posicionFinal = new AtomicInteger(1);
    private static AtomicInteger clientesAtendidos = new AtomicInteger(0);

    public Servidor() throws IOException {
        int idCliente = 0;
        try (
            ServerSocket skServer = new ServerSocket(Port);
        ) {
            while (true) {
                Socket cSocket = skServer.accept();
                idCliente++;
                clientesAtendidos.incrementAndGet();
                pool.execute(new GestorBabosa(cSocket, idCliente));
                if (clientesAtendidos.get() >= 2) {

                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Servidor servidor = new Servidor();
    }
}
