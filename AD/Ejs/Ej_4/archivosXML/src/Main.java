import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.printf("Número de argumentos invalido.%nArguments: <filename>");
            System.exit(1);
        }
        Competicion competicion = new Competicion();
        competicion.cargaResultadosXML(args[0]);
    }
}