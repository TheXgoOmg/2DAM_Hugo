import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String script = "C:\\Users\\usuario\\Documents\\2DAM_Hugo\\PSP\\Proyectos\\Ej_Recapitulacion\\src\\random_num.py";
        String comando = "\"C:\\Users\\usuario\\Documents\\2DAM_Hugo\\PSP\\Proyectos\\Ej_Recapitulacion\\src\\letras.txt\"";
        String cantidad = "26";

        ProcessBuilder pb = new ProcessBuilder("py", script,comando,cantidad);
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        try {
            Process p = pb.start();
            p.waitFor();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Map<String,Integer> mapa = new HashMap<>();

        for (char letra = 'a'; letra <= 'z'; letra++) {
            int c = 0;
            try {
                c = contar(letra);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            mapa.put(String.valueOf(letra), c);
        }

        for (String letra : mapa.keySet()) {
            System.out.printf("%s, %d%n", letra, mapa.get(letra));
        }
    }

    public static int contar(char letra) throws IOException {
        String comando = "Get-Content \"C:\\Users\\usuario\\Documents\\2DAM_Hugo\\PSP\\Proyectos\\Ej_Recapitulacion\\src\\letras.txt\" | Select-String -Pattern \"" + letra + "\" -AllMatches | % Matches | Measure-Object | % Count";
        String[] comandos = {"powershell.exe","/command",comando};
        ProcessBuilder pb = new ProcessBuilder(comandos);
        pb.redirectOutput();

        Process p = pb.start();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String line;
            if ((line = br.readLine()) != null) {
                line = line.trim();
            }
            return Integer.parseInt(line);
        }
    }
}