public class Main {
    public static void main(String[] args) throws InterruptedException {
        long inicio = System.nanoTime();
        for (String archivo : args) {
            Thread hash = new Thread(new HashSHA256(archivo));
            hash.start();
            hash.join();
        }
        long fin = System.nanoTime();
        System.out.println("Tiempo total del PROGRAMA: " + (fin - inicio)/1_000_000 + " ms");

    }
}
