package procesamiento;

import modelo.Sede;
import procesamiento.Archivo;
import modelo.Cliente;
import modelo.Vehiculo;
import modelo.Tarjeta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import procesamiento.GeneradorPdf;

public class Reserva {

	private String categoria;
	private String fechaRecogido;
	private int duracion;
	private String sedeInicial;
	private String sedeFinal;
	private String segurosIncluidos;
	private int conductoresAdicionales;
	private Vehiculo carro;

	public Reserva(String categoria, String fechaRecogido, int duracion, String sedeInicial, String sedeFinal,
			String segurosIncluidos, int conductoresAdicionales, Vehiculo elcarro) {
		this.categoria = categoria;
		this.fechaRecogido = fechaRecogido;
		this.duracion = duracion;
		this.sedeInicial = sedeInicial;
		this.sedeFinal = sedeFinal;
		this.segurosIncluidos = segurosIncluidos;
		this.conductoresAdicionales = conductoresAdicionales;
		this.carro = elcarro;

	}

	public void NuevaReserva2(Map<String, Reserva> ReservasClientes, Map<String, Cliente> ClienteMap,
			Map<String, Sede> sedeMap, Map<String, Vehiculo> carroMap) {
		String elnombre = (input("Ingrese cedula del cliente para la reserva-> "));

		if (ClienteMap.containsKey(elnombre)) {

			int categoria = Integer.parseInt(input("Categorias disponibles para arrendar:" + "\n1. Deportios"
					+ "\n2. Camionetas" + "\n3. Camperos" + "\n4. Sedanes" + "\n\n Opcion Seleccionada ->"));
			String lacategoria = darCategoria(categoria);
			String lafechaRecogido = (input("Ingrese la fecha de la reserva-> "));
			int laduracion = Integer.parseInt(input("Indique duracion en dias -> "));
			String lasedeInicial = (input("Ingrese sede donde desea recoger el carro-> "));
			String lasedeFinal = (input("Ingrese sede donde desea dejar el carro-> "));
			String losseguros = (input("Indique que seguros desea tomar separados por un \'-\' -> "));
			int losconductores = Integer.parseInt(input("Indique cuantos conductores adicionales va a tener: "));
			Vehiculo funcionesCarro = new Vehiculo(null, null, null, null, null, null,null,null);
			funcionesCarro.showCarrosDisponibles2(carroMap, lacategoria, lasedeInicial);
			System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
			String laplaca = (input("\n\nIngrese la placa del carro a arrendar-> "));

			if (Truesacarcarro(carroMap, laplaca, lasedeInicial, lacategoria)) {
				System.out.println("\nRESERVA HECHA\n");
				Vehiculo elarrendamiento = carroMap.get(laplaca);
				elarrendamiento.setEstado("arrendado");
				elarrendamiento.setSedeActual("Arrendamientos Activos");
				carroMap.put(laplaca, elarrendamiento);
				Reserva nuevareserva = new Reserva(lacategoria, lafechaRecogido, laduracion, lasedeInicial, lasedeFinal,
						losseguros, losconductores, elarrendamiento);
				ReservasClientes.put(elnombre, nuevareserva);
				mostrarFactura(laduracion, losconductores);
				PedirTarjeta();

			}

		}

		else {
			System.out.println("No se ha encontrado el cliente, " + "favor registrarlo en la seccion de clientes");
		}
	}

	
	public void NuevaReserva(Map<String, Reserva> ReservasClientes, Map<String, Cliente> ClienteMap,
			Map<String, Sede> sedeMap, Map<String, Vehiculo> carroMap) {
		String elnombre = (input("Ingrese cedula del cliente para la reserva-> "));

		if (ClienteMap.containsKey(elnombre)) {

			int categoria = Integer.parseInt(input("Categorias disponibles para arrendar:" + "\n1. Deportios"
					+ "\n2. Camionetas" + "\n3. Camperos" + "\n4. Sedanes" + "\n\n Opcion Seleccionada ->"));
			String lacategoria = darCategoria(categoria);
			String lafechaRecogido = (input("Ingrese la fecha de la reserva-> "));
			int laduracion = Integer.parseInt(input("Indique duracion en dias -> "));
			String lasedeInicial = (input("Ingrese sede donde desea recoger el carro-> "));
			String lasedeFinal = (input("Ingrese sede donde desea dejar el carro-> "));
			String losseguros = (input("Indique que seguros desea tomar separados por un \'-\' -> "));
			int losconductores = Integer.parseInt(input("Indique cuantos conductores adicionales va a tener: "));
			Vehiculo funcionesCarro = new Vehiculo(null, null, null, null, null, null,null,null);
			funcionesCarro.showCarrosDisponibles2(carroMap, lacategoria, lasedeInicial);
			System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
			String laplaca = (input("\n\nIngrese la placa del carro a arrendar-> "));

			if (Truesacarcarro(carroMap, laplaca, lasedeInicial, lacategoria)) {
				System.out.println("\nRESERVA HECHA\n");
				Vehiculo elarrendamiento = carroMap.get(laplaca);
				elarrendamiento.setEstado("arrendado");
				elarrendamiento.setSedeActual("Arrendamientos Activos");
				carroMap.put(laplaca, elarrendamiento);
				Reserva nuevareserva = new Reserva(lacategoria, lafechaRecogido, laduracion, lasedeInicial, lasedeFinal,
						losseguros, losconductores, elarrendamiento);
				ReservasClientes.put(elnombre, nuevareserva);
				mostrarFactura(laduracion, losconductores);
				PedirTarjeta();

			}

		}

		else {
			System.out.println("No se ha encontrado el cliente, " + "favor registrarlo en la seccion de clientes");
		}
	}
	private void mostrarFactura(int laduracion, int losconductores) {
		System.out.println("+----------FACTURA----------+\n\n");
		System.out.println("Precio diario del arriendo: 100.000 pesos(cantidad dias tomado:"
				+ Integer.toString(laduracion) + ")\n");
		System.out.println("Precio diario de conductor adicional: 50.000 pesos(cantidad conductores tomado:"
				+ Integer.toString(losconductores) + ")\n");
		System.out.println("Total:" + Integer.toString(laduracion * 100000 + losconductores * 50000) + ")\n");
		
		System.out.println(
				"Total anticipo:" + Integer.toString((laduracion * 100000 + losconductores * 50000) * 3 / 10));
	}

