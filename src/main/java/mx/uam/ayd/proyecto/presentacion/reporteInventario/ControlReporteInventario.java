package mx.uam.ayd.proyecto.presentacion.reporteInventario;

/**
 * @author VICTOR_SOSA
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.principal.empleado.ControlPrincipalEmpleados;

@Component
public class ControlReporteInventario {
	
	@Autowired
	private VentanaReporteInventario ventanaReporteInventario;
	
	@Autowired
	private ControlPrincipalEmpleados controlPrincipalEmpleados;
	
	@Autowired
	private ServicioProducto servicioProducto;

	@Autowired
	private ControlGenerarReporteInventario controlGenerarReporteInventario;
	
	/**
	 * Metodo que inicia la ventanaReporteEmpleados
	 * @param empleado
	 */
	public void iniciaReporte(Empleado empleado) {
		List<Producto> listaProductos = servicioProducto.obtenerProductos();
		ventanaReporteInventario.muestra(this, empleado, listaProductos);
	}
	
	
	/**
	 * Regresa a la vantana principal del empleado
	 * @param empleado
	 */
	public void regresar(Empleado empleado) {
		controlPrincipalEmpleados.inicia(empleado);
	}

	/**
	 * Manda al controlador de generar de reporte para mostrar 
	 * el producto seleccionado
	 * @param empleado
	 * @param nombre
	 */
	public void iniciaGeneraReporte(Empleado empleado, String nombre) {
		controlGenerarReporteInventario.inicia(empleado,nombre);
		
	}
	
	
}
