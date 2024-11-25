package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
        setBounds(0, 0, 800, 600);
        setBackground(new Color(255, 255, 255));
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Etiqueta de hover
        JLabel hoverMensaje = new JLabel("");
        hoverMensaje.setBounds(0, 0, 200, 20);
        hoverMensaje.setForeground(Color.BLUE);
        hoverMensaje.setFont(new Font("Arial", Font.PLAIN, 12));
        hoverMensaje.setVisible(false);
        add(hoverMensaje);

        // Campos de texto
        JTextField TextPrimerNombre = crearMensajes("Ingrese su primer nombre (*)", hoverMensaje, 230, 100);
        JTextField TextSegundoNombre = crearMensajes("Ingrese su segundo nombre", hoverMensaje, 230, 160);
        JTextField TextPrimerApellido = crearMensajes("Ingrese su primer apellido (*)", hoverMensaje, 230, 220);
        JTextField TextSegundoApellido = crearMensajes("Ingrese su segundo apellido (*)", hoverMensaje, 230, 280);
        JTextField TextLogin = crearMensajes("Ingrese su nombre de usuario (*)", hoverMensaje, 580, 135);
        JPasswordField TextClave = crearMensajeContras("Ingrese su contraseña (*)", hoverMensaje, 580, 195);
        JPasswordField TextConfirmarClave = crearMensajeContras("Confirme su contraseña (*)", hoverMensaje, 580, 255);

        // Botones
        JButton Agregar = new JButton("Agregar");
        Agregar.setBounds(150, 420, 150, 45);
        Agregar.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
        Agregar.setBackground(new Color(160, 82, 45));
        Agregar.setForeground(new Color(61, 43, 31));
        Agregar.setBorder(BorderFactory.createLineBorder(new Color(92, 51, 23), 2));
        Agregar.setFocusPainted(false);
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

                if(Nombre1.isEmpty() || Apellido1.isEmpty() || Apellido2.isEmpty() || Usuario.isEmpty() || Contraseña.isEmpty() || ConfirmarContraseña.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Debe de llenar los campos obligatorios (*)", "Error",
                    JOptionPane.ERROR_MESSAGE);
            }else{
                if (Contraseña.equals(ConfirmarContraseña)) {
                    JOptionPane.showMessageDialog(null, "Las contraseñas coinciden", "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);
//Aqui debe de ir la logica de conectar a la base de datos

                } else {
                    JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            }

        });

        JButton Regresar = new JButton("Regresar");
        Regresar.setBounds(470, 420, 150, 45);
        Regresar.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
        Regresar.setBackground(new Color(160, 82, 45));
        Regresar.setForeground(new Color(61, 43, 31));
        Regresar.setBorder(BorderFactory.createLineBorder(new Color(92, 51, 23), 2));
        Regresar.setFocusPainted(false);

        // Etiquetas fijas
        JLabel PrimerNombre = new JLabel("Primer nombre");
        PrimerNombre.setBounds(80, 100, 150, 40);
        PrimerNombre.setForeground(Color.black);
        PrimerNombre.setFont(new Font("Agency FB", Font.BOLD, 20));
        add(PrimerNombre);

        JLabel SegundoNombre = new JLabel("Segundo nombre");
        SegundoNombre.setBounds(80, 160, 150, 40);
        SegundoNombre.setForeground(Color.black);
        SegundoNombre.setFont(new Font("Agency FB", Font.BOLD, 20));
        add(SegundoNombre);

        JLabel PrimerApellido = new JLabel("Primer apellido");
        PrimerApellido.setBounds(80, 220, 150, 40);
        PrimerApellido.setForeground(Color.black);
        PrimerApellido.setFont(new Font("Agency FB", Font.BOLD, 20));
        add(PrimerApellido);

        JLabel SegundoApellido = new JLabel("Segundo apellido");
        SegundoApellido.setBounds(80, 280, 150, 40);
        SegundoApellido.setForeground(Color.black);
        SegundoApellido.setFont(new Font("Agency FB", Font.BOLD, 20));
        add(SegundoApellido);

        JLabel Login = new JLabel("Nombre usuario");
        Login.setBounds(445, 135, 150, 40);
        Login.setForeground(Color.black);
        Login.setFont(new Font("Agency FB", Font.BOLD, 20));
        add(Login);

        JLabel Clave = new JLabel("Contraseña");
        Clave.setBounds(445, 195, 150, 40);
        Clave.setForeground(Color.black);
        Clave.setFont(new Font("Agency FB", Font.BOLD, 20));
        add(Clave);

        JLabel ConfirmarClave = new JLabel("Confirmar contraseña");
        ConfirmarClave.setBounds(445, 255, 150, 40);
        ConfirmarClave.setForeground(Color.black);
        ConfirmarClave.setFont(new Font("Agency FB", Font.BOLD, 20));
        add(ConfirmarClave);

        // Agregar componentes al panel
        add(Agregar);
        add(Regresar);
        add(TextPrimerNombre);
        add(TextSegundoNombre);
        add(TextPrimerApellido);
        add(TextSegundoApellido);
        add(TextLogin);
        add(TextClave);
        add(TextConfirmarClave);
    } // Fin del constructor

    private JTextField crearMensajes(String hoverText, JLabel hoverLabel, int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 150, 40);
        textField.setBackground(new Color(101, 237, 225));
        textField.setForeground(Color.BLACK);
        textField.setBorder(null);

        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hoverLabel.setText(hoverText);
                hoverLabel.setVisible(true);
                hoverLabel.setForeground(new Color(17, 183, 255));
                hoverLabel.setLocation(x, y + 40);
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
        passwordField.setBounds(x, y, 150, 40);
        passwordField.setBackground(new Color(101, 237, 225));
        passwordField.setForeground(Color.BLACK);
        passwordField.setBorder(null);

        passwordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hoverLabel.setText(hoverText);
                hoverLabel.setForeground(new Color(17, 183, 255));
                hoverLabel.setVisible(true);
                hoverLabel.setLocation(x, y + 40);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hoverLabel.setVisible(false);
            }
        });

        return passwordField;
    }
}
