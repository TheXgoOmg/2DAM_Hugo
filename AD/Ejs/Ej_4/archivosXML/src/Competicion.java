import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class Competicion {
    public Competicion() {
    }

    public ArrayList<Carrera> cargaResultadosXML(String nomXML) {
        ArrayList<Carrera> carreras = new ArrayList<>();

        try {
            File file = new File(nomXML);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbFactory.newDocumentBuilder();
            Document doc = builder.parse(file);

            doc.getDocumentElement().normalize();

            NodeList listaResultados = doc.getElementsByTagName("resultado");

            for (int i = 0; i < listaResultados.getLength(); i++) {
                Node nodo = listaResultados.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;

                    int posicion = Integer.parseInt(element.getElementsByTagName("posicion").item(0).getTextContent());
                    String nombre = element.getElementsByTagName("nombre").item(0).getTextContent();
                    String equipo = element.getElementsByTagName("equipo").item(0).getTextContent();
                    int vueltas = Integer.parseInt(element.getElementsByTagName("vueltas").item(0).getTextContent());
                    String tiempo = element.getElementsByTagName("tiempo").item(0).getTextContent();
                    int puntos = Integer.parseInt(element.getElementsByTagName("puntos").item(0).getTextContent());

                    Carrera carrera = new Carrera(posicion, nombre, equipo, vueltas, tiempo, puntos);

                    carreras.add(carrera);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carreras;
    }

    public void saveAsCSV(String nombreFichero, ArrayList<Carrera> losResultados) {

    }

}
