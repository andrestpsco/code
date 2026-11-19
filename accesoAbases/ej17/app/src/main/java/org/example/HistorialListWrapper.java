package org.example;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "historial")
public class HistorialListWrapper {
    private List<RegistroHistorial> registros;

    @XmlElement(name= "registro")
    public List<RegistroHistorial> getRegistros() {return registros;}
    public void setRegistros(List<RegistroHistorial> registros) {this.registros = registros;}
    
}
