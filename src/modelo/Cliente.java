package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import procesamiento.Archivo;
import procesamiento.Reserva;

public class Cliente {

private String nombre;
private String numContacto;
private String fechaNacimiento;
private String nacionalidad;
private String documentoIdentidad;
private Boolean licenciaConduccion;



public void ejecutarCliente(Map<String, Cliente> ClienteMap,Map<String, Reserva> ReservasCliente,Map<String, Sede> sedeMaps,
		Map<String, Vehiculo> carroMap) throws IOException {
	boolean continuar = true;
	while (continuar) {

		System.out.println("\n\n-------------Opciones de usuario-------------\n\n");
		PrintmenuCliente();
		int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
		if (opcion_seleccionada == 1) {
			System.out.println("Opcion 1: Crear nuevo cliente");
			NuevoCliente(ClienteMap);
			@SuppressWarnings("unused")
			String hacertiempo = (input("Cliente Creado. Click para continuar"));
		}
		if (opcion_seleccionada == 2) {
			System.out.println("Opcion 2:  Buscar Cliente");
			BuscarCliente(ClienteMap);
			@SuppressWarnings("unused")
			String hacertiempo = (input("Click para continuar"));
		}
		if (opcion_seleccionada == 3) {
			System.out.println("Opcion 3:  Eliminar Cliente");
			EliminarCliente(ClienteMap);
			@SuppressWarnings("unused")
			String hacertiempo = (input("Cliente Eliminado.Click para continuar"));
		}
		if (opcion_seleccionada == 4) {
			System.out.println("Opcion 4:  Clientes Actuales");
			showClientes(ClienteMap);
			@SuppressWarnings("unused")
			String hacertiempo = (input("Click para continuar"));
			
		}
		if (opcion_seleccionada == 5) {
			System.out.println("Opcion 5:  Generar Reserva");
			GenerarReserva2(ClienteMap, ReservasCliente, sedeMaps, carroMap);
		}
		if (opcion_seleccionada == 6) {
			System.out.println("Opcion 6:  Mostrar Reservas");
			ShowReservas(ReservasCliente);
		}

		if (opcion_seleccionada == 7) {System.out.println("Opcion 7:  Guardar cambios en CSV");
		GuardarCambiosClientes(ClienteMap);
		}
		if (opcion_seleccionada == 8) {
			System.out.println("Opcion 8:  Por definir");
			
		}
		if (opcion_seleccionada == 9) {
			System.out.println("Saliendo de la app...\n\n...");
			continuar = false;
		}

	}
}



public void PrintmenuCliente() {
	{
		System.out.println("1.Crear nuevo Cliente\n2.Buscar Cliente\n3.Eliminar Cliente"
				+ "\n4.Mostrar todos los clientes\n" + "5.Generar nueva reserva\n6.Ver reservas actuales\n7.Guardar cambios en CSV"
				+ "\n9.Salir al Menu principal\n");
	}
	
}



public Cliente(String elnombre, String elcontacto, String lafechaNacimiento, String lanacionalidad, 
		String eldocumentoIdentidad) {
	super();
	this.nombre = elnombre;
	this.numContacto = elcontacto;
	this.fechaNacimiento = lafechaNacimiento;
	this.nacionalidad = lanacionalidad;
	this.documentoIdentidad = eldocumentoIdentidad;
	this.licenciaConduccion = true;
}

public String darNombre() {
	return nombre;
}

public String darContacto() {
	return numContacto;
}

public String darFechaNacimiento() {
	return fechaNacimiento;
}

public String darNacionalidad() {
	return nacionalidad;
}
public String darDocumento() {
	return documentoIdentidad;
}

public Boolean darLicencia() {
	return licenciaConduccion;
}

public void NuevoCliente(Map<String, Cliente> ClienteMap) {
	String elnombre = (input("Nombre del cliente -> "));
	String elnumero = (input("Numero de contacto dle cliente -> "));
	String lafechaNacimiento = (input("Fecha de nacimiento (dd/mm/aaaa) -> "));
	String lanacionalidad = (input("Nacionalidad -> "));
	String eldocumento= (input("Documento Identidad -> "));
	Cliente nuevoCliente = new Cliente(elnombre,elnumero,lafechaNacimiento,lanacionalidad,eldocumento);
	ClienteMap.put(eldocumento, nuevoCliente);
	System.out.println("Se ha guardado exitosamente el cliente, para buscarlo use su numero de identidad");
}

public void NuevoClienteInterfaz(Map<String, Cliente> ClienteMap, String elnombre, String elcontacto, String lafechaNacimiento, String lanacionalidad, 
		String eldocumentoIdentidad) throws IOException {
	
	Cliente nuevoCliente = new Cliente(elnombre,elcontacto,lafechaNacimiento,lanacionalidad,eldocumentoIdentidad);
	ClienteMap.put(eldocumentoIdentidad, nuevoCliente);
	GuardarCambiosClientes(ClienteMap);
}

public void BuscarCliente(Map<String, Cliente> ClienteMap) {
	String eldocumento= (input("Ingrese el documento de identidad del cliente -> "));
	if (ClienteMap.containsKey(eldocumento)){
		Cliente busqueda = ClienteMap.get(eldocumento);
		System.out.println("\n+--------------------------------------+\n");
		System.out.println("Nombre Cliente: " + busqueda.darNombre());
		System.out.println("Numero de contacto: " + busqueda.darContacto());
		System.out.println("Fecha Nacimiento: " + busqueda.darFechaNacimiento());
		System.out.println("Documento Identidad: " + busqueda.darDocumento());
		System.out.println("Pase Activo: " + busqueda.darLicencia());
		System.out.println("\n+--------------------------------------+\n");
	}
	else {
		System.out.println("Cliente no se ha encontrado");
	}
	
}

public void showClientes(Map<String, Cliente> ClienteMap) {
	System.out.println("\nSEDES ACTUALES: \n\n+--------------------------------------+\n");
	for (Map.Entry<String, Cliente> entry : ClienteMap.entrySet()) {
		Cliente busqueda = entry.getValue();
		System.out.println("\n+--------------------------------------+\n");
		System.out.println("Nombre Cliente: " + busqueda.darNombre());
		System.out.println("Numero de contacto: " + busqueda.darContacto());
		System.out.println("Fecha Nacimiento: " + busqueda.darFechaNacimiento());
		System.out.println("Documento Identidad: " + busqueda.darDocumento());
		System.out.println("Pase Activo: " + busqueda.darLicencia());
		System.out.println("\n+--------------------------------------+\n");

	}
}

public void EliminarCliente(Map<String, Cliente> ClienteMap) {
	String eldocumento= (input("Ingrese el documento de identidad del cliente -> "));
	if (ClienteMap.containsKey(eldocumento)) {
		ClienteMap.remove(eldocumento);

	} else {
		System.out.println("No se ha encontrado la sede que desea buscar");
	}
}


public void GenerarReserva2(Map<String, Cliente> ClienteMap,Map<String, Reserva> ReservasClientes,Map<String, Sede> sedeMap,
		Map<String, Vehiculo> carroMap) {
	
	Reserva funcionesReserva = new Reserva(null, null, 0, null, null, null, 0,null);
	funcionesReserva.NuevaReserva2(ReservasClientes, ClienteMap,sedeMap, carroMap);
}

public void ShowReservas(Map<String, Reserva> ReservasClientes) {
	Reserva funcionesReserva = new Reserva(null, null, 0, null, null, null, 0,null);
	funcionesReserva.showReservas(ReservasClientes);
}

@SuppressWarnings("static-access")
public void GuardarCambiosClientes(Map<String, Cliente> ClienteMap) throws IOException {
	Archivo funcionesArchivo = new Archivo();
	funcionesArchivo.VaciarCSVactual("./data/Clientes.csv");
	funcionesArchivo.subirClientes("Nombre", "NumContacto","FechaNacimiento", "Nacionalidad", "DocumentoIdentidad");
	
	for (String llaveCliente:ClienteMap.keySet()) {
		Cliente ClienteGuardado = ClienteMap.get(llaveCliente);
		String nombreCliente = ClienteGuardado.darNombre();
		String numContacto = ClienteGuardado.darContacto();
		String fechaNacimiento = ClienteGuardado.darFechaNacimiento();
		String nacionalidad = ClienteGuardado.darNacionalidad();
		String documento = ClienteGuardado.darDocumento();
		funcionesArchivo.subirClientes(nombreCliente, numContacto,fechaNacimiento, nacionalidad, documento);
		
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