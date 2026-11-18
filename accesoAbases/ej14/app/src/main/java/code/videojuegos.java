package code;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class videojuegos {
    private string titulo;
    private int anyo;   
    private string desarrollador;
    private string genero;
    private List<string> plataformas;

    public videojuego(){

    }
    public videojuego(string titulo, int anyo, string desarrollador, string genero, List<string> plataformas){
        this.titulo = titulo;
        this.anyo = anyo;
        this.desarrollador = desarrollador;
        this.genero = genero;
        this.plataformas = plataformas;
    }

    public string getTitulo(){
        return titulo;}   
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }   
    publicm Integer getAnyo(){return anyo;}
    public void setAnyo(int anyo){
        this.anyo = anyo;
    }
    public string getDesarrollador(){
        return desarrollador;
    } 
    public void setDesarrollador(string desarrollador){
        this.desarrollador = desarrollador;
    }

    public string getGenero(){
        return genero;
    }
    public void setGenero(string genero){
        this.genero = genero;
    }

    @XmlElementWrapper(name = "plataformas")
    @XmlElement(name = "plataforma")
    public list<string> getPlataformas(){
        return plataformas;
    }
    public void setPlataformas(List<string> plataformas){
        this.plataformas = plataformas;
    }



}
