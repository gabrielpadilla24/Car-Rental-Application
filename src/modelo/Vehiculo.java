package modelo;

import procesamiento.Archivo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

public class Vehiculo {
	private String placa;
	private String marca;
	private String modelo;
	private String color;
	private String categoria;
	private String estado;
	private String sedeActual;
	private String historial;
	private String tipo;
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPrimaSeguro() {
		return primaSeguro;
	}

	public void setPrimaSeguro(String primaSeguro) {
		this.primaSeguro = primaSeguro;
	}


	private String primaSeguro;
	
	public void ejecutarVehiculos(Map<String, Vehiculo> carroMap)
	{
		boolean continuar = true;
		while (continuar) {

			System.out.println("\n\n-------------Opciones de usuario-------------\n\n");
			System.out.println("1.Consultar Inventario Total de Vehiculos (Son mas de 100)"
					+ "\n2.Consultar Vehiculos de Sede Especifica"
					+ "\n3.Consultar Vehiculos de Categoria Especifica"
					+ "\n4.Consultar Vehiculo Especifico"
					+ "\n5.Añadir Vehiculo al Inventario"
					+ "\n6.Eliminar Vehiculo del Inventario"
					+ "\n7.Agregar evento al historial "
					+ "\n8.Salir del Menu de Vehiculos"
					);
			int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
			if (opcion_seleccionada == 1) {
				System.out.println("Opcion 1: Consultar Inventario de Vehiculos");
				showInventario(carroMap);
				@SuppressWarnings("unused")
				String hacertiempo = (input("Click para continuar"));
			}
			if (opcion_seleccionada == 2) {
				System.out.println("Opcion 2:  Consultar Vehiculos de Sede Especifica");
				String sede = input("\n\nIngrese Sede a buscar ->");
				showCarrosSede(carroMap,sede);
				@SuppressWarnings("unused")
				String hacertiempo = (input("Click para continuar"));
			}
			if (opcion_seleccionada == 3) {
				System.out.println("Opcion 3: Consultar Vehiculos de Categoria Especifica");
				String categoria = input("\n\nIngrese Categoria a buscar ->");
				showCarrosCategoria(carroMap,categoria);
				@SuppressWarnings("unused")
				String hacertiempo = (input("Click para continuar"));
			}
			if (opcion_seleccionada == 4) {
				System.out.println("Opcion 4: Consultar Vehiculo Especifico");
				String placa = input("\n\nIngrese Placa del Vehiculo ->");
				showCarroEspecifico(carroMap,placa);
				@SuppressWarnings("unused")
				String hacertiempo = (input("Click para continuar"));
			}
			if (opcion_seleccionada == 5) {
				System.out.println("Opcion 5: Añadir Vehiculo al Inventario");
				crearNuevoVehiculo(carroMap);
				
			}
			if (opcion_seleccionada == 6) {
				System.out.println("Opcion 6: Eliminar Vehiculo del Inventario");
				String placa = input("Ingrese placa ");
				if (carroMap.containsKey(placa)) {
					carroMap.remove(placa);
					System.out.println("Carro eliminado");
				}
				else
				{System.out.println("No se ha encontrado la placa");}
				
				@SuppressWarnings("unused")
				String hacertiempo = (input("Se han guardado los cambios en el archivo Sedes.csv "
						+ "\n\nClick para continuar"));
			}

			if (opcion_seleccionada == 7) {
				String placa = input("Ingrese placa ");
				agregarHistorial(carroMap, placa);
			}
			
			if (opcion_seleccionada == 8) {
				System.out.println("Saliendo del Menu...\n\n...");
				continuar = false;
			}
			
			
		}
		
		
		
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setSedeActual(String sedeActual) {
		this.sedeActual = sedeActual;
	}
	

	public Vehiculo (String laplaca,String lamarca, String elmodelo,String elcolor,
			String lacategoria, String laSede, String Eltipo, String Laprima) {
		this.placa = laplaca;
		this.marca = lamarca;
		this.modelo = elmodelo;
		this.color = elcolor;
		this.categoria = lacategoria;
		this.sedeActual = laSede;
		this.tipo= Eltipo;
		this.primaSeguro = Laprima;
		this.estado = "disponible";
		this.historial = "";	
	}
	
	public void crearNuevoVehiculo(Map<String, Vehiculo> carroMap) {
		String placa = (input("Placa del vehiculo "));
		String marca = (input("Marca del vehiculo "));
		String modelo = (input("Modelo del vehiculo "));
		String color = (input("Color del vehiculo "));
		String categoria = (input("Categoria del vehiculo "));
		String sede = (input("Sede del vehiculo "));
		String tipo= (input("Tipo del vechivulo "));
		String prima=(input("Porcentaje adicional de la prima del seguro "));
		
		Vehiculo nuevoVehiculo = new Vehiculo (placa,marca,modelo,color,categoria,sede,tipo,prima);
		carroMap.put(placa, nuevoVehiculo);
		
	}
	
	
	
	


	
	public void showCarrosDisponibles2(Map<String, Vehiculo> carroMap,String categoriabuscada,
			String sedebuscada) 
	{
	System.out.println("\n\nCARROS DISPONIBLES QUE CUMPLEN CON CATEGORIA Y SEDE:\n\n");
	System.out.println("+---------------------------------------+");
	for (Map.Entry<String, Vehiculo> entry : carroMap.entrySet())
	{
		Vehiculo carro = entry.getValue();
		if (carro.getCategoria().equals(categoriabuscada)&&carro.getSedeActual().equals(sedebuscada))
		 {
			System.out.println("Marca:"+carro.getMarca());
			System.out.println("Modelo:"+carro.getModelo());
			System.out.println("Placa:"+carro.getPlaca());
			System.out.println("+---------------------------------------+");
		}
 	}
 	}
	
	public void showCarrosSede(Map<String, Vehiculo> carroMap,
			String sedebuscada) 
	{
	System.out.println("\n\nCARROS DISPONIBLES QUE CUMPLEN CON CATEGORIA Y SEDE:\n\n");
	System.out.println("+---------------------------------------+");
	for (Map.Entry<String, Vehiculo> entry : carroMap.entrySet())
	{
		Vehiculo carro = entry.getValue();
		if (carro.getSedeActual().equals(sedebuscada))
		 {
			System.out.println("Marca:"+carro.getMarca());
			System.out.println("Modelo:"+carro.getModelo());
			System.out.println("Placa:"+carro.getPlaca());
			System.out.println("Categoria:"+carro.getCategoria());
			System.out.println("+---------------------------------------+");
		}
 	}
 	}
	
	public void showCarrosCategoria(Map<String, Vehiculo> carroMap,
			String categoriabuscada) 
	{
	System.out.println("\n\nCARROS DISPONIBLES QUE CUMPLEN CON CATEGORIA Y SEDE:\n\n");
	System.out.println("+---------------------------------------+");
	for (Map.Entry<String, Vehiculo> entry : carroMap.entrySet())
	{
		Vehiculo carro = entry.getValue();
		if (carro.getCategoria().equals(categoriabuscada))
		 {
			System.out.println("Marca:"+carro.getMarca());
			System.out.println("Modelo:"+carro.getModelo());
			System.out.println("Placa:"+carro.getPlaca());
			System.out.println("Sede:"+carro.getSedeActual());
			System.out.println("+---------------------------------------+");
		}
 	}
 	}
	
	public void showInventario(Map<String, Vehiculo> carroMap)
	{
		System.out.println("\n\nINVENTARIO TOTAL:\n\n");
		for (Map.Entry<String, Vehiculo> entry : carroMap.entrySet())
		{
			Vehiculo carro = entry.getValue();
				System.out.println("Marca:"+carro.getMarca());
				System.out.println("Modelo:"+carro.getModelo());
				System.out.println("Placa:"+carro.getPlaca());
				System.out.println("+---------------------------------------+");
	 	}
	}
	
	public void showCarroEspecifico(Map<String, Vehiculo> carroMap,String placa)
	{
		System.out.println("\n\n"+placa+"\n\n");
		if (carroMap.containsKey(placa)){
			Vehiculo carro = carroMap.get(placa);
			System.out.println("Marca:"+carro.getMarca());
			System.out.println("Modelo:"+carro.getModelo());
			System.out.println("Color:"+carro.getColor());
			System.out.println("Placa:"+carro.getPlaca());
			System.out.println("Sede:"+carro.getSedeActual());
			System.out.println("Categoria:"+carro.getCategoria());
			System.out.println("Historial:"+carro.getHistorial());
			
			
			
			System.out.println("+---------------------------------------+");
 	}
		else
		{System.out.println("No se ha encontrado el carro");}
		
		
	}
	
	public void agregarHistorial(Map<String, Vehiculo> carroMap,String placa)
	{
		System.out.println("\n\n"+placa+"\n\n");
		if (carroMap.containsKey(placa)){
			Vehiculo carro = carroMap.get(placa);
			String historialcarro = carro.getHistorial();
			String siniestro = input("Ingrese nuevo evento en historial");
			historialcarro+=", "+siniestro;
			carro.setHistorial(historialcarro);
			
 	}
		else
		{System.out.println("No se ha encontrado el carro");}
		
		
	}
	
	
	
	// Métodos adicionales

    public void showInventarioDialog(Map<String, Vehiculo> vehiculoMap) {
        StringBuilder inventario = new StringBuilder("Inventario Total:\n");
        for (Vehiculo vehiculo : vehiculoMap.values()) {
            inventario.append("Marca: ").append(vehiculo.getMarca()).append("\n");
            inventario.append("Modelo: ").append(vehiculo.getModelo()).append("\n");
            inventario.append("Placa: ").append(vehiculo.getPlaca()).append("\n");
            inventario.append("------------------------------\n");
        }
        JOptionPane.showMessageDialog(null, inventario.toString(), "Inventario de Vehículos", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showCarrosSedeDialog(Map<String, Vehiculo> vehiculoMap, String sede) {
        StringBuilder carrosSede = new StringBuilder("Vehículos en la Sede " + sede + ":\n");
        for (Vehiculo vehiculo : vehiculoMap.values()) {
            if (vehiculo.getSedeActual().equals(sede)) {
                carrosSede.append("Marca: ").append(vehiculo.getMarca()).append("\n");
                carrosSede.append("Modelo: ").append(vehiculo.getModelo()).append("\n");
                carrosSede.append("Placa: ").append(vehiculo.getPlaca()).append("\n");
                carrosSede.append("------------------------------\n");
            }
        }
        JOptionPane.showMessageDialog(null, carrosSede.toString(), "Vehículos por Sede", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showCarrosCategoriaDialog(Map<String, Vehiculo> vehiculoMap, String categoria) {
        StringBuilder carrosCategoria = new StringBuilder("Vehículos de la Categoría " + categoria + ":\n");
        for (Vehiculo vehiculo : vehiculoMap.values()) {
            if (vehiculo.getCategoria().equals(categoria)) {
                carrosCategoria.append("Marca: ").append(vehiculo.getMarca()).append("\n");
                carrosCategoria.append("Modelo: ").append(vehiculo.getModelo()).append("\n");
                carrosCategoria.append("Placa: ").append(vehiculo.getPlaca()).append("\n");
                carrosCategoria.append("------------------------------\n");
            }
        }
        JOptionPane.showMessageDialog(null, carrosCategoria.toString(), "Vehículos por Categoría", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showCarroEspecificoDialog(Map<String, Vehiculo> vehiculoMap, String placa) {
        if (vehiculoMap.containsKey(placa)) {
            Vehiculo vehiculo = vehiculoMap.get(placa);
            StringBuilder infoVehiculo = new StringBuilder("Información del Vehículo " + placa + ":\n");
            infoVehiculo.append("Marca: ").append(vehiculo.getMarca()).append("\n");
            infoVehiculo.append("Modelo: ").append(vehiculo.getModelo()).append("\n");
            infoVehiculo.append("Color: ").append(vehiculo.getColor()).append("\n");
            infoVehiculo.append("Placa: ").append(vehiculo.getPlaca()).append("\n");
            infoVehiculo.append("Sede: ").append(vehiculo.getSedeActual()).append("\n");
            infoVehiculo.append("Categoría: ").append(vehiculo.getCategoria()).append("\n");
            infoVehiculo.append("Historial: ").append(vehiculo.getHistorial()).append("\n");
            infoVehiculo.append("Tipo: ").append(vehiculo.getTipo()).append("\n");
            infoVehiculo.append("% Seguro prima: ").append(vehiculo.getPrimaSeguro()).append("\n");

            JOptionPane.showMessageDialog(null, infoVehiculo.toString(), "Información del Vehículo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un vehículo con la placa ingresada.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void crearNuevoVehiculoDialog(Map<String, Vehiculo> vehiculoMap) {
        String placa = JOptionPane.showInputDialog("Placa del vehículo:");
        String marca = JOptionPane.showInputDialog("Marca del vehículo:");
        String modelo = JOptionPane.showInputDialog("Modelo del vehículo:");
        String color = JOptionPane.showInputDialog("Color del vehículo:");
        String categoria = JOptionPane.showInputDialog("Categoría del vehículo:");
        String sede = JOptionPane.showInputDialog("Sede del vehículo:");
        String tipo = JOptionPane.showInputDialog("Tipo de vehiculo:");
        String prima = JOptionPane.showInputDialog("Porcentaje adicional de la prima del seguro:");
        
        Vehiculo nuevoVehiculo = new Vehiculo(placa, marca, modelo, color, categoria, sede,tipo, prima);
        vehiculoMap.put(placa, nuevoVehiculo);
        JOptionPane.showMessageDialog(null, "Vehículo añadido al inventario.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }




	
	
	
	public void setHistorial(String historial) {
		this.historial = historial;
	}

	public String getColor() {
		return color;
	}

	public String getHistorial() {
		return historial;
	}

	public String getPlaca() {
		return placa;
	}
	public String getModelo() {
		return modelo;
	}

	public String getMarca() {
		return marca;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getSedeActual() {
		return sedeActual;
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


