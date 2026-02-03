package org.dam.NeoAnalizer;

public class calculoProb {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Argumentos: <nombre> <velocidadNEO> <posicionNEO>");
        }

        String nombre = args[0];
        double velocidadNEO = Double.parseDouble(args[1]);
        double posicionNEO = Double.parseDouble(args[2]);

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

        System.out.printf("%s: %s\n", nombre, (resultado > 10) ? "Alerta mundial!!!!!!" : "Estamos a salvo...");
    }
}
