package consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import modelo.Cliente;
import modelo.Sede;
import modelo.Vehiculo;
import procesamiento.Reserva;

public class consolaEmpleado {
	
	public void ejecutarEmpleado(Map<String, Cliente> ClienteMap,Map<String, Reserva> ReservasCliente,Map<String, Sede> sedeMaps,
			Map<String, Vehiculo> carroMap) throws IOException {
		boolean continuar = true;
		Reserva funcionesReserva = new Reserva(null, null, 0, null, null, null, 0, null);
		Vehiculo funcionesVehiculo = new Vehiculo(null, null, null, null, null, null,null, null);
		while (continuar) {

			System.out.println("\n\n-------------MENU EMPLEADO-------------\n\n");
			System.out.println("1.Consultar Reservas"
					+ "\n2.Recibir carro alquiler"
					+ "\n3.Editar disponibilidad de carro"
					+ "\n4.Hacer Transferencia interna entre sedes"
					+ "\n5.Agregar historial carro"
					+ "\n9.Salir del perfil");
			int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
			if (opcion_seleccionada == 1) {
				funcionesReserva.showReservas(ReservasCliente);
			}
			
			if (opcion_seleccionada == 2) {
				String placa = input("Placa del Vehiculo");
				if (carroMap.containsKey(placa)) {
					Vehiculo editable = carroMap.get(placa);
					String cedulaReserva = input("Indique cedula reserva");
					if(ReservasCliente.containsKey(cedulaReserva)){
						ReservasCliente.remove(cedulaReserva);
					}
					String sede = input("Indique sede donde se entrega");
					editable.setSedeActual(sede);
				}
				else {
					System.out.println("\nNo se encontro la placa en la base de datos\n");}
				String hacertiempo = (input("Click para continuar"));
				
			}
			if (opcion_seleccionada == 3) {
				String placa = input("Placa del Vehiculo");
				if (carroMap.containsKey(placa)) {
					Vehiculo editable = carroMap.get(placa);
					String estado = input("Indique el estado del Vehiculo");
					editable.setEstado(estado);
				
				}
				else {
					System.out.println("\nNo se encontro la placa en la base de datos\n");
}
				String hacertiempo = (input("Click para continuar"));
			}
			if (opcion_seleccionada == 4) {
				String placa = input("Placa del Vehiculo");
				if (carroMap.containsKey(placa)) {
					Vehiculo editable = carroMap.get(placa);
					String sede = input("Indique sede a transferir el Vehiculo");
					editable.setSedeActual(sede);
				}
				else {
					System.out.println("\nNo se encontro la placa en la base de datos\n");
}
				String hacertiempo = (input("Click para continuar"));
			}
			if (opcion_seleccionada == 5) {
				String placa = input("Placa del Vehiculo");
				funcionesVehiculo.agregarHistorial(carroMap, placa);
				
				String hacertiempo = (input("Se han guardado los cambios  "
						+ "\n\nClick para continuar"));
				
			}
			
				
			
			if (opcion_seleccionada == 9) {
				System.out.println("Saliendo del Menu...\n\n...");
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
