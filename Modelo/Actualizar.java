package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Actualizar {

    public String conectar_editar(int id, String Nombre1, String Nombre2, String Apellido1, String Apellido2, String Usuario,
            String Contra) {
        String a = "";
        Connection conexion = null;
        PreparedStatement preparar = null;

        String SQL = "CALL actualizar_usuario(?,?,?,?,?,?,?);";

        try {
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/github?verifyServerCertificate=false&useSSL=true", "root",
                    "Proverbios18.22");
            conexion.setAutoCommit(true);

            preparar = conexion.prepareStatement(SQL);
            
            preparar.setInt(1, id);
            preparar.setString(2, Nombre1);
            preparar.setString(3, Nombre2);
            preparar.setString(4, Apellido1);
            preparar.setString(5, Apellido2);
            preparar.setString(6, Usuario);
            preparar.setString(7, Contra);
        

           
            

            int exito = preparar.executeUpdate();

            if (exito > 0) {
                JOptionPane.showMessageDialog(null, "Usuario Actualizado Correctamente");
            } else {

                JOptionPane.showMessageDialog(null, "Hubo un error al Actualizar el Usuario");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos: " + ex.getMessage());
        }  finally {

            try {
                if (preparar != null)
                    preparar.close();
                if (conexion != null)
                    conexion.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return a;
    }

}
