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
            this.server = server;
            this.port = port;
            this.user = user;
            this.pass = pass;
            this.dbname = dbname;
            this.connection = null;
            this.scanner = new Scanner(System.in);
        }

        public Connection connectDatabase() {
            try {
                String url = "jdbc:mysql://" + server + ":" + port + "/" + dbname;
                connection = DriverManager.getConnection(url, user, pass);
                System.out.println(Colores.Green+ "✓ Conectado a la base de datos: " + dbname + Colores.Reset);
                return connection;
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
                Utilidades.mostrarResultados(rs);
            }

        } catch (SQLException e) {
            System.out.println(Colores.Red + "Error mostrando tablas: " + e.getMessage() + Colores.Reset);
        }
    }

    public void showDescTable(String tableName) {
        try {
            if (connection == null || connection.isClosed()) {
                System.out.println(Colores.Red + "No hay conexión activa" + Colores.Reset);
                return;
            }
            DatabaseMetaData metaData = connection.getMetaData();
            try (ResultSet rs = metaData.getColumns(null, null, tableName, null)) {
                System.out.printf("%-30s%-30s%-30s%-30s%n", "NAME", "TYPE", "NULLABLE", "AUTOINCREMENT");
                System.out.println("=".repeat(120));

                while (rs.next()) {
                    System.out.printf("%-30s%-30s%-30s%-30s%n", rs.getString("COLUMN_NAME"), rs.getString("TYPE_NAME")+"("+rs.getString("COLUMN_SIZE")+")", rs.getString("IS_NULLABLE"), rs.getString("IS_AUTOINCREMENT"));
                }
                mostrarClavesPrimarias(tableName);
            }

        } catch (SQLException e) {
            System.out.println(Colores.Red + "Error mostrando estructura: " + e.getMessage() + Colores.Reset);
        }
    }

    private void mostrarClavesPrimarias(String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();

        try (ResultSet rs = metaData.getPrimaryKeys(null, null, tableName)) {
            System.out.printf("%n--- PRIMARY KEYS ---");
            Utilidades.mostrarResultados(rs);
        }
    }

    public void insertIntoTable(String nombreTabla) {
        try {
            if (connection == null || connection.isClosed()) {
                System.out.println(Colores.Red + "No hay conexión activa" + Colores.Reset);
                return;
            }

            // Obtener metadatos de la tabla
            List<String> campos = new ArrayList<>();
            List<String> tipos = new ArrayList<>();
            List<Boolean> nulos = new ArrayList<>();
            List<Boolean> autoIncrement = new ArrayList<>();

            DatabaseMetaData metaData = connection.getMetaData();
            try (ResultSet rs = metaData.getColumns(null, null, nombreTabla, null)) {
                while (rs.next()) {
                    campos.add(rs.getString("COLUMN_NAME"));
                    tipos.add(rs.getString("TYPE_NAME").toUpperCase());
                    nulos.add(rs.getString("IS_NULLABLE").equals("YES"));
                    autoIncrement.add(rs.getString("IS_AUTOINCREMENT").equals("YES"));
                }
            }

            if (campos.isEmpty()) {
                System.out.println(Colores.Red + "Tabla no encontrada: " + nombreTabla + Colores.Reset);
                return;
            }

            System.out.println(Colores.Cyan + "\n=== INSERTAR EN " + nombreTabla.toUpperCase() + " ===" + Colores.Reset);

            // Construir la consulta INSERT
            List<String> camposInsertar = new ArrayList<>();
            List<Object> valores = new ArrayList<>();
            List<String> tiposInsertar = new ArrayList<>();

            for (int i = 0; i < campos.size(); i++) {
                String campo = campos.get(i);
                String tipo = tipos.get(i);
                boolean esNulo = nulos.get(i);
                boolean esAutoIncrement = autoIncrement.get(i);

                // Saltar campos auto-increment
                if (esAutoIncrement) {
                    System.out.println(Colores.Yellow + "Campo auto-increment '" + campo + "' omitido" + Colores.Reset);
                    continue;
                }

                String valorStr = Utilidades.leerLinea(campo + " (" + tipo + ")" + (esNulo ? " [NULL permitido]" : " [OBLIGATORIO]") + ": ");

                // Manejar valores NULL
                if (valorStr.isEmpty() || valorStr.equalsIgnoreCase("null")) {
                    if (!esNulo) {
                        System.out.println(Colores.Red + "Este campo es obligatorio. Inténtalo de nuevo." + Colores.Reset);
                        i--; // Repetir este campo
                        continue;
                    }
                    valores.add(null);
                } else {
                    // Convertir según el tipo
                    try {
                        Object valor = convertirValor(valorStr, tipo);
                        valores.add(valor);
                    } catch (IllegalArgumentException e) {
                        System.out.println(Colores.Red + "Error: " + e.getMessage() + ". Inténtalo de nuevo." + Colores.Reset);
                        i--; // Repetir este campo
                        continue;
                    }
                }

                camposInsertar.add(campo);
                tiposInsertar.add(tipo);
            }

            // Construir y ejecutar la consulta
            if (!camposInsertar.isEmpty()) {
                String placeholders = String.join(",", Collections.nCopies(camposInsertar.size(), "?"));
                String sql = "INSERT INTO " + nombreTabla + " (" + String.join(",", camposInsertar) + ") VALUES (" + placeholders + ")";

                try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    for (int i = 0; i < valores.size(); i++) {
                        Object valor = valores.get(i);
                        String tipo = tiposInsertar.get(i);

                        if (valor == null) {
                            pstmt.setNull(i + 1, Types.NULL);
                        } else if (tipo.contains("INT")) {
                            pstmt.setInt(i + 1, (Integer) valor);
                        } else if (tipo.contains("DECIMAL") || tipo.contains("FLOAT") || tipo.contains("DOUBLE")) {
                            pstmt.setDouble(i + 1, (Double) valor);
                        } else if (tipo.contains("DATE") || tipo.contains("TIME")) {
                            pstmt.setDate(i + 1, (Date) valor);
                        } else {
                            pstmt.setString(i + 1, valor.toString());
                        }
                    }

                    int filasAfectadas = pstmt.executeUpdate();
                    System.out.println(Colores.Green + "✓ Fila insertada correctamente. Filas afectadas: " + filasAfectadas + Colores.Reset);
                }
            }

        } catch (SQLException e) {
            System.out.println(Colores.Red + "Error insertando datos: " + e.getMessage() + Colores.Reset);
        }
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
        try {
            if (connection == null || connection.isClosed()) {
                System.out.println(Colores.Red + "No hay conexión activa" + Colores.Reset);
                return;
            }

            System.out.println("--- SELECT ---");
            try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
                Utilidades.mostrarResultados(rs);
            }

        } catch (SQLException e) {
            System.out.println(Colores.Red + "Error ejecutando consulta: " + e.getMessage() + Colores.Reset);
        }
    }

    public void deleteFromTable(String tableName) {
            try {
                if (connection == null || connection.isClosed()) {
                    System.out.println(Colores.Red + "No hay conexión activa" + Colores.Reset);
                    return;
                }
                System.out.println("--- DELETE ---");
                Scanner sc  = new Scanner(System.in);
                System.out.println("Introduce el id:");
                System.out.printf("> ");
                Integer id = sc.nextInt();
                DatabaseMetaData meta = connection.getMetaData();
                ResultSet rs = meta.getPrimaryKeys(null, null, tableName);
                Utilidades.mostrarResultados(rs);
                try (PreparedStatement pstmt = connection.prepareStatement("DELETE FROM " + tableName + " WHERE id");) {
                    int afectados = pstmt.executeUpdate();

                    if (afectados != 0) {
                        System.out.println("Borrado exitosamente");
                    } else  {
                        System.out.println("No se ha podido borrar correctamente");

                    }
                }

            } catch (SQLException e) {
                System.out.println(Colores.Red + "Error borrando datos en la tabla "+tableName.toUpperCase()+": " + e.getMessage());
            }

    }

    public void getCatalogs() {
        System.out.println("--- GET CATALOGS ---");
            try {
                if (connection == null || connection.isClosed()) {
                    System.out.println(Colores.Red + "No hay conexión activa" + Colores.Reset);
                    return;
                }

                DatabaseMetaData metadata = connection.getMetaData();
                try (ResultSet rs = metadata.getCatalogs()) {
                    Utilidades.mostrarResultados(rs);
                }
                System.out.println("CATALOGS MOSTRADOS =========================");
            } catch (SQLException e) {
                System.out.println(Colores.Red + "Error obteniendo datos: " + e.getMessage());
            }
    }
}
