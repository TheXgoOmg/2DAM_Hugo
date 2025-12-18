public class Secar implements Runnable {
    private int n;
    private PilaPlatos pilaPlatos;

    public Secar(int n, PilaPlatos pilaPlatos) {
        this.n = n;
        this.pilaPlatos = pilaPlatos;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= n; i++) {
                Plato plato = pilaPlatos.secar();

                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
