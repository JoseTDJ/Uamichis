package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;

/**
 * @author VictorSosa
 */

import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.DetallePedidoProveedor;
import mx.uam.ayd.proyecto.negocio.modelo.DetalleVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.inventario.ControlCargarArchivo;

/**
 * Servicio para entidad de producto
 * 
 * @author Ana Karina Vergara Guzmán, Victor Manuel Sosa Piña
 *
 */
@Slf4j
@Service
public class ServicioProducto {

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private ControlCargarArchivo controlCargaArchivo;

	/**
	 * Método que busca el produco en la base de datos
	 * 
	 * @param nombre
	 * @return Regresa un producto si lo encontro y null si no está
	 * @throws IllegalArgumentException si se pasa un null.
	 */
	public Producto buscarProducto(String nombre) {
		Producto producto = productoRepository.findByNombre(nombre);
		if (producto == null) {
			log.warn(">>> EL PRODUCTO: " + nombre + " NO SE ENCONTRÓ.");
			//throw new IllegalArgumentException("No se encuentra el producto");
		} else {
			log.info(">>> SE OBTIENE EL PRODUCTO: " + producto.getIdProducto() + ": " + producto.getNombre());
		}

		return producto;

	}

	/**
	 * Método que decrementa la cantidad de piezas de un producto.
	 * 
	 * @param lista de productos de la venta
	 */
	public void actualizaInventarioMenos(List<Producto> listaProductos) {
		try {
			for (Producto producto : listaProductos) {
				producto.setPiezas(producto.getPiezas() - 1);
				productoRepository.save(producto);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se actualizaron las piezas");
		}

	}

	/**
	 * Obtiene los productos de una venta
	 * 
	 * @param detalleVenta detalle de los productos en la venta
	 * @return lista de productos segun la venta.
	 */
	public List<Producto> obtenerProductoPorVenta(DetalleVenta detalleVenta) {
		return productoRepository.findByVentas(detalleVenta);
	}

	public Producto buscarProductoCompuesto(String compuesto) {

		Producto producto = productoRepository.findByCompuesto(compuesto);

		System.out.print(producto);
		if (producto == null) {
			throw new IllegalArgumentException("No se encuentra el producto");
		} else {
			return producto;
		}
	}

	/**
	 * Obtiene una todos los productos existentes en el repositorio
	 * 
	 * @return lista con todos los productos, una lista vacia en caso de no haber
	 *         productos.
	 */
	public List<Producto> obtenerProductos() {
		List<Producto> listaProductos = (List<Producto>) productoRepository.findAll();
		if(listaProductos == null) 
			throw new IllegalArgumentException("No se encuentra el producto");
			
		log.info(">>> SE LEEN EN INVENTARIO: " + listaProductos.size() + " PRODUCTOS");
		return listaProductos;
	}

	public void guardar(Producto producto) {
		productoRepository.save(producto);
	}

	public List<Producto> obtenerProductosConReceta() {
		String receta = "Si";
		return productoRepository.findByReceta(receta);
	}

	/**
	 * Actualiza un producto en el repositorio
	 * 
	 * @param producto producto a actualizar
	 * @return false si el producto no fue actualizado, true si fue actualizado
	 */
	public boolean actualizarProducto(Producto producto) {
		if (producto == null) {
			log.warn(">>> El producto esta vacio");
			return false;
		} else {
			Producto productoN = productoRepository.save(producto);
			log.info(">>> Se actualizó el producto: " + producto.getIdProducto() + ": " + producto.getNombre());
			return true;
		}
	}

	/**
	 * Elimina productos del repositorio
	 * 
	 * @param listaNombres nombres de los productos a eliminar
	 * @return true en caso de eliminar todos los productos, false en caso de que
	 *         alguno no pueda ser eliminado
	 */
	public boolean eliminaProductos(List<String> listaNombres) {
		Producto producto;
		String nombreActual = null;
		int contEliminados = 0;
		if (listaNombres == null) {
			throw new IllegalArgumentException("La lista de nombres esta nula");
		} else if (listaNombres.isEmpty()) {
			return false;
		}
		try {
			for (String nombre : listaNombres) {
				nombreActual = nombre;
				producto = productoRepository.findByNombre(nombre);
				productoRepository.delete(producto);
				contEliminados++;
			}
			log.info(">>> SE ELIMINARON: " + contEliminados + " PRODUCTOS.");
			return true;
		} catch (IllegalArgumentException e) {
			log.warn(">>> ERROR AL ENCONTRAR: " + nombreActual + "\nSE ELIMINARON: " + contEliminados
					+ " PRODUCTOS DE UN TOTAL DE: " + listaNombres.size() + " SOLICITADOS. \nERROR ENCONTRADO: " + e);
			return false;
		}
	}

	/**
	 * Agrega un producto al repositorio
	 * 
	 * @param producto producto que se agrega
	 * @return true en caso de agregarlo, false si no se agrega
	 */
	public boolean agregarProducto(Producto producto) {
		Producto productoN = productoRepository.save(producto);
		if (productoN == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Agrega productos al repositorio
	 * 
	 * @param listaProductos lista de productos a agregar
	 */
	public void agregarProductos(ArrayList<Producto> listaProductos) {
		int contFallos = 0;
		int contExitos = 0;
		ArrayList<Producto> listaProductosFallo = new ArrayList<Producto>();
		for (Producto producto : listaProductos) {
			if (buscarProducto(producto.getNombre()) == null) {
				productoRepository.save(producto);
				contExitos++;
			} else {
				listaProductosFallo.add(producto);
				contFallos++;
			}
		}
		log.info(">>> SE ELIMINARON: " + contExitos + " PRODUCTOS.\nHUBO: " + contFallos + " PRODUCTOS DUPLICADOS");
		for (Producto producto : listaProductosFallo) {
			log.info(">>>> ERROR AL AGREGAR: " + producto.getIdProducto() + ": " + producto.getNombre());
		}
		controlCargaArchivo.muestraResultados(listaProductosFallo, contExitos, contFallos);

	}
	/**
	 * Agregar los productos del archivo default en el repositorio
	 * @param listaProductos lista de productos a agregar
	 */
	public void agregarProductosDefault(ArrayList<Producto> listaProductos) {
		int contFallos = 0;
		int contExitos = 0;
		ArrayList<Producto> listaProductosFallo = new ArrayList<Producto>();
		for (Producto producto : listaProductos) {
			if (buscarProducto(producto.getNombre()) == null) {
				productoRepository.save(producto);
				contExitos++;
			} else {
				listaProductosFallo.add(producto);
				contFallos++;
			}
		}
		log.info(">>> SE ELIMINARON: " + contExitos + " PRODUCTOS.\nHUBO: " + contFallos + " PRODUCTOS DUPLICADOS");
		for (Producto producto : listaProductosFallo) {
			log.info(">>>> ERROR AL AGREGAR: " + producto.getIdProducto() + ": " + producto.getNombre());
		}
		
	}
	
	/**
	 * metodo que recupera los productos que estar por debajo
	 * del minimo permitido para poder hacer un pedido
	 * @return lista de productos escazos
	 */
	public List<Producto> recuperarProductosEscazes() {
		List<Producto> productos = new ArrayList<>();
		for(Producto producto:productoRepository.findAll()) {
			if(producto.isEscasez()) {
				productos.add(producto);
			}
		}
		return productos;
	}
	/**
	 * Se actualiza el numero de piezas en el inventario
	 * @param producto
	 * @param nuevasPiezas
	 */
	public void actualizaInventarioNuevo(Producto producto, int nuevasPiezas) {
		
		if(producto == null) {
			throw new IllegalArgumentException("No se encuentra el producto");
		}
		try {
			producto.setPiezas(nuevasPiezas);
			productoRepository.save(producto);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se actualizaron las piezas");
		}
	}

	public Producto obtenerProductoPorPedidoProveedor(DetallePedidoProveedor detallePedidoProveedor) {
		Producto producto = productoRepository.findByDetallePedidos(detallePedidoProveedor);
		System.out.println("Del detalle de pedido: " + detallePedidoProveedor.getIdPedido() + " Obtenemos + " + producto);
		return producto;
	}

}
