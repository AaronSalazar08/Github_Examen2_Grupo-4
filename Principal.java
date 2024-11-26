import Controlador.ControladorMantenimiento;
import Modelo.Actualizar;
import Modelo.Buscar;
import Modelo.Mostrar;
import Vista.Mantenimiento;
import Vista.MenuEditar;

public class Principal {

    public static void main(String[] args) {

        Mantenimiento mantenimiento = new Mantenimiento();
        Mostrar mostrar = new Mostrar();
        Buscar buscar = new Buscar();
        Actualizar actualizar = new Actualizar();
        MenuEditar menuEditar = new MenuEditar();
        
        new ControladorMantenimiento(mantenimiento, mostrar, buscar, menuEditar, actualizar);
        mantenimiento.setVisible(true);
        
    }
    
}
