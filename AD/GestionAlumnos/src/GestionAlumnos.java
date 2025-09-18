import java.util.Scanner;

public class GestionAlumnos {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Número de argumentos incorrecto.\n\nSintaxis: \n\t java GestionAlumnos [ 1 | 2 | 3 ] [ fichero_texto ] [ fichero_objetos ]");
            System.exit(0);
        }

        switch (args[0]) {
            case "1":

        }
    }

    public void Introducir() {
        Scanner sc = new Scanner(System.in);
        String nombre = sc.nextLine();
        String apellidos = sc.nextLine();
        String NIA = sc.nextLine();
        String clase = sc.nextLine();

        System.out.println("Introduce los datos del alumno");
        System.out.print("Nombre: ");


    }
}