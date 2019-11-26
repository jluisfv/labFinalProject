package com.example.project.entidad;

public class DetHorasEntity {
    private int id;
    private int dia;
    private String horaInicio;
    private String horaFin;
    private int idInstructor;

    public DetHorasEntity(int id, int dia, String horaInicio, String horaFin, int  idInstructor) {
        this.id = id;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this. idInstructor =  idInstructor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public int getIdInstructor() {
        return  idInstructor;
    }

    public void setIdInstructor(int  idInstructor) {
        this. idInstructor =  idInstructor;
    }
}
