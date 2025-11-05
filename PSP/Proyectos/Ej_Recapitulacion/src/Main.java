public class Main {
    public static void main(String[] args) {
        for (char letra = 'a'; letra <= 'z'; letra++) {
            Thread hilo =  new ContadorLetra(letra, "letras.txt");
            hilo.start();
        }
    }
}