package mx.uam.ayd.proyecto.negocio.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PruductoTest {

	DetalleVenta detalleVenta;
	private Producto producto1 = new Producto();
	private Producto producto2 = new Producto();
	@BeforeEach
	void setUp() throws Exception {
		// Este método se ejecuta antes de la ejecución
		// de cada metodo de prueba, es útil para
		// establecer las precondiciones
		detalleVenta = new DetalleVenta();
		
		// Producto CON Escasez
		producto1.setNombre("AJOLOTIUS");
		producto1.setCompuesto("Miel de abeja");
		producto1.setReceta("No");
		producto1.setUbicacion("Estante 2");
		producto1.setPiezas(2);
		producto1.setPrecio(50);

		//Producto SIN Escasez
		producto2.setNombre("DICLOFENACO");
		producto2.setCompuesto("Diclofenaco sodico");
		producto2.setReceta("No");
		producto2.setUbicacion("Estante 2");
		producto2.setPiezas(10);
		producto2.setPrecio(45);

	}

	@AfterEach
	void tearDown() throws Exception {
		// Este método se ejecuta después de la ejecución
		// de cada método de prueba, es útil para
		// dejar todo como estaba antes de la prueba
	}

	@Test
	void test() {
		Venta venta = new Venta();

		// Prueba 1: Corroborar que addDetslleVenta funciona correctamente si no hay
		// detalles de ventas agregadas
		boolean resultado = venta.addDetalleVenta(detalleVenta);

		assertEquals(true, resultado);

		// Prueba 2: Corroborar que no es posible agregar un mismo detalle de venta dos
		// veces

		resultado = venta.addDetalleVenta(detalleVenta);

		assertEquals(false, resultado);

		// Prueba 3: Corroborar que no se puede pasar null como parámetro

		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			venta.addDetalleVenta(null);

		});
	}
	
	@Test
	public final void testIsEscacez() {
		// Caso 1: Corroborar que regresa true cuando existe escasez en un producto
		assertTrue(producto1.isEscasez());
		
		// Caso 2: Corroborar que regresa false cuando No existe escasez en un producto
		assertFalse(producto2.isEscasez());
	}

}
