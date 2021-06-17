package com.example.tabalumno.model;

public class Alumno {
    private Integer _id;
    private String nombres;
    private String telefono;
    private String dni;
    private String correo;

    public Alumno() {
    }

    public Alumno(Integer _id, String nombres, String telefono, String dni, String correo) {
        this._id = _id;
        this.nombres = nombres;
        this.telefono = telefono;
        this.dni = dni;
        this.correo = correo;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}