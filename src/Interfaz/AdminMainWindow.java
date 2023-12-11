package Interfaz;

import javax.swing.*;

import modelo.Sede;
import modelo.Vehiculo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class AdminMainWindow extends JFrame {

    public AdminMainWindow(Map<String, Sede> sedeMap, Map<String, String> empleadoMap, Map<String, Vehiculo> carroMap) {
        
    	//setVisible(true);
        setTitle("¡Bienvenido a la sección de administrador!");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        //setLocationRelativeTo(null);

        // Título
        JLabel titleLabel = new JLabel("¡Bienvenido a la sección de administrador!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        titleLabel.setOpaque(true);  // Permite establecer el fondo
        titleLabel.setBackground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

        // Subtítulo
        JLabel subtitleLabel = new JLabel("Ingrese al menú que desea acceder");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 20, 0));
        subtitleLabel.setOpaque(true);  // Permite establecer el fondo
        subtitleLabel.setBackground(Color.WHITE);
        add(subtitleLabel, BorderLayout.CENTER);

        // Panel para los botones
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 30, 50));
        buttonPanel.setBackground(Color.WHITE);

        // Botones para los menús
        JButton clientesButton = createMenuButton("Menú Clientes", new ClientesMenu());
        JButton sedesButton = createMenuButton("Menú Sedes", new SedesMenu(sedeMap));
        JButton empleadosButton = createMenuButton("Menú Empleados", new EmpleadosMenu(empleadoMap));
        JButton vehiculosButton = createMenuButton("Menú Vehículos", new VehiculosMenu(carroMap));

        // Botón de salir
        JButton salirButton = new JButton("Salir");
        salirButton.setFont(new Font("Arial", Font.BOLD, 14));
        salirButton.setBackground(Color.RED);
        salirButton.setForeground(Color.WHITE);
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Configurar los botones
        //configureButton(clientesButton);
        configureButton(sedesButton);
        configureButton(empleadosButton);
        configureButton(vehiculosButton);
        configureButton(salirButton);

        // Agregar botones al panel
        //buttonPanel.add(clientesButton);
        buttonPanel.add(sedesButton);
        buttonPanel.add(empleadosButton);
        buttonPanel.add(vehiculosButton);

        // Agregar botón de salir al panel
        buttonPanel.add(salirButton);

        // Agregar panel de botones al frame
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JButton createMenuButton(String buttonText, JFrame menu) {
        JButton button = new JButton(buttonText);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(true);
            }
        });
        return button;
    }

    private void configureButton(JButton button) {
        button.setBackground(Color.BLUE);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(150, 40));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

   
}



