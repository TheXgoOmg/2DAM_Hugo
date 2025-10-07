import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("mspaint.exe");
        try {
            Process p = pb.start();

            do {
                System.out.printf("Process state: active%n");
                Thread.sleep(3000);
            } while (p.isAlive());


            System.out.printf("Process state: done%n.");
        } catch (IOException e) {
            System.err.println("Exception: " + e.getMessage());
            System.exit(-1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}