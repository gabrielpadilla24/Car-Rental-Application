package Interfaz;

import modelo.Vehiculo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class VehiculosMenu extends JFrame {

    public VehiculosMenu(Map<String, Vehiculo> vehiculoMap) {
        setTitle("Menú de Vehículos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        // Botones
        JButton consultarInventarioButton = new JButton("Consultar Inventario de Vehículos");
        JButton consultarPorSedeButton = new JButton("Consultar Vehículos por Sede");
        JButton consultarPorCategoriaButton = new JButton("Consultar Vehículos por Categoría");
        JButton consultarPorPlacaButton = new JButton("Consultar Vehículo por Placa");
        JButton agregarVehiculoButton = new JButton("Añadir Vehículo al Inventario");
        JButton eliminarVehiculoButton = new JButton("Eliminar Vehículo del Inventario");
        JButton salirButton = new JButton("Salir");

        // Configuración de eventos para los botones
        consultarInventarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vehiculo vehiculo = new Vehiculo("", "", "", "", "", "", "", "");
                vehiculo.showInventarioDialog(vehiculoMap);
            }
        });

        consultarPorSedeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sede = JOptionPane.showInputDialog("Ingrese la sede a consultar:");
                Vehiculo vehiculo = new Vehiculo("", "", "", "", "", "", "", "");
                vehiculo.showCarrosSedeDialog(vehiculoMap, sede);
            }
        });

        consultarPorCategoriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String categoria = JOptionPane.showInputDialog("Ingrese la categoría a consultar:");
                Vehiculo vehiculo = new Vehiculo("", "", "", "", "", "", "", "");
                vehiculo.showCarrosCategoriaDialog(vehiculoMap, categoria);
            }
        });

        consultarPorPlacaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String placa = JOptionPane.showInputDialog("Ingrese la placa del vehículo a consultar:");
                Vehiculo vehiculo = new Vehiculo("", "", "", "", "", "", "", "");
                vehiculo.showCarroEspecificoDialog(vehiculoMap, placa);
            }
        });

        agregarVehiculoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vehiculo nuevoVehiculo = new Vehiculo("", "", "", "", "", "", "", "");
                nuevoVehiculo.crearNuevoVehiculoDialog(vehiculoMap);
            }
        });

        eliminarVehiculoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vehiculo vehiculo = new Vehiculo("", "", "", "", "", "", "", "");
                vehiculo.showInventarioDialog(vehiculoMap);
                String placa = JOptionPane.showInputDialog("Ingrese la placa del vehículo a eliminar:");
                if (vehiculoMap.containsKey(placa)) {
                    vehiculoMap.remove(placa);
                    JOptionPane.showMessageDialog(null, "Vehículo eliminado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró un vehículo con la placa ingresada.");
                }
            }
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1));
        panel.add(consultarInventarioButton);
        panel.add(consultarPorSedeButton);
        panel.add(consultarPorCategoriaButton);
        panel.add(consultarPorPlacaButton);
        panel.add(agregarVehiculoButton);
        panel.add(eliminarVehiculoButton);
        panel.add(salirButton);

        add(panel);

        setLocationRelativeTo(null);
    }
}
