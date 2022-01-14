package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

@ExtendWith(MockitoExtension.class)
class ServicioProductoTest {
	
	@Mock
	private ProductoRepository productoRepository;

	// Esta anotación hace que se inyecten todos los Mocks al módulo que quiero
	// probar para que no haya nullPointerException por que las dependencias
	// no están satisfechas en tiempo de pruebas
	@InjectMocks
	private ServicioProducto servicio;
	
	private Producto producto1 = new Producto();
	private Producto producto2 = new Producto();
	
	@BeforeEach
	void setUp() throws Exception {
		
		producto1.setNombre("AJOLOTIUS");
		producto1.setCompuesto("Miel de abeja");
		producto1.setReceta("No");
		producto1.setUbicacion("Estante 2");
		producto1.setPiezas(2);
		producto1.setPrecio(50);
		
		producto2.setNombre("DICLOFENACO");
		producto2.setCompuesto("Diclofenaco sodico");
		producto2.setReceta("No");
		producto2.setUbicacion("Estante 2");
		producto2.setPiezas(10);
		producto2.setPrecio(45);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testBuscarProductos() {
		
		Producto p1 = new Producto();
		p1.setNombre("aspirina");
		p1.setCompuesto("ff");
		p1.setReceta("Si");
		p1.setUbicacion("aqui");
		p1.setPrecio(20);
		p1.setPiezas(5);
		
		when(productoRepository.findByNombre("aspirina")).thenReturn(p1);
		// Prueba 1: El método regresa el producto 
		//si lo encuantra
		Producto p = new Producto(); 
		p=servicio.buscarProducto("aspirina");
		
		assertEquals(p1, p);
		
		//Prueba 3: El método se le pase un null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicio.buscarProducto(null);
			

		});

	}

	

	@Test
	void testActulizaInventarioMenos() {
		//Prueba 1: Se actualiza la cantidad de piezas de un producto
		LinkedList<Producto> lista = new LinkedList<>();
		Producto p1 = new Producto();
		p1.setNombre("aspirina");
		p1.setCompuesto("ff");
		p1.setReceta("Si");
		p1.setUbicacion("aqui");
		p1.setPrecio(20);
		p1.setPiezas(5);
		
		lista.add(p1);
		
		servicio.actualizaInventarioMenos(lista);
		
		int p = p1.getPiezas();
		
		assertEquals(4,p);
		
		//Prueba 2: No se actualizó el inventario
		
		servicio.actualizaInventarioMenos(lista);
		
		int o = p1.getPiezas();
		
		assertNotEquals(8,o);
	}
	
	@Test
	void testRecuperarProductosEscasez() {
		//Prueba 1: 
		List<Producto> productos = servicio.obtenerProductos();
		assertTrue(productos.isEmpty());
		//Prueba 2:
		LinkedList <Producto> lista = new LinkedList <> ();
		lista.add(producto1);
		lista.add(producto2);
		when(productoRepository.findAll()).thenReturn(lista);
		
		productoRepository.save(producto1);
		productoRepository.save(producto2);
		
		productos = servicio.recuperarProductosEscazes();
		
		assertEquals(1, productos.size());
	}
	
	@Test
	void testActualizaInventarioNuevo() {
		//Prueba 1: Se actualiza la cantidad de piezas de un prodcuto
		int nuevasPiezas = 12;	
		Producto p1 = new Producto();
		p1.setNombre("aspirina");
		p1.setCompuesto("ff");
		p1.setReceta("Si");
		p1.setUbicacion("aqui");
		p1.setPrecio(20);
		p1.setPiezas(5);
		
		servicio.actualizaInventarioNuevo(p1, nuevasPiezas);
		
		int p = p1.getPiezas();
		assertEquals(12, p);
		
		//Prueba 2: Se recibio un valor nulo en
		//en el parametro producto
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicio.actualizaInventarioNuevo(null, nuevasPiezas);

		});
	
	}
	
	/**
	 * Test al metodo obtener productos
	 */
	@Test
	void testObtenerProductos() {

		// Prueba 1: El método regresa una lista vacia en caso de no haber productos
		List<Producto> listaProductos = servicio.obtenerProductos();
		assertEquals(0, listaProductos.size());

		// Prueba 2: El metodo regresa la lista de productos en caso de haber.
		Producto producto1 = new Producto();
		producto1.setNombre("aspirina");
		producto1.setCompuesto("ff");
		producto1.setReceta("Si");
		producto1.setUbicacion("aqui");
		producto1.setPrecio(20);
		producto1.setPiezas(5);
		Producto producto2 = new Producto();
		producto2.setNombre("paracetamol");
		producto2.setCompuesto("ff");
		producto2.setReceta("Si");
		producto2.setUbicacion("aqui");
		producto2.setPrecio(20);
		producto2.setPiezas(5);

		List<Producto> listaProductosAct = new ArrayList<Producto>();
		listaProductosAct.add(producto1);
		listaProductosAct.add(producto2);

		when(productoRepository.findAll()).thenReturn(listaProductosAct);
		listaProductos = servicio.obtenerProductos();

		assertEquals(2, listaProductos.size());
	}
	
	/**
	 * Prueba para actualizar un producto
	 */
	@Test
	void testActualizarProducto() {
		// Prueba 1: El producto a actualizar esta vacio
		Producto producto = null;
		assertFalse(servicio.actualizarProducto(producto));

		// Prueba 2: El producto fue actualizado
		Producto producto1 = new Producto();
		producto1.setNombre("aspirina");
		producto1.setCompuesto("ff");
		producto1.setReceta("Si");
		producto1.setUbicacion("aqui");
		producto1.setPrecio(20);
		producto1.setPiezas(5);

		when(productoRepository.save(producto1)).thenReturn(producto1);
		assertTrue(servicio.actualizarProducto(producto1));
	}
	
	/**
	 * Prueba para eliminar multiples productos
	 */
	@Test
	void eliminaProductos() {
		//Prueba 1: La lista de productos es nula
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			servicio.eliminaProductos(null);
		});
		
		//Prueba 2: La lista de nombres esta vacia
		List<String> listaNombres = new ArrayList<String>();
		assertFalse(servicio.eliminaProductos(listaNombres));
		
		//Prueba 3: se eliminan todos los productos 
		List<String> listaNombresLlena = new ArrayList<String>();
		listaNombresLlena.add("aspirina");
		listaNombresLlena.add("paracetamol");
		
		Producto producto1 = new Producto();
		producto1.setNombre("aspirina");
		producto1.setCompuesto("ff");
		producto1.setReceta("Si");
		producto1.setUbicacion("aqui");
		producto1.setPrecio(20);
		producto1.setPiezas(5);
		Producto producto2 = new Producto();
		producto2.setNombre("paracetamol");
		producto2.setCompuesto("ff");
		producto2.setReceta("Si");
		producto2.setUbicacion("aqui");
		producto2.setPrecio(20);
		producto2.setPiezas(5);
		
		when(productoRepository.findByNombre("aspirina")).thenReturn(producto1);
		when(productoRepository.findByNombre("paracetamol")).thenReturn(producto2);
		
		assertTrue(servicio.eliminaProductos(listaNombresLlena));
	}
	
	/**
	 * Prueba para agregar producto
	 */
	@Test
	void testAgregarProducto() {
		Producto producto = new Producto();
		producto.setNombre("paracetamol");
		producto.setCompuesto("ff");
		producto.setReceta("Si");
		producto.setUbicacion("aqui");
		producto.setPrecio(20);
		producto.setPiezas(5);
		
		when(productoRepository.save(producto)).thenReturn(producto);
		
		assertTrue(servicio.agregarProducto(producto));
	}

	
}
