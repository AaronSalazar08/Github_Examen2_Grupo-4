import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login2 {

    // Método para conectar a la base de datos
    private static Connection conectarBD() {
        String url = "jdbc:mysql://localhost:3306/ex2ibd"; // Cambia esto según tu base de datos
        String usuario = "root"; // Cambia por tu usuario
        String contraseña = "-------"; // Cambia por tu contraseña

        try {
            return DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Método para validar los datos
    private static boolean validarCredenciales(String login, String clave) {
        String consulta = "SELECT COUNT(*) FROM usuarios WHERE login = ? AND clave = ?";
        try (Connection conexion = conectarBD();
             PreparedStatement ps = conexion.prepareStatement(consulta)) {

            ps.setString(1, login);  // Ahora validamos por 'login'
            ps.setString(2, clave);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;  // Si se encuentra un registro con ese 'login' y 'clave'
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al validar las credenciales: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static void main(String[] args) {
        // Crear ventana principal
        JFrame ventana = new JFrame("Inicio de sesion");
        ventana.setSize(400, 420);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(null);
        ventana.setResizable(false); // Ventana de tamaño fijo

        // --------------------- imagen de fondo--------------------------------------------
        ImageIcon fondoImagen = new ImageIcon("imagenes/kiznaiverplumblossoms.png");
        JLabel fondo = new JLabel(new ImageIcon(fondoImagen.getImage().getScaledInstance(400, 420, Image.SCALE_SMOOTH)));
        fondo.setBounds(0, 0, 400, 420);
        ventana.setContentPane(fondo);
        ventana.setLayout(null); // Asegurar posicionamiento manual

        // Etiqueta y campo para el login
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(50, 50, 300, 20);
        lblUsuario.setForeground(Color.BLACK); // Texto Negro
        ventana.add(lblUsuario);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(50, 80, 300, 30);
        ventana.add(txtUsuario);

        // Etiqueta y campo para la contraseña
        JLabel lblClave = new JLabel("Contraseña:");
        lblClave.setBounds(50, 140, 300, 20);
        lblClave.setForeground(Color.BLACK); // Texto Negro
        ventana.add(lblClave);

        JPasswordField txtClave = new JPasswordField();
        txtClave.setBounds(50, 170, 300, 30);
        ventana.add(txtClave);

        // Botón de iniciar sesión
        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBounds(125, 250, 150, 40);
        btnLogin.setBackground(new Color(60, 60, 60)); // Fondo oscuro del botón
        btnLogin.setForeground(Color.WHITE); // Texto blanco en el botón
        btnLogin.setFocusPainted(false);
        ventana.add(btnLogin);

        // Acción del botón
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = txtUsuario.getText();  // Ahora leemos el 'login'
                String clave = new String(txtClave.getPassword());

                if (validarCredenciales(login, clave)) {
                    JOptionPane.showMessageDialog(ventana, "Ingreso exitoso.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(ventana, "Los datos son incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Mostrar la ventana
        ventana.setLocationRelativeTo(null); // Centrar la ventana
        ventana.setVisible(true);
    }
}
