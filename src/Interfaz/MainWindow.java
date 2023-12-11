package Interfaz;


import javax.swing.*;

import modelo.Cliente;
import modelo.Sede;
import modelo.Vehiculo;
import procesamiento.Reserva;
import procesamiento.Archivo;
import Interfaz.SedesMenu;
import Interfaz.GeneradorReservas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class MainWindow {
	public static Map<String, Sede> sedeMap = new HashMap<>();
	public static Map<String, String> EmpleadoMap = new HashMap<>();
	public static Map<String, Vehiculo> carroMap = new HashMap<>();
	public static Map<String, Cliente> ClienteMap = new HashMap<>();
	private static Map<String, Reserva> ReservasCliente = new HashMap<>();
    public static void main(String[] args) throws FileNotFoundException, IOException {
    	

    	
    	Map<String, Reserva> ReservasClientes = new HashMap<>();
    	
    	Map<String, String> UserAdminMap = new HashMap<>();
    	Map<String, String> UserEmpleadoMap = new HashMap<>();
    	UserAdminMap.put("UsuarioAdministrador", "123Pollitos");
		UserEmpleadoMap.put("UsuarioEmpleado", "123Vaquitas");
		Archivo funciones = new Archivo();
		sedeMap = funciones.cargarSede("data/Sedes.csv");
		ClienteMap = funciones.cargarClientes("data/Clientes.csv");
		sedeMap = funciones.cargarEmpleadosaSede("data/Empleados.csv", sedeMap, EmpleadoMap);
		carroMap = funciones.cargarCarros("data/Carros.csv");
    	
    	
        // Create a JFrame
        JFrame frame = new JFrame("My Swing App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        
        // Set the background color of the content pane to red
        frame.getContentPane().setBackground(Color.PINK);

        // Create and add three buttons to the frame
        JButton BClientes = new JButton("INGRESO CLIENTES");
        JButton BEmpleados = new JButton("INGRESO EMPLEADOS");
        

        // Set background colors for the buttons
        BClientes.setBackground(Color.RED);
        BEmpleados.setBackground(Color.BLUE);
        

        // Set preferred sizes for the buttons
        BClientes.setPreferredSize(new Dimension(300, 75));
        BEmpleados.setPreferredSize(new Dimension(300, 75));
        

        // Add the buttons to the frame
        frame.add(BClientes);
        frame.add(BEmpleados);
 
        
        JButton BAdminInicio = new JButton("INICIO DE ADMINISTRADOR");

        BAdminInicio.setBackground(Color.GREEN);
        BAdminInicio.setPreferredSize(new Dimension(300, 75));
        frame.add(BAdminInicio);

        // Agrega un ActionListener para el nuevo botón
        BAdminInicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdminLoginWindow(sedeMap,EmpleadoMap,carroMap);  // Abre la ventana de inicio de sesión del administrador
            }
        });
        
        BEmpleados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EmpleadoInterface(frame, carroMap, ReservasCliente).solicitarCredencialesEmpleado();
            }
        });

        // Set the size of the window
        frame.setSize(400, 200);

        // Make the window visible
        frame.setVisible(true);
        
        // Add an action listener to the "BClientes" button to show the options
        BClientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OpcionesCliente(frame);
            }
        });
    }

	protected Map<String, Vehiculo> carroMapDos;

    // Method to show the options on the frame
	private static void OpcionesCliente(JFrame frame) {
        // Clear the existing components in the frame
        frame.getContentPane().removeAll();
        
        // Create and add the option buttons
        JButton BotonReserva = new JButton("Crear Reserva");
        JButton BotonConsultaSedes = new JButton("Consultar Sedes");
        JButton BotonRegistrarse = new JButton("Registrarse como nuevo cliente");
        JButton option5 = new JButton("Salir");
        

        
        BotonConsultaSedes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	SedesMenu funcionesSede = new SedesMenu(sedeMap);
            	funcionesSede.consultarSedes();
            }
        });
        
        BotonRegistrarse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String elnombre = JOptionPane.showInputDialog("Ingrese el nombre del Cliente");
                String  elcontacto= JOptionPane.showInputDialog("Ingrese numero de contacto cliente");
                String lafechaNacimiento = JOptionPane.showInputDialog("Ingrese fecha de nacimiento (dd/mm/aaaa)");
                String lanacionalidad = JOptionPane.showInputDialog("Ingrese nacionalidad del cliente");
                String eldocumentoIdentidad = JOptionPane.showInputDialog("Ingrese el documento de identidad");
                Cliente funcionesCliente = new Cliente(null,null,null,null,null);
                try {
					funcionesCliente.NuevoClienteInterfaz(ClienteMap,elnombre,elcontacto,lafechaNacimiento,lanacionalidad,eldocumentoIdentidad);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                JOptionPane.showMessageDialog(null, "Felicidades, se ha registrado como nuevo cliente");
            }
        });
        

        // Set preferred sizes for the option buttons
        BotonReserva.setPreferredSize(new Dimension(300, 75));
        BotonConsultaSedes.setPreferredSize(new Dimension(300, 75));
        BotonRegistrarse.setPreferredSize(new Dimension(300, 75));
        option5.setPreferredSize(new Dimension(300, 75));
        
        BotonReserva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	GeneradorReservas aaa = new GeneradorReservas(sedeMap, carroMap, ClienteMap, ReservasCliente);
            }
        });
        

        // Add the option buttons to the frame
        frame.add(BotonReserva);
        frame.add(BotonConsultaSedes);
        frame.add(BotonRegistrarse);
        frame.add(option5);


        // Repaint the frame
        frame.revalidate();
        frame.repaint();
    }
    
    
    
    
}