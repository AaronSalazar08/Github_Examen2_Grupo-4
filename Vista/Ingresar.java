package Vista;

import Modelo.ConectarIngresar;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Ingresar extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Ingresar Mostrar = new Ingresar();
            Mostrar.setVisible(true);
        });
    }

    public Ingresar() { // Método constructor
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
        int panelWidth = 700, panelHeight = 500;
        panelLogin.setOpaque(false); // Transparente para preservar la apariencia

        
        // Etiqueta de hover
        JLabel hoverMensaje = new JLabel("");
        hoverMensaje.setBounds(0, 0, 200, 20);
        hoverMensaje.setForeground(Color.BLUE);
        hoverMensaje.setFont(new Font("Arial", Font.PLAIN, 12));
        hoverMensaje.setVisible(false);
        add(hoverMensaje);

        // Campos de texto
        JTextField TextPrimerNombre = crearMensajes("Ingrese su primer nombre (*)", hoverMensaje, 160, 100);
        JTextField TextSegundoNombre = crearMensajes("Ingrese su segundo nombre", hoverMensaje, 160, 160);
        JTextField TextPrimerApellido = crearMensajes("Ingrese su primer apellido (*)", hoverMensaje, 160, 220);
        JTextField TextSegundoApellido = crearMensajes("Ingrese su segundo apellido (*)", hoverMensaje, 160, 280);
        JTextField TextLogin = crearMensajes("Ingrese su nombre de usuario (*)", hoverMensaje, 510, 100);
        JPasswordField TextClave = crearMensajeContras("Ingrese su contraseña (*)", hoverMensaje, 510, 160);
        JPasswordField TextConfirmarClave = crearMensajeContras("Confirme su contraseña (*)", hoverMensaje, 510, 220);

        // Botones
        JButton Agregar = new JButton("Agregar");
        Agregar.setBounds(520, 380, 150, 35);
        estilizarBoton(Agregar);
        Agregar.setToolTipText("Agregar usuario al sistema");
        Agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] Contra = TextClave.getPassword();
                String Contraseña = new String(Contra);

                char[] ConfirmarContra = TextConfirmarClave.getPassword();
                String ConfirmarContraseña = new String(ConfirmarContra);

                String Nombre1 = TextPrimerNombre.getText();
                String Apellido1 = TextPrimerApellido.getText();
                String Apellido2 = TextSegundoApellido.getText();
                String Usuario = TextLogin.getText();
                String Nombre2 = TextSegundoNombre.getText();

                if (Nombre1.isEmpty() || Apellido1.isEmpty() || Apellido2.isEmpty() || Usuario.isEmpty()
                        || Contraseña.isEmpty() || ConfirmarContraseña.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe de llenar los campos obligatorios (*)", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    if (Contraseña.equals(ConfirmarContraseña)) {
                        
                        ConectarIngresar conexion = new ConectarIngresar();
                        conexion.Conectar(Nombre1, Nombre2, Apellido1, Apellido2, Usuario, Contraseña);

                TextPrimerNombre.setText("");
                 TextPrimerApellido.setText("");
                TextSegundoApellido.setText("");
                TextLogin.setText("");
                TextSegundoNombre.setText("");
                TextConfirmarClave.setText("");
                TextClave.setText("");

                    } else {
                        JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

            }

        });

        

        JButton Regresar = new JButton("");
        Regresar.setBounds(-30, 450, 200, 35);
        estilizarBoton2(Regresar);
        Regresar.setOpaque(false);
        Regresar.setToolTipText("Volver al menu principal");
        Regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mantenimiento llamar = new Mantenimiento();
                llamar.setVisible(true);
                dispose();
            }

        });

        ImageIcon iconoRegresar = new ImageIcon("Vista//imagenes//Regre.png");
        Image imagenEscalada = iconoRegresar.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
        Regresar.setIcon(iconoEscalado);

        ImageIcon iconoGuardar = new ImageIcon("Vista//imagenes//Guardar.png");
        Image imagenEscalada2 = iconoGuardar.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado2 = new ImageIcon(imagenEscalada2);
        Agregar.setIcon(iconoEscalado2);

        // Etiquetas fijas
        JLabel PrimerNombre = new JLabel("Primer nombre");
        PrimerNombre.setBounds(40, 95, 150, 40);
        PrimerNombre.setForeground(Color.black);
        panelLogin.add(PrimerNombre);

        JLabel SegundoNombre = new JLabel("Segundo nombre");
        SegundoNombre.setBounds(40, 155, 150, 40);
        SegundoNombre.setForeground(Color.black);
        panelLogin.add(SegundoNombre);

        JLabel PrimerApellido = new JLabel("Primer apellido");
        PrimerApellido.setBounds(40, 215, 150, 40);
        PrimerApellido.setForeground(Color.black);
        panelLogin.add(PrimerApellido);

        JLabel SegundoApellido = new JLabel("Segundo apellido");
        SegundoApellido.setBounds(40, 275, 150, 40);
        SegundoApellido.setForeground(Color.black);
        panelLogin.add(SegundoApellido);

        JLabel Login = new JLabel("Nombre usuario");
        Login.setBounds(375, 95, 150, 40);
        Login.setForeground(Color.black);
        panelLogin.add(Login);

        JLabel Clave = new JLabel("Contraseña");
        Clave.setBounds(375, 155, 150, 40);
        Clave.setForeground(Color.black);
        panelLogin.add(Clave);

        JLabel ConfirmarClave = new JLabel("Confirmar contraseña");
        ConfirmarClave.setBounds(375, 215, 150, 40);
        ConfirmarClave.setForeground(Color.black);
        panelLogin.add(ConfirmarClave);

        // Agregar componentes al panel
        panelLogin.add(Agregar);
        panelLogin.add(Regresar);
        panelLogin.add(TextPrimerNombre);
        panelLogin.add(TextSegundoNombre);
        panelLogin.add(TextPrimerApellido);
        panelLogin.add(TextSegundoApellido);
        panelLogin.add(TextLogin);
        panelLogin.add(TextClave);
        panelLogin.add(TextConfirmarClave);

        panelPrincipal.add(panelLogin);
        add(panelPrincipal);

        centrarPanelLogin(panelLogin, panelPrincipal, panelWidth, panelHeight);
       
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                centrarPanelLogin(panelLogin, panelPrincipal, panelWidth, panelHeight);
            }
        });


    } // Fin del constructor

    private JTextField crearMensajes(String hoverText, JLabel hoverLabel, int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 180, 30);


        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hoverLabel.setText(hoverText);
                hoverLabel.setVisible(true);
                hoverLabel.setForeground(new Color(17, 183, 255));
                hoverLabel.setLocation(x + 40, y + 60);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hoverLabel.setVisible(false);
            }
        });

        return textField;
    }

    private JPasswordField crearMensajeContras(String hoverText, JLabel hoverLabel, int x, int y) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(x, y, 180, 30);

        passwordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hoverLabel.setText(hoverText);
                hoverLabel.setForeground(new Color(17, 183, 255));
                hoverLabel.setVisible(true);
                hoverLabel.setLocation(x + 40, y + 60);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hoverLabel.setVisible(false);
            }
        });

        return passwordField;
    }

    private void centrarPanelLogin(JPanel panelLogin, JPanel panelPrincipal, int panelWidth, int panelHeight) {
        int x = (panelPrincipal.getWidth() - panelWidth) / 2;
        int y = (panelPrincipal.getHeight() - panelHeight) / 2;
        panelLogin.setBounds(x, y, panelWidth, panelHeight);
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

    private void estilizarBoton2(JButton boton) {
        boton.setBackground(new Color(203, 32, 32));
        boton.setForeground(Color.WHITE); // Letra blanca
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        boton.setFont(new Font("Arial", Font.BOLD, 16)); // Letra gruesa

        boton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(new Color(228, 83, 83));
            }

            public void mouseExited(MouseEvent e) {
                boton.setBackground(new Color(203, 32, 32)); 
            }
        });
    }
}
