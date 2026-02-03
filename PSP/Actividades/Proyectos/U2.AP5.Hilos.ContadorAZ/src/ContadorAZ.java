import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ContadorAZ {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java ContadorAZ <archivo>");
            System.exit(1);
        }
        File archivo = new File(args[0]);

        int numProc = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numProc);

        List<Future<Integer>> listaFutures = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            char letra = (char) ('a' + i);
            ContadorLetra tarea = new ContadorLetra(archivo, letra);

            Future<Integer> futuro = executor.submit(tarea);
            listaFutures.add(futuro);
        }

        executor.shutdown();

        // Mostrar resultados
        for (int i = 0; i < 26; i++) {
            char letra = (char) ('a' + i);
            try {
                Integer resultado = listaFutures.get(i).get();
                System.out.println(letra + ": " + resultado);
            } catch (ExecutionException | InterruptedException e) {
                System.err.println("Error: letra<"+letra+"> "+e.getMessage());
            }
        }
    }
}