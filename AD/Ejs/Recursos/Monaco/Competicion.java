package org.dam;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

public class Competicion {

    /**
     * Recibe el nombre del archivo XML y carga todos los resultados
     * @param nomXML Nombre del archivo XML
     * @return ArrayList con todos los resultados de carrera
     */
    public ArrayList<Carrera> cargaResultadosXML(String nomXML) {
        ArrayList<Carrera> resultados = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(nomXML);

            // Obtener el elemento raíz
            Element root = document.getDocumentElement();

            
            // Completa aquí el código
            // Buscar la sección de carrera

        } catch (Exception e) {
            System.err.println("Error al cargar XML: " + e.getMessage());
            e.printStackTrace();
        }

        return resultados;
    }

    /**
     * Guarda los resultados cargados previamente en un archivo de texto CSV
     * @param nombreFichero Nombre del archivo CSV de salida
     * @param losResultados ArrayList con los resultados a guardar
     */
    public void saveAsCSV(String nombreFichero, ArrayList<Carrera> losResultados) {
        if (losResultados == null || losResultados.isEmpty()) {
            System.err.println("No hay resultados para guardar.");
            return;
        }
	
	// Desarrolla aquí el resto de la función que almacena el CSV 
	
        try ()) {
            // Escribir encabezado en español
           

            // Escribir datos de cada resultado
            

        } catch (IOException e) {
            System.err.println("Error al guardar CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Método auxiliar para mostrar los resultados por consola
     * @param resultados Lista de resultados a mostrar
     */
    public void mostrarResultados(ArrayList<Carrera> resultados) {
        if (resultados == null || resultados.isEmpty()) {
            System.out.println("No hay resultados para mostrar.");
            return;
        }

        System.out.println("\n=== RESULTADOS DETALLADOS ===");
        System.out.println("--------------------------------------------------------------");
        System.out.printf("%-3s | %-20s | %-15s | %-6s | %-12s | %-4s%n",
                "Pos", "Nombre", "Equipo", "Vueltas", "Tiempo", "Pts");
        System.out.println("--------------------------------------------------------------");

        for (Carrera resultado : resultados) {
            System.out.printf("%-3d | %-20s | %-15s | %-6d | %-12s | %-4d%n",
                    resultado.getPosicion(),
                    resultado.getNombre(),
                    resultado.getEquipo(),
                    resultado.getVueltas(),
                    resultado.getTiempo(),
                    resultado.getPuntos());
        }
        System.out.println("--------------------------------------------------------------");
    }




}
