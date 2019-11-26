package com.example.project.entidad;

public class DetAsigInstructorEntity {
    private int id;
    private String dias;
    private String horaInicio;
    private String horaFin;
    private int idAsignacion;

    public DetAsigInstructorEntity(int id, String dias, String horaInicio, String horaFin, int idAsignacion) {
        this.id = id;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.idAsignacion = idAsignacion;
    }

    public int getId() {
        return id;
    }

    public String getDias() {
        return dias;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public int getIdAsignacion() {
        return idAsignacion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public void setIdAsignacion(int idAsignacion) {
        this.idAsignacion = idAsignacion;
    }
}
