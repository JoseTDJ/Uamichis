package mx.uam.ayd.proyecto.presentacion.inventario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

/**
 * 
 * @author Ana Karina Vergara Guzmán
 * Se lleva el flujo de la ventana de agregar producto
 *
 */
@Component
public class ControlAgregarProducto {
	
	@Autowired
	private VentanaAgregarProducto ventana;
	
	@Autowired
	private ServicioProducto servicioProducto;
	
	@Autowired
	private ControlInventario controlInventario;
	
	/**
	 * Inicia el flujo de control de la ventana de agregar producto
	 * @param empleado empleado que inicio sesión
	 */
	public void inicia(Empleado empleado) {
		ventana.muestra(this, empleado);
	}

	/**
	 * Inicia la ventana de inventario
	 * @param empleado empleado que inicia sesión
	 */
	public void inciaInventario(Empleado empleado) {
		ventana.oculta();
		controlInventario.inicia(empleado);
	}

	/**
	 * Solicitamos al servicio agregar un producto
	 * @param producto producto que se quiere agregar
	 */
	public void agregarProducto(Producto producto) {
		if(servicioProducto.agregarProducto(producto)) {
			ventana.muestraConfirmacion();
		} else {
			ventana.muestraError("¡El producto no pudo ser agregado!");
		}
	}

}
