package mx.uam.ayd.proyecto.presentacion.actualizaInventario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

/*
 * Esta clase lleva el flujo de la ventana de actualizar el inventario
 */

@Component
public class ControlActualiza {
	
	@Autowired
	private VentanaActualiza ventanaActualiza;
	
	@Autowired
	private ServicioProducto servicioProducto;
	
	/**
	 * Inicia la historia de usuario de actulizar el inventario
	 * @param empleado
	 */
	public void inicia(Empleado empleado) {
			ventanaActualiza.muestra(this,empleado);
	}
	
	/**
	 * Se obtienen los productos del inventario y se muestran en la tabla.
	 */
	public void obtenerProductos() {
		List<Producto> productos = servicioProducto.obtenerProductos();
		if(productos.isEmpty()) {
			ventanaActualiza.sinProductos("No hay productos para mostrar");
		} else {
			for (Producto producto : productos) {
				ventanaActualiza.agregaProductos(producto);
			}
		}
	}
	
	/**
	 * Manda a llamar al metodo buscar producto,
	 * para despues pasarlo al servicio de producto
	 * @param nombre
	 * @param nuevasPiezas
	 */
	public void actualiza(String nombre, int nuevasPiezas) {
		Producto producto = buscarProducto(nombre);
		servicioProducto.actualizaInventarioNuevo(producto,nuevasPiezas);
	}
	
	/**
	 * Metodo para buscar el producto del inventario 
	 * @param nombre
	 * @return regresa un producto si lo encuentra y null si no. 
	 */
	public Producto buscarProducto(String nombre) {
		try {
			return servicioProducto.buscarProducto(nombre);
		} catch (Exception ex) {
			return null;
		}
	}

	
}
