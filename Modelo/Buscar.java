package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import Vista.Mantenimiento;

public class Buscar {

    Mantenimiento mantenimiento;

    Connection con = null;
    ResultSet rs = null;

    String SQL = "CALL BuscarUsuarioPorID(?);";
    String id_buscado = mantenimiento.txt_id.getText().trim();

    public void buscarID() {

        try {

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/github?verifyServerCertificate=false&useSSL=true",
                    "root", "Proverbios18.22");
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

        } catch (Exception e) {

        }

    }

}
