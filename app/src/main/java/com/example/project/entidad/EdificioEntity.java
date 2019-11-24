package com.example.project.entidad;

public class EdificioEntity {
    private int id;
    private String nombre;
    private String prefijo;
    private String estado;

    public EdificioEntity(int id, String nombre, String prefijo, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.prefijo = prefijo;
        this.estado = estado;
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

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
