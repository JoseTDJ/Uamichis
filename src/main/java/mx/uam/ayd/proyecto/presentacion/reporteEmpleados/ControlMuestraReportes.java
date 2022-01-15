package mx.uam.ayd.proyecto.presentacion.reporteEmpleados;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioEmpleado;
import mx.uam.ayd.proyecto.negocio.ServicioReporteEmpleados;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.ReporteEmpleados;
import mx.uam.ayd.proyecto.presentacion.principal.encargado.ControlPrincipalEncargado;

@Component
public class ControlMuestraReportes {
	
	@Autowired
	private VentanaMuestraReportes ventanaMuestraReportes;
	
	@Autowired
	private ControlPrincipalEncargado controlPrincipalEncargado;
	
	@Autowired
	private ServicioReporteEmpleados servicioReporteEmpleados;
	
	@Autowired
	private ServicioEmpleado servicioEmpleado;
	
	@Autowired
	private VentanaReporte ventanaReporte;
	
	/**
	 * Metodo que muestra la ventana con los reportes creados
	 * @param empleado nombre del empleado con sesion iniciada
	 */
	public void inicia(Empleado empleado) {
		List<ReporteEmpleados> reportes = servicioReporteEmpleados.recuperaReportes();
		ventanaMuestraReportes.muestra(this, empleado, reportes);
	}
	
	
	public void reportePorEmpleado(Empleado empleado) {
		List<Empleado> empleados = servicioEmpleado.recuperaEmpleados();
		List<ReporteEmpleados> reportes = servicioReporteEmpleados.recuperaReportesPorEmpleado(empleado);
		ventanaReporte.muestra(this, empleado,reportes);
	}
	


	
	
	/**
	 * Metodo que regresa a la ventana con los nombres de los empleados
	 * @param empleado nombre del empleado con sesion iniciada
	 */
	public void iniciaVentanaReporteEmpleados(Empleado empleado) {
		controlPrincipalEncargado.inicia(empleado);
	}

}
