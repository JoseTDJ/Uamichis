package mx.uam.ayd.proyecto.presentacion.reporteEmpleados;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioEmpleado;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.presentacion.principal.encargado.ControlPrincipalEncargado;

@Component
public class ControlReporteEmpleados {
	
	@Autowired
	private VentanaReporteEmpleados ventanaReporteEmpleados;
	
	@Autowired
	private ControlPrincipalEncargado controlPrincipalEncargado;
	
	@Autowired
	private ServicioEmpleado servicioEmpleado;
	
	@Autowired
	private ControlGenerarReporte controlGenerarReporte;
	
	@Autowired
	private ControlMuestraReportes controlMuestraReportes;
	
	/**
	 * Metodo que inicia la ventanaReporteEmpleados
	 * @param empleado
	 */
	public void inicia(Empleado empleado) {
		List<Empleado> listaEmpleados = servicioEmpleado.recuperaEmpleados();
		ventanaReporteEmpleados.muestra(this, empleado, listaEmpleados);
	}
	
	public void regresar(Empleado empleado) {
		controlPrincipalEncargado.inicia(empleado);
	}
	
	/**
	 * metodo que obtiene una lista de los empleados
	 * @return una lista de empleados
	 */
	public List<Empleado> obtenerEmpleados(){
		return servicioEmpleado.recuperaEmpleados();
	}
	/**
	 * metodo que le pasa el mando al controlGenerarReporte
	 * para que muestre la ventana GenerarReporte
	 * @param empleado empleado de la sesion activa
	 * @param nombre nombre del empleado a reportar
	 */
	public void iniciaGeneraReporte(Empleado empleado, String nombre) {
		controlGenerarReporte.inicia(empleado, nombre);
	}
	/**
	 * metodo que muestra VentanaMuestraReportes
	 * @param empleado empleado con cargo encargado
	 */
	public void muestraReportes(Empleado empleado) {
		controlMuestraReportes.inicia(empleado);
	}
	
	public void muestraReportePorEmpleado(Empleado empleado) {
		controlMuestraReportes.reportePorEmpleado(empleado);
	}
	
}
