package Interfaz;

import modelo.Sede;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;

public class SedesMenu extends JFrame {

    private Map<String, Sede> sedeMap;
   
    public SedesMenu(Map<String, Sede> sedeMap) {
        this.sedeMap = sedeMap;

        setTitle("Menú Sedes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton btnNuevaSede = new JButton("Crear Nueva Sede");
        JButton btnConsultarSedes = new JButton("Consultar Sedes");
        JButton btnVerDatosSede = new JButton("Ver Datos de Sede");
        JButton btnEliminarSede = new JButton("Eliminar Sede");
        JButton btnGuardarCambios = new JButton("Guardar Cambios en CSV");
        JButton btnSalir = new JButton("Salir");

        btnNuevaSede.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearNuevaSede();
            }
        });

        btnConsultarSedes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarSedes();
            }
        });

        btnVerDatosSede.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verDatosSede();
            }
        });

        btnEliminarSede.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarSede();
            }
        });

        btnGuardarCambios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //guardarCambios();
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(btnNuevaSede);
        panel.add(btnConsultarSedes);
        panel.add(btnVerDatosSede);
        panel.add(btnEliminarSede);
        panel.add(btnGuardarCambios);
        panel.add(btnSalir);

        add(panel);
    }

    
    private void crearNuevaSede() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la nueva sede:");
        String ubicacion = JOptionPane.showInputDialog("Ingrese la ubicación de la nueva sede:");
        String horarios = JOptionPane.showInputDialog("Ingrese los horarios de atención de la nueva sede:");

        Sede nuevaSede = new Sede(nombre, ubicacion, horarios);
        sedeMap.put(nombre, nuevaSede);

        JOptionPane.showMessageDialog(null, "Nueva sede creada con éxito.");
    }

    public void consultarSedes() {
        StringBuilder sedesInfo = new StringBuilder("Sedes Actuales:\n\n");
        for (Map.Entry<String, Sede> entry : sedeMap.entrySet()) {
            Sede sede = entry.getValue();
            sedesInfo.append("Nombre: ").append(sede.darNombre()).append("\n");
            sedesInfo.append("Ubicación: ").append(sede.darUbicacion()).append("\n");
            sedesInfo.append("Horarios: ").append(sede.darHorario()).append("\n\n");
        }

        JOptionPane.showMessageDialog(null, sedesInfo.toString());
    }

    private void verDatosSede() {
        String nombreSede = JOptionPane.showInputDialog("Ingrese el nombre de la sede:");
        if (sedeMap.containsKey(nombreSede)) {
            Sede sede = sedeMap.get(nombreSede);
            StringBuilder infoSede = new StringBuilder("Datos de la Sede:\n\n");
            infoSede.append("Nombre: ").append(sede.darNombre()).append("\n");
            infoSede.append("Ubicación: ").append(sede.darUbicacion()).append("\n");
            infoSede.append("Horarios: ").append(sede.darHorario()).append("\n");

            JOptionPane.showMessageDialog(null, infoSede.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró la sede.");
        }
    }

    private void eliminarSede() {
        String nombreSede = JOptionPane.showInputDialog("Ingrese el nombre de la sede a eliminar:");
        if (sedeMap.containsKey(nombreSede)) {
            sedeMap.remove(nombreSede);
            JOptionPane.showMessageDialog(null, "Sede eliminada con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró la sede.");
        }
    }

   

    
}
