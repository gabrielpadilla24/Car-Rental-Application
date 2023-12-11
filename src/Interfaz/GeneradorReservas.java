package Interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import procesamiento.Reserva;
import modelo.Cliente;
import modelo.Sede;
import modelo.Vehiculo;
import procesamiento.GeneradorPdf;

public class GeneradorReservas extends JFrame {

	private Map<String, Sede> sedeMap = new HashMap<>();
	private Map<String, Vehiculo> carroMap = new HashMap<>();
	private Map<String, Cliente> ClienteMap = new HashMap<>();
	private Map<String, Reserva> ReservasClientes = new HashMap<>();

	GeneradorReservas(Map<String, Sede> sedeMap, Map<String, Vehiculo> carroMap, Map<String, Cliente> ClienteMap,
			Map<String, Reserva> ReservasClientes) {
		// Configurar la ventana principal
		setTitle("Generador de reservas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);

		// Crear el panel para el formulario
		JPanel principal = new JPanel();
		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(10, 4));
		panel.setLayout(new GridLayout(7, 2));

		// Campos del formulario

		JLabel lblCedula = new JLabel("Cedula:");
		JTextField txtCedula = new JTextField(20);


		JLabel lblFecha = new JLabel("Fecha:");
		JTextField txtFecha = new JTextField(20);

		JLabel lblDuracion = new JLabel("Duración:");
		JTextField txtDuracion = new JTextField(20);

		JLabel lblSedeRecoger = new JLabel("Sede a recoger el carro:");
		JTextField txtSedeRecoger = new JTextField(20);

		JLabel lblSedeDejar = new JLabel("Sede donde se deja el carro:");
		JTextField txtSedeDejar = new JTextField(20);

		JLabel lblSeguros = new JLabel("Seguros que se toman:");
		JTextField txtSeguros = new JTextField(20);

		JLabel lblCantidadConductores = new JLabel("Cantidad de conductores:");
		JTextField txtCantidadConductores = new JTextField(20);

		JButton btnGuardar = new JButton("Buscar Carros Disponibles");
		

		// Agregar los componentes al panel
		
		panel.add(lblCedula);
		panel.add(txtCedula);

		panel.add(lblFecha);
		panel.add(txtFecha);
		panel.add(lblDuracion);
		panel.add(txtDuracion);
		panel.add(lblSedeRecoger);
		panel.add(txtSedeRecoger);
		panel.add(lblSedeDejar);
		panel.add(txtSedeDejar);
		panel.add(lblSeguros);
		panel.add(txtSeguros);
		panel.add(lblCantidadConductores);
		panel.add(txtCantidadConductores);
		panel.add(btnGuardar);

		btnGuardar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				String categoria = showOptionPanel();
				JOptionPane.showMessageDialog(btnGuardar, "A CONTINUACION DEBE ESCRIBIR UNA DE LAS SIGUIENTES PLACAS EN EL SISTEMA");
				String fecha = txtFecha.getText();
				String duracion = txtDuracion.getText();
				String sedeRecoger = txtSedeRecoger.getText();
				String sedeDejar = txtSedeDejar.getText();
				String seguros = txtSeguros.getText();
				String cantidadConductores = txtCantidadConductores.getText();
				StringBuilder CarrosInfo = new StringBuilder("Carros Disponibles:\n\n");
				// public Reserva(String categoria, String fechaRecogido, int duracion, String
				// sedeInicial, String sedeFinal,
				// String segurosIncluidos, int conductoresAdicionales, Vehiculo elcarro)

				for (Map.Entry<String, Vehiculo> entry : carroMap.entrySet()) {
					Vehiculo carro = entry.getValue();

					if (carro.getCategoria().equals(categoria) && carro.getSedeActual().equals(sedeRecoger))

					{

						CarrosInfo.append("Categoria: ").append(carro.getCategoria()).append("\n");
						CarrosInfo.append("Marca: ").append(carro.getMarca()).append("\n");
						CarrosInfo.append("Modelo: ").append(carro.getModelo()).append("\n");
						CarrosInfo.append("Placa: ").append(carro.getPlaca()).append("\n\n+---------------+");

					}
				}

				JOptionPane.showMessageDialog(null, CarrosInfo.toString());

				String laplaca = JOptionPane.showInputDialog("Ingrese la placa del vehiculo a reservar:");
				if (carroMap.containsKey(laplaca)) {
					Vehiculo arriendo = carroMap.get(laplaca);
					arriendo.setEstado("arrendado");
					arriendo.setSedeActual("Arrendamientos Activos");
					Reserva nuevaReserva = new Reserva(categoria, fecha, Integer.parseInt(duracion), sedeRecoger,
							sedeDejar, seguros, Integer.parseInt(cantidadConductores), arriendo);
					carroMap.put(laplaca, arriendo);
					ReservasClientes.put(laplaca, nuevaReserva);
					
					mostrarFactura(Integer.parseInt(duracion),Integer.parseInt(cantidadConductores));
					// Abre la ventana de pago después de generar la reserva
                    abrirVentanaPago();
				}
				else
				{JOptionPane.showMessageDialog(btnGuardar, "No se ha encontrado esa placa");}

			}
			
			private void abrirVentanaPago() {
                SwingUtilities.invokeLater(() -> {
                    // Crear la ventana de pago
                    PagoWindow pagoWindow = new PagoWindow();
                    pagoWindow.setVisible(true);

                    // Cerrar la ventana actual después de abrir la ventana de pago
                    dispose();
                });
            }

			private void mostrarFactura(int laduracion, int losconductores) {
				// Construct the message string
				String message = "+----------FACTURA----------+\n\n"
						+ "Precio diario del arriendo: 100.000 pesos (cantidad dias tomado: "
						+ Integer.toString(laduracion) + ")\n"
						+ "Precio diario de conductor adicional: 50.000 pesos (cantidad conductores tomado: "
						+ Integer.toString(losconductores) + ")\n" + "Total: "
						+ Integer.toString(laduracion * 100000 + losconductores * 50000) + "\n" + "Total anticipo: "
						+ Integer.toString((laduracion * 100000 + losconductores * 50000) * 3 / 10);
				String total = "Total: COP " + Integer.toString(laduracion * 100000 + losconductores * 50000)+" PESOS";
				String anticipo = total +("      |     Total anticipo: COP " + Integer.toString((laduracion * 100000 + losconductores * 50000) * 3 / 10))+" PESOS ";
				GeneradorPdf.generatePdf(anticipo, "./data/FacturaCliente.pdf");

				// Show the message in a JOptionPane
				JOptionPane.showMessageDialog(null, message, "Factura", JOptionPane.INFORMATION_MESSAGE);
			}
			
			public static String showOptionPanel() {
		        // Options to display in the dialog
		        Object[] options = {"Sedan", "Camioneta", "Convertible", "Campero"};

		        // Display the option dialog
		        Object result = JOptionPane.showInputDialog(
		                null,                      
		                "Tipo Vehiculo:", 
		                "Option Dialog",           
		                JOptionPane.PLAIN_MESSAGE, 
		                null,                      
		                options,                   
		                options[0]                 
		        );

		        return (String) result; // Convert the result to a String
		    }
			
		});

		// Agregar el panel a la ventana
		principal.add(btnGuardar);
		principal.add(panel);
		add(principal);

		// Mostrar la ventana
		setVisible(true);
	}

}