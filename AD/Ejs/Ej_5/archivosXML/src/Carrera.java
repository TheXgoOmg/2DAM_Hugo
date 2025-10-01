import org.w3c.dom.Element;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.w3c.dom.Element;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Carrera {
    private int posicion;
    private String nombre;
    private String equipo;
    private int vueltas;
    private String tiempo; 
    private int puntos;

    /**
     * Constructor que recibe un elemento XML
     * @param result Elemento XML con los datos del resultado
     */
    public Carrera(Element result) {
        try {
            this.posicion = Integer.parseInt(getElementText(result, "posicion"));
            this.nombre = getElementText(result, "nombre");
            this.equipo = getElementText(result, "equipo");
            this.vueltas = Integer.parseInt(getElementText(result, "vueltas"));
            this.tiempo = getElementText(result, "tiempo");
            this.puntos = Integer.parseInt(getElementText(result, "puntos"));

        } catch (Exception e) {
            System.err.println("Error al crear ResultadoCarrera desde XML: " + e.getMessage());
        }
    }

    private String getElementText(Element parent, String tagName) {
        var nodes = parent.getElementsByTagName(tagName);
        if (nodes.getLength() > 0) {
            return nodes.item(0).getTextContent();
        }
        return "";
    }

    /**
     * Devuelve todos los campos separados por ;
     * @return String en formato CSV
     */
    public String toCSV() {
        return String.format("%d;%s;%s;%d;%s;%d",
                posicion, nombre, equipo, vueltas, tiempo, puntos);
    }

    // Getters
    public int getPosicion() { return posicion; }
    public String getNombre() { return nombre; }
    public String getEquipo() { return equipo; }
    public int getVueltas() { return vueltas; }
    public String getTiempo() { return tiempo; }
    public int getPuntos() { return puntos; }

    @Override
    public String toString() {
        return String.format("Posición: %d, Piloto: %s, Equipo: %s, Vueltas: %d, Tiempo: %s, Puntos: %d",
                posicion, nombre, equipo, vueltas, tiempo, puntos);
    }
}