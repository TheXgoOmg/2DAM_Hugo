package org.dam.RecapitulacionHilos;

import java.io.FileReader;

public class ContadorLetra implements Runnable {
    private char letra;
    private int contador;
    private String file;

    public ContadorLetra(char letra, String file) {
        this.letra = letra;
        contador = 0;
        this.file = file;
    }

    @Override
    public void run() {
        int caracter;
        try (FileReader fr = new FileReader(file)) {
            while ((caracter = fr.read()) != -1) {
                if ((char)caracter == letra) {
                    contador++;
                }
            }
            System.out.println(letra +  ": " + contador);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
