import java.io.FileReader;
import java.io.IOException;

public class ContadorLetra extends Thread {
    int contador;
    String archivo;
    char letra;

    public ContadorLetra(char letra, String archivo) {
        this.letra = letra;
        this.contador = 0;
        this.archivo = archivo;
    }

    public int getContador() {
        return contador;
    }

    public char getLetra() {
        return letra;
    }

    public String getArchivo() {
        return archivo;
    }

    @Override
    public void run() {
        int caracter;
        try (FileReader fr = new FileReader(archivo)) {
            while ((caracter = fr.read()) != -1) {
                if ((char) caracter == letra) {
                    contador++;
                }
            }
            System.out.printf("Número de ocurrencias de la letra %s: %d\n", letra, contador);
        } catch (IOException e) {
            System.out.println("Error al abrir archivo " + archivo);
            System.out.printf(e.getStackTrace().toString());
        }
    }
}
