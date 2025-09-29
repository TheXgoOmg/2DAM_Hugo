public class Carrera {
    private int posicion;
    private String nombre;
    private String equipo;
    private int vueltas;
    private String tiempo;
    private int puntos;

    public Carrera(int posicion, String nombre, String equipo, int vueltas, String tiempo, int puntos) {
        this.posicion = posicion;
        this.nombre = nombre;
        this.equipo = equipo;
        this.vueltas = vueltas;
        this.tiempo = tiempo;
        this.puntos = puntos;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public int getVueltas() {
        return vueltas;
    }

    public void setVueltas(int vueltas) {
        this.vueltas = vueltas;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "posicion=" + posicion +
                ", nombre='" + nombre + '\'' +
                ", equipo='" + equipo + '\'' +
                ", vueltas=" + vueltas +
                ", tiempo='" + tiempo + '\'' +
                ", puntos=" + puntos +
                '}';
    }
}