package consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import modelo.Cliente;
import modelo.Sede;
import modelo.Vehiculo;
import procesamiento.Archivo;
import procesamiento.Reserva;

public class consolaCliente {
	
	
	
	
	
	public void opcionesUsuario()
	{System.out.println("1.Crear Reserva\n2.Consultar Vehiculos de Sede\n3.Consultar Sedes"
			+ "\n4.Registrarse como nuevo cliente"
			+ "\n9.Salir\n");}
	
	
	
	public void ejecutar_aplicacion(Map<String, Cliente> ClienteMap,Map<String, Reserva> ReservasCliente,Map<String, Sede> sedeMaps,
			Map<String, Vehiculo> carroMap) throws FileNotFoundException, IOException
	{
		Archivo funciones = new Archivo();
	 	Cliente funcionesCliente = new Cliente(null, null, null, null, null);

	boolean continuar = true;
	
	while (continuar)
		
	
	{
		System.out.println("\n\n-------------Opciones de usuario-------------\n\n");
		opcionesUsuario();
		int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
	 if (opcion_seleccionada ==1) 
	 {
		 
		 funcionesCliente.GenerarReserva2(ClienteMap, ReservasCliente, sedeMaps, carroMap);
	 }	
	 if (opcion_seleccionada ==2) 
	 	{
		 Vehiculo funcionesVehiculo = new Vehiculo(null, null, null, null, null, null,null, null);
		 String sedebuscada = input("Sede a consultar ");
		 funcionesVehiculo.showCarrosSede(carroMap, sedebuscada);
	 	}
	 
	 if (opcion_seleccionada ==3) 
	 	{
		 Sede funcionesSede = new Sede(null, null, null);
		 funcionesSede.showSede(sedeMaps);
	 	}	
	 if (opcion_seleccionada ==4) 
	 	{
		 funcionesCliente.NuevoCliente(ClienteMap);
		 funcionesCliente.GuardarCambiosClientes(ClienteMap);
	 	}
	 if (opcion_seleccionada ==9) 
	 	{System.out.println("Saliendo de la app...\n\n...");
		 continuar=false;}
	}}
	

	
	
	
	public String input(String mensaje) {
		try {
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		} catch (IOException e) {
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;

}

}

