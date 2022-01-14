/**
 * 
 */
package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.ClienteRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;

/**
 * @author PC
 *
 */
@ExtendWith(MockitoExtension.class)
class ServicioClienteTest {
	
	@Mock
	private ClienteRepository clienteRepository;
	
	@InjectMocks
	private ServicioCliente servicioCliente;
	
	Cliente cliente1 = new Cliente();
	Cliente cliente2 = new Cliente();
	
	@BeforeEach
	void setUp() throws Exception {
		cliente1.setIdCliente(1);
		cliente1.setNombre("Alvaro");
		cliente1.setApellidos("Gonzalez Gonzalez");
		cliente1.setCorreo("gonzalez@gmail.com");
		cliente1.setTelefono("5512369874");
		cliente1.setUsuario("alvaGG");
		
		cliente2.setIdCliente(2);
		cliente2.setNombre("Fernando");
		cliente2.setApellidos("Mejia Perez");
		cliente2.setCorreo("fer@gmail.com");
		cliente2.setTelefono("5513467982");
		cliente2.setUsuario("ferMP");
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testGuardarCliente() {
		//1: corroborar que se guardan los clientes
		servicioCliente.guardarCliente(cliente1);
		//se corrobora que el cliente1 tiene el id 1
		assertTrue(cliente1 != null);
		
	}
	
	@Test
	void testValidaUsuario() {
		//1: corroborar que un usuario no existe
		Cliente cliente3 = new Cliente();
		
		cliente3.setIdCliente(3);
		cliente3.setNombre("Alejandro");
		cliente3.setApellidos("Medina Juarez");
		cliente3.setCorreo("alva@gmail.com");
		cliente3.setTelefono("5519487362");
		cliente3.setUsuario("alvaGG");
		
		when(clienteRepository.findByUsuario("alvaGG")).thenReturn(cliente3);
		assertTrue(servicioCliente.validaUsuario("alvaGG") != null);
		
		//2: corroborar que no se puede guardar un usuario como null
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			servicioCliente.validaUsuario(null);
		});
		
	}
	
	@Test
	void testRecuperaClientes() {
		//1: corroborar que regresa una lista vacia si no hay usuarios en la BD
		List<Cliente> clientes = servicioCliente.recuperaClientes();
		assertTrue(clientes.isEmpty());
		
		//2: corroborar que regresa una lista con clientes
		List<Cliente> listaClientes = new ArrayList<>();
		
		listaClientes.add(cliente1);
		listaClientes.add(cliente2);
		
		when(clienteRepository.findAll()).thenReturn(listaClientes);
		listaClientes =  servicioCliente.recuperaClientes();
		assertEquals(2, listaClientes.size());
	}
	
	@Test
	void testBuscaCliente() {
		when(clienteRepository.findByNombre("Alvaro")).thenReturn(cliente1);
		//1: corroborar que se encuantra a un cliente registrado
		assertTrue(servicioCliente.buscaCliente("Alvaro") != null);
		
		//2: Corroborar que regresa null si no se encuentra un cliente
		assertNull(servicioCliente.buscaCliente("Angel"));
		
		//3: Corroborar que regresa null cuando se le pasa null
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			servicioCliente.buscaCliente(null);
		});
	}
	
	@Test
	void testEliminarCliente() {
		List<String> lista = new ArrayList<>();
		
		//1: Corroborar que regresa true cuando se elimina un cliente
		assertTrue(servicioCliente.eliminarCliente(lista));
	}
	
	@Test
	void testActualizaCliente() {
		//1: corroborar que se pueden actualizar los datos de un cliente existente
		when(clienteRepository.findByNombre("Fernando")).thenReturn(cliente2);
		System.out.println(cliente2);
		cliente2 = servicioCliente.actualizaCliente("Fernando", "Mejia Perez", "mart@gmail.com", "5578963214", "mart");
		System.out.println(cliente2);
		assertTrue(cliente2.getUsuario() == "mart");
		
		//2: corroborar que no se pude modificar un cliente nulo
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			servicioCliente.actualizaCliente(null, null, null, null, null);
		});
	}
	
}
