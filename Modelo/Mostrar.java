package Modelo;

import java.sql.*;
import java.util.*;
import javax.swing.*;

public class Mostrar {

    public Object[][] mostrarDatos() {
        Connection con = null;
        ResultSet rs = null;

        String SQL = "CALL MostrarUsuarios();";

        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/github?verifyServerCertificate=false&useSSL=true",
                    "root", "Proverbios18.22");
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            ArrayList<Object[]> dataList = new ArrayList<>();
            while (rs.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = rs.getObject(i);
                }
                dataList.add(rowData);
            }

            Object[][] dataArray = new Object[dataList.size()][];
            return dataList.toArray(dataArray);

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos: " + ex.getMessage());
            return new Object[0][0];
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (con != null)
                    con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
