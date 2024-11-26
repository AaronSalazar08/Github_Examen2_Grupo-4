package Vista;

import Controlador.ResultadoLogin;
import Modelo.ConexionBaseDatos;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VentanaLogin extends JFrame {
    public JTextField campoUsuario;
    public JPasswordField campoContrasena;
    public JLabel etiquetaMensaje;
    public JButton botonLogin;
    public String nombreUsuario;

    @SuppressWarnings("unused")
    public VentanaLogin() {
        setTitle("Bienvenido"); // Titulo del panel principal
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 600);
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

        // Panel de inicio de sesión con bordes redondeados
        JPanel panelLogin = new JPanel() {
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
        panelLogin.setLayout(null);
        int panelWidth = 300, panelHeight = 380;
        panelLogin.setOpaque(false); // Transparente para preservar la apariencia

        // Añadir componentes al panelLogin
        JLabel etiquetaTitulo = new JLabel("Inicio de sesión");
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        etiquetaTitulo.setBounds(45, 20, 400, 50);
        etiquetaTitulo.setForeground(new Color(51, 51, 51));

        // Usuario
        JLabel etiquetaUsuario = new JLabel("Usuario");
        etiquetaUsuario.setBounds(60, 80, 240, 25);

        // Imagen de usuario
        ImageIcon iconoUsuario = new ImageIcon("Vista\\imagenes\\Usuario.png");
        Image imgUsuario = iconoUsuario.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel imagenUsuario = new JLabel(new ImageIcon(imgUsuario));
        imagenUsuario.setBounds(30, 115, 20, 20);

        campoUsuario = new JTextField();
        campoUsuario.setBounds(60, 110, 210, 30);
        estilizarCampoTexto(campoUsuario);

        // Contraseña
        JLabel etiquetaContrasena = new JLabel("Contraseña");
        etiquetaContrasena.setBounds(60, 160, 240, 25);

        // Imagen de contraseña
        ImageIcon iconoContrasena = new ImageIcon("Vista\\imagenes\\Contraseña.png");
        Image imgContrasena = iconoContrasena.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel imagenContrasena = new JLabel(new ImageIcon(imgContrasena));
        imagenContrasena.setBounds(30, 195, 20, 20);

        campoContrasena = new JPasswordField();
        campoContrasena.setBounds(60, 190, 210, 30);
        estilizarCampoTexto(campoContrasena);

        // Botón de inicio de sesión
        botonLogin = new JButton("Iniciar sesión");
        botonLogin.setBounds(30, 250, 240, 35);
        estilizarBoton(botonLogin);

        // Mensaje de error
        etiquetaMensaje = new JLabel("");
        etiquetaMensaje.setBounds(30, 300, 240, 25);
        etiquetaMensaje.setForeground(Color.RED);
        etiquetaMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        // Añadir componentes al panel
        panelLogin.add(etiquetaTitulo);
        panelLogin.add(etiquetaUsuario);
        panelLogin.add(imagenUsuario);
        panelLogin.add(campoUsuario);
        panelLogin.add(etiquetaContrasena);
        panelLogin.add(imagenContrasena);
        panelLogin.add(campoContrasena);
        panelLogin.add(botonLogin);
        panelLogin.add(etiquetaMensaje);

        panelPrincipal.add(panelLogin);
        add(panelPrincipal);

        // Centrado inicial
        centrarPanelLogin(panelLogin, panelPrincipal, panelWidth, panelHeight);

        // Añadir un listener de cambio de tamaño
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                centrarPanelLogin(panelLogin, panelPrincipal, panelWidth, panelHeight);
            }
        });

        // Acciones de inicio de sesión

        
       
        KeyAdapter enterKeyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    //realizarLogin();
                }
            }
        };
        campoUsuario.addKeyListener(enterKeyListener);
        campoContrasena.addKeyListener(enterKeyListener);

    }

    public void funcion_btn_login(ActionListener listener) {
        botonLogin.addActionListener(listener);
    }

    private void centrarPanelLogin(JPanel panelLogin, JPanel panelPrincipal, int panelWidth, int panelHeight) {
        int x = (panelPrincipal.getWidth() - panelWidth) / 2;
        int y = (panelPrincipal.getHeight() - panelHeight) / 2;
        panelLogin.setBounds(x, y, panelWidth, panelHeight);
    }

    private void estilizarCampoTexto(JTextField campoTexto) {
        campoTexto.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(66, 139, 202)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        campoTexto.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    private void estilizarBoton(JButton boton) {
        boton.setBackground(new Color(0, 128, 128)); // Verde azul oscuro
        boton.setForeground(Color.WHITE); // Letra blanca
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        boton.setFont(new Font("Arial", Font.BOLD, 16)); // Letra gruesa

        boton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(new Color(0, 100, 100)); // Verde azul oscuro más claro
            }

            public void mouseExited(MouseEvent e) {
                boton.setBackground(new Color(0, 128, 128)); // Verde azul oscuro
            }
        });
    }



}