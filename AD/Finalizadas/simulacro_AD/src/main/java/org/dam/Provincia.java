package org.dam;

import java.util.ArrayList;

public class Provincia {
    private int idProvincia;
    private String nombre;
    private ArrayList<Ciudad> ciudades;

    public Provincia() {
    }

    public Provincia(int idProvincia, String nombre, ArrayList<Ciudad> ciudades) {
        this.idProvincia = idProvincia;
        this.nombre = nombre;
        this.ciudades = ciudades;
    }

    public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(ArrayList<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }

    @Override
    public String toString() {
        return "Provincia{" +
                "idProvincia=" + idProvincia +
                ", nombre='" + nombre + '\'' +
                ", ciudades=" + ciudades +
                '}';
    }
}
