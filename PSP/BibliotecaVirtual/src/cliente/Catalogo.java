package cliente;

import java.util.ArrayList;

public class Catalogo {
    private ArrayList<Libro> catalogo = new ArrayList<>();

    public Catalogo() {
        catalogo.add(new Libro(1, "El Quijote", "Miguel de Cervantes", "Clásico", 3, "libros/quijote.txt"));
        catalogo.add(new Libro(2, "Cien Años de Soledad", "Gabriel García Márquez", "Realismo Mágico", 3, "libros/soledad.txt"));
        catalogo.add(new Libro(3, "1984", "George Orwell", "Distopía", 3, "libros/1984.txt"));
        catalogo.add(new Libro(4, "Clean Code", "Robert C. Martin", "Técnico", 2, "libros/cleancode.txt"));
        catalogo.add(new Libro(5, "El Hobbit", "J.R.R. Tolkien", "Fantasía", 3, "libros/hobbit.txt"));

        // Ejemplo de acceso
        System.out.println("Catálogo cargado con " + catalogo.size() + " libros.");
    }

    public ArrayList<Libro> getCatalogo() {
        return catalogo;
    }
}
