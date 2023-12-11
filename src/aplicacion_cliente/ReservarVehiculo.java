package aplicacion_cliente;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReservarVehiculo {

    private static JComboBox<String> comboPlacas;
    private static JComboBox<String> comboSeguros;  
    private static JComboBox<String> comboConductores; 
    private static JTextField campoCedulaCliente; 
    private static String sedeSeleccionada;
    private static String categoriaSeleccionada;
    private static Date fechaInicio;
    private static Date fechaFin;

    public static JPanel getPanelFormularioReserva() {
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        campoCedulaCliente = new JTextField(20);  
        addFormField(panelFormulario, gbc, "Cédula Cliente:", campoCedulaCliente);

        JSpinner spinnerFechaInicio = createDatePicker();
        addFormField(panelFormulario, gbc, "Fecha Inicio:", spinnerFechaInicio);

        JSpinner spinnerFechaFin = createDatePicker();
        addFormField(panelFormulario, gbc, "Fecha Fin:", spinnerFechaFin);

        JComboBox<String> comboSedes = new JComboBox<>(leerSedes());
        addFormField(panelFormulario, gbc, "Sede donde fue recogido:", comboSedes);

        JComboBox<String> comboCategorias = new JComboBox<>(leerCategorias());
        addFormField(panelFormulario, gbc, "Categoría del Vehículo:", comboCategorias);

        JButton botonConfirmar = new JButton("Confirmar");
        botonConfirmar.addActionListener(e -> {
            sedeSeleccionada = (String) comboSedes.getSelectedItem();
            categoriaSeleccionada = (String) comboCategorias.getSelectedItem();
            fechaInicio = (Date) spinnerFechaInicio.getValue();
            fechaFin = (Date) spinnerFechaFin.getValue();
            ClienteApp.cambiarPanel(crearPanelSegundaParteFormulario());
        });

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelFormulario.add(botonConfirmar, gbc);

        JButton botonVolver = new JButton("Volver");
        botonVolver.addActionListener(e -> ClienteApp.cambiarPanel(InterfazCliente.crearPanelPrincipal()));
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelFormulario.add(botonVolver, gbc);

        return panelFormulario;
    }

    public static JPanel crearPanelSegundaParteFormulario() {
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        comboPlacas = new JComboBox<>(leerPlacas().toArray(new String[0]));
        if (comboPlacas.getItemCount() == 0) {
            JOptionPane.showMessageDialog(null, "No hay vehículos disponibles en esta categoría en la sede seleccionada.");
            return getPanelFormularioReserva();
        }
        addFormField(panelFormulario, gbc, "Placa:", comboPlacas);

        comboSeguros = crearComboBoxSeguros();
        addFormField(panelFormulario, gbc, "Seguros Incluidos:", comboSeguros);

        comboConductores = crearComboBoxConductores();
        addFormField(panelFormulario, gbc, "Conductores adicionales:", comboConductores);

        JButton botonEnviar = new JButton("Enviar Reserva");
        botonEnviar.addActionListener(e -> {
            String cedulaCliente = campoCedulaCliente.getText();
            String placa = (String) comboPlacas.getSelectedItem();
            if (!esPlacaDisponible(placa, fechaInicio, fechaFin, sedeSeleccionada)) {
                JOptionPane.showMessageDialog(null, "La placa seleccionada no está disponible para las fechas elegidas. Por favor, intente con otra placa.");
                return;
            }

            String[] marcaModelo = obtenerMarcaModeloPorPlaca(placa);
            String seguros = (String) comboSeguros.getSelectedItem();
            String conductoresAdicionales = (String) comboConductores.getSelectedItem();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fechaInicioStr = sdf.format(fechaInicio);
            String fechaFinStr = sdf.format(fechaFin);
            String sedeEntrega = sedeSeleccionada; 

            ClienteUtils.guardarReservaEnCSV(new String[]{
                cedulaCliente, categoriaSeleccionada, fechaInicioStr, fechaFinStr, 
                sedeSeleccionada, sedeEntrega, seguros, conductoresAdicionales, 
                marcaModelo[0], marcaModelo[1], placa
            });

            JOptionPane.showMessageDialog(panelFormulario, "Reserva guardada con éxito.");
        });

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelFormulario.add(botonEnviar, gbc);

        JButton botonVolver = new JButton("Volver");
        botonVolver.addActionListener(e -> ClienteApp.cambiarPanel(getPanelFormularioReserva()));
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelFormulario.add(botonVolver, gbc);

        return panelFormulario;
    }

    private static boolean esPlacaDisponible(String placa, Date fechaInicio, Date fechaFin, String sedeSeleccionada) {
        Set<String> placasReservadas = leerPlacasReservadasParaFechas(fechaInicio, fechaFin, sedeSeleccionada);
        return !placasReservadas.contains(placa);
    }

    private static Vector<String> leerPlacas() {
        Vector<String> placasDisponibles = new Vector<>();
        Set<String> placasReservadas = leerPlacasReservadasParaFechas(fechaInicio, fechaFin, sedeSeleccionada);

        String archivoCarros = "data/Carros.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCarros))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length > 5 && datos[5].trim().equals(sedeSeleccionada.trim()) && 
                    datos[4].trim().equals(categoriaSeleccionada.trim()) && 
                    !placasReservadas.contains(datos[0].trim())) {
                    placasDisponibles.add(datos[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return placasDisponibles;
    }

    private static Set<String> leerPlacasReservadasParaFechas(Date fechaInicio, Date fechaFin, String sedeSeleccionada) {
        Set<String> placasReservadas = new HashSet<>();
        String archivoReservas = "data/Reservas.csv";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(archivoReservas))) {
            String linea;
            br.readLine(); // Saltar el encabezado
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length > 10) {
                    Date fechaInicioReserva = sdf.parse(datos[2].trim());
                    Date fechaFinReserva = sdf.parse(datos[3].trim());
                    String sedeRecogida = datos[4].trim();
                    String placaReservada = datos[10].trim();

                    if (sedeSeleccionada.equals(sedeRecogida) && 
                        !fechaInicioReserva.after(fechaFin) && 
                        !fechaFinReserva.before(fechaInicio)) {
                        placasReservadas.add(placaReservada);
                    }
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return placasReservadas;
    }





    private static Vector<String> leerCategorias() {
        Vector<String> categorias = new Vector<>();
        String archivoCarros = "data/Carros.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCarros))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (!categorias.contains(datos[4].trim())) {
                    categorias.add(datos[4].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    private static void addFormField(JPanel panel, GridBagConstraints gbc, String labelText, JComponent field) {
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel(labelText), gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }
    
    private static String[] obtenerMarcaModeloPorPlaca(String placa) {
        String archivoCarros = "data/Carros.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCarros))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos[0].trim().equals(placa.trim())) {
                    return new String[]{datos[1], datos[2]}; // Marca y Modelo
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[]{"", ""}; // Retornar vacío si no se encuentra la placa
    }

    private static JSpinner createDatePicker() {
        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(editor);
        ((SpinnerDateModel) dateSpinner.getModel()).setCalendarField(Calendar.DAY_OF_MONTH);
        return dateSpinner;
    }

    private static JComboBox<String> crearComboBoxSeguros() {
        JComboBox<String> comboBoxSeguros = new JComboBox<>();
        comboBoxSeguros.addItem("Sin seguro");
        comboBoxSeguros.addItem("Seguro básico");
        comboBoxSeguros.addItem("Seguro intermedio");
        comboBoxSeguros.addItem("Seguro cubre todo");
        return comboBoxSeguros;
    }

    private static JComboBox<String> crearComboBoxConductores() {
        JComboBox<String> comboBoxConductores = new JComboBox<>();
        comboBoxConductores.addItem("0");
        comboBoxConductores.addItem("1");
        comboBoxConductores.addItem("2");
        comboBoxConductores.addItem("3");
        return comboBoxConductores;
    }

    private static Vector<String> leerSedes() {
        Vector<String> sedes = new Vector<>();
        String archivoSedes = "data/Sedes.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(archivoSedes))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (!datos[0].trim().equals("Arrendamientos Activos")) {
                    sedes.add(datos[0].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sedes;
    }
}
