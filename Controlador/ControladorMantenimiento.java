package Controlador;

import javax.swing.JOptionPane;

import Modelo.Buscar;
import Modelo.Mostrar;
import Vista.Mantenimiento;

public class ControladorMantenimiento {

    private Mostrar mostrar;
    private Buscar buscar;
    private Mantenimiento mantenimiento;

    public ControladorMantenimiento(Mantenimiento mantenimiento, Mostrar mostrar, Buscar buscar) {
        this.mantenimiento = mantenimiento;
        this.mostrar = mostrar;
        this.buscar = buscar;

        this.mantenimiento.funcion_btn_refrescar(e -> refrescarDatos());
        this.mantenimiento.funcion_btn_buscar(e -> validar_ID());
    }

    private void refrescarDatos(){

        Object[][] datos = mostrar.mostrarDatos();

        String [] nombre_columnas = {"ID", "Primer Nombre", "Segundo Nombre", "Primer Apellido", "Segundo Apellido", "Usuario", "Clave", "Fecha Creacion"};
        mantenimiento.cargarDatosEnTabla(datos, nombre_columnas);
    }

    public void validar_ID (){

        String id_obtenido = mantenimiento.txt_id.getText().trim();

        if(id_obtenido.isEmpty()){

            JOptionPane.showMessageDialog(null, "Debe digitar el ID del usuario para continuar con la búsqueda");
        }else {

            JOptionPane.showMessageDialog(null, "Buscando Usuario");
            Object[][] datos = buscar.buscar_ID(id_obtenido);
            JOptionPane.showMessageDialog(null, "Usuario Encontrado");
            String [] nombre_columnas = {"ID", "Primer Nombre", "Segundo Nombre", "Primer Apellido", "Segundo Apellido", "Usuario", "Clave", "Fecha Creacion"};
            mantenimiento.cargarDatosEnTabla(datos, nombre_columnas);

        }
    }

}
