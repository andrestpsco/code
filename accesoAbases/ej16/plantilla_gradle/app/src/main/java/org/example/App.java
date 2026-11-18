package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Marshaller;

public class App {

    public static ArrayList<Medico> cargarMedicos(File archivo){
        try{
        
        JAXBContext context = JAXBContext.newInstance(MedicoListWrapper.class);
        Unmarshaller um = context.createUnmarshaller();
        MedicoListWrapper wrapper = (MedicoListWrapper) um.unmarshal(archivo);
        ArrayList<Medico> lista = new ArrayList<>();
        if (wrapper.getMedicos() != null){
            lista.addAll(wrapper.getMedicos());
        }
        return lista;
    
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Paciente> cargarPacientes(File archivo){
        try{
            JAXBContext context = JAXBContext.newInstance(PacienteListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            PacienteListWrapper wrapper = (PacienteListWrapper) um.unmarshal(archivo);
            ArrayList<Paciente> lista = new ArrayList<>();
            if (wrapper.getPacientes() != null) {
                lista.addAll(wrapper.getPacientes());
            }
            return lista;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;


    }







    public static void main(String[] args) {
        File archivoMedicos = new File("medicos.xml");
        File archivoPacientes = new File("pacientes.xml");
        
        ArrayList<Medico> listaMedicos = cargarMedicos(archivoMedicos);
        ArrayList<Paciente> listaPacientes = cargarPacientes(archivoPacientes);

        if (listaMedicos == null || listaPacientes == null){
            System.out.println("Error al cargar los archivos XML.");
            return;
        }
        int opcion;
        do{ 
        System.out.println("Menú");
        System.out.println("\n1. Mostrar los pacientes de cada médico");
        System.out.println("\n2. Buscar un paciente por nombre");
        System.out.println("\n3. Insertar un nuevo paciente");
        System.out.println("\n4. Salir");
        
        Scanner scanner = new Scanner(System.in);
        opcion = scanner.nextInt();

        switch (opcion){

        case 1:
        System.out.println("=== PACIENTES POR MEDICO ===");
        for (Medico m : listaMedicos){
            int contadorPacientes = 0;
            for (Paciente p : listaPacientes){
                if (p.getDoctor() != null && p.getDoctor().equals(m.getNumeroDoctor()))
                    contadorPacientes++;
            }
        
        
        System.out.println("El/La "+ m.getNombre() + "(Especialidad; " + m.getEspecialidad() + 
            ") tiene " + contadorPacientes + " paciente(s) asignado(s).");
   
        }
        break;
        case 2:
        System.out.println("\n=== BUSCAR PACIENTE POR NOMBRE ===");
        
        System.out.print("Introduce el nombre del paciente que deseas buscar:");
        
        String nombreBuscado = scanner.nextLine();

        boolean encontrado= false;
        for (Paciente p : listaPacientes){
            if (p.getNombre().equalsIgnoreCase(nombreBuscado)){
                encontrado = true;
                System.out.println("\nPaciente encontrado");
                System.out.println(" - DNI: " + p.getDNI());
                System.out.println(" - Nombre: " + p.getNombre());
                System.out.println(" - Fecha de Nacimiento: " + p.getFechaNacimiento());
                System.out.println(" - Ciudad: " + p.getCiudad());
                
                Medico medicoAsociado = null;
                for (Medico m : listaMedicos){
                    if (m.getNumeroDoctor().equals(p.getDoctor())){
                        medicoAsociado = m;
                        
                    }
                }

                if(medicoAsociado != null){
                    System.out.println("-> Médico Asignado: "+ medicoAsociado.getNombre() + 
                    "(Especialidad: " + medicoAsociado.getEspecialidad() + 
                    ", Experiencia: " + medicoAsociado.getAnosExperiencia() + "Años)");
                }else{
                    System.out.println("-> Este paciente no tiene un médico válido asignado");

                }
               
                
            }
        } 
         if (!encontrado){
            System.out.println("No se ha encontrado ningún paciente con ese nombre.");
         }
        break;

        case 3:
        System.out.println("\n=== INSERTAR NUEVO PACIENTE ==="); 
        System.out.println("Introduce el ID del doctor al que quieras insertarle un paciente");
        int idMedicoBuscado = scanner.nextInt();
        scanner.nextLine();
        

        Medico medicoDestino = null;
        for(Medico m : listaMedicos){
            if (m.getNumeroDoctor().equals(idMedicoBuscado)){
                medicoDestino = m;
                break;
            }
        }
        if (medicoDestino != null){
            System.out.println("Medico encontrado ("+ medicoDestino.getNombre()+")");

            Paciente nuevoPaciente = new Paciente();
            System.out.println("Introduzca el nombre del paciente");
            String nombre = scanner.nextLine();
            nuevoPaciente.setNombre(nombre);
            System.out.println("Introduzca el DNI del paciente");
            String dni = scanner.nextLine();
            nuevoPaciente.setDni(dni);
            System.out.println("Introduzca la fecha de nacimiento del paciente");
            String fechaNacimiento=scanner.nextLine();
            nuevoPaciente.setFechaNacimiento(fechaNacimiento);
            System.out.println("Introduzca la ciudad del paciente");
            String ciudad = scanner.nextLine();
            nuevoPaciente.setCiudad(ciudad);
            nuevoPaciente.setDoctor(idMedicoBuscado);

            listaPacientes.add(nuevoPaciente);

            try {
                JAXBContext context = JAXBContext.newInstance(PacienteListWrapper.class);
                Marshaller m = context.createMarshaller();
                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                PacienteListWrapper wrapper = new PacienteListWrapper();
                wrapper.setPacientes(listaPacientes);

                m.marshal(wrapper, archivoPacientes);
                System.out.println("¡Nuevo paciente insertado y guardado correctamente en 'pacientes.xml'!");

            }catch(Exception e ){
                e.printStackTrace();

            }
            

        }else {
            System.out.println("Error: El número de doctor introducido no existe. No se puede realizar la inserción.");
        }

         scanner.close();
         break;
         default:
        System.out.println("introduzca un numero válido");
        break;
        
    }

    }while(opcion!=4);
    
}
}

    

