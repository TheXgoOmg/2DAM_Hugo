import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

class Servidor {
    static final int Puerto = 2000;
    public Servidor() {
        try (ServerSocket skServidor = new ServerSocket(Puerto)) {
            System.out.println("Escuchando el puerto " + Puerto);

            for (int i = 1; i <= 3; i++) {
                try (
                        Socket sCliente = skServidor.accept();

                        BufferedReader in = new BufferedReader(new InputStreamReader(sCliente.getInputStream(), StandardCharsets.UTF_8));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(sCliente.getOutputStream(), StandardCharsets.UTF_8), true);
                ) {
                    out.println("Hola cliente #"+i);

                    String msgCliente = in.readLine();
		    if (msgCliente != null) {
			System.out.println("Mensaje Cliente: "+msgCliente);
		    } else {
			System.out.println("Mensaje Cliente: <conexión cerrada sin mensaje>");
		    }
                } catch (Exception eCliente) {
                    System.out.println("Error con elcliente "+i+": "+eCliente.getMessage());
                }

            }

            System.out.println("Servicio: fin de servicio (3 clientes atendidos)");
        } catch (Exception e) {
            System.out.println("Error del servidor: "+e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Servidor();
    }
}
