import java.net.*;

class Servidor {
    static final int Puerto = 2000;
    public Servidor() {
        try {
            ServerSocket skServidor = new ServerSocket(Puerto);
            System.out.println("Escuchando el puerto " + Puerto);

            for (int i = 1; i <= 3; i++) {
                Socket sCliente = skServidor.accept();

                System.out.printf("Cliente #%d conectado%n",i);

                sCliente.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Servidor();
    }
}