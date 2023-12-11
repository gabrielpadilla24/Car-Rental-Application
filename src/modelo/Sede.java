package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import procesamiento.Archivo;

public class Sede {
	private String nombre;
	private String ubicacion;
	private String horariosAtencion;
	private String empleados;

	public Sede(String elNombre, String laUbicacion, String loshorariosAtencion) {
		this.nombre = elNombre;
		this.ubicacion = laUbicacion;
		this.horariosAtencion = loshorariosAtencion;
		this.empleados = "";
		
	}

	
	

	public String darNombre() {
		return nombre;
	}

	public String darUbicacion() {
		return ubicacion;
	}

	public String darHorario() {
		return horariosAtencion;
	}

	

	public void NuevaSede(Map<String, Sede> sedeMap) {

		String elnombre = (input("Nombre de la sede -> "));
		String laubicacion = (input("Ubicacion de la sede -> "));
		String losHorarios = (input("Horario Atencion -> "));
		Sede nuevasede = new Sede(elnombre, laubicacion, losHorarios);
		sedeMap.put(elnombre, nuevasede);

	}

	public void ejecutarSede(Map<String, Sede> sedeMap) throws IOException {
		boolean continuar = true;
		while (continuar) {

			System.out.println("\n\n-------------Opciones de usuario-------------\n\n");
			PrintmenuSede();
			int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
			if (opcion_seleccionada == 1) {
				System.out.println("Opcion 1: Crear nueva sede");
				NuevaSede(sedeMap);
			}
			if (opcion_seleccionada == 2) {
				System.out.println("Opcion 2:  Consultar sedes");
				showSede(sedeMap);
				@SuppressWarnings("unused")
				String hacertiempo = (input("Click para continuar"));
			}
			if (opcion_seleccionada == 3) {
				System.out.println("Opcion 3:  Eliminar sede");
				Eliminarsede(sedeMap);
				@SuppressWarnings("unused")
				String hacertiempo = (input("Click para continuar"));
			}
			if (opcion_seleccionada == 4) {
				System.out.println("Opcion 4:  Sede especifica");
				SedeEspecifica(sedeMap);
				@SuppressWarnings("unused")
				String hacertiempo = (input("Click para continuar"));
			}
			if (opcion_seleccionada == 5) {
				System.out.println("Opcion 5:  Guardar Cambios en Archivo CSV");
				GuardarCambios(sedeMap);
				@SuppressWarnings("unused")
				String hacertiempo = (input("Se han guardado los cambios en el archivo Sedes.csv "
						+ "\n\nClick para continuar"));
				
			}
			if (opcion_seleccionada == 6) {
				System.out.println("Opcion 6:  Guardar Cambios en Archivo CSV");
				GuardarCambios(sedeMap);
				@SuppressWarnings("unused")
				String hacertiempo = (input("Se han guardado los cambios en el archivo Sedes.csv "
						+ "\n\nClick para continuar"));
			}

			if (opcion_seleccionada == 7) {
			}
			if (opcion_seleccionada == 8) {
				System.out.println("Opcion 8:  Por definir");
			}
			if (opcion_seleccionada == 9) {
				System.out.println("Saliendo del Menu...\n\n...");
				continuar = false;
			}

		}
	}

	public void Eliminarsede(Map<String, Sede> sedeMap) {
		String elnombre = (input("Nombre de la sede -> "));
		if (sedeMap.containsKey(elnombre)) {
			sedeMap.remove(elnombre);

		} else {
			System.out.println("No se ha encontrado la sede que desea buscar");
		}
	}

	public void PrintmenuSede() {

		{
			System.out.println("1.Crear nueva Sede\n2.Consultar Sedes\n3.Eliminar Sede"
					+ "\n4.Ver datos Sede especifica\n" + "5.Guardar Cambios en archivo CSV"
							+ "\n9.Salir al Menu principal\n");
		}

	}

	public void SedeEspecifica(Map<String, Sede> sedeMap) {
		String elnombre = (input("Nombre de la sede -> "));
		if (sedeMap.containsKey(elnombre)) {
			Sede sede = sedeMap.get(elnombre);
			System.out.println("\n+--------------------------------------+\n");
			System.out.println("Nombre Sede: " + sede.darNombre());
			System.out.println("Ubicacion Sede: " + sede.darUbicacion());
			System.out.println("Horario Sede: " + sede.darHorario());
			System.out.println("\n+--------------------------------------+\n");
		}

		else {
			System.out.println("No se ha encontrado la sede que desea buscar");
		}

	}

	

	public void showSede(Map<String, Sede> sedeMap) {
		System.out.println("\nSEDES ACTUALES: \n\n+--------------------------------------+\n");
		for (Map.Entry<String, Sede> entry : sedeMap.entrySet()) {
			Sede sede = entry.getValue();
			System.out.println("Nombre Sede: " + sede.darNombre());
			System.out.println("Ubicacion Sede: " + sede.darUbicacion());
			System.out.println("Horario Sede: " + sede.darHorario());
			System.out.println("Empleados Sede: " + sede.darEmpleados());
			System.out.println("\n+--------------------------------------+\n");
			

		}
	}

	public String darEmpleados() {
		return empleados;
	}

	
	
	public void setEmpleados(String empleados) {
		this.empleados = empleados;
	}
	
	@SuppressWarnings("static-access")
	public static void GuardarCambios(Map<String, Sede> sedeMap) throws IOException {
		Archivo funcionesArchivo = new Archivo();
		funcionesArchivo.VaciarCSVactual("./data/Sedes.csv");
		funcionesArchivo.subirsede("Nombre", "Ubicacion", "Horarios Atencion");
		
		for (String llaveSede:sedeMap.keySet()) {
			Sede sedeGuardada = sedeMap.get(llaveSede);
			String nombreSede = sedeGuardada.darNombre();
			String ubicacion = sedeGuardada.darUbicacion();
			String horario = sedeGuardada.darHorario();
			funcionesArchivo.subirsede(nombreSede, ubicacion, horario); 
					
			
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
