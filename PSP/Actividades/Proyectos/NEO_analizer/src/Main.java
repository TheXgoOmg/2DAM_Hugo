import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        long inicio = System.currentTimeMillis();

        int n_cores = Runtime.getRuntime().availableProcessors();

        ArrayList<String> NEOs = new ArrayList<>();
        NEOs = leerCSV("E:\\2DAM_Hugo\\PSP\\Proyectos\\NEO_analizer\\NEOs.csv");

        if (NEOs.size() > n_cores) {
            System.out.printf("⚠ Advertencia: número de NEOs (%s) diferente al número de cores (%d)%n", NEOs.size(), n_cores);
        }

        ArrayList<Process> procesos = new ArrayList<>();

        for (int i = 0; i < n_cores; i++) {
            String[] NEO = NEOs.get(i).split(",");
            String nombre = NEO[0].trim();
            String posicion = NEO[1].trim();
            String velocidad = NEO[2].trim();

            ProcessBuilder pb = new ProcessBuilder("cmd.exe","/c","java","E:\\2DAM_Hugo\\PSP\\Proyectos\\NEO_analizer\\src\\NEOProcess.java", nombre, posicion, velocidad);

            try {
                pb.redirectErrorStream(true);
                procesos.add(pb.start());
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        List<Long> listaTiempos = new ArrayList<>();
        for (Process p : procesos) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] tokens = linea.split(",");
                    long tiempo = Integer.parseInt(tokens[tokens.length-1].strip());
                    listaTiempos.add(tiempo);
                    System.out.println(linea);
                }
                p.waitFor();
            } catch (IOException | InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
        long fin = System.currentTimeMillis();
        System.out.println("-".repeat(50));
        System.out.printf("Tiempo total de ejecución del programa: %d ms (%.2f s)%n", fin - inicio, (double) (fin - inicio)/1000);
        double suma = listaTiempos.stream()
                        .mapToInt(Long::intValue)
                                .sum();
        System.out.printf("Tiempo medio de ejecución de cada proceso: %d ms (%.2f s)%n", (int) suma/listaTiempos.size(), (double) (suma/listaTiempos.size())/1000);
    }

    public static ArrayList<String> leerCSV(String filePath) {
        ArrayList<String> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
            String line;
            while ((line = br.readLine()) != null) {
                lista.add(line);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return lista;
    }
}