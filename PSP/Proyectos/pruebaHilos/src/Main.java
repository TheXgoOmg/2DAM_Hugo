import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Thread> threadsRatones = new ArrayList<>();
        threadsRatones.add(new Thread(new Raton("Fievel", 4)));
        threadsRatones.add(new Thread(new Raton("Jerry", 5)));
        threadsRatones.add(new Thread(new Raton("Pinky", 3)));
        threadsRatones.add(new Thread(new Raton("Mickey", 6)));

        threadsRatones.forEach(thread -> {
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException _) {

            }
        });
        System.exit(0);
    }
}