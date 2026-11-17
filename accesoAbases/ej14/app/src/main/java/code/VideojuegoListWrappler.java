
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "videojuegos")


public class VideojuegoListWrappler {
    private List<Videojuego> videojuegos;

    @XmlElement(name = "videojuego")
    public List<Videojuego> getVideojuegos() {
        return videojuegos;
    }   
    public void setVideojuegos(List<Videojuego> videojuegos) {
        this.videojuegos = videojuegos;
    }

}
