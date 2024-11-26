package Controlador;

import java.awt.Color;

import javax.swing.JOptionPane;

import Modelo.Actualizar;
import Modelo.Buscar;
import Modelo.ConexionBaseDatos;
import Modelo.Insertar;
import Modelo.Mostrar;
import Vista.MenuEditar;
import Vista.MenuInsertar;
import Vista.VentanaLogin;
import Vista.Mantenimiento;
import Vista.Menu;

public class ControladorMantenimiento {

    private Mostrar mostrar;
    private Buscar buscar;
    private Actualizar actualizar;
    private Insertar insertar;
    private MenuEditar menuEditar;
    private MenuInsertar menuInsertar;
    private Mantenimiento mantenimiento;
    private Menu menu;
    private VentanaLogin login;

    public ControladorMantenimiento(VentanaLogin login, Mantenimiento mantenimiento, Mostrar mostrar, Buscar buscar,
            MenuEditar menuEditar,
            Actualizar actualizar, MenuInsertar menuInsertar, Menu menu, Insertar insertar) {
        this.mantenimiento = mantenimiento;
        this.mostrar = mostrar;
        this.buscar = buscar;
        this.menuEditar = menuEditar;
        this.actualizar = actualizar;
        this.menuInsertar = menuInsertar;
        this.menu = menu;
        this.login = login;
        this.insertar = insertar;

        this.mantenimiento.funcion_btn_refrescar(e -> refrescarDatos());
        this.mantenimiento.funcion_btn_buscar(e -> validar_ID());
        this.mantenimiento.funcion_btn_editar(e -> manenimiento_editar());
        this.mantenimiento.funcion_btn_volver(e -> mantenimiento_menu());

        this.menuEditar.funcion_btn_editar((e -> editar_usuario()));
        this.menuEditar.funcion_btn_regresar((e -> editar_menu()));


        this.menu.funcion_btn_agregar(e -> menu_insertar());
        this.menu.funcion_btn_mantenimiento(e -> menu_mantenimiento());
        this.menu.funcion_btn_apagar(e -> apagar_sistema());
        this.menu.funcion_btn_cerrarsesion(e -> cerrar_sesion());

        this.login.funcion_btn_login(e -> inicio_sesion());

        this.menuInsertar.funcion_btn_regresar(e -> insertar_menu());
        this.menuInsertar.funcion_btn_insertar(e -> insertar_usuario());


    }

    public void inicio_sesion (){

        String usuario = login.campoUsuario.getText();
        String contrasena = new String(login.campoContrasena.getPassword());

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            mostrarError("Por favor complete todos los campos");
            return;
        }

