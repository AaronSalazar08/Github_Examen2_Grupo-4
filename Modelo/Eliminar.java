package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.CallableStatement;
public class Eliminar {
    
    private Connection conexion;

    public Eliminar() {
        try {
            // Cambia estos datos según tu configuración
            String url = "jdbc:mysql://localhost:3306/github";
            String usuario = "root";
            String contraseña = "Aiug010442004*";
            conexion = DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public String eliminarUsuario(int idUsuario) {
        String mensaje = "";
        try {
            String procedimiento = "{CALL sp_eliminar_usuario(?, ?, ?)}";  
            CallableStatement stmt = conexion.prepareCall(procedimiento);

            // Configurando los parámetros
            stmt.setInt(1, idUsuario); // Entrada: ID del usuario
            stmt.registerOutParameter(2, java.sql.Types.BOOLEAN); // Salida: Éxito
            stmt.registerOutParameter(3, java.sql.Types.VARCHAR); // Salida: Mensaje

            // Ejecutar procedimiento
            stmt.execute();

            // Recuperar valores de salida
            boolean exito = stmt.getBoolean(2);
            mensaje = stmt.getString(3);

            // Mostrar mensaje de éxito o error
            if (!exito) {
                System.out.println("Error: " + mensaje);
            }

            stmt.close();
        } catch (SQLException e) {
            mensaje = "Error en la operación: " + e.getMessage();
            e.printStackTrace();
        }
        return mensaje;
    }
}
