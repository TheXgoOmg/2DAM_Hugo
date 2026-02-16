public class Consumidor implements Runnable {
    private int n;
    private Mostrador mostrador;

    public Consumidor(int n, Mostrador mostrador) {
        this.n = n;
        this.mostrador = mostrador;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= n; i++) {
                mostrador.retirar();
                Thread.sleep(450);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
