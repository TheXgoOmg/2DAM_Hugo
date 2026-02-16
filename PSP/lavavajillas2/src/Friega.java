public class Friega implements Runnable {
    private int n;
    private PilaPlatos pila;

    public Friega(int n, PilaPlatos pila) {
        this.n = n;
        this.pila = pila;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= n ; i++) {
                Plato plato = new Plato(i);
                pila.Lavar(plato);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
