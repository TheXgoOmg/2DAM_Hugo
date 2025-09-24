package org.example;

import java.io.File;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("ERROR: No se han recibido argumentos.");
            System.exit(1);
        }

        File directorio = new File(args[0]);

        if (!directorio.exists() || !directorio.isDirectory()) {
            System.out.println("ERROR: No existe el directorio.");
            System.exit(1);
        }
        String[] filenames = directorio.list();

        assert filenames != null;
        if (filenames.length == 0) {
            System.out.printf("No se han encontrado archivos en el directorio %s.%n",directorio.getName());
        }

        ListaColumnas(filenames, args[0]);
    }

    public static void ListaColumnas(String[] filenames, String path) {
        final int MAX_FILES_BY_COLUMN = 4;
        int columnas = (filenames.length / MAX_FILES_BY_COLUMN)+1;
        String[][] salida = new String[MAX_FILES_BY_COLUMN][columnas];

        for (int i=0;i<filenames.length;i++){
            salida[i % MAX_FILES_BY_COLUMN][i / MAX_FILES_BY_COLUMN] = filenames[i];
        }

        // LISTAS
        System.out.println("--- LISTA ---\n"+"-".repeat(50));
        for (String file:filenames){
            System.out.println(file);
        }

        System.out.println("-".repeat(50));

        // COLUMNAS
        System.out.println("--- COLUMNAS ---\n"+"-".repeat(50));
        for (int i=0;i<MAX_FILES_BY_COLUMN;i++){
            for (int j=0; j<columnas;j++)
                System.out.printf("%-25s - %n",salida[i][j]);
        }

        System.out.println("-".repeat(50));

        // TABLA
        System.out.println("--- TABLA ---\n"+"-".repeat(50));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        for (String file:filenames){
            File archivo = new File(path+"\\"+file);
            String permisos = "";
            if (archivo.isDirectory()){
                permisos += "D";
            } else {
                permisos += "F";
            }
            if (archivo.canRead()) {
                permisos += "R";
            }
            if (archivo.canWrite()) {
                permisos += "W";
            }
            if (archivo.isHidden()) {
                permisos += "H";
            }
            System.out.printf("%s - %-25s - %.2f MB - %-20s %n",permisos,archivo.getName(),(double) archivo.length() / (1024*1024),sdf.format(archivo.lastModified()));
        }
    }
}