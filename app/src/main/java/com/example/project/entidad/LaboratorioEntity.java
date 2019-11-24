package com.example.project.entidad;

public class LaboratorioEntity {

    private int id;
    private String nombre;
    private int idEdificio;
    private String nivel;

    public LaboratorioEntity(int id, String nombre, int idEdificio, String nivel) {
        this.id = id;
        this.nombre = nombre;
        this.idEdificio = idEdificio;
        this.nivel = nivel;
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

    public int getIdEdificio() {
        return idEdificio;
    }

    public void setIdEdificio(int idEdificio) {
        this.idEdificio = idEdificio;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
