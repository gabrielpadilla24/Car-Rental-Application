package Interfaz;

import javax.swing.*;

import consola.ArrendamientoCarros;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import modelo.Vehiculo;
import procesamiento.Archivo;
import procesamiento.Reserva;
import java.util.Map;

public class EmpleadoInterface {

    private JFrame frame;
    private Map<String, Vehiculo> carroMap;
    private Map<String, Reserva> ReservasCliente;

    public EmpleadoInterface(JFrame frame, Map<String, Vehiculo> carroMap, Map<String, Reserva> ReservasCliente) {
        this.frame = frame;
        this.carroMap = carroMap;
        this.ReservasCliente = ReservasCliente;
        mostrarMenuEmpleado();
    }
    
    public void cargarDatosCarros() {
        Archivo funciones = new Archivo();
        try {
            this.carroMap = funciones.cargarCarros("data/Carros.csv");
        } catch (IOException e) {
            System.out.println("Error al cargar los datos de los carros: " + e.getMessage());
        }
    }
    
    public EmpleadoInterface(JFrame frame) {
        this.frame = frame;
        cargarDatosCarros(); // Llama al método para cargar los datos
        mostrarMenuEmpleado();
    }

    public void solicitarCredencialesEmpleado() {
        // Limpia el JFrame y prepara para mostrar la interfaz de inicio de sesión
        frame.getContentPane().removeAll();
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Crear un panel para centrar los campos y botón
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Usuario:");
        JTextField userField = new JTextField(20);
        JLabel passLabel = new JLabel("Contraseña:");
        JPasswordField passField = new JPasswordField(20);
        JButton loginButton = new JButton("Ingresar");

        // Agregar componentes al panel
        panel.add(userLabel, gbc);
        panel.add(userField, gbc);
        panel.add(passLabel, gbc);
        panel.add(passField, gbc);
        panel.add(loginButton, gbc);

        // Añadir el panel al frame para centrar los elementos
        frame.add(panel);
        frame.add(Box.createVerticalGlue());

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());
                if (ArrendamientoCarros.UserEmpleadoMap.containsKey(username) &&
                    ArrendamientoCarros.UserEmpleadoMap.get(username).equals(password)) {
                    mostrarMenuEmpleado();
                } else {
                    JOptionPane.showMessageDialog(frame, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.pack(); // Ajusta el tamaño del frame basado en los componentes
        frame.setLocationRelativeTo(null); // Centrar el frame en la pantalla
        frame.revalidate();
        frame.repaint();
    }


    private void mostrarMenuEmpleado() {
        frame.getContentPane().removeAll();
        frame.setLayout(new FlowLayout());

        // Los botones y su funcionalidad
        JButton btnConsultarReservas = new JButton("1. Consultar Reservas");
        btnConsultarReservas.addActionListener(e -> mostrarConsultaReservas());

        JButton btnRecibirCarroAlquiler = new JButton("2. Recibir carro alquiler");
        btnRecibirCarroAlquiler.addActionListener(e -> mostrarRecibirCarroAlquiler());

        JButton btnEditarDisponibilidadCarro = new JButton("3. Editar disponibilidad de carro");
        btnEditarDisponibilidadCarro.addActionListener(e -> mostrarEditarDisponibilidadCarro());

        JButton btnHacerTransferenciaInternas = new JButton("4. Hacer Transferencia interna entre sedes");
        btnHacerTransferenciaInternas.addActionListener(e -> mostrarHacerTransferenciaInternas());

        JButton btnAgregarHistorialCarro = new JButton("5. Agregar historial carro");
        btnAgregarHistorialCarro.addActionListener(e -> mostrarAgregarHistorialCarro());
        
        // Añadir botones al frame
        frame.add(btnConsultarReservas);
        frame.add(btnRecibirCarroAlquiler);
        frame.add(btnEditarDisponibilidadCarro);
        frame.add(btnHacerTransferenciaInternas);
        frame.add(btnAgregarHistorialCarro);

        frame.revalidate();
        frame.repaint();
    }

    private void mostrarConsultaReservas() {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea(20, 50);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Aquí agregas el código para mostrar las reservas en el JTextArea
        StringBuilder reservasTexto = new StringBuilder();
        for (Map.Entry<String, Reserva> entry : ReservasCliente.entrySet()) {
            Reserva reserva = entry.getValue();
            reservasTexto.append("Cedula Cliente: ").append(entry.getKey()).append("\n")
                .append("Categoria Vehiculo: ").append(reserva.getCategoria()).append("\n")
                .append("Fecha Sacado: ").append(reserva.getFechaRecogido()).append("\n")
                .append("Duracion de la reserva: ").append(reserva.getDuracion()).append("\n")
                .append("Sede donde fue recogido: ").append(reserva.getSedeInicial()).append("\n")
                .append("Sede donde va a ser entregado: ").append(reserva.getSedeFinal()).append("\n")
                .append("Seguros Incluidos: ").append(reserva.getSegurosIncluidos()).append("\n")
                .append("Conductores adicionales: ").append(reserva.getConductoresAdicionales()).append("\n")
                .append("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n");
        }

        textArea.setText(reservasTexto.toString());
        frame.add(scrollPane, BorderLayout.CENTER);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarMenuEmpleado();
            }
        });

        frame.add(btnVolver, BorderLayout.SOUTH);
        frame.revalidate();
        frame.repaint();
    }

    private void mostrarRecibirCarroAlquiler() {
        String placa = JOptionPane.showInputDialog(frame, "Ingrese la placa del vehículo:");
        if (placa != null && !placa.trim().isEmpty()) {
            if (carroMap.containsKey(placa)) {
                Vehiculo vehiculo = carroMap.get(placa);
                mostrarFormularioRecibirCarro(vehiculo, placa);
            } else {
                JOptionPane.showMessageDialog(frame, "No se encontró la placa en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void mostrarFormularioRecibirCarro(Vehiculo vehiculo, String placa) {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());

        JLabel etiqueta = new JLabel("Introduce los datos del carro alquilado para la placa: " + placa);
        etiqueta.setHorizontalAlignment(JLabel.CENTER);

        JPanel panelFormulario = new JPanel(new GridLayout(0, 2));
        panelFormulario.add(new JLabel("Cédula de Reserva:"));
        JTextField campoCedulaReserva = new JTextField();
        panelFormulario.add(campoCedulaReserva);

        panelFormulario.add(new JLabel("Sede de Entrega:"));
        JTextField campoSedeEntrega = new JTextField();
        panelFormulario.add(campoSedeEntrega);

        JButton btnConfirmar = new JButton("Confirmar Recepción");
        btnConfirmar.addActionListener(e -> procesarRecepcionCarro(vehiculo, campoCedulaReserva.getText(), campoSedeEntrega.getText()));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.add(btnConfirmar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> mostrarMenuEmpleado());
        panelBotones.add(btnVolver);

        frame.add(etiqueta, BorderLayout.NORTH);
        frame.add(panelFormulario, BorderLayout.CENTER);
        frame.add(panelBotones, BorderLayout.PAGE_END);

        frame.revalidate();
        frame.repaint();
    }
    
    private void procesarRecepcionCarro(Vehiculo vehiculo, String cedulaReserva, String sedeEntrega) {
        if (!cedulaReserva.trim().isEmpty() && ReservasCliente.containsKey(cedulaReserva)) {
            ReservasCliente.remove(cedulaReserva);
            vehiculo.setSedeActual(sedeEntrega);
            JOptionPane.showMessageDialog(frame, "Carro con placa " + vehiculo.getPlaca() + " recibido exitosamente en " + sedeEntrega + ".");
            mostrarMenuEmpleado();
        } else {
            JOptionPane.showMessageDialog(frame, "Por favor, verifique la cédula de reserva y la placa del vehículo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void mostrarEditarDisponibilidadCarro() {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());

        JLabel etiqueta = new JLabel("Editar disponibilidad del carro:");
        etiqueta.setHorizontalAlignment(JLabel.CENTER);

        JPanel panelFormulario = new JPanel(new GridLayout(0, 2));
        panelFormulario.add(new JLabel("Placa del Vehículo:"));
        JTextField campoPlaca = new JTextField();
        panelFormulario.add(campoPlaca);

        panelFormulario.add(new JLabel("Nueva Disponibilidad:"));
        JComboBox<String> comboDisponibilidad = new JComboBox<>(new String[]{"Disponible", "No Disponible"});
        panelFormulario.add(comboDisponibilidad);

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addActionListener(e -> actualizarDisponibilidadCarro(campoPlaca.getText(), (String) comboDisponibilidad.getSelectedItem()));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.add(btnConfirmar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> mostrarMenuEmpleado());
        panelBotones.add(btnVolver);

        frame.add(etiqueta, BorderLayout.NORTH);
        frame.add(panelFormulario, BorderLayout.CENTER);
        frame.add(panelBotones, BorderLayout.PAGE_END);

        frame.revalidate();
        frame.repaint();
    }
    
    private void actualizarDisponibilidadCarro(String placa, String disponibilidad) {
        if (carroMap.containsKey(placa)) {
            Vehiculo vehiculo = carroMap.get(placa);
            vehiculo.setEstado(disponibilidad);
            JOptionPane.showMessageDialog(frame, "Disponibilidad actualizada a " + disponibilidad + " para el carro con placa " + placa + ".");
        } else {
            JOptionPane.showMessageDialog(frame, "La placa ingresada no existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        mostrarMenuEmpleado();
    }

    private void mostrarHacerTransferenciaInternas() {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());

        JLabel etiqueta = new JLabel("Hacer Transferencia Interna Entre Sedes:");
        etiqueta.setHorizontalAlignment(JLabel.CENTER);

        JPanel panelFormulario = new JPanel(new GridLayout(0, 2));
        panelFormulario.add(new JLabel("Placa del Vehículo:"));
        JTextField campoPlaca = new JTextField();
        panelFormulario.add(campoPlaca);

        panelFormulario.add(new JLabel("Sede Destino:"));
        JComboBox<String> comboSedes = new JComboBox<>(new String[]{
            "Sede Amazonas", "Arrendamientos Activos", "Sede Cartagena", 
            "Sede Prueba", "Sede Arauca", "Sede Bogota", "Sede Santa Marta", "Sede Venezuela"
        });
        panelFormulario.add(comboSedes);

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addActionListener(e -> transferirVehiculo(campoPlaca.getText(), (String) comboSedes.getSelectedItem()));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.add(btnConfirmar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> mostrarMenuEmpleado());
        panelBotones.add(btnVolver);

        frame.add(etiqueta, BorderLayout.NORTH);
        frame.add(panelFormulario, BorderLayout.CENTER);
        frame.add(panelBotones, BorderLayout.PAGE_END);

        frame.revalidate();
        frame.repaint();
    }

    private void transferirVehiculo(String placa, String sedeDestino) {
        if (carroMap.containsKey(placa)) {
            Vehiculo vehiculo = carroMap.get(placa);
            vehiculo.setSedeActual(sedeDestino);
            JOptionPane.showMessageDialog(frame, "Vehículo con placa " + placa + " transferido exitosamente a la sede " + sedeDestino + ".");
        } else {
            JOptionPane.showMessageDialog(frame, "La placa ingresada no existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        mostrarMenuEmpleado();
    }


    private void mostrarAgregarHistorialCarro() {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());

        JLabel etiqueta = new JLabel("Agregar Historial de Carro:");
        etiqueta.setHorizontalAlignment(JLabel.CENTER);

        JPanel panelFormulario = new JPanel(new GridLayout(0, 2));
        panelFormulario.add(new JLabel("Placa del Vehículo:"));
        JTextField campoPlaca = new JTextField();
        panelFormulario.add(campoPlaca);

        panelFormulario.add(new JLabel("Detalle del Historial:"));
        JTextArea campoDetalleHistorial = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(campoDetalleHistorial);
        panelFormulario.add(scrollPane);

        JButton btnAgregar = new JButton("Agregar Historial");
        btnAgregar.addActionListener(e -> agregarHistorialCarro(campoPlaca.getText(), campoDetalleHistorial.getText()));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.add(btnAgregar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> mostrarMenuEmpleado());
        panelBotones.add(btnVolver);

        frame.add(etiqueta, BorderLayout.NORTH);
        frame.add(panelFormulario, BorderLayout.CENTER);
        frame.add(panelBotones, BorderLayout.PAGE_END);

        frame.revalidate();
        frame.repaint();
    }

    private void agregarHistorialCarro(String placa, String detalleHistorial) {
        if (carroMap.containsKey(placa)) {
            Vehiculo vehiculo = carroMap.get(placa);
            // Aquí puedes agregar la lógica para actualizar el historial del vehículo
            JOptionPane.showMessageDialog(frame, "Historial agregado al carro con placa " + placa + ".");
        } else {
            JOptionPane.showMessageDialog(frame, "La placa ingresada no existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        mostrarMenuEmpleado();
    }
    
}