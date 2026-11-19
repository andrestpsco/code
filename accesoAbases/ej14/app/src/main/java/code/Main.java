package code;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class Main {

    public static ArrayList<Videojuego> loadVideojuegosFromFile(File file) {
        if (file == null || !file.exists()) {
            System.err.println("No se encontró el archivo: " + (file == null ? "" : file.getPath()));
            return new ArrayList<>();
        }

        try {
            JAXBContext context = JAXBContext.newInstance(VideojuegoListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            VideojuegoListWrapper wrapper = (VideojuegoListWrapper) um.unmarshal(file);

            ArrayList<Videojuego> videojuegos = new ArrayList<>();
            if (wrapper.getVideojuegos() != null) {
                videojuegos.addAll(wrapper.getVideojuegos());
            }
            return videojuegos;
        } catch (JAXBException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void saveVideojuegosToFile(File file, ArrayList<Videojuego> videojuegos) {
        try {
            JAXBContext context = JAXBContext.newInstance(VideojuegoListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            VideojuegoListWrapper wrapper = new VideojuegoListWrapper();
            wrapper.setVideojuegos(videojuegos);

            m.marshal(wrapper, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File archivo = buscarArchivoXml();
        if (!archivo.exists()) {
            System.err.println("No se encontró videojuegos.xml.");
            return;
        }

        ArrayList<Videojuego> videojuegos = loadVideojuegosFromFile(archivo);
        String opcion = "";

        try (Scanner teclado = new Scanner(System.in)) {
            while (!opcion.equals("0")) {
                System.out.print("1. Mostrar  2. Insertar  3. Eliminar  0. Salir: ");
                opcion = teclado.nextLine();

                switch (opcion) {
                    case "1":
                        for (Videojuego v : videojuegos) {
                            System.out.println(v.getTitulo() + " - " + v.getAnyo() + " - "
                                    + v.getDesarrollador() + " - " + v.getGenero()
                                    + " - " + v.getPlataformas());
                            for (String plataforma : v.getPlataformas()) {
                                System.out.println(" Plataforma: " + plataforma);
                            }
                        }
                        break;

                    case "2":
                        Videojuego nuevo = new Videojuego();

                        System.out.print("Titulo: ");
                        nuevo.setTitulo(teclado.nextLine());
                        System.out.print("Año de lanzamiento: ");
                        nuevo.setAnyo(Integer.parseInt(teclado.nextLine().trim()));
                        System.out.print("Desarrollador: ");
                        nuevo.setDesarrollador(teclado.nextLine());
                        System.out.print("Género: ");
                        nuevo.setGenero(teclado.nextLine());

                        ArrayList<String> plataformas = new ArrayList<>();
                        while (true) {
                            System.out.print("Plataforma (dejar en blanco para terminar): ");
                            String plataforma = teclado.nextLine();
                            if (plataforma.isBlank()) {
                                break;
                            }
                            plataformas.add(plataforma);
                        }
                        nuevo.setPlataformas(plataformas);

                        videojuegos.add(nuevo);
                        saveVideojuegosToFile(archivo, videojuegos);
                        System.out.println("Videojuego añadido correctamente.");
                        break;

                    case "3":
                        System.out.print("Introduce el título del videojuego a eliminar: ");
                        String titulo = teclado.nextLine();
                        boolean encontrado = false;

                        for (int i = 0; i < videojuegos.size(); i++) {
                            if (videojuegos.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                                videojuegos.remove(i);
                                encontrado = true;
                                break;
                            }
                        }
                        if (encontrado) {
                            saveVideojuegosToFile(archivo, videojuegos);
                            System.out.println("Videojuego eliminado correctamente.");
                        } else {
                            System.out.println("No se encontró ningún videojuego con ese título.");
                        }
                        break;

                    case "0":
                        System.out.println("Saliendo del programa...");
                        break;

                    default:
                        System.out.println("Opción no válida. Por favor, elige una opción del 0 al 3.");
                }
            }
        }
    }

    private static File buscarArchivoXml() {
        File[] candidatos = {
            new File("videojuegos.xml"),
            new File("code/accesoAbases/ej14/videojuegos.xml"),
            new File("../videojuegos.xml")
        };
        for (File candidato : candidatos) {
            if (candidato.exists()) {
                return candidato;
            }
        }
        return candidatos[0];
    }
}