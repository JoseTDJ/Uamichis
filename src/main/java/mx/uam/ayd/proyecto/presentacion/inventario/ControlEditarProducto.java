package mx.uam.ayd.proyecto.presentacion.inventario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

/**
 * 
 * @author Ana Karina Vergara Guzmán
 * Se lleva el flujo de la ventana de editar producto
 *
 */
@Component
public class ControlEditarProducto {
	
	@Autowired
	private VentanaEditarProducto ventana;
	
	@Autowired
	private ServicioProducto servicioProducto;
	
	@Autowired
	private ControlInventario controlInventario;
	
	/**
	 * Inicia la ventana editar producto
	 * @param empleado empleado que inició sesión
	 * @param nombre nombre del producto que se desea editar
	 */
	public void inicia(Empleado empleado, String nombre) {
		Producto producto = servicioProducto.buscarProducto(nombre);
		ventana.muestra(this, empleado, producto);
	}
	
	/**
	 * Inicia la ventana de inventario
	 * @param empleado nombre del producto que se desea editar
	 */
	public void inciaInventario(Empleado empleado) {
		ventana.oculta();
		controlInventario.inicia(empleado);
	}

	/**
	 * Actualiza un producto 
	 * @param productoEditado producto editado
	 */
	public void actualizarProducto(Producto productoEditado) {
		if(servicioProducto.actualizarProducto(productoEditado)) {
			ventana.muestraConfirmacionEditado();
		} else {
			ventana.muestraError("El producto no pudo ser actualizado");
		}
	}

}