        ResultadoLogin resultado = ConexionBaseDatos.validarUsuario(usuario, contrasena);
        if (resultado.esExitoso()) {

            login.nombreUsuario = resultado.obtenerNombreUsuario();
            JOptionPane.showMessageDialog(null, "Bienvenido " + login.nombreUsuario);
            login.dispose(); // Cierra la ventana de inicio de sesión
            menu.setVisible(true);

        } else {
            mostrarError(resultado.obtenerMensaje());
        }
    }

    public void insertar_usuario (){

        char[] Contra = menuInsertar.TextClave.getPassword();
        String Contraseña = new String(Contra);

        char[] ConfirmarContra = menuInsertar.TextConfirmarClave.getPassword();
        String ConfirmarContraseña = new String(ConfirmarContra);

        String Nombre1 = menuInsertar.TextPrimerNombre.getText();
        String Apellido1 = menuInsertar.TextPrimerApellido.getText();
        String Apellido2 = menuInsertar.TextSegundoApellido.getText();
        String Usuario = menuInsertar.TextLogin.getText();
        String Nombre2 = menuInsertar.TextSegundoNombre.getText();

        if (Nombre1.isEmpty() || Apellido1.isEmpty() || Usuario.isEmpty()
                || Contraseña.isEmpty() || ConfirmarContraseña.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe de llenar los campos obligatorios (*)", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            if (Contraseña.equals(ConfirmarContraseña)) {
                
                Insertar conexion = new Insertar();
                conexion.Conectar(Nombre1, Nombre2, Apellido1, Apellido2, Usuario, Contraseña);

                menuInsertar.TextPrimerNombre.setText("");
                menuInsertar.TextPrimerApellido.setText("");
                menuInsertar.TextSegundoApellido.setText("");
                menuInsertar.TextLogin.setText("");
                menuInsertar.TextSegundoNombre.setText("");
                menuInsertar.TextConfirmarClave.setText("");
                menuInsertar.TextClave.setText("");

            } else {
                JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

    }
}

    public void mostrarError(String mensaje){
        login.etiquetaMensaje.setText(mensaje);
        login.etiquetaMensaje.setForeground(Color.RED);
        limpiarCampos();

    }

    public String getnombreUsuario() {

        return login.nombreUsuario;

    }

    public void limpiarCampos (){

        login.campoUsuario.setText(""); 
        login.campoContrasena.setText("");
    }

    private void refrescarDatos() {

        Object[][] datos = mostrar.mostrarDatos();

        String[] nombre_columnas = { "ID", "1° Nombre", "2° Nombre", "1° Apellido", "2° Apellido", "Usuario", "Clave",
                "Creado" };
        mantenimiento.cargarDatosEnTabla(datos, nombre_columnas);
    }

    public void validar_ID() {

        String id_obtenido = mantenimiento.txt_id.getText().trim();

        if (id_obtenido.isEmpty()) {

            JOptionPane.showMessageDialog(null, "Debe digitar el ID del usuario para continuar con la búsqueda");
        } else {

            JOptionPane.showMessageDialog(null, "Buscando Usuario");

            Object[][] datos = buscar.buscar_ID(id_obtenido);

            if (datos.length > 0) {

                JOptionPane.showMessageDialog(null, "Usuario Encontrado");

                Object[] usuario = datos[0];
                menuEditar.llenarCampos(usuario);

                String[] nombre_columnas = { "ID", "1° Nombre", "2° Nombre", "1° Apellido", "2° Apellido", "Usuario",
                        "Clave", "Creado" };
                mantenimiento.cargarDatosEnTabla(datos, nombre_columnas);

            } else {

                JOptionPane.showMessageDialog(null, "Usuario No Encontrado");
            }

        }
    }

    public void manenimiento_editar() {

        mantenimiento.setVisible(false);
        menuEditar.setVisible(true);
    }

    public void editar_usuario() {

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

        if (primer_apellido.isEmpty() && primer_nombre.isEmpty() && usuario.isEmpty() && clave.isEmpty()
                && ConfirmarContraseña.isEmpty()) {

            JOptionPane.showMessageDialog(null, "Verifique llenar los campos requerido");
        } else {

            actualizar.conectar_editar(id, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, usuario,
                    clave);
            menuEditar.setVisible(false);
            mantenimiento.setVisible(true);

        }

    }

    public void menu_mantenimiento() {

        menu.setVisible(false);
        mantenimiento.setVisible(true);
    }

    public void mantenimiento_menu() {

        mantenimiento.setVisible(false);
        menu.setVisible(true);
    }

    public void menu_insertar() {

        menu.setVisible(false);
        menuInsertar.setVisible(true);
    }

    public void insertar_menu() {

        menuInsertar.setVisible(false);
        menu.setVisible(true);
    }

    public void editar_menu() {

        menuEditar.setVisible(false);
        menu.setVisible(true);
    }

    public void apagar_sistema() {
        int opcion = JOptionPane.showConfirmDialog(
                null,
                "¿Está seguro de que desea salir de la aplicación?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (opcion == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Apagando Sistema");
            System.exit(0);
        }

    }

    public void cerrar_sesion() {

        int opcion = JOptionPane.showConfirmDialog(
                null,
                "¿Deseas Cerrar Sesión?",
                "Cerrar Sesión",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (opcion == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Cerrando Sesión");
            login.setVisible(true);
            menu.setVisible(false);

        }

    }

}
