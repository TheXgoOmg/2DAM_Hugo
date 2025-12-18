public class Main {
    public static void main(String[] args) {
        PilaPlatos pp =  new PilaPlatos();
        Thread hiloFriega = new Thread(new Fregar(20, pp));
        Thread hiloSecar = new Thread(new Secar(20,pp));

        hiloFriega.start();
        hiloSecar.start();
    }
}