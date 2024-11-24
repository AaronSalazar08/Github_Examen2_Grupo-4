package Vista;
import javax.swing.*;

import Controlador.ResultadoLogin;
import Modelo.ConexionBaseDatos;

import java.awt.*;
import java.awt.event.*;

public class VentanaLogin extends JFrame {
    private JTextField campoUsuario;
    private JPasswordField campoContrasena;
    private JLabel etiquetaMensaje;
    private JButton botonLogin; // Asegúrate de declarar el botón aquí.

    @SuppressWarnings("unused")
    public VentanaLogin() {
        setTitle("Inicio de sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
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
        JPanel panelLogin = new JPanel();
        panelLogin.setLayout(null);
        panelLogin.setBounds(50, 50, 300, 380);
        panelLogin.setBackground(new Color(255, 255, 255, 240));

        // Título
        JLabel etiquetaTitulo = new JLabel("Inicio de sesión");
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        etiquetaTitulo.setBounds(40, 20, 400, 50);
        etiquetaTitulo.setForeground(new Color(51, 51, 51));

        // Usuario
        JLabel etiquetaUsuario = new JLabel("Usuario");
        etiquetaUsuario.setBounds(30, 80, 240, 25);

        // Imagen junto al campo de "Usuario"
        JLabel imagenUsuario = new JLabel(new ImageIcon("Vista/imagenes/Usuario.png")); // Ajusta la ruta de la imagen
        imagenUsuario.setBounds(-5, 105, 38, 38); // Ajusta el tamaño y la posición de la imagen

        campoUsuario = new JTextField();
        campoUsuario.setBounds(30, 110, 240, 30);
        estilizarCampoTexto(campoUsuario);

        // Contraseña
        JLabel etiquetaContrasena = new JLabel("Contraseña");
        etiquetaContrasena.setBounds(30, 160, 240, 25);

        // Imagen junto al campo de "Contraseña"
        JLabel imagenContrasena = new JLabel(new ImageIcon("Vista/imagenes/Contraseña.png")); // Ajusta la ruta de la imagen
        imagenContrasena.setBounds(-4, 185, 35, 35); // Ajusta el tamaño y la posición de la imagen

        campoContrasena = new JPasswordField();
        campoContrasena.setBounds(30, 190, 240, 30);
        estilizarCampoTexto(campoContrasena);

        // Botón de inicio de sesión
        botonLogin = new JButton("Iniciar sesión"); // Asegúrate de que la variable esté declarada aquí
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
        panelLogin.add(imagenUsuario); // Añadir la imagen
        panelLogin.add(campoUsuario);
        panelLogin.add(etiquetaContrasena);
        panelLogin.add(imagenContrasena); // Añadir la imagen
        panelLogin.add(campoContrasena);
        panelLogin.add(botonLogin); // Añadir el botón
        panelLogin.add(etiquetaMensaje);

        panelPrincipal.add(panelLogin);
        add(panelPrincipal);

        // Acciones de inicio de sesión
        botonLogin.addActionListener(e -> realizarLogin());

        KeyAdapter enterKeyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    realizarLogin();
                }
            }
        };
        campoUsuario.addKeyListener(enterKeyListener);
        campoContrasena.addKeyListener(enterKeyListener);
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

    private void realizarLogin() {
        String usuario = campoUsuario.getText();
        String contrasena = new String(campoContrasena.getPassword());

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            mostrarError("Por favor complete todos los campos");
            return;
        }

        ResultadoLogin resultado = ConexionBaseDatos.validarUsuario(usuario, contrasena);
        if (resultado.esExitoso()) {
            dispose(); // Cierra la ventana de inicio de sesión
            mostrarMenuAplicacion();
        } else {
            mostrarError(resultado.obtenerMensaje());
        }
    }

    private void mostrarError(String mensaje) {
        etiquetaMensaje.setText(mensaje);
        etiquetaMensaje.setForeground(Color.RED);
    }

    private void mostrarMenuAplicacion() {
        JFrame ventanaPrincipal = new JFrame("Menú Aplicación");
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setSize(800, 600);
        ventanaPrincipal.setLocationRelativeTo(null);
        ventanaPrincipal.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaLogin ventana = new VentanaLogin();
            ventana.setVisible(true);
        });
    }
}
