public class NEOProcess {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        String nombre = args[0];
        double posicionNEO = Double.parseDouble(args[1]);
        double velocidadNEO = Double.parseDouble(args[2]);

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

        long endTime = System.currentTimeMillis();

        long totalTime = endTime - startTime;

        if (resultado > 10) {
            System.err.printf("🚨 ALERTA MUNDIAL: %s (Prob: %.3f%%), %d%n", nombre, resultado, totalTime);
        } else {
            System.out.printf("✅ Tranquilos: %s (Prob: %.3f%%), %d%n", nombre, resultado, totalTime);
        }
    }
}