package ej15;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;

public class Profesor {
    private String nombre;
    private String departamento;
    private List<String> asignaturas = new ArrayList<>();

    public Profesor() {
    }

    public Profesor(String nombre, String departamento, List<String> asignaturas) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.asignaturas = asignaturas == null ? new ArrayList<>() : asignaturas;
    }

    @XmlElement(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement(name = "departamento")
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @XmlElementWrapper(name = "asignaturas")
    @XmlElement(name = "asignatura")
    public List<String> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List<String> asignaturas) {
        this.asignaturas = asignaturas == null ? new ArrayList<>() : asignaturas;
    }
}
