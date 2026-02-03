package org.dam.RecapitulacionHilos;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        for (char c = 'a';c <= 'z';c++) {
            Thread t = new Thread(new ContadorLetra(c,"./src/main/java/org/dam/Recapitulacion/file.txt"));
            t.start();
            t.join();
        }
    }
}
