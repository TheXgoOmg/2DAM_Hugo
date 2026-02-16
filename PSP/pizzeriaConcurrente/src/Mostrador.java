import java.util.ArrayList;

public class Mostrador {
    private ArrayList<Pizza> pila = new ArrayList<>();
    private final Object lock = new Object();

    public void depositar(Pizza p) throws InterruptedException {
        synchronized (lock) {
            if (pila.size() == 5) {
                lock.wait();
            }
            pila.add(p);
            lock.notifyAll();
            System.out.println("Pizza depositada: " + p.getId() + ", pizzas en el mostrador: " + pila.size());
        }
    }

    public void retirar() throws InterruptedException {
        Pizza p;
        synchronized (lock) {
            if (pila.isEmpty()) {
                lock.wait();
            }
            p = pila.removeFirst();
            lock.notifyAll();
            System.out.println("Pizza retirada: " + p.getId() + ", pizzas en el mostrador: " + pila.size());
        }
    }
}
