package Interfaz;

import javax.swing.*;

import modelo.Sede;
import modelo.Vehiculo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Map;

public class AdminLoginWindow {
    public AdminLoginWindow(Map<String, Sede> sedeMap, Map<String, String> empleadoMap, Map<String, Vehiculo> carroMap) {
        JFrame frame = new JFrame("Inicio de Sesión - Administrador");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.WHITE);

        // Título
        JLabel titleLabel = new JLabel("¡Bienvenido a la sección de administrador!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel loginPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));
        loginPanel.setBackground(Color.WHITE);

        JTextField userField = createPlaceholderTextField("Usuario");
        JPasswordField passwordField = createPlaceholderPasswordField("Contraseña");

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(Color.GREEN);
        loginButton.setForeground(Color.WHITE);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtiene el usuario y la contraseña ingresados
                String usuarioIngresado = userField.getText();
                String contraseñaIngresada = new String(passwordField.getPassword());

                // Verifica la autenticación (en este caso, usuario de prueba)
                if (usuarioIngresado.equals("1") && contraseñaIngresada.equals("1")) {
                    // Autenticación exitosa
                	
                    frame.dispose();  // Cierra la ventana de inicio de sesión
                    new AdminMainWindow(sedeMap,empleadoMap,carroMap).setVisible(true);  // Abre la nueva interfaz del administrador
                    
                } else {
                    // Autenticación fallida
                    JOptionPane.showMessageDialog(frame, "Usuario o contraseña incorrectos", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        loginPanel.add(userField);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel()); // Espaciador
        loginPanel.add(loginButton);

        frame.add(loginPanel, BorderLayout.CENTER);

        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        frame.setVisible(true);
    }

    private JTextField createPlaceholderTextField(String placeholder) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setForeground(Color.GRAY);
        textField.setText(placeholder);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
        return textField;
    }

    private JPasswordField createPlaceholderPasswordField(String placeholder) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setForeground(Color.GRAY);
        passwordField.setEchoChar((char) 0);
        passwordField.setText(placeholder);
        passwordField.setHorizontalAlignment(JPasswordField.CENTER);
        passwordField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals(placeholder)) {
                    passwordField.setText("");
                    passwordField.setEchoChar('*');
                    passwordField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setEchoChar((char) 0);
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setText(placeholder);
                }
            }
        });
        return passwordField;
    }
}
