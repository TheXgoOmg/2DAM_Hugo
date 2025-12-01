public class Hilo extends Thread {
    String nombre;
    int contador;

    public Hilo(String nombre) {
        this.nombre = nombre;
        this.contador = 0;
    }

    @Override
    public void run() {
        while (contador < 1000) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            contador++;
        }
    }

    public int getContador() {
        return contador;
    }

    public String getNombre() {
        return nombre;
    }
}