	public void EliminarReserva(Map<String, Reserva> ReservasClientes) {
		String elnombre = (input("Cedula bajo la que se encuentra la reserva: "));
		if (ReservasClientes.containsKey(elnombre)) {
			ReservasClientes.remove(elnombre);
			System.out.println("La reserva se ha cancelado exitosamente");

		} else {
			System.out.println("No se ha encontrado la reserva que desea buscar");
		}
	}

	public String getCategoria() {
		return categoria;
	}

	public String getFechaRecogido() {
		return fechaRecogido;
	}

	public int getDuracion() {
		return duracion;
	}

	public String getSedeInicial() {
		return sedeInicial;
	}

	public String getSedeFinal() {
		return sedeFinal;
	}

	public String getSegurosIncluidos() {
		return segurosIncluidos;
	}

	public int getConductoresAdicionales() {
		return conductoresAdicionales;
	}

	public void showReservas(Map<String, Reserva> ReservasClientes) {
		System.out.println("\nRESERVAS ACTUALES: \n\n+--------------------------------------+\n");
		for (Map.Entry<String, Reserva> entry : ReservasClientes.entrySet()) {
			Reserva busqueda = entry.getValue();
			System.out.println("\n+--------------------------------------+\n");
			System.out.println("Cedula Cliente: " + entry.getKey());
			System.out.println("Categoria Vehiculo: " + busqueda.getCategoria());
			System.out.println("Fecha Sacado: " + busqueda.getFechaRecogido());
			System.out.println("Duracion de la reserva: " + busqueda.getDuracion());
			System.out.println("Sede donde fue recogido: " + busqueda.getSedeInicial());
			System.out.println("Sede donde va a ser entregado: " + busqueda.getSedeFinal());
			System.out.println("Seguros Incluidos: " + busqueda.getSegurosIncluidos());
			System.out.println("Conductores adicionales: " + busqueda.getConductoresAdicionales());
			System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
			Vehiculo carro = busqueda.getCarro();
			System.out.println("Marca:" + carro.getMarca());
			System.out.println("Modelo:" + carro.getModelo());
			System.out.println("Placa:" + carro.getPlaca());
			System.out.println("+---------------------------------------+");

		}
	}

	public Vehiculo getCarro() {
		return carro;
	}

	public String darCategoria(int category) {
		String retorno = "Reserva no disponible";
		if (category == 1) {
			retorno = "Convertible";
		}
		if (category == 2) {
			retorno = "Camioneta";
		}
		if (category == 3) {
			retorno = "Campero";
		}
		if (category == 4) {
			retorno = "Sedan";
		}

		return retorno;
	}

	public void PedirTarjeta() {
		System.out.println("A continuacion ingrese los datos de su tarjeta: \n");
		String numero = input("Numero de tarejta");
		String nombreTitular = input("Nombre del titular");
		String fechaVencimiento = input("Fecha de vencimiento");
		Tarjeta aaa = new Tarjeta(numero, nombreTitular, fechaVencimiento);
	}

	public Boolean Truesacarcarro(Map<String, Vehiculo> carroMap, String placa, String sedeUser, String categoriaUser) {
		if (carroMap.containsKey(placa)) {
			Vehiculo carroarrendado = carroMap.get(placa);
			String categoria = carroarrendado.getCategoria();
			String sede = carroarrendado.getSedeActual();
			if (categoria.equals(categoriaUser) && sede.equals(sedeUser)) {
				return true;
			}

		}
		System.out.println("La placa ingresada no coincide con la sede o categoria del carro");
		return false;
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
