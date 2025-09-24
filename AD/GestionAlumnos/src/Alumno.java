import java.io.Serializable;

public class Alumno implements Serializable {
    public  String nombre;
    public String apellido;
    public String NIA;
    public String clase;


    public Alumno(String nombre, String apellido, String NIA, String clase) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.NIA = NIA;
        this.clase = clase;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNIA() {
        return NIA;
    }

    public String getClase() {
        return clase;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNIA(String NIA) {
        this.NIA = NIA;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", NIA='" + NIA + '\'' +
                ", clase='" + clase + '\'' +
                '}';
    }
}