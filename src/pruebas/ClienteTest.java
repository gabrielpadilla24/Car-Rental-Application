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

import modelo.Cliente;

public class ClienteTest {

    private Cliente cliente;
    private Map<String, Cliente> clienteMap;

    @Before
    public void setUp() throws IOException {
        cliente = new Cliente(null, null, null, null, null);
        clienteMap = new HashMap<>();
        cliente.NuevoClienteInterfaz(clienteMap, "Prueba Cliente", "3173368349", "01/01/1990", "Colombia", "1000505430");
        
    }

    @Test
    public void testdarNombre() throws IOException {

        assertEquals("Prueba Cliente", clienteMap.get("1000505430").darNombre());

    }
    
    @Test
    public void testdarDocumento() throws IOException {

        assertEquals("1000505430", clienteMap.get("1000505430").darDocumento());
  
    }
    
    @Test
    public void testdarContacto() throws IOException {

        assertEquals("3173368349", clienteMap.get("1000505430").darContacto());
    }
    
    @Test
    public void testContainer() throws IOException {
        assertTrue(clienteMap.containsKey("1000505430"));
    }

    @Test
    public void testBuscarCliente() {
    	
    	Cliente sampleCliente = new Cliente("Sample", "987654321", "02/02/1995", "Canada", "987654321");
        clienteMap.put("987654321", sampleCliente);

        String input = "987654321";
        System.setIn(new ByteArrayInputStream(input.getBytes()));


        cliente.BuscarCliente(clienteMap);

    }
    
    @Test
    public void testEliminarCliente() {
    	
    	Cliente sampleCliente = new Cliente("Sample", "987654321", "02/02/1995", "Canada", "987654321");
        clienteMap.put("987654321", sampleCliente);

        String input = "987654321";
        System.setIn(new ByteArrayInputStream(input.getBytes()));


        cliente.EliminarCliente(clienteMap);

    }


    @After
    public void restoreSystemInputOutput() {
        System.setIn(System.in);
    }
}
