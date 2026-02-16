public class Seca implements Runnable {
    private int n;
    private PilaPlatos pila;

    public Seca(int n, PilaPlatos pila) {
        this.n = n;
        this.pila = pila;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= n; i++) {
                pila.Secar();
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
