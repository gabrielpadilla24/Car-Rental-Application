package aplicacion_cliente;

import javax.swing.*;
import java.awt.*;

public class AutenticarUsuario {

    public static JPanel getPanelAutenticacion() {
        JPanel panelAutenticacion = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);

        JLabel labelTitulo = new JLabel("Autenticación de Usuario");
        gbc.anchor = GridBagConstraints.CENTER;
        panelAutenticacion.add(labelTitulo, gbc);

        JLabel labelLogin = new JLabel("Login:");
        JTextField campoLogin = new JTextField(20);
        panelAutenticacion.add(labelLogin, gbc);
        panelAutenticacion.add(campoLogin, gbc);

        JLabel labelPassword = new JLabel("Password:");
        JPasswordField campoPassword = new JPasswordField(20);
        panelAutenticacion.add(labelPassword, gbc);
        panelAutenticacion.add(campoPassword, gbc);

        // Panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        JButton botonAutenticar = new JButton("Autenticar");
        botonAutenticar.addActionListener(e -> {
        	String login = campoLogin.getText();
            String password = new String(campoPassword.getPassword());
            if (ClienteUtils.autenticarUsuario(login, password)) {
                // Si el usuario existe, cambiar al panel de opciones
                ClienteApp.cambiarPanel(InterfazCliente.getPanelOpciones());
            } else {
                JOptionPane.showMessageDialog(panelAutenticacion, "Login fallido. Por favor, regístrese.");
            }        });
        buttonPanel.add(botonAutenticar);

        JButton botonRegistrarse = new JButton("Registrarse");
        botonRegistrarse.addActionListener(e -> ClienteApp.cambiarPanel(RegistrarUsuario.getPanelRegistro()));
        buttonPanel.add(botonRegistrarse);

        // Añadir el panel de botones al panel de autenticación
        gbc.fill = GridBagConstraints.NONE;
        panelAutenticacion.add(buttonPanel, gbc);

        return panelAutenticacion;
    }
}


