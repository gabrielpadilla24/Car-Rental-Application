package pruebas;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import modelo.Vehiculo;

public class VehiculoTest {

    private Vehiculo vehiculo;
    private Map<String, Vehiculo> vehiculoMap;

    @Before
    public void setUp() {
        vehiculo = new Vehiculo(null, null, null, null, null, null, null, null);
        vehiculoMap = new HashMap<>();
        vehiculoMap.put("Placa1", new Vehiculo("Placa1", "Marca1", "Modelo1", "Color1", "Categoria1", "Sede1", "Tipo1", "Prima1"));
        vehiculoMap.put("Placa2", new Vehiculo("Placa2", "Marca2", "Modelo2", "Color2", "Categoria2", "Sede2", "Tipo2", "Prima2"));
    }

    

    @Test
    public void testShowCarroEspecifico() {
        // Mock user input for testing
        String input = "Placa1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Run the method to test
        vehiculo.showCarroEspecifico(vehiculoMap, "Placa1");

        // Reset System.in after the test
        System.setIn(System.in);
    }
    
    @Test
    public void TestGetters() {
    	assertEquals("Categoria1", vehiculoMap.get("Placa1").getCategoria());
    	assertEquals("", vehiculoMap.get("Placa1").getHistorial());
    	assertEquals("disponible", vehiculoMap.get("Placa1").getEstado());
    	assertEquals("Color1", vehiculoMap.get("Placa1").getColor());
    	assertEquals("Modelo1", vehiculoMap.get("Placa1").getModelo());

    }

    


}
