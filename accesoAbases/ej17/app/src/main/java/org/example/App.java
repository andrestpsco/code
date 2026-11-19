package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.io.File;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class App {
public static void guardarEnXML(File archivoDestino, List<RegistroHistorial> registros) {
    try {
        JAXBContext context = JAXBContext.newInstance(HistorialListWrapper.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        HistorialListWrapper wrapper = new HistorialListWrapper();
        wrapper.setRegistros(registros);

        marshaller.marshal(wrapper, archivoDestino);
        System.out.println("Archivo guardado correctamente: " + archivoDestino.getName());
    }catch (Exception e) {
        e.printStackTrace();
    }
}

public static ArrayList<RegistroHistorial> leerHistorialDesdeBD(){
    String url = "jdbc:mysql://51.48.4.228:3306/basededatos_DAM";
    String user = "alumno";
    String password = "123456";
    String sql = "SELECT ID, NOMBRE_USUARIO, FECHA_CONEXION, IP_EQUIPO FROM EXAMEN_HISTORIAL";

    ArrayList<RegistroHistorial> lista = new ArrayList<>();

    try (Connection conn = DriverManager.getConnection(url,user,password);
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {

            while (rs.next()){
                RegistroHistorial reg = new RegistroHistorial();
                reg.setId(rs.getInt("ID"));
                reg.setNombreUsuario(rs.getString("NOMBRE_USUARIO"));
                reg.setFechaConexion(rs.getString("FECHA_CONEXION"));
                reg.setIpEquipo(rs.getString("IP_EQUIPO"));
                lista.add(reg); 
            }




        }catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
}






    public static void main(String[] args) {
        ArrayList<RegistroHistorial> todosLosRegistros = leerHistorialDesdeBD(); 

        if (todosLosRegistros ==null){
            System.out.println("Error al obtener los datos de la base de datos");
            return;

        }
        File archivoGeneral = new File("historial.xml");
        guardarEnXML(archivoGeneral, todosLosRegistros);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre de usuario para filtrar: ");
        String nombreUsuario = scanner.nextLine().trim();

        ArrayList<RegistroHistorial> registrosUsuario = new ArrayList<>();
        for (RegistroHistorial reg : todosLosRegistros) {
            if (reg.getNombreUsuario() != null && reg.getNombreUsuario().equalsIgnoreCase(nombreUsuario)){
                registrosUsuario.add(reg);
            }
    }

    File archivoUsuario = new File("historial_" + nombreUsuario + ".xml");
    guardarEnXML(archivoUsuario, registrosUsuario);

    System.out.println("Archivos XML generados correctamente.");
    scanner.close();
}
}