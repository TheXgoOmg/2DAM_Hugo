import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // test the args
        if (args.length != 2) {
            System.out.println("Número de argumentos incorrecto.\n\nSintaxis: \n\t java Moduls2 [ read | write ] archivo.obj");
            System.exit(0);
        }

        Modulo2 modulos = new Modulo2();

        // depending the args
        if (args[0].equals("read")) {
            modulos.LeeObjeto(args[1]);
        } else if (args[0].equals("write")) {
            modulos.EscribeObjeto(args[1]);
        } else {
            System.out.println("No entiendo el mandato " + args[0] + "\n");
        }

    }
}