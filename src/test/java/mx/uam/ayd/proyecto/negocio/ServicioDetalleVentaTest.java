package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.DetalleVentaRepository;
import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.datos.VentaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;

@ExtendWith(MockitoExtension.class)
class ServicioDetalleVentaTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Mock
	private ProductoRepository productoRepository;
	
	@Mock
	private VentaRepository venta;
	
	@Mock
	private DetalleVentaRepository detalle;
	
	@InjectMocks
	private ServicioDetalleVenta servicio;

	@Test
	void testAgregarDetalleVenta() {
		
		//Prueba 1: se pasa una lista vacia y un objeto de venta null
		LinkedList<Producto> lista1 = new LinkedList<>();
		
		Assertions.assertThrows(NullPointerException.class, () ->{
			//servicio.agregarDetalleVenta(null, lista1);
		});

		//Prueba 2: Se guarda con exito el detalle de venta
		LinkedList<Producto> lista = new LinkedList<>();
		Producto p1 = new Producto();
		p1.setNombre("aspirina");
		p1.setCompuesto("ff");
		p1.setReceta("Si");
		p1.setUbicacion("aqui");
		p1.setPrecio(20);
		p1.setPiezas(5);
		Producto p2 = new Producto();
		p2.setNombre("dualgos");
		p2.setCompuesto("fff");
		p2.setReceta("Si");
		p2.setUbicacion("aqui 2");
		p2.setPrecio(200);
		p2.setPiezas(7);
		
		lista.add(p1);
		lista.add(p2);
		
		Venta venta = new Venta();
		
//		boolean resultado=servicio.agregarDetalleVenta(venta, lista);
//		assertTrue(resultado);
		
		
	}

}
