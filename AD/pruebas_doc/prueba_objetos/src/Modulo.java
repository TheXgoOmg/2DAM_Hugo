import java.io.Serializable;

public class Modulo implements Serializable {

    String nombre;
    int horas;
    double nota;

    public Modulo(){
// Constructor vacío
    }

    public Modulo(String nombre, int horas, double nota){
        this.nombre=nombre;
        this.horas=horas;
        this.nota=nota;
    }

    public String getModulo() {return this.nombre;}
    public int getHoras() {return this.horas;}
    public double getNota() {return this.nota;}


    /*
     Write and Read modules to/from file
     */




}