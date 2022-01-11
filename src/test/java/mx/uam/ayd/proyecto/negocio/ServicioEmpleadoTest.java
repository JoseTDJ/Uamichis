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

import mx.uam.ayd.proyecto.datos.EmpleadoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;

@ExtendWith(MockitoExtension.class)
class ServicioEmpleadoTest {
	
	@Mock
	private EmpleadoRepository empleadoRepository;
	
	
	@InjectMocks
	private ServicioEmpleado servicioEmpleado;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	/**
	 * Test del metodo agregarEmpleado
	 */

	@Test
	void testAgregarEmpleado() {
		//Prueba 1: Corroboro que se puede agregar un empleado
				Empleado empleado1;
				empleado1 = servicioEmpleado.agregarEmpleado("Paco", "Perez Hernandez", "empleado",23, "AV.Patito", "55564", "paco","Gonza23242");
				
				assertTrue(empleado1 !=null);
				
				
				//Prueba 2: Corroborar que no es posible agregar un mismo empleado
				Empleado empleado2;
				empleado2 = servicioEmpleado.agregarEmpleado("Paco", "Perez Hernandez", "empleado",23, "AV.Patito", "55564", "paco","Gonza23242");
						
				assertEquals(empleado2,empleado1);
				
			
				
	}

	/**
	 * Test del metodo actualizarEmpleado
	 */
	//@Test
	void testActualizarEmpleado() {
		//Prueba 1: Se actualiza los atributos de un empleado
		
				Empleado empleado = new Empleado();
				empleado = servicioEmpleado.actualizarEmpleado("Ximena", "Pereda Rodriguez", "empleado", 21, "AV. Patito", "342", "pac", "Pac214321");

				Empleado empleado1 = new Empleado();
		
				empleado1.setPassword("2324324");
				empleado1.setEdad(20);
		
				when(empleadoRepository.findByPassword("dasdasdas")).thenReturn(empleado1);
				empleado = servicioEmpleado.actualizarEmpleado("Ximena", "Pereda Rodriguez", "empleado", 22, "AV. Patito", "342", "pac", "Pac214321");
				
				assertEquals(empleado.getEdad(),empleado1.getEdad());
				
				

	}

	/**
	 * Test del metodo eliminarEmpleado
	 */
	@Test
	void testEliminarEmpleado() {
		List<String> lista = new ArrayList<>();
		
		//Prueba 1: Regresa null si no se a eliminado un empleado
		assertEquals(null, servicioEmpleado.eliminarEmpleado(lista));
		
		//Prueba 2: Regresa true si se elimino un empleado
		assertTrue(servicioEmpleado.eliminarEmpleado(lista));
	}

	/**
	 * Test del metodo validarContraseña
	 */
	@Test
	void testValidarContrasena() {
		Empleado empleado1 = new Empleado();
		empleado1.setPassword("98765432");
		
		when(empleadoRepository.findByPassword("98765432")).thenReturn(empleado1);
		
		//Prueba 1: Corroborar que se la contraseña del empelado sea unica
				//Si llega a encontrar la contraseña
		Empleado empleado2 = new Empleado();
		empleado2 = servicioEmpleado.validarContrasena("98765432");
		assertEquals(empleado1, empleado2);
		
		//Prueba 2: Corroborar que regrese un null si no es correcta la contraseña
		
		empleado2 = servicioEmpleado.validarContrasena("876544");
		assertNull(empleado2);
		

		
		//Prueba 3: Corroborar que no se puede pasar null como parámetro
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicioEmpleado.validarContrasena(null);
		});
	}

	/**
	 * Test del metodo buscarEmpleado
	 */
	@Test
	void testBuscarEmpleado() {
		Empleado empleado1 = new Empleado();
		
		empleado1.setNombre("Ximena");
		empleado1.setApellido("Pereda Rodriguez");
		empleado1.setEdad(20);
		empleado1.setDireccion("AV.Patito");
		empleado1.setTelefono("55677853");
		empleado1.setPassword("1234567894");
		
		when(empleadoRepository.findByNombre("Ximena")).thenReturn(empleado1);
		
		// Prueba 1: El método regresa al empleado 
				//si lo encuentra
				Empleado e = new Empleado();
				e=servicioEmpleado.buscarEmpleado("Ximena");
				
				assertEquals(empleado1, e);

		
		// Prueba 2: El método regresa null si no se encuentra el empleado
				e = servicioEmpleado.buscarEmpleado("Juan");
				assertNull(e);
				
		//Prueba 3: El método se le pasa como parametro un null
				Assertions.assertThrows(IllegalArgumentException.class, () -> {

					servicioEmpleado.buscarEmpleado(null);
					

				});
	}

}
