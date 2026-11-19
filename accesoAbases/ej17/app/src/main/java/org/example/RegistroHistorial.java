package org.example;

import javax.xml.bind.annotation.XmlElement;


public class RegistroHistorial {
    private Integer id;
    private String nombreUsuario;
    private String fechaConexion;
    private String ipEquipo; 

    public RegistroHistorial(){

    }
    public RegistroHistorial(Integer id, String nombreUsuario, String fechaConexion, String ipEquipo) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.fechaConexion = fechaConexion;
        this.ipEquipo = ipEquipo;
    }
    @XmlElement(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement(name = "NOMBRE_USUARIO")
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @XmlElement(name = "FECHA_CONEXION")
    public String getFechaConexion() {
        return fechaConexion;
    }

    public void setFechaConexion(String fechaConexion) {
        this.fechaConexion = fechaConexion;
    }

    @XmlElement(name = "IP_EQUIPO")
    public String getIpEquipo() {
        return ipEquipo;
    }

    public void setIpEquipo(String ipEquipo) {
        this.ipEquipo = ipEquipo;
    }










}
