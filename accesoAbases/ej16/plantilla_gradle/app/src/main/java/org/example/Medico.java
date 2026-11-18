package org.example;



import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Medico{
    private Integer numeroDoctor;
    private String nombre;
    private String especialidad;
    private Integer anosExperiencia;

    public Medico(){

    }

    @XmlAttribute(name="numeroDoctor")
    public Integer getNumeroDoctor() {return numeroDoctor;}
    public void setNumeroDoctor(Integer numeroDoctor){this.numeroDoctor = numeroDoctor;}

    @XmlElement(name = "nombre")
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @XmlElement(name = "especialidad")
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    @XmlElement(name = "anosExperiencia")
    public Integer getAnosExperiencia() { return anosExperiencia; }
    public void setAnosExperiencia(Integer anosExperiencia) { this.anosExperiencia = anosExperiencia; }
    





}