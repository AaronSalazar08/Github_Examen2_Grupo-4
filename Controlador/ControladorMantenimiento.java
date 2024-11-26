package Controlador;

import javax.swing.JOptionPane;

import Modelo.Actualizar;
import Modelo.Buscar;
import Modelo.Mostrar;
import Vista.MenuEditar;
import Vista.Mantenimiento;

public class ControladorMantenimiento {

    private Mostrar mostrar;
    private Buscar buscar;
    private Actualizar actualizar;
    private MenuEditar menuEditar;
    private Mantenimiento mantenimiento;

    public ControladorMantenimiento(Mantenimiento mantenimiento, Mostrar mostrar, Buscar buscar, MenuEditar menuEditar, Actualizar actualizar) {
        this.mantenimiento = mantenimiento;
        this.mostrar = mostrar;
        this.buscar = buscar;
        this.menuEditar = menuEditar;
        this.actualizar = actualizar;

        this.mantenimiento.funcion_btn_refrescar(e -> refrescarDatos());
        this.mantenimiento.funcion_btn_buscar(e -> validar_ID());
        this.mantenimiento.funcion_btn_editar(e -> manenimiento_editar());

        this.menuEditar.funcion_btn_editar((e ->editar_usuario() ));
       
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
                menuEditar.llenarCampos(usuario);

                String [] nombre_columnas = {"ID", "1° Nombre", "2° Nombre", "1° Apellido", "2° Apellido", "Usuario", "Clave", "Creado"};
                mantenimiento.cargarDatosEnTabla(datos, nombre_columnas);

            } else {

                JOptionPane.showMessageDialog(null, "Usuario No Encontrado");
            }
           
        }
    }

    public void manenimiento_editar(){

        mantenimiento.setVisible(false);
        menuEditar.setVisible(true);
    }

    public void editar_usuario (){

        String entrada_id = mantenimiento.txt_id.getText();
        int id = Integer.parseInt(entrada_id);
        String primer_nombre = menuEditar.TextPrimerNombre.getText();
        String segundo_nombre = menuEditar.TextSegundoNombre.getText();
        String primer_apellido = menuEditar.TextPrimerApellido.getText();
        String segundo_apellido = menuEditar.TextSegundoApellido.getText();
        String usuario = menuEditar.TextLogin.getText();
        char[] entrada_clave = menuEditar.TextClave.getPassword();
        String clave = new String(entrada_clave);
        char[] ConfirmarContra = menuEditar.TextConfirmarClave.getPassword();
        String ConfirmarContraseña = new String(ConfirmarContra);

        if (primer_apellido.isEmpty() && primer_nombre.isEmpty() && usuario.isEmpty() && clave.isEmpty() && ConfirmarContraseña.isEmpty()) {
            
            JOptionPane.showMessageDialog(null, "Verifique llenar los campos requerido");
        }
        else{

            actualizar.conectar_editar(id, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, usuario, clave);

        }

    }

}
