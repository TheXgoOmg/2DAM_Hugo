package eco_binario;

import java.io.*;
import java.net.*;

public class ServerTCP_bin {
    static final int Puerto = 5000;

    public ServerTCP_bin() {
        try (
            ServerSocket skServer = new ServerSocket(Puerto);
        ) {
            System.out.println("Escuchando el puerto: " + Puerto);

            try (Socket sClient = skServer.accept();
                InputStream in = sClient.getInputStream();
                OutputStream out = sClient.getOutputStream();
            ) {
                byte[] buffer = new byte[1024];
                int bytesLeidos;

                while((bytesLeidos = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesLeidos);
                    out.flush();
                }
            } catch (Exception eCliente) {
                System.out.println("Error en el cliente: " + eCliente.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error del servidor: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
	    new ServerTCP_bin();
    }
}
