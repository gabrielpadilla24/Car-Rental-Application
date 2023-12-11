package Interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PagoWindow extends JFrame {
    private Map<String, PasarelaPago> pasarelas;

    public PagoWindow() {
        pasarelas = new HashMap<>();
        pasarelas.put("PayPal", new PayPal("data/PayPal.txt"));
        pasarelas.put("PayU", new PayU("data/PayU.txt"));

        setTitle("Realizar Pago");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLayout(new BorderLayout());

        // Panel para seleccionar la pasarela de pago
        JPanel pasarelaPanel = new JPanel(new FlowLayout());
        JLabel pasarelaLabel = new JLabel("Seleccionar Pasarela:");
        JComboBox<String> pasarelaComboBox = new JComboBox<>(pasarelas.keySet().toArray(new String[0]));
        pasarelaPanel.add(pasarelaLabel);
        pasarelaPanel.add(pasarelaComboBox);

        // Panel para ingresar la información de la tarjeta
        JPanel tarjetaPanel = new JPanel(new GridLayout(4, 2));
        JTextField numeroTarjetaField = new JTextField();
        JTextField nombreTarjetaField = new JTextField();
        JTextField montoField = new JTextField();
        JPasswordField cvvField = new JPasswordField();
        tarjetaPanel.add(new JLabel("Número de Tarjeta:"));
        tarjetaPanel.add(numeroTarjetaField);
        tarjetaPanel.add(new JLabel("Nombre en la Tarjeta:"));
        tarjetaPanel.add(nombreTarjetaField);
        tarjetaPanel.add(new JLabel("Monto a Pagar:"));
        tarjetaPanel.add(montoField);
        tarjetaPanel.add(new JLabel("CVV:"));
        tarjetaPanel.add(cvvField);

        // Botón para realizar el pago
        JButton pagarButton = new JButton("Realizar Pago");
        pagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la información ingresada
                String pasarelaSeleccionada = (String) pasarelaComboBox.getSelectedItem();
                String numeroTarjeta = numeroTarjetaField.getText();
                String nombreTarjeta = nombreTarjetaField.getText();
                double monto = Double.parseDouble(montoField.getText());
                String cvv = new String(cvvField.getPassword());

                // Obtener la pasarela asociada
                PasarelaPago pasarela = pasarelas.get(pasarelaSeleccionada);

                // Validar la tarjeta
                if (pasarela.validarTarjeta(numeroTarjeta, nombreTarjeta, cvv)) {
                    // Realizar el pago simulado
                    boolean pagoExitoso = pasarela.realizarPago(numeroTarjeta, nombreTarjeta, monto, "cuenta123", "transaccion456");

                    // Mostrar el resultado del pago
                    if (pagoExitoso) {
                        JOptionPane.showMessageDialog(null, "Pago exitoso con " + pasarelaSeleccionada);
                        // Registrar la transacción exitosa en el archivo correspondiente
                        registrarTransaccionExitosa(pasarelaSeleccionada, numeroTarjeta, monto);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error en el pago con " + pasarelaSeleccionada, "Error de pago", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error: Tarjeta inválida", "Error de tarjeta", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Agregar componentes al frame
        add(pasarelaPanel, BorderLayout.NORTH);
        add(tarjetaPanel, BorderLayout.CENTER);
        add(pagarButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void registrarTransaccionExitosa(String pasarela, String numeroTarjeta, double monto) {
    	try {
            // Obtener la fecha actual
            LocalTime fechaActual = LocalTime.now();

            // Crear el mensaje de transacción exitosa
            String mensaje = "Transacción Exitosa - Fecha: " + fechaActual + " - Monto: $" + monto + " - Número de Tarjeta: **** **** **** " + numeroTarjeta;

            // Obtener el nombre del archivo de la pasarela
            String nombreArchivo = "data/" + pasarela + ".txt";

            // Registrar la transacción en el archivo correspondiente
            try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo, true))) {
                writer.println(mensaje);
            }

            // Mostrar un mensaje de éxito
            JOptionPane.showMessageDialog(this, "Pago registrado con éxito en " + pasarela);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al registrar el pago", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
}

