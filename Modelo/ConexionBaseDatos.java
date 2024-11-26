package Modelo;
import Controlador.ResultadoLogin;
import java.sql.*;
import javax.swing.JOptionPane;

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
             CallableStatement stmt = conn.prepareCall("{CALL sp_validar_login(?, ?, ?, ?, ?)}")) {

            // Registrar parámetros de entrada
            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);

            // Registrar parámetros de salida
            stmt.registerOutParameter(3, Types.BOOLEAN); // p_valido
            stmt.registerOutParameter(4, Types.INTEGER); // p_id_usuario
            stmt.registerOutParameter(5, Types.VARCHAR); // p_primer_nombre

            // Ejecutar el procedimiento
            stmt.execute();

            // Obtener resultados de los parámetros de salida
            boolean valido = stmt.getBoolean(3); // p_valido
            int idUsuario = stmt.getInt(4); // p_id_usuario
            String nombreUsuario = stmt.getString(5); // p_primer_nombre

            if (valido) {
                return new ResultadoLogin(true, "Exito", nombreUsuario);
            } else {
                return new ResultadoLogin(false, "Usuario o contraseña incorrectos", null);
            }
        } catch (ClassNotFoundException e) {
            return new ResultadoLogin(false, "Error al cargar el driver MySQL: " + e.getMessage(), null);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión o consulta SQL: " + e.getMessage());
            return new ResultadoLogin(false, "Error de conexión o consulta SQL: " + e.getMessage(), null);
        }
    }
}