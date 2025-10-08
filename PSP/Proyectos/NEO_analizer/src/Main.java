public class Main {
    public static void main(String[] args) {
        int n_cores = Runtime.getRuntime().availableProcessors();


    }
    
    public static double calculoProb(double posicionNEO, double velocidadNEO){
        double posicionTierra = 1;
        double velocidadTierra = 100;
        double correccion = 0;

        for (int i = 0; i < (10 * 365 * 24 * 60 * 60); i++) {
            posicionNEO = posicionNEO + velocidadNEO * i;
            posicionTierra = posicionTierra + velocidadTierra * i;
            correccion += Math.sin(posicionNEO) * Math.cos(posicionTierra);
        }
        double resultado = 100 * Math.random() *
                Math.pow(((posicionNEO - posicionTierra) /
                        (posicionNEO + posicionTierra)), 2)
                + correccion % 1;
    }
}