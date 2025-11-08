import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        ArrayList<ContadorLetra> hilos = new ArrayList();
        for (char letra = 'a'; letra <= 'z'; letra++) {
            ContadorLetra hilo =  new ContadorLetra(letra, "letras.txt");
            hilos.add(hilo);
            hilo.start();
        }

        System.out.println("-".repeat(200));
        System.out.println("Max Occurs: " +
                hilos.stream()
                        .max(Comparator.comparing(ContadorLetra::getContador))
                        .orElse(null)
        );
        System.out.printf("Min Occurs: " +
                hilos.stream()
                        .min(Comparator.comparing(ContadorLetra::getContador))
                        .orElse(null)
        );
    }
}