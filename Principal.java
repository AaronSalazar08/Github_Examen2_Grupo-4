import Controlador.ControladorMantenimiento;
import Modelo.Actualizar;
import Modelo.Buscar;
import Modelo.Mostrar;
import Vista.Mantenimiento;
import Vista.MenuEditar;
import Vista.VentanaLogin;

public class Principal {

    public static void main(String[] args) {

        VentanaLogin login = new VentanaLogin();
        Mantenimiento mantenimiento = new Mantenimiento();
        Mostrar mostrar = new Mostrar();
        Buscar buscar = new Buscar();
        Actualizar actualizar = new Actualizar();
        MenuEditar menuEditar = new MenuEditar();
        
        new ControladorMantenimiento(mantenimiento, mostrar, buscar, menuEditar, actualizar);
        login.setVisible(true);
        
    }
    
}
