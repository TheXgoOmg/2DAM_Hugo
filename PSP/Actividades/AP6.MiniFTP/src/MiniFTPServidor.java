import java.io.*;
import java.net.*;

public class MiniFTPServidor {

    private static final int PUERTO = 5000;
    private static final String DIRECTORIO = "archivos";

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(PUERTO);
        System.out.println("Servidor Mini-FTP esperando conexión...");

        Socket socket = serverSocket.accept();
        System.out.println("Cliente conectado");

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        boolean salir = false;

        while (!salir) {
            String comando = in.readUTF();

            if (comando.equals("L")) {
                enviarListado(out);
            }
            else if (comando.startsWith("G ")) {
                String nombreArchivo = comando.substring(2);
                enviarArchivo(nombreArchivo, out);
            }
            else if (comando.equals("Q")) {
                salir = true;
            }
        }

        in.close();
        out.close();
        socket.close();
        serverSocket.close();

        System.out.println("Servidor cerrado");
    }

    private static void enviarListado(DataOutputStream out) throws IOException {

        File dir = new File(DIRECTORIO);
        File[] archivos = dir.listFiles();

        if (archivos == null) {
            out.writeUTF("No hay archivos disponibles");
            return;
        }

        StringBuilder listado = new StringBuilder();

        for (File f : archivos) {
            if (f.isFile()) {
                listado.append(f.getName()).append("\n");
            }
        }

        out.writeUTF(listado.toString());
    }

    private static void enviarArchivo(String nombre, DataOutputStream out)
            throws IOException {

        File archivo = new File(DIRECTORIO, nombre);

        if (!archivo.exists() || !archivo.isFile()) {
            out.writeLong(0);
            return;
        }

        out.writeLong(archivo.length());

        FileInputStream fileIn = new FileInputStream(archivo);
        byte[] buffer = new byte[4096];
        int bytesLeidos;

        while ((bytesLeidos = fileIn.read(buffer)) != -1) {
            out.write(buffer, 0, bytesLeidos);
        }

        fileIn.close();
    }
}
