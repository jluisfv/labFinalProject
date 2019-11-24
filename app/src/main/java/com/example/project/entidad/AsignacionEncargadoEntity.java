package com.example.project.entidad;

public class AsignacionEncargadoEntity {
    private int id;
    private int idEmpleado;
    private int idLab;
    private int idCiclo;

    public AsignacionEncargadoEntity(int id, int idEmpleado, int idLab, int idCiclo) {
        this.id = id;
        this.idEmpleado = idEmpleado;
        this.idLab = idLab;
        this.idCiclo = idCiclo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdLab() {
        return idLab;
    }

    public void setIdLab(int idLab) {
        this.idLab = idLab;
    }

    public int getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(int idCiclo) {
        this.idCiclo = idCiclo;
    }
}
