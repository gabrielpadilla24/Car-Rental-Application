package aplicacion_cliente;

import javax.swing.*;

public class ClienteApp {

    private static JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> crearYMostrarGUI());
    }

    private static void crearYMostrarGUI() {
        frame = new JFrame("Aplicacion para clientes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        cambiarPanel(InterfazCliente.crearPanelPrincipal());

        frame.setVisible(true);
    }

    public static void cambiarPanel(JPanel nuevoPanel) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(nuevoPanel);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }
}
