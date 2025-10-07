import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce la ruta: ");
        String ruta = sc.nextLine();

        String[] cmd = {"cmd.exe", "/c", "dir "+ruta+"\\*.png /S /B"};
        String line;
        ProcessBuilder pb = new ProcessBuilder(cmd);

        try
        {
            Process p = pb.start();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            System.out.println("Process output:");
            while ((line = br.readLine()) != null)
            {
                String[] lines = line.split("\\\\");

                System.out.println(lines[lines.length-1]);
            }
        } catch (Exception e) {
            System.err.println("Exception:" + e.getMessage());
        }
    }
}