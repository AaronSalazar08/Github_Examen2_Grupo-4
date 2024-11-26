import Controlador.ControladorMantenimiento;
import Modelo.Buscar;
import Modelo.Mostrar;
import Vista.Mantenimiento;
import Vista.MenuEditar;

public class Principal {

    public static void main(String[] args) {

        Mantenimiento mantenimiento = new Mantenimiento();
        Mostrar mostrar = new Mostrar();
        Buscar buscar = new Buscar();
        MenuEditar actualizar = new MenuEditar();
        
        new ControladorMantenimiento(mantenimiento, mostrar, buscar, actualizar);
        mantenimiento.setVisible(true);
        
    }
    
}
