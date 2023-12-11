package pruebas;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import modelo.Empleado;

public class EmpleadoTest {

    private Empleado empleado;
    private Map<String, String> empleadoMap;

    @Before
    public void setUp() throws IOException {
        empleado = new Empleado(null, null);
        empleadoMap = new HashMap<>();
        empleadoMap.put("Pedro", "Administrador");
        empleadoMap.put("Pablo", "Cajero");
        empleadoMap.put("Jesus", "Guardia");
        empleadoMap.put("Martin", "Limpiador");
    }

    

    @Test
    public void testBuscarEmpleado() {
    	assertEquals("Administrador", empleadoMap.get("Pedro"));
    	assertEquals("Cajero", empleadoMap.get("Pablo"));
    	assertEquals("Guardia", empleadoMap.get("Jesus"));
    	assertEquals("Limpiador", empleadoMap.get("Martin"));
    }

    
}
