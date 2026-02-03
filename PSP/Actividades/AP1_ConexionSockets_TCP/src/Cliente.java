import java.net.Socket;

class Cliente {
    static final String Host = "localhost";
    static final int Puerto = 2000;

    public Cliente() {
        try {
            Socket sCliente = new Socket(Host, Puerto);

            sCliente.close();
        } catch (Exception e ) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Cliente();
    }
}