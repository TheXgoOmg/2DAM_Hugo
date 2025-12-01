import java.io.*;
import java.security.*;
public class HashSHA256 implements Runnable {
    String ruta;

    public HashSHA256(String ruta) {
        this.ruta = ruta;
    }

    public String calcularHash(String ruta) throws IOException,
            NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        try (InputStream in = new FileInputStream(ruta)) {
            byte[] buffer = new byte[8192];
            int n;
            while ((n = in.read(buffer)) > 0) {
                md.update(buffer, 0, n);
            }
        }
        byte[] hashBytes = md.digest();
// Convertir a hexadecimal
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) sb.append(String.format("%02x", b));
        return sb.toString();
    }

    @Override
    public void run() {
        long inicio = System.nanoTime();
        try {
            calcularHash(ruta);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        long fin = System.nanoTime();
        System.out.println("Tiempo de ejecución del hilo: " + (fin - inicio)/1_000_000 + " ms");
    }
}