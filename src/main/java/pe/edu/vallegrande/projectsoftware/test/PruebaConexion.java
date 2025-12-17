package pe.edu.vallegrande.projectsoftware.test;

import pe.edu.vallegrande.projectsoftware.db.SqlConnection;

import java.sql.SQLException;

public class PruebaConexion {
    public static void main(String[] args) throws SQLException {
        SqlConnection.getConnection();
        System.out.println("Conexion establecida.");
    }
}