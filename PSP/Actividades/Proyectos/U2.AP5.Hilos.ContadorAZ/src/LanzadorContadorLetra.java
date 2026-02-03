import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LanzadorContadorLetra {
    static final char letra = 'd';
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Uso: java LanzadorContadorLetra <archivo>");
            System.exit(1);
        }
        File archivo = new File(args[0]);
        try {
            ContadorLetra contador = new ContadorLetra(archivo, letra);
            contador.call();
            ExecutorService exec = Executors.newSingleThreadExecutor();
            Future<Integer> future = exec.submit(contador);
            int ocurrencias = future.get();
            System.out.println("Numero de ocurrencias para " + letra + " : " + ocurrencias);
        } catch (Exception e) {
            System.err.println("Error: letra<"+letra+"> " + e.getMessage());
        }
    }
}