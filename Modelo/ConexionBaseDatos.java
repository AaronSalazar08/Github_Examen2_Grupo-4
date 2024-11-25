package Modelo;
import java.sql.*;

import Controlador.ResultadoLogin;

public class ConexionBaseDatos {
    private static final String URL = "jdbc:mysql://localhost:3306/github?verifyServerCertificate=false&useSSL=true";
    private static final String Usuario = "root";
    private static final String Contrasena = "Proverbios18.22";

    public static Connection obtenerConexion() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(URL, Usuario, Contrasena);
    }

    public static ResultadoLogin validarUsuario(String usuario, String contrasena) {
        try (Connection conn = obtenerConexion();
             CallableStatement stmt = conn.prepareCall("{CALL sp_validar_login(?, ?)}")) {
    
            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);
    
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String resultado = rs.getString("resultado");
                    String mensajeError = rs.getString("mensaje_error");
                    String nombreUsuario = rs.getString("nombre_usuario");
                    return new ResultadoLogin(resultado.equals("Exito"), mensajeError, nombreUsuario);
                }
            }
        } catch (ClassNotFoundException e) {
            return new ResultadoLogin(false, "Error al cargar el driver MySQL: " + e.getMessage(), null);
        } catch (SQLException e) {
            return new ResultadoLogin(false, "Error de conexión o consulta SQL: " + e.getMessage(), null);
        }
        return new ResultadoLogin(false, "Error desconocido en la autenticación", null);
    }
}
