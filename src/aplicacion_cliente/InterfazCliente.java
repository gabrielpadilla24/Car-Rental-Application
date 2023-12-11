package aplicacion_cliente;

import javax.swing.*;
import java.awt.*;

public class InterfazCliente {

    public static JPanel crearPanelPrincipal() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton btnRegistrar = crearBoton("Registrar Usuario", 70);
        btnRegistrar.addActionListener(e -> ClienteApp.cambiarPanel(RegistrarUsuario.getPanelRegistro()));

        JButton btnAutenticar = crearBoton("Autenticar Usuario", 70);
        btnAutenticar.addActionListener(e -> ClienteApp.cambiarPanel(AutenticarUsuario.getPanelAutenticacion()));

        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnRegistrar);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnAutenticar);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    public static JPanel getPanelOpciones() {
        JPanel panelOpciones = new JPanel();
        panelOpciones.setLayout(new BoxLayout(panelOpciones, BoxLayout.Y_AXIS));

        JButton btnConsultarDisponibilidad = crearBoton("Consultar Disponibilidad", 70);
        btnConsultarDisponibilidad.addActionListener(e -> ClienteApp.cambiarPanel(ConsultarDisponibilidad.getPanelConsulta()));

        JButton btnReservarVehiculo = crearBoton("Reservar Vehículo", 70);
        btnReservarVehiculo.addActionListener(e -> ClienteApp.cambiarPanel(ReservarVehiculo.getPanelFormularioReserva()));

        panelOpciones.add(Box.createRigidArea(new Dimension(0, 10)));
        panelOpciones.add(btnConsultarDisponibilidad);
        panelOpciones.add(Box.createRigidArea(new Dimension(0, 10)));
        panelOpciones.add(btnReservarVehiculo);
        panelOpciones.add(Box.createVerticalGlue());

        return panelOpciones;
    }

    public static JButton crearBoton(String texto, int altura) {
        JButton boton = new JButton(texto);
        boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, altura));
        boton.setPreferredSize(new Dimension(Integer.MAX_VALUE, altura));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        return boton;
    }
}
