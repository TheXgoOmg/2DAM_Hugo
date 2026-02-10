package org.dam;

import java.io.*;
import java.net.*;

public class ServidorArchivo {

    public static void main(String[] args) throws IOException {

        int puerto = 5000;
        File archivo = new File("archivo.bin");

        ServerSocket serverSocket = new ServerSocket(puerto);
        System.out.println("Servidor esperando conexión...");

        Socket socket = serverSocket.accept();
        System.out.println("Cliente conectado");

        DataOutputStream out =
                new DataOutputStream(socket.getOutputStream());
        FileInputStream fileIn =
                new FileInputStream(archivo);

        // 1. Enviar tamaño del archivo
        out.writeLong(archivo.length());

        // 2. Enviar archivo en bloques
        byte[] buffer = new byte[4096];
        int bytesLeidos;

        while ((bytesLeidos = fileIn.read(buffer)) != -1) {
            out.write(buffer, 0, bytesLeidos);
        }

        fileIn.close();
        out.close();
        socket.close();
        serverSocket.close();

        System.out.println("Archivo enviado correctamente");
    }
}
