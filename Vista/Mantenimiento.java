package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Modelo.Eliminar;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Mantenimiento extends JFrame {

 
    public JButton btn_eliminar, btn_volver, btn_editar, btn_buscar, btn_refrescar;
    public JLabel lb_id;
    public JTextField txt_id;
    public DefaultTableModel modelo;
    public JTable tabla_usuarios;
    public JScrollPane scroll;

    @SuppressWarnings("unused")
    public Mantenimiento() {
        setTitle("Bienvenido"); //Titulo del panel principal
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal con fondo degradado
        JPanel panel_principal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth(), h = getHeight();
                Color color1 = new Color(66, 139, 202);
                Color color2 = new Color(51, 51, 51);
                GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        panel_principal.setLayout(null);

        // Panel de inicio de sesión con bordes redondeados
        JPanel panel_mantenimiento = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(255, 255, 255, 240)); // Fondo blanco con algo de transparencia
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25); // Bordes redondeados
                g2d.dispose();
            }
        };
        panel_mantenimiento.setLayout(null);
        int panelWidth = 700, panelHeight = 550;
        panel_mantenimiento.setOpaque(false); // Transparente para preservar la apariencia

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
        btn_eliminar = new JButton(" Eliminar");
        btn_eliminar.setBounds(420, 460, 120, 30);
        btn_eliminar.setBackground(new Color(255, 0, 0));
        btn_eliminar.setForeground(new Color(255, 255, 255));
        btn_eliminar.setBorderPainted(false);

        ImageIcon iconoEliminar = new ImageIcon("Vista/Imagenes/basura.png");
        btn_eliminar.setToolTipText("Eliminar al Usuario del Sistema");
        if (iconoEliminar != null && iconoEliminar.getImage() != null) {
            Image imagenEliminarAjustada = iconoEliminar.getImage().getScaledInstance(30, 20, Image.SCALE_SMOOTH);
            btn_eliminar.setIcon(new ImageIcon(imagenEliminarAjustada));
        }

        btn_eliminar.addActionListener(e -> {
            String idTexto = txt_id.getText();
            if (idTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID del usuario que desea eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            try {
                int idUsuario = Integer.parseInt(idTexto);
        
                // Mostrar mensaje de confirmación
                int confirmacion = JOptionPane.showConfirmDialog(
                    this, 
                    "¿Está seguro de que desea eliminar el usuario con ID " + idUsuario + "?", 
                    "Confirmar eliminación", 
                    JOptionPane.YES_NO_OPTION
                );
        
                // Si elige "Sí", proceder con la eliminación
                if (confirmacion == JOptionPane.YES_OPTION) {
                    Eliminar eliminador = new Eliminar();
                    String mensaje = eliminador.eliminarUsuario(idUsuario);
        
                    // Mostrar el mensaje del procedimiento
                    JOptionPane.showMessageDialog(this, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);
        
                    // Limpiar el campo de texto
                    txt_id.setText("");
                } else {
                    // Mensaje opcional para indicar que se canceló la acción
                    JOptionPane.showMessageDialog(this, "Eliminación cancelada.", "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        

        btn_buscar = new JButton("  Buscar");
        btn_buscar.setBounds(270, 45, 120, 30);
        btn_buscar.setForeground(new Color(255, 255, 255));
        btn_buscar.setBackground(new Color(53, 89, 252));
        btn_buscar.setBorderPainted(false);

        ImageIcon iconoBuscar = new ImageIcon("Vista/Imagenes/busqueda.png");
        btn_buscar.setToolTipText("Buscar usuario");
        if (iconoBuscar != null && iconoBuscar.getImage() != null) {
            Image imagenBuscarAjustada = iconoBuscar.getImage().getScaledInstance(30, 20,
                    Image.SCALE_SMOOTH);
            btn_buscar.setIcon(new ImageIcon(imagenBuscarAjustada));
        }

        btn_editar = new JButton("   Editar");
        btn_editar.setBounds(550, 460, 120, 30);
        btn_editar.setForeground(new Color(255, 255, 255));
        btn_editar.setBackground(new Color(0, 180, 16 ));
        btn_editar.setBorderPainted(false);

        ImageIcon iconoEditar = new ImageIcon("Vista/imagenes/lapiz-de-usuario.png");
        btn_editar.setToolTipText("Editar Datos del Usuario");
        if (iconoEditar != null && iconoEditar.getImage() != null) {
            Image imagenEditarAjustada = iconoEditar.getImage().getScaledInstance(30, 20,
                    Image.SCALE_SMOOTH);
            btn_editar.setIcon(new ImageIcon(imagenEditarAjustada));
        }

        // Boton para volver al menu principal
        btn_volver = new JButton("");
        btn_volver.setBounds(20, 480, 100, 40);
        btn_volver.setBackground(new Color(255, 255, 255, 240));
        btn_volver.setBorderPainted(false);
        btn_volver.setOpaque(false);

        ImageIcon iconoVolver = new ImageIcon("Vista/imagenes/deshacer (1).png");
        btn_volver.setToolTipText("Atrás");
        if (iconoVolver != null && iconoVolver.getImage() != null) {
            Image imagenVolverAjustada = iconoVolver.getImage().getScaledInstance(55, 40,
                    Image.SCALE_SMOOTH);
            btn_volver.setIcon(new ImageIcon(imagenVolverAjustada));
        }

        btn_refrescar = new JButton("");
        btn_refrescar.setBounds(620, 60, 60, 30);
        btn_refrescar.setForeground(new Color(255, 255, 255));
        btn_refrescar.setBackground(new Color(53, 89, 252));
        btn_refrescar.setBorderPainted(false);
        btn_refrescar.setOpaque(false);

        ImageIcon iconoRefrescar = new ImageIcon("Vista/imagenes/gira-a-la-derecha.png");
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
        scroll.setBounds(30, 100, 650, 350);

        // AGREGAR CONSTANTES AL PANEL
        panel_mantenimiento.add(lb_id);
        panel_mantenimiento.add(btn_buscar);
        panel_mantenimiento.add(txt_id);
        panel_mantenimiento.add(btn_volver);
        panel_mantenimiento.add(btn_eliminar);
        panel_mantenimiento.add(btn_editar);
        panel_mantenimiento.add(btn_refrescar);
        panel_mantenimiento.add(scroll);

        panel_principal.add(panel_mantenimiento);
        add(panel_principal);

        centrarPanelLogin(panel_mantenimiento, panel_principal, panelWidth, panelHeight);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                centrarPanelLogin(panel_mantenimiento, panel_principal, panelWidth, panelHeight);
            }
        });

    }

    private void centrarPanelLogin(JPanel panelLogin, JPanel panelPrincipal, int panelWidth, int panelHeight) {
        int x = (panelPrincipal.getWidth() - panelWidth) / 2;
        int y = (panelPrincipal.getHeight() - panelHeight) / 2;
        panelLogin.setBounds(x, y, panelWidth, panelHeight);
    }

    public void funcion_btn_refrescar(ActionListener listener) {
        btn_refrescar.addActionListener(listener);
    }

    public void funcion_btn_buscar(ActionListener listener) {
        btn_buscar.addActionListener(listener);
    }

    public void funcion_btn_editar(ActionListener listener) {
        btn_editar.addActionListener(listener);
    }

    public void cargarDatosEnTabla(Object[][] datos, String[] columnNames) {
        modelo.setColumnIdentifiers(columnNames);
        modelo.setRowCount(0); 
        for (Object[] fila : datos) {
            modelo.addRow(fila);
        }
        tabla_usuarios.revalidate();
        tabla_usuarios.repaint();
        txt_id.setText("");
    }

}
