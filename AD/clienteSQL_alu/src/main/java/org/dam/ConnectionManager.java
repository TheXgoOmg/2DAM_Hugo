package org.dam;

import java.io.*;
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
    }

    public Connection connectDBMS() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                String connectionUrl = "jdbc:mysql://" + server + ":" + port;
                this.connection = DriverManager.getConnection(connectionUrl, user, pass);
            }
        } catch (SQLException e) {
            System.out.println(Colores.Red + "Error conectando al servidor: " + e.getMessage() + Colores.Reset);
            return null;
        }
        return this.connection;
    }

    public void showInfo() {
        try {

            DatabaseMetaData dbmd = connection.getMetaData();

            System.out.println(Colores.Blue+"\nDBMS information--------"+Colores.Reset);
            System.out.println(Colores.Bright_White+"SGBD:\t"+Colores.Reset + dbmd.getDatabaseProductName());
            System.out.println(Colores.Bright_White+"JDBC:\t"+Colores.Reset + dbmd.getDriverName());
            System.out.println(Colores.Bright_White+"URL:\t"+Colores.Reset + dbmd.getURL());
            System.out.println(Colores.Bright_White+"User:\t"+Colores.Reset + dbmd.getUserName());

        } catch (SQLException e) {
            System.err.println("Error mostrando la información de la BD: " + e.getMessage() + Colores.Reset);
        }
    }

    public void showDatabases() {
        try {
            ResultSet rs = connection.getMetaData().getCatalogs();
            while (rs.next()) {
                System.out.println(Colores.Blue+"Base de datos: "+rs.getString("TABLE_CAT")+Colores.Reset);
            }

        } catch (SQLException e) {
            System.out.println(Colores.Red + "Error mostrando bases de datos: " + e.getMessage() + Colores.Reset);
        }
    }

    public void importScript(String scriptPath) {
//        StringBuilder scriptText = new StringBuilder();
//        try (BufferedReader br = new BufferedReader(new FileReader(scriptPath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                scriptText.append(line);
//            }
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        }
        Utilidades.ejecutarScriptSQL(this.connection, scriptPath);
    }

    public void showHelp() {
        System.out.println(Colores.Blue + "Estos son los comandos disponibles:" +  Colores.Reset);
        System.out.println("> show databases");
        System.out.println("> info");
        System.out.println("> import <nombre_del_script>");
        System.out.println("> use <nombre_de_la_bd>");
        System.out.println("> quit");
    }

    public void startShell() {
        System.out.println(Colores.Green +
                "Cliente MySQL iniciado. Escribe 'help' para ver los comandos disponibles." + Colores.Reset);

        while (true) {
            String prompt = Colores.Green + "(" + user + ") on " + server + ":" + port + "> " + Colores.Reset;
            String input = Utilidades.leerLinea(prompt).trim();

            switch (input) {
                case "show databases":
                case "show db":
                case "show database":
                    showDatabases();
                    break;

                case "info":
                    showInfo();
                    break;

                case "quit":
                case "exit":
                    System.out.println(Colores.Yellow + "Saliendo..." + Colores.Reset);
                    try {
                        if (connection != null && !connection.isClosed()) {
                            connection.close();
                        }
                    } catch (SQLException e) {
                        // Ignorar errores al cerrar
                    }
                    return;

                case "help":
                    showHelp();
                    break;

                default:
                    if (input.startsWith("import ")) {
                        String[] parts = input.split(" ", 2);
                        if (parts.length == 2) {
                            importScript(parts[1]);
                        } else {
                            System.out.println(Colores.Red + "Uso: import <ruta_del_script>" + Colores.Reset);
                        }
                    } else if (input.startsWith("use ")) {
                        String[] parts = input.split(" ", 2);
                        if (parts.length == 2) {
                            String databaseName = parts[1];
                            // Cambiar al modo base de datos
                            DatabaseManager dbManager = new DatabaseManager(server, port, user, pass, databaseName);
                            Connection dbConnection = dbManager.connectDatabase();
                            if (dbConnection != null) {
                                dbManager.startShell();
                            }
                        } else {
                            System.out.println(Colores.Red + "Uso: use <nombre_base_datos>" + Colores.Reset);
                        }
                    } else if (!input.isEmpty()) {
                        System.out.println(Colores.Red + "Comando no reconocido: " + input + Colores.Reset);
                    }
                    break;
            }
        }
    }

}
