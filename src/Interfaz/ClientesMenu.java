package Interfaz;

import javax.swing.*;
import java.awt.*;

public class ClientesMenu extends JFrame {
    public ClientesMenu() {
        setTitle("Menú Clientes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(400, 300);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Esta es la interfaz del Menú Clientes");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
    }
}
