import java.net.Socket;

public class GestorBabosa implements Runnable {
    Socket socket;
    int idCliente;
    int distanciaRecorrida = 0;

    public GestorBabosa(Socket socket, int idCliente) {
        this.socket = socket;
        this.idCliente = idCliente;
    }

    @Override
    public void run() {
        try {
            while (distanciaRecorrida <= 100){
                Thread.sleep(100);
                avanzar();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void avanzar() {
        int nAvanzar = (int) (Math.random() * 10 + 1);
        distanciaRecorrida += nAvanzar;
    }
}
