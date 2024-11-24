import Controlador.ControladorMantenimiento;
import Modelo.Mostrar;
import Vista.Mantenimiento;

public class Principal {

    public static void main(String[] args) {

        Mantenimiento mantenimiento = new Mantenimiento();
        Mostrar mostrar = new Mostrar();
        
        new ControladorMantenimiento(mantenimiento, mostrar);
        mantenimiento.setVisible(true);
        
    }
    
}
