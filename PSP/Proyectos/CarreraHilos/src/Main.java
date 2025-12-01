import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Hilo> hilos =  new ArrayList<Hilo>();
        hilos.add(new Hilo("A"));
        hilos.add(new Hilo("B"));
        hilos.add(new Hilo("C"));

        for (Hilo hilo : hilos) {
            new Thread(hilo).start();
        }

        boolean vivos = true;

        while (vivos) {
            for (Hilo hilo : hilos) {
                if (hilo.getContador() == 1000) {
                    vivos = false;
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
            for (Hilo hilo : hilos) {
                System.out.printf("Hilo %s: %d\t",hilo.getNombre(),hilo.getContador());
            }
            System.out.println();
        }
    }
}