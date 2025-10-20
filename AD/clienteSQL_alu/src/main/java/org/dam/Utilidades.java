package org.dam;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Utilidades {

    private static Scanner sc = new Scanner(System.in);

    public static String leerLinea(String cadena) {
        System.out.print(cadena);
        return sc.nextLine().trim();
    }

    // Eficiente con Files.readAllLines

    public static void ejecutarScriptSQL(Connection conn, String file) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(file));
            StringBuilder sb = new StringBuilder();
            String breakLine = System.lineSeparator();

            for (String line : lines) {
                sb.append(line).append(breakLine);
            }

            String query = sb.toString();

            try (Statement stm = conn.createStatement()) {
                int result = stm.executeUpdate(query);
                System.out.println(Colores.Bright_Blue+"Script ejecutado con salida " + result+Colores.Reset);
            } catch (SQLException e) {
                System.out.println(Colores.Red+"Error ejecutando script SQL: " + e.getMessage()+Colores.Reset);
            }

        } catch (IOException e) {
            System.out.println(Colores.Red+"Error leyendo el archivo: " + e.getMessage()+Colores.Reset);
        }
    }

    // Menos eficiente

//    public static void ejecutarScriptSQL(Connection conn, String file) {
//        File script = new File(file);
//
//        BufferedReader bfr = null;
//        try {
//            bfr = bfr = new BufferedReader(new FileReader(script));
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        String line = null;
//        StringBuilder sb = new StringBuilder();
//
//        // Obtenemos el salto de línea del sistema subyacente
//        String breakLine = System.lineSeparator();
//
//        while (true) {
//            try {
//                if (!((line = bfr.readLine()) != null)) break;
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            sb.append(line);
//            sb.append(breakLine);
//        }
//
//        String query = sb.toString(); // generamos el Script en un String
//        Statement stm = null;
//        try {
//            stm = conn.createStatement();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        int result = 0;
//        try {
//            result = stm.executeUpdate(query);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("Script ejecutado con salida " + result);
//        try {
//            stm.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static void mostrarResultados(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Mostrar nombres de columnas
        System.out.println(Colores.Cyan);
        for (int i = 1; i <= columnCount; i++) {
            System.out.printf("%-20s", metaData.getColumnName(i));
        }
        System.out.println(Colores.Reset);

        // Línea separadora
        for (int i = 1; i <= columnCount; i++) {
            System.out.print("--------------------");
        }
        System.out.println();

        // Mostrar datos
        int rowCount = 0;
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                String value = rs.getString(i);
                System.out.printf("%-20s", value != null ? value : "NULL");
            }
            System.out.println();
            rowCount++;
        }

        System.out.println(Colores.Yellow + "\nFilas mostradas: " + rowCount + Colores.Reset);
    }
}
