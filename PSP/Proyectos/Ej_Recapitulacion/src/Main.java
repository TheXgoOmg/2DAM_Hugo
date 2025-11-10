import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        ArrayList<ContadorLetra> hilos = new ArrayList();
        for (char letra = 'a'; letra <= 'z'; letra++) {
            ContadorLetra hilo =  new ContadorLetra(letra, "letras.txt");
            hilos.add(hilo);
            hilo.start();
            try {
                hilo.join();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        System.out.println("-".repeat(200));
        int max=0;
        int min=0;
        boolean first=true;

        for (ContadorLetra contadorLetra : hilos){
            if (first) {
                max=contadorLetra.getContador();
                min=contadorLetra.getContador();
                first=false;
            }
            else {
                if (contadorLetra.getContador()>max){
                    max=contadorLetra.getContador();
                }
                if (contadorLetra.getContador()<min){
                    min=contadorLetra.getContador();
                }
            }
        }

        System.out.println("Max Occurs: "+max);
        System.out.println("Min Occurs: "+min);
    }
}