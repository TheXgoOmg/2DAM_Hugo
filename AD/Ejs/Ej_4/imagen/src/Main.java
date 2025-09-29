import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: <filename>");
            System.exit(1);
        }
        try {
            File file = new File(args[0]);
            BufferedImage imagen = ImageIO.read(file);

            if (imagen != null) {
                int anchura = imagen.getWidth();
                int altura = imagen.getHeight();

                System.out.println("Anchura: " + anchura);
                System.out.println("Altura: " + altura);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}