public class Raton implements Runnable {
    private String nombre;
    private int tiempoAlimentacion;

    public Raton(String nombre, int tiempoAlimentacion) {
        super();
        this.nombre = nombre;
        this.tiempoAlimentacion = tiempoAlimentacion;
    }

    @Override
    public void run() {
        comer();
    }

    public void comer() {
        try {
            System.out.printf("El raton %s ha comenzado a alimentarse%n", nombre);
            Thread.sleep(tiempoAlimentacion * 1000);
            System.out.printf("El raton %s ha terminado de alimentarse%n", nombre);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}