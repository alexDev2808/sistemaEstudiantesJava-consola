package jath.conexion;
import jath.variables.Variables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static Connection getConexion() {
        Connection conexion = null;
        String nombreDB = "estudiantes_db";
        String url = "jdbc:mysql://localhost:3306/" + nombreDB;
        String usuario = Variables.username;
        String password = Variables.password;

        //Cargamos la clase del driver de mysql en memoria
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch (ClassNotFoundException | SQLException e ) {
            System.out.println("Ocurrio un error en la conexion: " + e.getMessage());
        }


        return conexion;
    }

    public static void main(String[] args) {
        var conn = Conexion.getConexion();
        if (conn != null){
            System.out.println("Conexion exitosa! :) => " + conn);
        } else {
            System.out.println("Error al conectarse :(");
        }
    }
}
