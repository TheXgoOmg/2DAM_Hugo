public class Main {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("notepad.exe");
        try
        {
            Process p = pb.start();
            System.out.println("Notepad ejecutado.");
            Thread.sleep(10000);

            p.destroy();
            System.out.println("Notepad exterminado.");

        } catch (Exception e) {
            System.err.println("Exception:" + e.getMessage());
        }
    }
}