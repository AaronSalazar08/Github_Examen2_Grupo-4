package Controlador;

import Modelo.Mostrar;
import Vista.Mantenimiento;

public class ControladorMantenimiento {

    private Mostrar mostrar;
    private Mantenimiento mantenimiento;

    public ControladorMantenimiento(Mantenimiento mantenimiento, Mostrar mostrar) {
        this.mantenimiento = mantenimiento;
        this.mostrar = mostrar;

        this.mantenimiento.funcion_btn_refrescar(e -> refrescarDatos());
    }

    private void refrescarDatos(){

        Object[][] datos = mostrar.mostrarDatos();

        String [] nombre_columnas = {"ID", "Primer Nombre", "Segundo Nombre", "Primer Apellido", "Segundo Apellido", "Usuario", "Clave", "Fecha Creacion"};
        mantenimiento.cargarDatosEnTabla(datos, nombre_columnas);
    }

}
