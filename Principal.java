import Controlador.ControladorMantenimiento;
import Controlador.ResultadoLogin;
import Modelo.Actualizar;
import Modelo.Buscar;
import Modelo.ConexionBaseDatos;
import Modelo.Insertar;
import Modelo.Mostrar;
import Vista.Mantenimiento;
import Vista.Menu;
import Vista.MenuEditar;
import Vista.MenuInsertar;
import Vista.VentanaLogin;

public class Principal {

    public static void main(String[] args) {

        VentanaLogin login = new VentanaLogin();
        Mantenimiento mantenimiento = new Mantenimiento();
        Mostrar mostrar = new Mostrar();
        Buscar buscar = new Buscar();
        MenuInsertar menuInsertar = new MenuInsertar();
        Actualizar actualizar = new Actualizar();
        MenuEditar menuEditar = new MenuEditar();
        Insertar insertar = new Insertar();
        String usuario = login.campoUsuario.getText();
        char[] entrada_clave = login.campoContrasena.getPassword();
        String clave = new String(entrada_clave);
        ResultadoLogin resultadoLogin = ConexionBaseDatos.validarUsuario(usuario, clave);
        String primer_nombre = resultadoLogin.obtenerNombreUsuario();
        Menu menu = new Menu(primer_nombre);
        
        new ControladorMantenimiento(login, mantenimiento, mostrar, buscar, menuEditar, actualizar, menuInsertar, menu, insertar);
        login.setVisible(true);
        
    }
    
}
