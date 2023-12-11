package procesamiento;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import modelo.Cliente;
import modelo.Empleado;
import modelo.Sede;
import modelo.Vehiculo;

public class Archivo {
	//* Creo que es mas facil solo crear un atributo privado para cada archivo que vayamos a guardar
	
	public static Map<String, Sede> cargarSede (String nombreArchivo) throws FileNotFoundException, IOException{
		Map<String, Sede> lasSedes = new HashMap<>();;
		
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
		String linea;
		linea = br.readLine();
		linea = br.readLine();
		while (linea != null) {
			String[] partes = linea.split(",");
			String nombreSede = partes[0];
			String UbicacionSede = partes[1];
			String HorariosSede = partes[2];
			Sede nuevaSede = new Sede(nombreSede,UbicacionSede,HorariosSede);
			
			linea = br.readLine();
			lasSedes.put(nombreSede, nuevaSede);
		}
		br.close();
		
		
		
		
		return lasSedes;
	}
	
	public static Map<String, Vehiculo> cargarCarros (String nombreArchivo) throws FileNotFoundException, IOException{
		Map<String, Vehiculo> losCarros = new HashMap<>();;
		
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
		String linea;
		linea = br.readLine();
		linea = br.readLine();
		while (linea != null) {
			String[] partes = linea.split(";");
			String placa = partes[0];
			String marca = partes[1];
			String modelo = partes[2];
			String color = partes[3];
			String categoria = partes[4];
			String sede = partes[5];
			String tipo = partes[6];
			String PorcentajePrima = partes[7];
			
			Vehiculo nuevocarro = new Vehiculo (placa,marca,modelo,color,categoria,sede,tipo,PorcentajePrima);
			
			linea = br.readLine();
			losCarros.put(placa, nuevocarro);
		}
		br.close();
		
		
		
		
		return losCarros;
	}
	
	public static Map<String, Cliente> cargarClientes (String nombreArchivo) throws FileNotFoundException, IOException{
		Map<String, Cliente> losClientes = new HashMap<>();;
		
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
		String linea;
		linea = br.readLine();
		//No es un error de repetir lineas, estoy ignorando la primera linea
		linea = br.readLine();
		while (linea != null) {
			String[] partes = linea.split(",");
			String nombre = partes[0];
			String numContacto = partes[1];
			String fechaNacimiento = partes[2];
			String nacionalidad = partes[3];
			String documento = partes[4];
			Cliente nuevoCliente = new Cliente(nombre,numContacto,fechaNacimiento,nacionalidad,documento);
			losClientes.put(documento, nuevoCliente);
			
			
			linea = br.readLine();
		}
		br.close();
		
		
		
		
		return losClientes;
	}

	public static Map<String, Sede> cargarEmpleadosaSede (String nombreArchivo,Map<String, Sede> sedeMap,Map<String, String> EmpleadoMap) throws FileNotFoundException, IOException{
		
		
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
		String linea;
		linea = br.readLine();
		//No es un error de repetir lineas, estoy ignorando la primera linea
		linea = br.readLine();
		while (linea != null) {
			String[] partes = linea.split(";");
			String nombre = partes[0];
			String cargo = partes[1];
			String sede = partes[2];
			Empleado nuevoEmpleado = new Empleado(nombre,cargo);
			Sede editable = sedeMap.get(sede);
			EmpleadoMap.put(nombre, cargo);
			String empleadosSede = editable.darEmpleados();
			if (empleadosSede!="") {empleadosSede+=",";}
			empleadosSede +=(nombre+"("+cargo+")");
			editable.setEmpleados(empleadosSede);
			linea = br.readLine();
		}
		br.close();
		return sedeMap;
	
}
	
	public static void VaciarCSVactual(String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, false); 
        fileWriter.close();
    }
	
	public static void subirsede (String nombre, String ubicacion, String horario) {

        FileWriter fw = null;
        try {
            fw = new FileWriter("./data/Sedes.csv", true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedWriter bw = new BufferedWriter(fw);
        String data = nombre+","+ubicacion+","+horario
        		;
        try {
        	
            bw.write(data);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
	public static void subirClientes (String nombre, String numcontacto, String nacimiento, String nacionalidad, String documento) {

        FileWriter fw = null;
        try {
            fw = new FileWriter("./data/Clientes.csv", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        
        String data = nombre+","+numcontacto+","+nacimiento+","+nacionalidad+","+documento
        		;
        try {
        	
            bw.write(data);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // close the writer
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
	
	
}

