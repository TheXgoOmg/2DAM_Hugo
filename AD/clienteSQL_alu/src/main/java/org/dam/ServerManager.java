package org.dam;

public class ServerManager {
    public static void main(String[] args) {
        System.out.println(Colores.Bright_Cyan +
                "=== CLIENTE MySQL - CONSOLA ===" + Colores.Reset);

        // Solicitamos los datos de conexión
        String server = Utilidades.leerLinea("$ Server: ");
        String port = Utilidades.leerLinea("$ Port: ");
        String username = Utilidades.leerLinea("$ Username: ");
        String password = Utilidades.leerLinea("$ Password: ");

        // invoca a connectionManager para generar un objeto de conexión
        ConnectionManager connectionManager = new ConnectionManager();

        // Intentar conectar
        if (connectionManager.connectDBMS() != null) {
            // Iniciar la shell del servidor el de ConnectionManager
            connectionManager.startShell();
        } else {
            System.out.println(Colores.Red + "No se pudo establecer la conexión. Saliendo..." + Colores.Reset);
        }
    }
}