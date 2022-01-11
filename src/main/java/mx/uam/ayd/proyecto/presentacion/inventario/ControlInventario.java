package mx.uam.ayd.proyecto.presentacion.inventario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.principal.encargado.ControlPrincipalEncargado;

/**
 * 
 * @author Ana Karina Vergara Guzmán
 * Se lleva el flujo de la ventana de inventario
 *
 */
@Component
public class ControlInventario {
	
	@Autowired
	private VentanaInventario ventana;
	
	@Autowired
	private ServicioProducto servicioProducto;
	
	@Autowired
	private ControlPrincipalEncargado controlPrincipalEncargado;
	
	@Autowired
	private ControlEditarProducto controlEditarProducto;
	
	@Autowired
	private ControlAgregarProducto controlAgregarProducto;
	
	@Autowired
	private ControlCargarArchivo controlCargarArchivo;
	
	/**
	 * Inicia la ventana de inventario
	 * @param empleado empleado que ha iniciado sesión
	 */
	public void inicia(Empleado empleado) {
		ventana.muestra(this, empleado);
	}

	/**
	 * Se encarga de obtener los productos existentes en el inventario
	 * @return lista con los productos
	 */
	public List<Producto> obtenerProductos() {
		return servicioProducto.obtenerProductos();
	}

	/**
	 * Se encarga de eliminar productos del inventario
	 * @param listaNombres lista con los nombres de los productos que se quieren eliminar
	 */
	public void eliminaProductos(List<String> listaNombres) {
		if(servicioProducto.eliminaProductos(listaNombres)) {
			ventana.muestraConfirmacionEliminar();	
		} else {
			ventana.muestraError("Error al eliminar");
		}
	}

	/**
	 * Inicia la ventana principal del encargado y cierra la ventana inventario
	 * @param empleado empleado que ha iniciado sesión. 
	 */
	public void iniciaPrincipalEncargado(Empleado empleado) {
		ventana.oculta();
		controlPrincipalEncargado.inicia(empleado);
	}
	
	/**
	 * Inicia la ventada de editar producto
	 * @param nombre nombre del producto que se desea editar
	 * @param empleado empleado que inició sesión
	 */
	public void iniciaEditar(String nombre, Empleado empleado) {
		ventana.oculta();
		controlEditarProducto.inicia(empleado, nombre);
	}

	/**
	 * Inicia la ventana de agregar producto
	 * @param empleado empleado que inició sesión
	 */
	public void agregarProducto(Empleado empleado) {
		ventana.oculta();
		controlAgregarProducto.inicia(empleado);
		
	}

	/**
	 * Inicia la ventana de cargar archivo
	 * @param empleado empleado que inició sesión
	 */
	public void iniciaCargarArchivo(Empleado empleado) {
		ventana.oculta();
		controlCargarArchivo.inicia(empleado);
	}

}
