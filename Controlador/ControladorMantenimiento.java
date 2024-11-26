package Controlador;

import javax.swing.JOptionPane;

import Modelo.Buscar;
import Modelo.Mostrar;
import Vista.MenuEditar;
import Vista.Mantenimiento;

public class ControladorMantenimiento {

    private Mostrar mostrar;
    private Buscar buscar;
    private MenuEditar actualizar;
    private Mantenimiento mantenimiento;

    public ControladorMantenimiento(Mantenimiento mantenimiento, Mostrar mostrar, Buscar buscar, MenuEditar actualizar) {
        this.mantenimiento = mantenimiento;
        this.mostrar = mostrar;
        this.buscar = buscar;
        this.actualizar = actualizar;

        this.mantenimiento.funcion_btn_refrescar(e -> refrescarDatos());
        this.mantenimiento.funcion_btn_buscar(e -> validar_ID());
        this.mantenimiento.funcion_btn_editar(e -> manenimiento_editar());
       
    }

    private void refrescarDatos(){

        Object[][] datos = mostrar.mostrarDatos();

        String [] nombre_columnas = {"ID", "1° Nombre", "2° Nombre", "1° Apellido", "2° Apellido", "Usuario", "Clave", "Creado"};
        mantenimiento.cargarDatosEnTabla(datos, nombre_columnas);
    }

    public void validar_ID (){

        String id_obtenido = mantenimiento.txt_id.getText().trim();


        if(id_obtenido.isEmpty()){

            JOptionPane.showMessageDialog(null, "Debe digitar el ID del usuario para continuar con la búsqueda");
        }else {

            JOptionPane.showMessageDialog(null, "Buscando Usuario");
            
            Object[][] datos = buscar.buscar_ID(id_obtenido);

            if(datos.length > 0 ){

                JOptionPane.showMessageDialog(null, "Usuario Encontrado");

                Object[] usuario = datos[0];
                actualizar.llenarCampos(usuario);

                String [] nombre_columnas = {"ID", "1° Nombre", "2° Nombre", "1° Apellido", "2° Apellido", "Usuario", "Clave", "Creado"};
                mantenimiento.cargarDatosEnTabla(datos, nombre_columnas);

            } else {

                JOptionPane.showMessageDialog(null, "Usuario No Encontrado");
            }
           
        }
    }

    public void manenimiento_editar(){

        mantenimiento.setVisible(false);
        actualizar.setVisible(true);
    }

}
