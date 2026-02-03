package org.cipfpcheste.dam2;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println(Colores.Bright_Cyan +
                "=== CLIENTE MySQL - CONSOLA ===" + Colores.Reset);

        // Solicitar datos de conexión
        String server = Utilidades.leerLinea("$ Server: ");
        String port = Utilidades.leerLinea("$ Port: ");
        String username = Utilidades.leerLinea("$ Username: ");
        String password = Utilidades.leerLinea("$ Password: ");

        // Crear y configurar el gestor de conexión
        ConnectionManager connectionManager = new ConnectionManager(server, port, username, password);

        // Con el objeto connectionManager llamamos a connectDBMS para establecer la coonexión
        connectionManager.connectDBMS();

        // Mostramos la información
        connectionManager.showInfo();

        //Mostramos las bases de datos.
        connectionManager.showDatabases();

        //Ahora se conecta a las bases de datos.

        DatabaseManager dbManager = new DatabaseManager(server, port, username, username, "comunidades_db");

        // Establecemos la conexión
        dbManager.connectDatabase();
        // Mostramos las tablas:
        dbManager.showTables();

        // Insertamos una población
        dbManager.insertIntoTable("poblaciones");

        // Realizamos una consulta:

        dbManager.executeSelect("select * from poblaciones where parent_code = 46");


    }
}