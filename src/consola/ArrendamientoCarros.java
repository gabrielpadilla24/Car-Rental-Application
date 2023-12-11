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
import consola.consolaAdministrador;
import consola.consolaCliente;
import consola.consolaEmpleado;

public class ArrendamientoCarros {

	Map<String, Sede> sedeMap = new HashMap<>();
	Map<String, Cliente> ClienteMap = new HashMap<>();
	Map<String, String> EmpleadoMap = new HashMap<>();
	Map<String, Reserva> ReservasClientes = new HashMap<>();
	Map<String, Vehiculo> carroMap = new HashMap<>();
	Map<String, String> UserAdminMap = new HashMap<>();
    public static Map<String, String> UserEmpleadoMap = new HashMap<>();

    static {
        UserEmpleadoMap.put("UsuarioEmpleado", "123Vaquitas");
    }

	public void ejecutar_aplicacion() throws FileNotFoundException, IOException {
		UserAdminMap.put("UsuarioAdministrador", "123Pollitos");
		UserEmpleadoMap.put("UsuarioEmpleado", "123Vaquitas");
		Archivo funciones = new Archivo();
		sedeMap = funciones.cargarSede("data/Sedes.csv");
		ClienteMap = funciones.cargarClientes("data/Clientes.csv");
		sedeMap = funciones.cargarEmpleadosaSede("data/Empleados.csv", sedeMap, EmpleadoMap);
		carroMap = funciones.cargarCarros("data/Carros.csv");

		boolean continuar = true;

		while (continuar)

		{
			System.out.println("\n\n-------------APLICACION ARRENDAMIENTO CARROS-------------\n\n");
			System.out.println("\nUSERS:\n1.Administrador\n2.Empleado\n3.Cliente\n9.Salir\n");
			int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione un tipo de usuario"));
			if (opcion_seleccionada == 1) {
				System.out.println("Opcion 1: Administrador");
				boolean checkAdmin = checkAdministrador();
				if (checkAdmin) {
					consolaAdministrador funcionesAdmin = new consolaAdministrador();
					funcionesAdmin.ejecutar_aplicacion(ClienteMap, ReservasClientes, sedeMap, carroMap, EmpleadoMap);
				}
			}
			if (opcion_seleccionada == 2) {
				System.out.println("Opcion 2:  Empleado");
				boolean checkEmpleado = checkEmpleado();
				if (checkEmpleado) {
					consolaEmpleado funcionesEmp = new consolaEmpleado();
					funcionesEmp.ejecutarEmpleado(ClienteMap, ReservasClientes, sedeMap, carroMap);
				}
			}

			if (opcion_seleccionada == 3) {
				System.out.println("Opcion 3: Cliente");
				consolaCliente funcionesCl = new consolaCliente();
				funcionesCl.ejecutar_aplicacion(ClienteMap, ReservasClientes, sedeMap, carroMap);
			}

			if (opcion_seleccionada == 9) {
				System.out.println("Saliendo de la app...\n\n...");
				continuar = false;
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		ArrendamientoCarros Ejecutador = new ArrendamientoCarros();
		Ejecutador.ejecutar_aplicacion();

	}

	public boolean checkAdministrador() {
		String usuario = input("User: ");
		if (UserAdminMap.containsKey(usuario)) {
			String clave = input("Clave: ");
			if (UserAdminMap.get(usuario).equals(clave)) {
				System.out.println("Bienvenido " + usuario);
				return true;
			}
		}
		System.out.println("Usuario no encontrado");

		return false;
	}

	public boolean checkEmpleado() {
		String usuario = input("User: ");
		if (UserEmpleadoMap.containsKey(usuario)) {
			String clave = input("Clave: ");
			if (UserEmpleadoMap.get(usuario).equals(clave)) {
				System.out.println("Bienvenido " + usuario);
				return true;
			}
		}
		System.out.println("Usuario no encontrado");

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