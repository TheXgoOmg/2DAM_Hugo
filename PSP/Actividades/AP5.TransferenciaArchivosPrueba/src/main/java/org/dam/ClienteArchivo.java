package org.dam;

import java.io.*;
import java.net.*;

public class ClienteArchivo {

    public static void main(String[] args) throws IOException {

        String host = "localhost";
        int puerto = 5000;

        Socket socket = new Socket(host, puerto);
        System.out.println("Conectado al servidor");

        DataInputStream in =
                new DataInputStream(socket.getInputStream());
        FileOutputStream fileOut =
                new FileOutputStream("archivo_recibido.bin");

        // 1. Leer tamaño del archivo
        long tamano = in.readLong();

        // 2. Recibir archivo
        byte[] buffer = new byte[4096];
        long bytesRecibidos = 0;
        int bytesLeidos;

        while (bytesRecibidos < tamano) {
            bytesLeidos = in.read(buffer);
            fileOut.write(buffer, 0, bytesLeidos);
            bytesRecibidos += bytesLeidos;
        }

        fileOut.close();
        in.close();
        socket.close();

        System.out.println("Archivo recibido correctamente");
    }
}
