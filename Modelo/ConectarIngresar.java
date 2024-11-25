package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

public class ConectarIngresar {
    
    
public String Conectar(String Nombre1, String Nombre2, String Apellido1, String Apellido2, String Usuario, String Contra){
                String a = "";
                Connection conexion = null;
                PreparedStatement preparar = null;

                String SQL = "{CALL agregar_usuario(?,?,?,?,?,?)}";

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    conexion = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/prueba2?verifyServerCertificate=false&useSSL=true", "root",
                            "Deiby_R04");
                    conexion.setAutoCommit(true);

                    preparar = conexion.prepareStatement(SQL);
                    preparar.setString(1, Nombre1);
                    preparar.setString(2, Nombre2);
                    preparar.setString(3, Apellido1);
                    preparar.setString(4, Apellido2);
                    preparar.setString(5, Usuario);
                    preparar.setString(6, Contra);

                    int exito = preparar.executeUpdate();

                    if (exito > 0) {
                        JOptionPane.showMessageDialog(null, "La persona se ha agregado a la base de datos");
                    }

                } catch (Exception ew) {
                    ew.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
                } finally {

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
