package mx.uam.ayd.proyecto.negocio.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VentaTest {

	DetalleVenta detalleVenta;

	@BeforeEach
	void setUp() throws Exception {
		detalleVenta = new DetalleVenta();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAddDetalleVenta() {
		Venta venta = new Venta();
		

		// Prueba 1: Corroborar que addDetslleVenta funciona correctamente si no hay
		// detalles de ventas agregadas
		boolean resultado = venta.addDetalleVenta(detalleVenta);

		assertEquals(true, resultado);

		// Prueba 2: Corroborar que no es posible agregar un mismo detalle de venta dos veces
		
		resultado = venta.addDetalleVenta(detalleVenta);

		assertEquals(false, resultado);
		
		// Prueba 3: Corroborar que no se puede pasar null como parámetro
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			venta.addDetalleVenta(null);

		});
		
	}

}
