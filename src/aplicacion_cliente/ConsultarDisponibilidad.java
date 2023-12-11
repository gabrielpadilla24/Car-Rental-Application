package aplicacion_cliente;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsultarDisponibilidad {

    public static JPanel getPanelConsulta() {
        JPanel panelConsulta = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel labelTitulo = new JLabel("Consultar Disponibilidad de Vehículos");
        panelConsulta.add(labelTitulo, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        JLabel labelSede = new JLabel("Sede:");
        panelConsulta.add(labelSede, gbc);

        gbc.gridx = 1;
        JComboBox<String> comboSedes = new JComboBox<>();
        List<String> sedes = leerSedes();
        for (String sede : sedes) {
            comboSedes.addItem(sede);
        }
        panelConsulta.add(comboSedes, gbc);

        // Selector de fecha de inicio
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel labelFechaInicio = new JLabel("Fecha de inicio:");
        panelConsulta.add(labelFechaInicio, gbc);

        gbc.gridx = 1;
        JSpinner spinnerFechaInicio = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editorFechaInicio = new JSpinner.DateEditor(spinnerFechaInicio, "dd/MM/yyyy");
        spinnerFechaInicio.setEditor(editorFechaInicio);
        panelConsulta.add(spinnerFechaInicio, gbc);

        // Selector de fecha de fin
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel labelFechaFin = new JLabel("Fecha de fin:");
        panelConsulta.add(labelFechaFin, gbc);

        gbc.gridx = 1;
        JSpinner spinnerFechaFin = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editorFechaFin = new JSpinner.DateEditor(spinnerFechaFin, "dd/MM/yyyy");
        spinnerFechaFin.setEditor(editorFechaFin);
        panelConsulta.add(spinnerFechaFin, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton botonConfirmar = new JButton("Confirmar");
        botonConfirmar.addActionListener(e -> {
            String sedeSeleccionada = (String) comboSedes.getSelectedItem();
            Date fechaInicio = (Date) spinnerFechaInicio.getValue();
            Date fechaFin = (Date) spinnerFechaFin.getValue();
            mostrarCarrosEnSede(sedeSeleccionada, fechaInicio, fechaFin, panelConsulta);
        });
        panelConsulta.add(botonConfirmar, gbc);
        
        // Botón para volver al menú anterior
        gbc.gridy++;
        JButton botonVolver = new JButton("Volver");
        botonVolver.addActionListener(e -> ClienteApp.cambiarPanel(InterfazCliente.getPanelOpciones()));
        panelConsulta.add(botonVolver, gbc);

        return panelConsulta;
    }

    private static List<String> leerSedes() {
        List<String> sedes = new ArrayList<>();
        String archivoSedes = "data/Sedes.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(archivoSedes))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (!datos[0].equals("Arrendamientos Activos")) {
                    sedes.add(datos[0].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sedes;
    }
    
    

    private static void mostrarCarrosEnSede(String sede, Date fechaInicio, Date fechaFin, JPanel panelActual) {
        List<String[]> carrosEnSede = leerCarrosEnSede(sede);
        StringBuilder sb = new StringBuilder("Carros disponibles en la sede " + sede + ":\n");

        for (String[] carro : carrosEnSede) {
            sb.append("Placa: ").append(carro[0]);
            sb.append(", Marca: ").append(carro[1]);  // Columna de la marca
            sb.append(", Modelo: ").append(carro[2]); // Columna del modelo
            sb.append(", Color: ").append(carro[3]);  // Columna del color
            sb.append(", Categoría: ").append(carro[4]); // Columna de la categoría
            sb.append("\n");
        }

        if (carrosEnSede.isEmpty()) {
            sb.append("No hay carros disponibles en esta sede.");
        }

        // Muestra los carros en un diálogo
        JOptionPane.showMessageDialog(panelActual, sb.toString());
    }

    private static List<String[]> leerCarrosEnSede(String sede) {
        List<String[]> carrosEnSede = new ArrayList<>();
        String archivoCarros = "data/Carros.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCarros))) {
            String linea;
            boolean primeraLinea = true; // Variable para omitir la primera línea
            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue; // Omitir la primera línea
                }
                String[] datos = linea.split(";"); // Cambio aquí: usa ';' en lugar de ','
                if (datos.length >= 6 && datos[5].trim().equals(sede.trim())) {
                    carrosEnSede.add(datos);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return carrosEnSede;
    }
}
