public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.printf("Número de argumentos invalido.%nArguments: <filename>");
            System.exit(1);
        }
        Competicion competicion = new Competicion();
        competicion.cargaResultadosXML(args[0]);
    }
}