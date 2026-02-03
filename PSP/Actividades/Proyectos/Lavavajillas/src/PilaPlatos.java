import java.util.ArrayList;

public class PilaPlatos {
    private ArrayList<Plato> pila = new ArrayList<>();
    private int maxCap = 5;

    public PilaPlatos() {
    }

    public ArrayList<Plato> getPila() {
        return pila;
    }

    public void setPila(ArrayList<Plato> pila) {
        this.pila = pila;
    }

    public synchronized void lavar(Plato plato) {
        try {
            if (pila.size()>=5) {
                wait();
            }
            this.pila.add(plato);
            System.out.printf("Plato lavado #%d, total en pila: %d\n", plato.getId(), pila.size());
            notify();
        } catch (InterruptedException e) {

        }
    }

    public synchronized Plato secar() {
        try {
            if (pila.isEmpty()) {
                wait();
            }

            Plato plato = pila.removeFirst();
            System.out.printf("Plato secado #%d, total en pila: %d\n", plato.getId(), pila.size());
            notify();
            return plato;
        } catch (InterruptedException e) {
            return null;
        }
    }
}
