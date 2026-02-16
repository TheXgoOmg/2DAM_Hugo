public class Main {
    public static void main(String[] args) {
        Mostrador mostrador = new Mostrador();

        Thread productor = new Thread(new Productor(20, mostrador));
        Thread consumidor = new Thread(new Consumidor(20, mostrador));

        productor.start();
        consumidor.start();
    }
}
