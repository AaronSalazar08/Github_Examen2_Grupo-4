import Controlador.ControladorMantenimiento;
import Modelo.Buscar;
import Modelo.Mostrar;
import Vista.Mantenimiento;

public class Principal {

    public static void main(String[] args) {

        Mantenimiento mantenimiento = new Mantenimiento();
        Mostrar mostrar = new Mostrar();
        Buscar buscar = new Buscar();
        
        new ControladorMantenimiento(mantenimiento, mostrar, buscar);
        mantenimiento.setVisible(true);
        
    }
    
}
