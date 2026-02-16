public class Productor implements Runnable {
    private int n;
    private Mostrador mostrador;

    public Productor(int n, Mostrador mostrador) {
        this.n = n;
        this.mostrador = mostrador;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= n; i++) {
                mostrador.depositar(new Pizza(i));
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
