package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import Vista.Mantenimiento;

public class Mostrar {

    public Mantenimiento mantenimiento;


    public void mostrarDatosEnTabla() {
        Connection con = null;
        ResultSet rs = null;

        String SQL = " CALL MostrarUsuarios();";

        try {
            // realizando conexión con la base de datos
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/github?verifyServerCertificate=false&useSSL=true",
                    "root", "Proverbios18.22");
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            // Obtener metadata de la consulta
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Obtener nombres de las columnas
            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = metaData.getColumnName(i);
            }

            mantenimiento.modelo.setColumnIdentifiers(columnNames);
            mantenimiento.modelo.setRowCount(0);

            while (rs.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = rs.getObject(i);
                }
                mantenimiento.modelo.addRow(rowData);
                mantenimiento.txt_id.setText(" ");
            }

            mantenimiento.tabla_usuarios.revalidate();
            mantenimiento.tabla_usuarios.repaint();

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
    }

}
