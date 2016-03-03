package com.example.admin.psp_practica3_jaime.pojo;

/**
 * Created by Admin on 23/02/2016.
 */
public class Profesor {

    private String id,nombre,apellidos,departamento;

    public Profesor() {
    }

    public Profesor(String nombre, String apellidos, String departamento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.departamento = departamento;
    }

    public Profesor(String id, String nombre, String apellidos, String departamento) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.departamento = departamento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", departamento='" + departamento + '\'' +
                '}';
    }
}
