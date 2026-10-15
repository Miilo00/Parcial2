package co.edu.poli.seguros.Infraestructura.Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase utilitaria para manejar la conexión JDBC con la base de datos MySQL.
 * 
 * @author Cristian Camilo Vera
 * @version 1.0
 */
public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/GestionSeguros?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /**
     * Obtiene una conexión activa con la base de datos.
     * 
     * @return Objeto Connection de JDBC
     */
    public static Connection obtenerConexion() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar con la base de datos: " + e.getMessage(), e);
        }
    }
}