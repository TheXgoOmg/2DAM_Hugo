import java.io.*;

public class Modulo2 {

    // Arrays with source data
    String[] modulos = {"Acceso a Datos", "Programación de servicios y procesos", "Desarrollo de interfaces", "Programación Multimedia y dispositivod móviles", "Sistemas de Gestión Empresarial", "Empresa e iniciativa emprendedora"};
    int[] horas = {6, 3, 6, 5, 5, 3};
    double[] notas = {8.45, 9.0, 8.0, 7.34, 8.2, 7.4};

    public void EscribeObjeto(String nombre) throws IOException {

        //destination file
        ObjectOutputStream f = new ObjectOutputStream(new FileOutputStream(nombre));

        Modulo m; // Single object

        // loop along the arrays
        for (int i = 0; i < this.modulos.length; i++) {
            m = new Modulo(this.modulos[i], this.horas[i], this.notas[i]);
            f.writeObject(m);
        }

        // close the file
        f.close();

    }

    public void LeeObjeto(String nombre) throws IOException, ClassNotFoundException {

        // input file
        ObjectInputStream f = new ObjectInputStream(new FileInputStream(nombre));

        Modulo m;
        // No se puede saber que hay objetos existentes en el archivo.
        try {
            while (true) { // forever

                m = (Modulo) f.readObject();

                // show the module
                System.out.println("Modulo: " + m.getModulo());
                System.out.println("Horas: " + m.getHoras());
                System.out.println("Nota: " + m.getNota());
                System.out.println();

            }
        } catch (EOFException ex) {
            f.close();
        }

    }
}