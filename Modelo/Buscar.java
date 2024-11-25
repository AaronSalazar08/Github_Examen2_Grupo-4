package Modelo;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Buscar {

    Connection con = null;
    ResultSet rs = null;

    String SP = "CALL BuscarUsuarioPorID(?);";

    public  Object[][] buscar_ID(String id_buscado) {

        try {

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/github?verifyServerCertificate=false&useSSL=true",
                    "root", "Proverbios18.22");
            PreparedStatement pstmt = con.prepareStatement(SP);
            pstmt.setString(1, id_buscado);
            rs = pstmt.executeQuery();

           

                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                ArrayList<Object[]> dataList = new ArrayList<>();
                while (rs.next()) {

                    Object[] rowData = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        rowData[i - 1] = rs.getObject(i);
                    }
                    dataList.add(rowData);
                

                Object[][] dataArray = new Object[dataList.size()][];
                return dataList.toArray(dataArray);

                }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos: " + ex.getMessage());
        } finally {
            // Cerrar ResultSet y Connection
            try {
                if (rs != null)
                    rs.close();
                if (con != null)
                    con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
                return null;

    }

}
