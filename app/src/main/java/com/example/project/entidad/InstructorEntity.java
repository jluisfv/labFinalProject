package com.example.project.entidad;

public class InstructorEntity {
    private int id;
    private int idInstructor;
    private int idLab;
    private int idCiclo;

    public InstructorEntity(int id, int idInstructor, int idLab, int idCiclo) {
        this.id = id;
        this.idInstructor = idInstructor;
        this.idLab = idLab;
        this.idCiclo = idCiclo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(int idInstructor) {
        this.idInstructor = idInstructor;
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
