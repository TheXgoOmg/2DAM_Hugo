public class Main {
    public static void main(String[] args) {
        PilaPlatos pila = new PilaPlatos();
        Thread fregar = new Thread(new Friega(20, pila));
        Thread secar = new Thread(new Seca(20, pila));

        fregar.start();
        secar.start();
    }
}