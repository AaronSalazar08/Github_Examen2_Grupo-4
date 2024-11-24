package Modelo;
import java.sql.*;

import Controlador.ResultadoLogin;

public class ConexionBaseDatos {
    private static final String URL = "jdbc:mysql://localhost:3306/MuniLiberia_BestMuni?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String Usuario = "MrDouglas";
    private static final String Contrasena = "Muni123";

    public static Connection obtenerConexion() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, Usuario, Contrasena);
    }

    public static ResultadoLogin validarUsuario(String usuario, String contrasena) {
        try (Connection conn = obtenerConexion();
             CallableStatement stmt = conn.prepareCall("{CALL VerificarUsuario(?, ?)}")) {

            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String resultado = rs.getString("resultado");
                    String mensajeError = rs.getString("mensaje_error");
                    return new ResultadoLogin(resultado.equals("Exito"), mensajeError);
                }
            }
        } catch (ClassNotFoundException e) {
            return new ResultadoLogin(false, "Error al cargar el driver MySQL: " + e.getMessage());
        } catch (SQLException e) {
            return new ResultadoLogin(false, "Error de conexión o consulta SQL: " + e.getMessage());
        }
        return new ResultadoLogin(false, "Error desconocido en la autenticación");
    }
}
