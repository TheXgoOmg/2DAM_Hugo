public class Fregar implements Runnable {
    private int n;
    private PilaPlatos pilaPlatos;

    public Fregar(int n, PilaPlatos pilaPlatos) {
        this.n = n;
        this.pilaPlatos = pilaPlatos;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= n; i++) {
                Plato plato = new Plato(i);
                pilaPlatos.lavar(plato);

                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
