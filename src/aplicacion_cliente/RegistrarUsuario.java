package aplicacion_cliente;

import javax.swing.*;
import java.awt.*;

public class RegistrarUsuario {

    public static JPanel getPanelRegistro() {
        JPanel panelRegistro = new JPanel();
        panelRegistro.setLayout(new BoxLayout(panelRegistro, BoxLayout.Y_AXIS));

        // Crear el panel para el título
        JPanel panelTitulo = new JPanel();
        JLabel labelTitulo = new JLabel("Registrar Nuevo Usuario");
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTitulo.add(labelTitulo);

        // Añadir el panel del título al panel principal
        panelRegistro.add(panelTitulo);

        JLabel labelNombre = new JLabel("Nombre:");
        JTextField campoNombre = new JTextField(20);
        campoNombre.setToolTipText("Ingrese su nombre completo");

        JLabel labelNumContacto = new JLabel("Número de Contacto:");
        JTextField campoNumContacto = new JTextField(20);
        campoNumContacto.setToolTipText("Ingrese su número de contacto");

        JLabel labelFechaNacimiento = new JLabel("Fecha de Nacimiento (dd/mm/aaaa):");
        JTextField campoFechaNacimiento = new JTextField(20);
        campoFechaNacimiento.setToolTipText("Ingrese su fecha de nacimiento");

        JLabel labelNacionalidad = new JLabel("Nacionalidad:");
        JTextField campoNacionalidad = new JTextField(20);
        campoNacionalidad.setToolTipText("Ingrese su nacionalidad");

        JLabel labelDocumentoIdentidad = new JLabel("Documento de Identidad:");
        JTextField campoDocumentoIdentidad = new JTextField(20);
        campoDocumentoIdentidad.setToolTipText("Ingrese su documento de identidad");

        JLabel labelLogin = new JLabel("Login:");
        JTextField campoLogin = new JTextField(20);
        campoLogin.setToolTipText("Cree un nombre de usuario para ingresar al sistema");

        JLabel labelPassword = new JLabel("Password:");
        JPasswordField campoPassword = new JPasswordField(20);
        campoPassword.setToolTipText("Cree una contraseña");

        JButton botonRegistrar = new JButton("Registrar");
        botonRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonRegistrar.addActionListener(e -> {
            String nombre = campoNombre.getText();
            String numContacto = campoNumContacto.getText();
            String fechaNacimiento = campoFechaNacimiento.getText();
            String nacionalidad = campoNacionalidad.getText();
            String documentoIdentidad = campoDocumentoIdentidad.getText();
            String login = campoLogin.getText();
            String password = new String(campoPassword.getPassword());

            // Llamada a la clase de utilidad para guardar los datos en CSV
            ClienteUtils.guardarClienteEnCSV(new String[]{nombre, numContacto, fechaNacimiento, nacionalidad, documentoIdentidad, login, password});

            // Opcional: mostrar un mensaje de confirmación
            JOptionPane.showMessageDialog(panelRegistro, "Cliente registrado con éxito.");
        });

        JButton botonSalir = new JButton("Salir");
        botonSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonSalir.addActionListener(e -> ClienteApp.cambiarPanel(InterfazCliente.crearPanelPrincipal()));

        // Panel para los botones alineados al centro
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
        panelBotones.add(Box.createHorizontalGlue());
        panelBotones.add(botonRegistrar);
        panelBotones.add(Box.createRigidArea(new Dimension(10, 0))); // Espacio entre los botones
        panelBotones.add(botonSalir);

        // Añadir componentes al panel principal
        panelRegistro.add(labelNombre);
        panelRegistro.add(campoNombre);
        panelRegistro.add(labelNumContacto);
        panelRegistro.add(campoNumContacto);
        panelRegistro.add(labelFechaNacimiento);
        panelRegistro.add(campoFechaNacimiento);
        panelRegistro.add(labelNacionalidad);
        panelRegistro.add(campoNacionalidad);
        panelRegistro.add(labelDocumentoIdentidad);
        panelRegistro.add(campoDocumentoIdentidad);
        panelRegistro.add(labelLogin);
        panelRegistro.add(campoLogin);
        panelRegistro.add(labelPassword);
        panelRegistro.add(campoPassword);
        panelRegistro.add(panelBotones); // Agregar el panel de botones

        return panelRegistro;
    }

    // Otros métodos si es necesario
}
