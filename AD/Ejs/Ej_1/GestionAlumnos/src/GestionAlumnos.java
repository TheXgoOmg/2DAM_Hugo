import java.io.*;
import java.util.Scanner;


public class GestionAlumnos {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Número de argumentos incorrecto.\n\nSintaxis: \n\t java GestionAlumnos [ 1 | 2 | 3 ] [ fichero_texto ] [ fichero_objetos ]");
            System.exit(0);
        }

        File archivos = new File(args[1]);
        File objetos = new File(args[2]);

        switch (args[0]) {
            case "1":
                Introducir(archivos);
                break;
            case "2":
                Conversion(archivos, objetos);
                break;
            case "3":
                Listar(objetos);
                break;
        }
    }

    public static void Introducir(File archivos) {
        Scanner sc = new Scanner(System.in);
        String nombre, apellidos, NIA, clase;
        String datos = "";

        System.out.println("Introduce los datos del alumno");
        System.out.print("Nombre: ");
        nombre = sc.nextLine();
        System.out.print("Apellidos: ");
        apellidos = sc.nextLine();
        System.out.print("NIA: ");
        NIA = sc.nextLine();
        System.out.print("Clase: ");
        clase = sc.nextLine();

        datos += nombre + ";" + apellidos + ";" + NIA + ";" + clase + "\n";

        try (PrintWriter pw = new PrintWriter(new FileWriter(archivos, true))) {
            if (!archivos.exists()) {
                System.out.printf("El fichero %s no existe", archivos.getName());
            } else {
                pw.write(datos);
                System.out.print("Los datos han sido escritos correctamente");
            }
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo.");
        }

    }

    public static void Conversion(File archivos, File objetos) {

        try (BufferedReader br = new BufferedReader(new FileReader(archivos))) {
            String[] registros = br.readLine().split("\n");

            if (registros.length == 0) {
                System.out.println("No hay objetos que convertir.");
                System.exit(0);
            } else {
                for (String registro:registros) {
                    String[] datos = registro.split(";");

                    Alumno alumno = new Alumno(datos[0], datos[1], datos[2], datos[3]);

                    if (!objetos.exists()) {
                        System.out.printf("El fichero %s no existe", objetos.getName());
                    } else {
                        if (objetos.length() == 0) {
                            System.out.printf("El archivo %s está vacío. ",objetos.getName());
                            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(objetos))) {
                                oos.writeObject(alumno);
                                System.out.println("Se ha añadido la cabecera correctamente.");
                            }
                        } else {
                            try (MiOOS oos = new MiOOS(new FileOutputStream(objetos, true))) {
                                oos.writeObject(alumno);
                            }
                        }
                        System.out.print("Los datos han sido escritos correctamente");
                    }
                }
            }


        } catch (IOException ex) {
            System.out.println("Eror al leer el archivo.");
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivos))) {
        pw.write("");
    } catch (IOException ex) {
            System.out.printf("Error al escribir en el archivo %s\n.", archivos.getName());
        }
    }

    public static void Listar(File objetos) {
        int contador = 0;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(objetos))) {
            do {
                Alumno alumno = (Alumno) ois.readObject();
                System.out.println(alumno);
                contador++;
            } while (ois.available() != 0);
            System.out.printf("\nSe han leído %s objetos\n", contador);
        } catch (IOException ex) {
            System.out.printf("Error al leer el archivo %s.\n", objetos.getName());
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Clase no encontrada.");
        }
    }
}