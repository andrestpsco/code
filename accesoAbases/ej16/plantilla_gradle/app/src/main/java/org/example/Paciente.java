package org.example;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Paciente{
    private String dni;
    private String nombre;
    private String fechaNacimiento;
    private String ciudad;
    private Integer doctor;

    public Paciente() {}

    @XmlAttribute(name= "DNI")
    public String getDNI(){ return dni;}
    public void setDni(String dni){this.dni = dni;
    }
    
    @XmlElement(name = "nombre")
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @XmlElement(name = "fechaNacimiento")
    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    @XmlElement(name = "ciudad")
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    @XmlElement(name = "doctor")
    public Integer getDoctor() { return doctor; }
    public void setDoctor(Integer doctor) { this.doctor = doctor; }







}