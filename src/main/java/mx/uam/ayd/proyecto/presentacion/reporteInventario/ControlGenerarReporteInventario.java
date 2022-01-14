package mx.uam.ayd.proyecto.presentacion.reporteInventario;

/**
 * @author VICTOR_SOSA
 */

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.ServicioReporteInventario;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

@Component
public class ControlGenerarReporteInventario { 
	
	@Autowired
	private VentanaGenerarReporteInventario ventanaGenerarReporteInventario;
	
	@Autowired 
	private ControlReporteInventario controlReporteInventario;	
	
	@Autowired
	private ServicioProducto servicioProducto;
	
	@Autowired
	private ServicioReporteInventario servicioReporteInventario;
	
	/**
	 * Metodo que inicia la ventana que genera el reporte a un producto
	 * @param empleado empleado que inicio sesion
	 * @param nombre nombre del producto a reportar
	 */
	public void inicia(Empleado empleado, String nombre) {
		Producto p =  servicioProducto.buscarProducto(nombre);
		ventanaGenerarReporteInventario.muestra(this, empleado, p);
	}
	
	/**
	 * Metodo que crea y guarda el reporte generado a un empleado
	 * @param empleadoSesion empleado con nivel encargado que inicio sesion
	 * @param nombre nombre del producto al que se le hara un reporte
	 * @param comentario acerca del reporte
	 */
	public void mandaReporteInventario(Empleado empleado,String nombre, String comentario) {
		String empleado2 = empleado.getNombre() + " " + empleado.getApellido();
		Producto producto = servicioProducto.buscarProducto(nombre);
		LocalDate hoy = LocalDate.now();
		int anio= hoy.getYear();
		int mes = hoy.getMonthValue();
		int dia= hoy.getDayOfMonth();
		String fecha = dia+"/"+mes+"/"+anio;
		servicioReporteInventario.registroReporteInventario(fecha,empleado2,producto, comentario);
	}
	
	/**
	 * Metodo que hace que el control vuelva a la ventana 
	 * de Reporte Productos, donde estan todos los productos
	 * @param empleado empleado con la sesion activa
	 */
	public void iniciaVentanaReporteInventario(Empleado empleado) {
		controlReporteInventario.iniciaReporte(empleado);
	}
	
}
