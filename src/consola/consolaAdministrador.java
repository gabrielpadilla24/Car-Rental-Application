package consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import modelo.Cliente;
import modelo.Empleado;
import modelo.Sede;
import modelo.Vehiculo;
import procesamiento.Archivo;
import procesamiento.Reserva;

public class consolaAdministrador {
	public void opcionesUsuario() {
		System.out.println("\n2.Menu Clientes\n3.Menu Sede\n4.Menu empleado\n" + "5.Menu Vehiculos\n9.Salir\n");
	}

	public void ejecutar_aplicacion(Map<String, Cliente> ClienteMap, Map<String, Reserva> ReservasCliente,
			Map<String, Sede> sedeMaps, Map<String, Vehiculo> carroMap, Map<String, String> EmpleadoMap)
			throws FileNotFoundException, IOException {

		boolean continuar = true;

		while (continuar)

		{
			System.out.println("\n\n-------------Opciones de usuario-------------\n\n");
			opcionesUsuario();
			int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));

			if (opcion_seleccionada == 2) {
				System.out.println("Opcion 2:  Menu Clientes");
				Cliente funcionesCliente = new Cliente(null, null, null, null, null);
				funcionesCliente.ejecutarCliente(ClienteMap, ReservasCliente, sedeMaps, carroMap);
			}

			if (opcion_seleccionada == 3) {
				System.out.println("Opcion 3:  Menu Sede");
				Sede funcionesSede = new Sede(null, null, null);
				funcionesSede.ejecutarSede(sedeMaps);
			}
			if (opcion_seleccionada == 4) {
				System.out.println("Opcion 4:  Menu Empleado");
				Empleado funcionesEmpleado = new Empleado(null, null);
				funcionesEmpleado.ejecutarEmpleado(EmpleadoMap);
			}
			if (opcion_seleccionada == 5) {
				System.out.println("Opcion 5:  9 se sale de la app");
				Vehiculo funcionesCarro = new Vehiculo(null, null, null, null, null, null,null,null);
				funcionesCarro.ejecutarVehiculos(carroMap);
			}

			if (opcion_seleccionada == 6) {
				System.out.println("Opcion 6:  9 se sale de la app");
			}

			if (opcion_seleccionada == 7) {
				System.out.println("Opcion 7:  9 se sale de la app");

			}
			if (opcion_seleccionada == 8) {
				System.out.println("Opcion 8:  9 se sale de la app");
			}
			if (opcion_seleccionada == 9) {
				System.out.println("Saliendo de la app...\n\n...");
				continuar = false;
			}
		}
	}

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
