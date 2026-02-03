import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class ContadorLetra implements Callable<Integer> {

    private File archivo;
    private char letra;
    private int cuenta;

    public ContadorLetra(File archivo, char letra) {
        this.archivo = archivo;
        this.letra = letra;
        this.cuenta = 0;
    }

    @Override
    public Integer call() throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            int c;
            while ((c = br.read()) != -1) {
                if ((char) c == letra) {
                    cuenta++;
                }
            }
        }
        return cuenta;
    }
}
