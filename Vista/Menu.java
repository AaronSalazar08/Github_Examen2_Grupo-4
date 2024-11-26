package Vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Menu extends JFrame implements ActionListener {

    public JButton botonMantenimiento, botonAgregar, botonApagado, botonUsuario;
    public JLabel fraseLabel, tituloLabel, labelDescripcion, nombreAdministrador, labelUSuario;
    private ImageIcon imagen;
    public VentanaLogin instancia;
   
    
    private ImageIcon icono;
   
    @SuppressWarnings("unused")
    public Menu(String cosa) {
        this.instancia = instancia;
        
        setTitle("Inicio de sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal con fondo degradado
        JPanel panelPrincipal = new JPanel() {
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
        panelPrincipal.setLayout(null);

        // Panel de inicio de sesión
        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(null);
        panelMenu.setBounds(30, 25, 720, 520);
        panelMenu.setBackground(new Color(255, 255, 255, 240));

        // Título
        JLabel etiquetaTitulo = new JLabel("Sistema de Mantenimiento");
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        etiquetaTitulo.setBounds(200, 50, 400, 50);
        etiquetaTitulo.setForeground(new Color(53, 89, 252));


        JLabel nombreAdministrador = new JLabel(cosa);
        nombreAdministrador.setFont(new Font("Arial", Font.ITALIC, 18));
        nombreAdministrador.setBounds(80, 5, 400, 50);
        nombreAdministrador.setForeground(new Color(0, 0, 0));


        // Botón de Agregar Usuario
        botonAgregar = new JButton("          Agregar Usuario           ");
        botonAgregar.setBounds(245, 200, 240, 35);
        botonAgregar.setBackground(new Color(53, 89, 252));
        botonAgregar.setForeground(Color.white);
        botonAgregar.setToolTipText("Agregar nuevo usuario al sistema");
        ImageIcon iconoAgregar = new ImageIcon("Vista/Imagenes/agregar (2).png");
        Image imagenAgregar = iconoAgregar.getImage();
        Image imagenAgregarAjustada = imagenAgregar.getScaledInstance(30, 20, Image.SCALE_SMOOTH);
        ImageIcon iconoAgregarAjustado = new ImageIcon(imagenAgregarAjustada);
        botonAgregar.setIcon(iconoAgregarAjustado);
        botonAgregar.addActionListener(this);

        // Botón de Mantenimiento a los usuarios agregados
        botonMantenimiento = new JButton("    Mantenimiento Usuario       ");
        botonMantenimiento.setBounds(245, 290, 240, 35);
        botonMantenimiento.setBackground(new Color(53, 89, 252));
        botonMantenimiento.setForeground(Color.white);
        botonMantenimiento.setToolTipText("Realizar acción sobre usuario");
        ImageIcon iconoMantenimiento = new ImageIcon("Vista/Imagenes/Mantenimiento.png");
        Image imagenMantenimiento = iconoMantenimiento.getImage();
        Image imagenMantenimientoAjustada = imagenMantenimiento.getScaledInstance(30, 20, Image.SCALE_SMOOTH);
        ImageIcon iconoMantenimientoAjustado = new ImageIcon(imagenMantenimientoAjustada);
        botonMantenimiento.setIcon(iconoMantenimientoAjustado);
        botonMantenimiento.addActionListener(this);

        // Botón de Apagado
        botonApagado = new JButton("");
        botonApagado.setBounds(590, 470, 100, 35);
        botonApagado.setBackground(new Color(255, 255, 240));
        botonApagado.setToolTipText("Si presiona este botón sale del programa");
        botonApagado.setBorderPainted(false);
        botonApagado.setFocusPainted(false);
        botonApagado.setContentAreaFilled(false);
        botonApagado.addActionListener(this);
        ImageIcon iconoApagar = new ImageIcon("Vista/Imagenes/apagado.png");
        Image imagenApagar = iconoApagar.getImage();
        Image imagenApagarAjustada = imagenApagar.getScaledInstance(30, 20, Image.SCALE_SMOOTH);
        ImageIcon iconoApagarAjustado = new ImageIcon(imagenApagarAjustada);
        botonApagado.setIcon(iconoApagarAjustado);

        // Botón de Usuario
        botonUsuario = new JButton("");
        botonUsuario.setBounds(1, 10, 100, 35);
        botonUsuario.setBackground(new Color(255, 255, 240));
        botonUsuario.setToolTipText("Cerrar Sesión");
        botonUsuario.setBorderPainted(false);
        botonUsuario.setFocusPainted(false);
        botonUsuario.setContentAreaFilled(false);
        ImageIcon iconoUsuario = new ImageIcon("Vista/Imagenes/usuarioo.png");
        Image imagenUsuario = iconoUsuario.getImage();
        Image imagenUsuarioAjustada = imagenUsuario.getScaledInstance(30, 20, Image.SCALE_SMOOTH);
        ImageIcon iconoUsuarioAjustado = new ImageIcon(imagenUsuarioAjustada);
        botonUsuario.setIcon(iconoUsuarioAjustado);
        botonUsuario.addActionListener(this);

        // Añadir componentes al panel
        panelMenu.add(etiquetaTitulo);
        panelMenu.add(botonAgregar);
        panelMenu.add(botonMantenimiento);
        panelMenu.add(botonApagado);
        panelMenu.add(nombreAdministrador);
        panelMenu.add(botonUsuario);
        panelPrincipal.add(panelMenu);
        add(panelPrincipal);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonApagado) {
            int opcion = JOptionPane.showConfirmDialog(
                    null,
                    "¿Está seguro de que desea salir de la aplicación?",
                    "Confirmar salida",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (opcion == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Apagando Sistema");
                System.exit(0);
            }


        } else if (e.getSource() == botonUsuario) {
            
           

            int opcion = JOptionPane.showConfirmDialog(
                null,
                "¿Deseas Cerrar Sesión?",
                "Cerrar Sesión",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (opcion == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Cerrando Sesión");
           VentanaLogin login = new VentanaLogin();
           login.setVisible(true);
           this.dispose();
        }

        } else if (e.getSource() == botonMantenimiento) {
            Mantenimiento mant = new Mantenimiento();
            mant.setVisible(true);
            this.dispose();

        } else if (e.getSource() == botonAgregar) {
            MenuInsertar ing = new MenuInsertar();
            ing.setVisible(true);
            this.dispose();



        }
    }

    private void PintarB(JButton lbl, String ruta) {
        this.imagen = new ImageIcon(ruta);
        this.icono = new ImageIcon(
                this.imagen.getImage().getScaledInstance(
                        lbl.getWidth(),
                        lbl.getHeight(),
                        Image.SCALE_DEFAULT));
        lbl.setIcon(this.icono);
        this.repaint();
    }

    private void Pintar(JLabel lbl, String ruta) {
        this.imagen = new ImageIcon(ruta);
        this.icono = new ImageIcon(
                this.imagen.getImage().getScaledInstance(
                        lbl.getWidth(),
                        lbl.getHeight(),
                        Image.SCALE_DEFAULT));
        lbl.setIcon(this.icono);
        this.repaint();
    }
}
