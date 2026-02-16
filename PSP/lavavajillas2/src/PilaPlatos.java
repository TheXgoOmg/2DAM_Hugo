import java.util.Stack;

public class PilaPlatos {
    private Stack<Plato> pila = new Stack<>();

    public PilaPlatos() {
    }

    synchronized public void Lavar(Plato plato) throws InterruptedException {
        if (pila.size() == 5) {
            wait();
        }
        pila.push(plato);
        System.out.printf("Plato lavado #%d, total en pila: %d%n", plato.getId(), pila.size());
        notify();
    }

    synchronized public void Secar() throws InterruptedException {
        if (pila.isEmpty()) {
            wait();
        }
        Plato plato = pila.pop();
        System.out.printf("Plato secado #%d, total en pila: %d%n", plato.getId(), pila.size());
        notify();
    }
}
