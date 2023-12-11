package pruebas;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import modelo.Sede;

public class SedeTest {
	private Sede sede;
	private Map<String, Sede> sedeMap;
	
	@Before
    public void setUp() throws IOException {
		sede = new Sede(null, null, null);
		sedeMap = new HashMap<>();
		Sede sedeprueba = new Sede("Sede Prueba", "Bogota", "7am-9pm");
		sedeMap.put("Sede Prueba", sedeprueba);
	}
	@Test
    public void testdarNombre() throws IOException {

        assertEquals("Sede Prueba", sedeMap.get("Sede Prueba").darNombre());

    }
	
	@Test
    public void testdarUbicacion() throws IOException {

        assertEquals("Bogota", sedeMap.get("Sede Prueba").darUbicacion());

    }
	
	@Test
    public void testdarHorario() throws IOException {

        assertEquals("7am-9pm", sedeMap.get("Sede Prueba").darHorario());

    }
	
	@Test
    public void testNuevaSede() {

        String input = "NuevaSede\nNuevaUbicacion\nNuevoHorario";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        sede.NuevaSede(sedeMap);

        assertTrue(sedeMap.containsKey("NuevaSede"));
        Sede nuevaSede = sedeMap.get("NuevaSede");
        String nombre = nuevaSede.darNombre();
        
        assertEquals("NuevaSede", sedeMap.get(nombre).darNombre());

        System.setIn(System.in);
    }

}
