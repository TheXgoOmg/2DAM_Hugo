package org.dam;


import lombok.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Data
public class DatabaseManager {
    // Getters y Setters

    private String server;
    private String port;
    private String user;
    private String pass;
    private String dbname;
    private Connection connection;
    private Scanner scanner;

    public DatabaseManager() {
        this.server = "";
        this.port = "";
        this.user = "";
        this.pass = "";
        this.dbname = "";
        this.connection = null;
        this.scanner = new Scanner(System.in);
    }

    public DatabaseManager(String server, String port, String user, String pass, String dbname) {

    }

    public Connection connectDatabase() {
        try {

        } catch (SQLException e) {
            System.out.println(Colores.Red + "✗ Error conectando a la base de datos: " + e.getMessage() + Colores.Reset);
            return null;
        }
    }

    public void showTables() {
        try {
            if (connection == null || connection.isClosed()) {
                System.out.println(Colores.Red + "No hay conexión activa" + Colores.Reset);
                return;
            }

            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery("SHOW TABLES")) {

                System.out.println(Colores.Cyan + "\n=== TABLAS EN " + dbname.toUpperCase() + " ===" + Colores.Reset);
                int count = 0;
                while (rs.next()) {
                    System.out.println("- " + rs.getString(1));
                    count++;
                }
                System.out.println(Colores.Yellow + "Total: " + count + " tablas" + Colores.Reset);
            }
        } catch (SQLException e) {
            System.out.println(Colores.Red + "Error mostrando tablas: " + e.getMessage() + Colores.Reset);
        }
    }

    public void showDescTable(String tableName) {

    }

    public void insertIntoTable(String tableName) {

    }

    private Object convertirValor(String valorStr, String tipo) {
        if (valorStr == null || valorStr.isEmpty()) {
            return null;
        }

        try {
            if (tipo.contains("INT")) {
                return Integer.parseInt(valorStr);
            } else if (tipo.contains("DECIMAL") || tipo.contains("FLOAT") || tipo.contains("DOUBLE")) {
                return Double.parseDouble(valorStr);
            } else if (tipo.contains("DATE") || tipo.contains("TIME")) {
                // Formato esperado: YYYY-MM-DD
                return java.sql.Date.valueOf(valorStr);
            } else {
                return valorStr;
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Formato inválido para tipo " + tipo);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Formato de fecha inválido. Use YYYY-MM-DD");
        }
    }

    public void executeSelect(String query) {

    }

    public void startShell() {
        System.out.println(Colores.Green +
                "Modo base de datos activado. Escribe 'help' para ver los comandos disponibles." + Colores.Reset);

        while (true) {
            String prompt = Colores.Bright_Blue + "(" + user + ") on " + server + ":" + port + "[" + dbname + "]> " + Colores.Reset;
            String input = Utilidades.leerLinea(prompt).trim();

            switch (input) {
                case "show tables":
                case "sh tables":
                    showTables();
                    break;

                case "help":
                    mostrarAyudaDatabase();
                    break;

                case "quit":
                case "exit":
                    System.out.println(Colores.Yellow + "Volviendo al modo servidor..." + Colores.Reset);
                    try {
                        if (connection != null && !connection.isClosed()) {
                            connection.close();
                        }
                    } catch (SQLException e) {
                        // Ignorar errores al cerrar
                    }
                    return;

                default:
                    if (input.startsWith("describe ")) {
                        String[] parts = input.split(" ", 2);
                        if (parts.length == 2) {
                            showDescTable(parts[1]);
                        } else {
                            System.out.println(Colores.Red + "Uso: describe <nombre_tabla>" + Colores.Reset);
                        }
                    } else if (input.startsWith("insert ")) {
                        String[] parts = input.split(" ", 2);
                        if (parts.length == 2) {
                            insertIntoTable(parts[1]);
                        } else {
                            System.out.println(Colores.Red + "Uso: insert <nombre_tabla>" + Colores.Reset);
                        }
                    } else if (input.startsWith("select ") || input.startsWith("update ") ||
                            input.startsWith("delete ") || input.startsWith("create ") ||
                            input.startsWith("alter ") || input.startsWith("drop ")) {
                        executeSelect(input);
                    } else if (!input.isEmpty()) {
                        System.out.println(Colores.Red + "Comando no reconocido: " + input + Colores.Reset);
                    }
                    break;
            }
        }
    }

    private void mostrarAyudaDatabase() {
        System.out.println(Colores.Cyan + "\n=== COMANDOS DISPONIBLES ===" + Colores.Reset);
        System.out.println("show tables/sh tables - Mostrar todas las tablas");
        System.out.println("describe <table>     - Mostrar estructura de la tabla");
        System.out.println("insert <table>       - Insertar datos en la tabla");
        System.out.println("SELECT/UPDATE/DELETE - Ejecutar consulta SQL");
        System.out.println("quit/exit            - Volver al modo servidor");
        System.out.println();
    }


}
