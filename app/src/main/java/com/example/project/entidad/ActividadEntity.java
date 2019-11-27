package com.example.project.entidad;

public class ActividadEntity {
    private int id;
    private String actividad;
    private String descripcion;
    private String estado;

    public ActividadEntity(int id, String actividad, String descripcion, String estado) {
        this.id = id;
        this.actividad = actividad;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getDescripcion() {
        return descripcion;}

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
