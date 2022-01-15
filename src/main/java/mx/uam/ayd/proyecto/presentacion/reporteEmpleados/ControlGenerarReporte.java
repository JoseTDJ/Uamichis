package mx.uam.ayd.proyecto.presentacion.reporteEmpleados;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioEmpleado;
import mx.uam.ayd.proyecto.negocio.ServicioReporteEmpleados;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

@Component
public class ControlGenerarReporte { 
	
	@Autowired
	private VentanaGenerarReporte ventanaGenerarReporte;
	
	@Autowired
	private ServicioEmpleado servicioEmpleado;
	
	@Autowired 
	private ControlReporteEmpleados controlReporteEmpleados;
	
	@Autowired
	private ServicioReporteEmpleados servicioReporteEmpleados;
	
	@Autowired
	private ControlReporteEmpleados controlPrincipalEncargado;
	
	/**
	 * Metodo que hace que el control vuelva a la ventana 
	 * de Reporte Empleados
	 * @param empleado empleado con la sesion activa
	 */
	public void iniciaVentanaReporteEmpleados(Empleado empleado) {
		controlReporteEmpleados.inicia(empleado);
	}
	
	/**
	 * Metodo que inicia la ventana que genera el reporte a un empleado
	 * @param empleado empleado que inicio sesion
	 * @param nombre nombre del empleado a reportar
	 */
	public void inicia(Empleado empleado, String nombre) {
		List<Empleado> empleados = servicioEmpleado.recuperaEmpleados();
		Empleado empleado2 = servicioEmpleado.buscarEmpleado(nombre);
		ventanaGenerarReporte.muestra(this, empleado, empleados, empleado2);
	}
	
	/**
	 * Metodo que crea y guarda el reporte generado a un empleado
	 * @param empleadoSesion empleado con nivel encargado que inicio sesion
	 * @param empleado2 empleado al que se le hara un reporte
	 * @param text comentario acerca del reporte
	 */
	public void mandaReporteConEmpleado(Empleado empleadoSesion, Empleado empleado2, String text) {
		String empleadoQueReporta = empleadoSesion.getNombre() + " " + empleadoSesion.getApellido();
		LocalDate hoy = LocalDate.now();
		int anio= hoy.getYear();
		int mes = hoy.getMonthValue();
		int dia= hoy.getDayOfMonth();
		String fecha = dia+"/"+mes+"/"+anio;
		servicioReporteEmpleados.registroReporteConEmpleado(fecha, text, empleado2, empleadoQueReporta);
	}

	/**
	 * Metodo que regresa a la ventana principal del encargado
	 * @param empleadoSesion
	 */
	public void iniciaPrincipalEncargado(Empleado empleadoSesion) {
		controlPrincipalEncargado.inicia(empleadoSesion);
	}
	
}
