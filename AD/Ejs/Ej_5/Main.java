package org.dam;

import java.util.ArrayList;

public class Main {
       /**
     * Método main para demostrar el funcionamiento
     */
    public static void main(String[] args) {
        Competicion gestion = new Competicion();

        // 1. Cargar resultados desde XML completo
        ArrayList<Carrera> resultados = gestion.cargaResultadosXML("monaco2023.xml");

        // 2. Mostrar resultados por consola
        gestion.mostrarResultados(resultados);

        // 3. Guardar resultados en CSV
        gestion.saveAsCSV("resultados_carrera.csv", resultados);

        // Mostrar información del primer resultado en CSV
        if (!resultados.isEmpty()) {
            System.out.println("\n=== EJEMPLO DE FORMATO CSV ===");
            System.out.println("Encabezado: Posición;Nombre;Equipo;Vueltas;Tiempo;Puntos");
            System.out.println("Datos: " + resultados.get(0).toCSV());
        }
    }
}
