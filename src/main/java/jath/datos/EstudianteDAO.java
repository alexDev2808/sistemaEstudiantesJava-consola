package jath.datos;

import jath.dominio.Estudiante;
import static jath.conexion.Conexion.getConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// DAO Data Access Object
public class EstudianteDAO {

    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection conn = getConexion();
        String sql = "SELECT * FROM estudiante ORDER BY id_estudiante";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Estudiante estudiante = new Estudiante();

                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellidos(rs.getString("apellidos"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));

                estudiantes.add(estudiante);
            }

        } catch (Exception e) {
            System.out.println("Ocurrio un error al seleccionar datos: " + e.getMessage());
        }
        finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Ocurrio un error al cerrar la conexion: " + e.getMessage());
            }
        }

        return estudiantes;
    }

    public boolean buscarEstudiantePorId(Estudiante estudiante) {
        PreparedStatement ps;
        ResultSet rs;
        Connection conn = getConexion();
        String sql = "SELECT * FROM estudiante WHERE id_estudiante = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            rs = ps.executeQuery();

            if(rs.next()) {
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellidos(rs.getString("apellidos"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));

                return true;
            }

        } catch (Exception e) {
            System.out.println("Ocurrio un error al buscar estudiante: " + e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Ocurrio un error al cerrar conexion: " + e.getMessage());
            }
        }

        return false;
    }

    public boolean buscarEstudiantePorNombre(Estudiante estudiante) {
        PreparedStatement ps;
        ResultSet rs;
        Connection conn = getConexion();
        String sql = "SELECT * FROM estudiante WHERE (nombre LIKE ?) ORDER BY nombre";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + estudiante.getNombre() + "%");
            rs = ps.executeQuery();

            if(rs.next()) {
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellidos(rs.getString("apellidos"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));

                return true;
            }

        } catch (Exception e) {
            System.out.println("Ocurrio un error al buscar estudiante: " + e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Ocurrio un error al cerrar conexion: " + e.getMessage());
            }
        }

        return false;
    }

    public boolean agregarEstudiante(Estudiante estudiante) {
        PreparedStatement ps;
        Connection conn = getConexion();
        String sql = "INSERT INTO estudiante(nombre, apellidos, telefono, email) " +
                        "VALUES (?, ?, ?, ?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellidos());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());

            ps.execute();
            return true;

        } catch (Exception e){
            System.out.println("Ocurrio un error al agregar estudiante: " + e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (Exception e){
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }

        return false;
    }

    public boolean modificarEstudiante(Estudiante estudiante) {
        PreparedStatement ps;
        Connection conn = getConexion();
        String sql = "UPDATE estudiante SET nombre=?, apellidos=?, telefono=?, email=? " +
                        "WHERE id_estudiante = ?";

        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellidos());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.setInt(5, estudiante.getIdEstudiante());

            ps.execute();
            return true;

        } catch (Exception e) {
            System.out.println("Ocurrio un error al modificar estudiante: " + e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }

        return false;
    }

    public boolean eliminarEstudiante(Estudiante estudiante) {

        PreparedStatement ps;
        Connection conn = getConexion();
        String sql = "DELETE FROM estudiante WHERE id_estudiante = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            ps.execute();
            return true;

        } catch (Exception e){
            System.out.println("Error al eliminar estudiante: " + e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (Exception e){
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }

        return false;
    }

    public static void main(String[] args) {
        EstudianteDAO estudianteDAO = new EstudianteDAO();

        //agregar
//        var nuevoEstudiante = new Estudiante("Irma", "Hernandez", "2461881975", "delfino@test.com");
//        var agregado = estudianteDAO.agregarEstudiante(nuevoEstudiante);
//
//        if(agregado){
//            System.out.println("Estudiante agregado: " + nuevoEstudiante);
//        } else {
//            System.out.println("No se agrego estudiante: " + nuevoEstudiante);
//        }


        //modificar
//        var modificarEstudiante = new Estudiante(1, "J. Alexis", "Tenorio", "2461765663", "alexis@test.com");
//        var modificado = estudianteDAO.modificarEstudiante(modificarEstudiante);
//
//        if(modificado) {
//            System.out.println("Estudiante modificado: " + modificarEstudiante);
//        } else {
//            System.out.println("No se pudo modificar: " + modificarEstudiante);
//        }

        //eliminar
        var eliminarEstudiante = new Estudiante(1);
        var eliminado = estudianteDAO.eliminarEstudiante(eliminarEstudiante);

        if(eliminado){
            System.out.println("Estudiante eliminado: " + eliminarEstudiante);
        } else {
            System.out.println("No se pudo eliminar: " + estudianteDAO);
        }

        //listar
        System.out.println("Listado de estudiantes: ");
        List<Estudiante> estudiantes = estudianteDAO.listarEstudiantes();
        estudiantes.forEach(System.out::println);

        //buscar
//        var estudiante1 = new Estudiante(2);
//        System.out.println("Estudiante antes de la busqueda: " + estudiante1);
//        var encontrado = estudianteDAO.buscarEstudiantePorId(estudiante1);
//
//        if(encontrado){
//            System.out.println("Estudiante encontrado: " + estudiante1);
//        } else {
//            System.out.println("No se encontro estudiante");
//        }
    }
}
