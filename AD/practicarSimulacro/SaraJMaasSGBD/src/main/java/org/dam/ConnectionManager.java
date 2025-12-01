package org.dam;

import java.sql.*;

public class ConnectionManager {
        private String server;
        private String port;
        private String user;
        private String pass;
        private Connection connection;

        public ConnectionManager() {
            this.server = "";
            this.port = "";
            this.user = "";
            this.pass = "";
            this.connection = null;
        }

        public ConnectionManager(String server, String port, String user, String pass) {
            this.server = server;
            this.port = port;
            this.user = user;
            this.pass = pass;
            this.connection = null;
        }

        public Connection connectDBMS() {
            try {
                String url = "jdbc:mysql://" + server + ":" + port + "/";
                connection = DriverManager.getConnection(url, user, pass);
                System.out.println(Colores.Green + "Conexión establecida con el servidor" + Colores.Reset);
                return connection;
            } catch (SQLException e) {
                System.out.println(Colores.Red + "Error conectando al servidor: " + e.getMessage() + Colores.Reset);
                return null;
            }
        }

    public void showInfo() {
        System.out.println(Colores.Cyan + "\n=== INFORMACIÓN DEL SERVIDOR ===" + Colores.Reset);
        System.out.println("Servidor: " + server);
        System.out.println("Puerto: " + port);
        System.out.println("Usuario: " + user);

        try {
            if (connection != null && !connection.isClosed()) {
                try (Statement stmt = connection.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT VERSION()")) {
                    if (rs.next()) {
                        System.out.println("Versión MySQL: " + rs.getString(1));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("No se pudo obtener la versión del servidor");
        }
        System.out.println();
    }
    public void showDatabases() {
        try {
            if (connection == null || connection.isClosed()) {
                System.out.println(Colores.Red + "No hay conexión activa" + Colores.Reset);
                return;
            }
            try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SHOW DATABASES")) {
                Utilidades.mostrarResultados(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error conectando al servidor: " + e.getMessage() + Colores.Reset);
        }

    }
}
