import java.io.*;
import java.security.*;
public class HashSHA256 {
    public static void main(String[] args) throws IOException,
            NoSuchAlgorithmException {
        long inicio = System.nanoTime();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        try (InputStream in = new FileInputStream(args[0])) {
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
        long fin = System.nanoTime();
        System.out.println("Tiempo total del PROCESO: " + (fin - inicio)/1_000_000 + " ms");

        System.out.println(sb);
    }
}