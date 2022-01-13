package mx.uam.ayd.proyecto.presentacion.reporteVenta;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioEmpleado;
import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.ServicioReporteVenta;
import mx.uam.ayd.proyecto.negocio.ServicioVenta;
import mx.uam.ayd.proyecto.negocio.modelo.DetalleVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;
/**
 * Clase que lleva el flujo de control de generar el reporte de una 
 * venta. 
 * @author Luis Cristofer Alvarado Gabriel
 * @since 18/02/2021
 */
@Component
public class ControlGeneraReporteVenta {
	
	@Autowired
	ServicioProducto servicioProducto;
	
	@Autowired
	VentanaGeneraReporteVenta ventana;
	
	@Autowired
	ServicioVenta servicioVenta;
	
	@Autowired
	ServicioReporteVenta servicioReporteVenta;
	
	@Autowired
	ServicioEmpleado servicioEmpleado;
	
	private Calendar fecha = new GregorianCalendar();
	private int ano = fecha.get(Calendar.YEAR);
	private int mes = fecha.get(Calendar.MONTH);
	private int dia = fecha.get(Calendar.DAY_OF_MONTH);
	private String fechaF = ano + "/" + mes + "/" + dia;
	
	/**
	 * Metodo que inicia la ventana para generar un reporte de venta.
	 * @param empleado
	 * @param idVenta
	 */
	public void inicia(Empleado empleado, long idVenta) {
		List<Venta> ventas = servicioVenta.obtenerVentasPorFecha(fechaF);
		String empleadoHizoVenta = "Prueba";
		Venta ventaSel = new Venta();
		List<Producto> productos = new ArrayList();
		List<Empleado> empleados = servicioEmpleado.recuperaEmpleados();
		for(Venta venta : ventas) {
			if(venta.getIdVenta() == idVenta) {
				ventaSel = venta;
				for (DetalleVenta detalleVenta : ventaSel.getVentas()) {
					productos.add(servicioProducto.obtenerProductoPorVenta(detalleVenta).get(0));
				}
			}
		}
		//productos.addAll(servicioProducto.obtenerProductoPorVenta(dV));
		for(Empleado emp : empleados) {
			Set<Venta> ventasEmp = emp.getVentas();
			for(Venta vent : ventasEmp) {
				if(vent.getIdVenta() == idVenta) {
					empleadoHizoVenta = emp.getNombre();
				}
			}	
		}
		for(Producto prod : productos) {
			ventana.llenaDatosVenta(prod, idVenta, productos.size(), empleadoHizoVenta);
		}
		ventana.muestra(this, empleado, ventaSel);
	}
	
	/**
	 * Metodo que llama al servicio de reporte de ventas para agregar
	 * un nuevo reporte al repositorio con los datos de la venta y de 
	 * la persona que reporta.
	 * @param venta
	 * @param idVenta
	 * @param comentario
	 * @param empleado
	 */
	public void generaReporteVenta(Venta venta, long idVenta, String comentario, Empleado empleado) {
		String empQueReporta = empleado.getNombre() + " " + empleado.getApellido();
		LocalDate hoy = LocalDate.now();
		int anio= hoy.getYear();
		int mes = hoy.getMonthValue();
		int dia= hoy.getDayOfMonth();
		String fecha = dia+"/"+mes+"/"+anio;
		servicioReporteVenta.registroReporteVenta(venta, fecha, idVenta, comentario, empQueReporta);
	}
}
