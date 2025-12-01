package org.dam;

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

        DatabaseManager dbManager = new DatabaseManager(server, port, username, username, "SarahJMaasDB");

        // Establecemos la conexión
        dbManager.connectDatabase();
        // Mostramos las tablas:
        dbManager.showTables();

        dbManager.showDescTable("Books");

        dbManager.executeSelect("select * from Books b join Series s join Universe u where u.idUniverse = s.idUniverse AND b.idSeries = s.idSeries AND s.Title = 'Crescent City'");

//        dbManager.insertIntoTable("Books");

        dbManager.deleteFromTable("Books");
    }
}