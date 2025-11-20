package org.dam;

import java.util.ArrayList;

public class Comunidad {
    private int id;
    private String nombre;
    private ArrayList<Provincia> provincias;

    public Comunidad() {
    }

    public Comunidad(int id, String nombre, ArrayList<Provincia> provincias) {
        this.id = id;
        this.nombre = nombre;
        this.provincias = provincias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(ArrayList<Provincia> provincias) {
        this.provincias = provincias;
    }

    @Override
    public String toString() {
        return "Comunidad{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", provincias=" + provincias +
                '}';
    }
}
