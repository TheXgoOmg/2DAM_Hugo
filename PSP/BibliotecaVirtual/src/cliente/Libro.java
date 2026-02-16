package cliente;

public class Libro {
    int id;
    String titulo;
    String autor;
    String genero;
    int copias_disponibles;
    int copias_totales;
    String ruta_archivo;

    public Libro(int id, String titulo, String autor, String genero, int copias_totales, String ruta_archivo) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.copias_disponibles = copias_totales;
        this.copias_totales = copias_totales;
        this.ruta_archivo = ruta_archivo;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", genero='" + genero + '\'' +
                ", copias_disponibles=" + copias_disponibles +
                ", copias_totales=" + copias_totales +
                ", ruta_archivo='" + ruta_archivo + '\'' +
                "}";
    }
}
