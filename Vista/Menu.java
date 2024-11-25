package Vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;




public class Menu  extends JFrame implements ActionListener {


    public JButton  botonMantenimiento, botonAgregar, botonApagado, botonUsuario;
    public JLabel fraseLabel, tituloLabel, labelDescripcion,nombreAdministrador, labelUSuario;
    private ImageIcon imagen;
    private JLabel logo;
    private ImageIcon icono;
    Font fuenteTitulo = new Font("Roboto Slab", Font.BOLD, 24);
    Font fuenteConst = new Font("Open Sans", Font.PLAIN, 16);
   
   

    @SuppressWarnings("unused")
    public Menu() {
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

       

       

        //Boton de Mantenimiento a los usuarios agregados
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
       



        botonApagado = new JButton("");
        botonApagado.setBounds(590, 470, 100, 35);
        botonApagado.setBackground(new Color(255, 255, 240));
        botonApagado.setToolTipText("Si presiona este boton sale del programa");
        botonApagado.setBorderPainted(false); // Quita el borde
        botonApagado.setFocusPainted(false);  // Quita el borde al hacer clic
        botonApagado.setContentAreaFilled(false); 

        ImageIcon iconoApagar = new ImageIcon("Vista/Imagenes/apagado.png");
        Image imagenApagar = iconoApagar.getImage();
        Image imagenApagarAjustada = imagenApagar.getScaledInstance(30, 20, Image.SCALE_SMOOTH);
        ImageIcon iconoApagarAjustado = new ImageIcon(imagenApagarAjustada);
        botonApagado.setIcon(iconoApagarAjustado);




        botonUsuario = new JButton("");
        botonUsuario.setBounds(1, 10, 100, 35);
        botonUsuario.setBackground(new Color(255, 255, 240));
        botonUsuario.setToolTipText("Cerrar Sesión");
        botonUsuario.setBorderPainted(false); // Quita el borde
        botonUsuario.setFocusPainted(false);  // Quita el borde al hacer clic
        botonUsuario.setContentAreaFilled(false); 

        ImageIcon iconoUsuario = new ImageIcon("Vista/Imagenes/usuarioo.png");
        Image imagenUsuario = iconoUsuario.getImage();
        Image imagenUsuarioAjustada = imagenUsuario.getScaledInstance(30, 20, Image.SCALE_SMOOTH);
        ImageIcon iconoUsuarioAjustado = new ImageIcon(imagenUsuarioAjustada);
        botonUsuario.setIcon(iconoUsuarioAjustado);
       


       


        
      

        // Añadir componentes al panel
        panelMenu.add(etiquetaTitulo);
        panelMenu.add(botonAgregar);
        panelMenu.add(botonMantenimiento);
        panelMenu.add(botonApagado);
        panelMenu.add(botonUsuario);

        panelPrincipal.add(panelMenu);
        add(panelPrincipal);


    

        }


    public void actionPerformed(ActionEvent e) {

        
        
        }


         //Metodo para poder insertar imagenes a los JButton
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

    private void Pintar(JLabel lbl, String ruta) { // Este metodo se utiliza para ponerle imagenes de fondo a los
        // JLabels
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





