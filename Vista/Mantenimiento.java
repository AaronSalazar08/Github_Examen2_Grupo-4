package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Modelo.Mostrar;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mantenimiento extends JFrame{


    public JPanel panel_mamtenimiento = new JPanel();
    public JButton btn_eliminar, btn_volver, btn_editar, btn_buscar, btn_refrescar;
    public JLabel lb_id;
    public JTextField txt_id;
    public DefaultTableModel modelo;
    public JTable tabla_usuarios;
    public JScrollPane scroll;


    public Mantenimiento() {
        this.setTitle("Mantenimiento");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(800, 600);
        setLocationRelativeTo(null);
        this.getContentPane().add(panel_mamtenimiento);
        panel_mamtenimiento.setBackground(new Color(255, 255, 255));
        panel_mamtenimiento.setBorder(BorderFactory.createLineBorder(new Color(171, 171, 171), 4));
        panel_mamtenimiento.setLayout(null);

        Elementos();// llamada al metodo de elementos para agregar los elementos del panel a la
                    // interfaz grafica

    }

    public void Elementos() {

        // Inicializando constantes para el JPanel

        // JLabel
        lb_id = new JLabel("Cédula: ");
        lb_id.setBounds(40, 30, 200, 60);

        // JTextField

        txt_id = new JTextField();
        txt_id.setBounds(100, 50, 150, 20);
        txt_id.setBorder(BorderFactory.createLineBorder(new Color(171, 171, 171)));
        txt_id.setToolTipText("Ingrese su número de identificación");

        // JButton

        // Boton eliminar para eliminar pacientes
        btn_eliminar = new JButton("Eliminar");
        btn_eliminar.setBounds(420, 40, 140, 30);
        btn_eliminar.setBackground(new Color(53, 89, 252));
        btn_eliminar.setForeground(new Color(255, 255, 255));
        btn_eliminar.setBorderPainted(false);

        ImageIcon iconoEliminar = new ImageIcon("Vista/Imagenes/eliminar.png");
        btn_eliminar.setToolTipText("Eliminar Usuario");
        if (iconoEliminar != null && iconoEliminar.getImage() != null) {
            Image imagenEliminarAjustada = iconoEliminar.getImage().getScaledInstance(40, 30, Image.SCALE_SMOOTH);
            btn_eliminar.setIcon(new ImageIcon(imagenEliminarAjustada));
        }

        btn_buscar = new JButton("Buscar");
        btn_buscar.setBounds(270, 40, 140, 30);
        btn_buscar.setForeground(new Color(255, 255, 255));
        btn_buscar.setBackground(new Color(53, 89, 252));
        btn_buscar.setBorderPainted(false);

        ImageIcon iconoBuscar = new ImageIcon("Vista/Imagenes/buscar.png");
        btn_buscar.setToolTipText("Buscar usuario");
        if (iconoBuscar != null && iconoBuscar.getImage() != null) {
            Image imagenBuscarAjustada = iconoBuscar.getImage().getScaledInstance(40, 30,
                    Image.SCALE_SMOOTH);
            btn_buscar.setIcon(new ImageIcon(imagenBuscarAjustada));
        }

        btn_editar = new JButton("Editar");
        btn_editar.setBounds(570, 40, 150, 30);
        btn_editar.setForeground(new Color(255, 255, 255));
        btn_editar.setBackground(new Color(53, 89, 252));
        btn_editar.setBorderPainted(false);

        ImageIcon iconoEditar = new ImageIcon("Vista/Imagenes/editar.png");
        btn_editar.setToolTipText("Editar usuario");
        if (iconoEditar != null && iconoEditar.getImage() != null) {
            Image imagenEditarAjustada = iconoEditar.getImage().getScaledInstance(40, 30,
                    Image.SCALE_SMOOTH);
            btn_editar.setIcon(new ImageIcon(imagenEditarAjustada));
        }

        // Boton para volver al menu principal
        btn_volver = new JButton("Volver");
        btn_volver.setBounds(40, 500, 150, 30);
        btn_volver.setForeground(new Color(255, 255, 255));
        btn_volver.setBackground(new Color(53, 89, 252));
        btn_volver.setBorderPainted(false);

        ImageIcon iconoVolver = new ImageIcon("Vista/Imagenes/volver (1).png");
        btn_volver.setToolTipText("Atrás");
        if (iconoVolver != null && iconoVolver.getImage() != null) {
            Image imagenVolverAjustada = iconoVolver.getImage().getScaledInstance(55, 40,
                    Image.SCALE_SMOOTH);
            btn_volver.setIcon(new ImageIcon(imagenVolverAjustada));
        }

        btn_refrescar = new JButton("Refrescar");
        btn_refrescar.setBounds(590, 470, 150, 30);
        btn_refrescar.setForeground(new Color(255, 255, 255));
        btn_refrescar.setBackground(new Color(53, 89, 252));
     
        
        btn_refrescar.setBorderPainted(false);

        ImageIcon iconoRefrescar = new ImageIcon("Vista/Imagenes/refrescar (1).png");
        btn_refrescar.setToolTipText(
                "Cada vez que se realice un cambio a la Tabla presione este boton para actualizarla a su estado más reciente");
        if (iconoVolver != null && iconoRefrescar.getImage() != null) {
            Image imagenRefrescarAjustada = iconoRefrescar.getImage().getScaledInstance(40, 30,
                    Image.SCALE_SMOOTH);
            btn_refrescar.setIcon(new ImageIcon(imagenRefrescarAjustada));
        }

        // JSCROLLPANE
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        tabla_usuarios = new JTable(modelo);
        scroll = new JScrollPane(tabla_usuarios);
        scroll.setBounds(40, 100, 700, 350);

        // AGREGAR CONSTANTES AL PANEL
        panel_mamtenimiento.add(lb_id);
        panel_mamtenimiento.add(btn_buscar);
        panel_mamtenimiento.add(txt_id);
        panel_mamtenimiento.add(btn_volver);
        panel_mamtenimiento.add(btn_eliminar);
        panel_mamtenimiento.add(btn_editar);
        panel_mamtenimiento.add(btn_refrescar);
        panel_mamtenimiento.add(scroll);

    }

    public void funcion_btn_refrescar(ActionListener listener) {
        btn_refrescar.addActionListener(listener);
    }

    public void cargarDatosEnTabla(Object[][] datos, String[] columnNames) {
        modelo.setColumnIdentifiers(columnNames);
        modelo.setRowCount(0); // Limpia la tabla
        for (Object[] fila : datos) {
            modelo.addRow(fila);
        }
        tabla_usuarios.revalidate();
        tabla_usuarios.repaint();
        txt_id.setText("");
    }
    

}
