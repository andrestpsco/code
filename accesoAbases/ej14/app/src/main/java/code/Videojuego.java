package code;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "videojuego")
public class Videojuego {
    private String titulo;
    private int anyo;
    private String desarrollador;
    private String genero;
    private List<String> plataformas = new ArrayList<>();

    public Videojuego() {
    }

    public Videojuego(String titulo, int anyo, String desarrollador, String genero,
                      List<String> plataformas) {
        this.titulo = titulo;
        this.anyo = anyo;
        this.desarrollador = desarrollador;
        this.genero = genero;
        this.plataformas = plataformas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public String getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(String desarrollador) {
        this.desarrollador = desarrollador;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @XmlElementWrapper(name = "plataformas")
    @XmlElement(name = "plataforma")
    public List<String> getPlataformas() {
        return plataformas;
    }

    public void setPlataformas(List<String> plataformas) {
        this.plataformas = plataformas;
    }
}
