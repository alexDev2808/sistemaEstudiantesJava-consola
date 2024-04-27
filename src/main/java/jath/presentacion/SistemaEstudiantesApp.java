package jath.presentacion;

import jath.datos.EstudianteDAO;
import jath.dominio.Estudiante;

import java.util.List;
import java.util.Scanner;


public class SistemaEstudiantesApp {
    public static void main(String[] args) {
        Scanner consola = new Scanner(System.in);
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        boolean salir = false;

        System.out.println("########## Sistema de Estudiantes ###############");

        while (!salir) {

            mostrarMenu();

            try {

                int opcionElegida = Integer.parseInt(consola.nextLine());

                switch (opcionElegida) {
                    case 1 -> {
                        System.out.println("Listado de estudiantes: ");
                        List<Estudiante> estudiantes = estudianteDAO.listarEstudiantes();
                        estudiantes.forEach(System.out::println);
                    }
                    case 2 -> {
                        System.out.println("Busqueda de estudiantes: ");
                        buscarEstudiante(consola, estudianteDAO);
                    }
                    case 3 -> {
                        System.out.println("Agregar estudiante: ");
                        agregarEstudiante(consola, estudianteDAO);
                    }
                    case 4 -> {
                        System.out.println("Modificar estudiantes: ");
                        modificarEstudiante(consola, estudianteDAO);
                    }
                    case 5 -> {
                        System.out.println("Eliminar estudiantes: ");
                        eliminarEstudiante(consola, estudianteDAO);
                    }
                    case 6 -> {
                        System.out.println("Hasta pronto ...");
                        salir = true;
                    }
                    default -> System.out.println("Opcion incorrecta, intenta de nuevo");
                }
            } catch (Exception e) {
                System.out.println("Ocurrio un error: " + e.getMessage());
            }

            System.out.println();
        }

    }

    private static void eliminarEstudiante(Scanner consola, EstudianteDAO estudianteDAO) {
        System.out.print("Ingresa el idEstudiante a eliminar: ");
        int idEstudiante = Integer.parseInt(consola.nextLine());
        Estudiante estudiante = new Estudiante(idEstudiante);
        boolean eliminado = estudianteDAO.eliminarEstudiante(estudiante);

        if(eliminado) {
            System.out.println("Estudiante eliminado: " + estudiante);
        } else {
            System.out.println("No se pudo eliminar :(, intenta de nuevo");
        }
    }

    private static void modificarEstudiante(Scanner consola, EstudianteDAO estudianteDAO) {

        System.out.print("IdEstudiante a modificar: ");
        int idEstudiante = Integer.parseInt(consola.nextLine());
        System.out.println("Ingrese los datos a modificar: ");
        System.out.print("Nombre: ");
        String nombreEstudiante = consola.nextLine();
        System.out.print("Apellidos: ");
        String apellidosEstudiante = consola.nextLine();
        System.out.print("Telefono: ");
        String telefonoEstudiante = consola.nextLine();
        System.out.print("Email: ");
        String emailEstudiante = consola.nextLine();

        Estudiante estudiante = new Estudiante(idEstudiante,nombreEstudiante, apellidosEstudiante, telefonoEstudiante, emailEstudiante);
        boolean modificado = estudianteDAO.modificarEstudiante(estudiante);

        if(modificado) {
            System.out.println("Estudiante modificado: " + estudiante);
        } else {
            System.out.println("No se pudo modificar estudiante :(, intenta de nuevo");
        }
    }

    private static void agregarEstudiante(Scanner consola, EstudianteDAO estudianteDAO) {

        System.out.print("Nombre:");
        String nombreEstudiante = consola.nextLine();
        System.out.print("Apellidos:");
        String apellidosEstudiante = consola.nextLine();
        System.out.print("Telefono:");
        String telefonoEstudiante = consola.nextLine();
        System.out.print("Email:");
        String emailEstudiante = consola.nextLine();

        Estudiante estudiante = new Estudiante(nombreEstudiante, apellidosEstudiante, telefonoEstudiante, emailEstudiante);
        boolean agregado = estudianteDAO.agregarEstudiante(estudiante);

        if(agregado) {
            System.out.println("Estudiante agregado: " + estudiante);
        } else {
            System.out.println("No se pudo agregar estudiante :(, intenta de nuevo");
        }
    }

    public static void buscarEstudiante(Scanner consola, EstudianteDAO estudianteDAO){
        System.out.print("Ingresa el idEstudiante a buscar: ");
        int idEstudiante = Integer.parseInt(consola.nextLine());
        var buscarEstudiante = new Estudiante(idEstudiante);
        boolean encontrado = estudianteDAO.buscarEstudiantePorId(buscarEstudiante);

        if (encontrado) {
            System.out.println("Estudiante encontrado: " + buscarEstudiante);
        } else {
            System.out.println("No se encontro estudiante :(, intenta de nuevo");
        }
    }

    public static void mostrarMenu(){
        System.out.println("""
                ##############################
                
                1. Listar Estudiantes
                2. Buscar Estudiantes
                3. Agregar Estudiante
                4. Modificar Estudiante
                5. Eliminar Estudiante
                6. Salir
                
                #############################
                """);
        System.out.print("Elige una opcion: ");
    }
}