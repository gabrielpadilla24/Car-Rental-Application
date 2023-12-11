package Interfaz;

import modelo.Empleado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class EmpleadosMenu extends JFrame {

    public EmpleadosMenu(Map<String, String> empleadoMap) {
        setTitle("Menú de Empleados");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

     
        // Componentes para crear empleado
        JButton crearButton = new JButton("Crear Empleado");
        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Empleado nuevoEmpleado = new Empleado("", ""); // Puedes ajustar los parámetros según tu lógica
                nuevoEmpleado.NuevoEmpleado(empleadoMap);
            }
        });
        
        // Componentes para modificar empleado
        JButton modificarButton = new JButton("Modificar Empleado");
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreEmpleado = JOptionPane.showInputDialog("Ingrese el nombre del empleado a modificar");
                Empleado empleado = new Empleado("", ""); // Puedes ajustar los parámetros según tu lógica
                empleado.ModificarEmpleado(empleadoMap, nombreEmpleado);
            }
        });

        // Componentes para ver todos los empleados
        JButton verTodosButton = new JButton("Ver Todos los Empleados");
        verTodosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Empleado empleado = new Empleado("", ""); // Puedes ajustar los parámetros según tu lógica
                empleado.showEmpleados(empleadoMap);
            }
        });

        // Componentes para eliminar empleado
        JButton eliminarButton = new JButton("Eliminar Empleado");
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Empleado empleado = new Empleado("", ""); // Puedes ajustar los parámetros según tu lógica
                empleado.EliminarEmpleado(empleadoMap);
            }
        });

        // Componentes para buscar empleado
        JButton buscarButton = new JButton("Buscar Empleado");
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Empleado empleado = new Empleado("", ""); // Puedes ajustar los parámetros según tu lógica
                empleado.BuscarEmpleado(empleadoMap);
            }
        });

        // Botón de salir
        JButton salirButton = new JButton("Salir");
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
            }
        });

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        panel.add(verTodosButton);
        panel.add(modificarButton);
        panel.add(crearButton);
        panel.add(eliminarButton);
        panel.add(buscarButton);
        panel.add(salirButton);

        add(panel);

        setLocationRelativeTo(null);
    }
}