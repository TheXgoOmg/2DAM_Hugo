import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;

class Cliente {
    static final String Host = "localhost";
    static final int Puerto = 2000;

    public Cliente() {
        try (
            Socket sCliente = new Socket(Host, Puerto);
	    BufferedReader in = new BufferedReader(new InputStreamReader(sCliente.getInputStream(), StandardCharsets.UTF_8));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(sCliente.getOutputStream(), StandardCharsets.UTF_8), true);

	    Scanner sc = new Scanner(System.in);

	) {

            String msgServidor = in.readLine();

	    if (msgServidor != null) 
	    {
		System.out.println("Mensaje Servidor: " + msgServidor);
	    } else {
		System.out.println("Mensaje Servidor: <no hay mensaje del Servidor>");
	    }

            System.out.println("Escriba el mensaje para enviar al servidor:");
            String mensaje = sc.nextLine();

	    if (mensaje == null) mensaje = "";

            out.println(mensaje);

	    System.out.println("Conexión cerrada");
        } catch (Exception e ) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Cliente();

    }
}
