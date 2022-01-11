package mx.uam.ayd.proyecto.presentacion.reporteInventario;

/**
 * @author VICTOR_SOSA
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioReporteInventario;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.ReporteEmpleados;
import mx.uam.ayd.proyecto.negocio.modelo.ReporteInventario;
import mx.uam.ayd.proyecto.presentacion.principal.encargado.ControlPrincipalEncargado;

@Component
public class ControlMuestraReportesInventario {
	
	@Autowired
	private VentanaMuestraReportesInventario ventanaMuestraReportes;
	
	@Autowired
	private ControlPrincipalEncargado controlPrincipalEncargado;
	
	@Autowired
	private ServicioReporteInventario servicioReporteInventario;
	
	/**
	 * Metodo que muestra la ventana con los reportes creados
	 * @param empleado nombre del empleado con sesion iniciada
	 */
	public void inicia(Empleado empleado) {
		List<ReporteInventario> reportes = servicioReporteInventario.recuperaReportes();
		ventanaMuestraReportes.muestra(this, empleado, reportes);
	}
	
	/**
	 * Metodo que regresa a la ventana con los nombres de los empleados
	 * @param empleado nombre del empleado con sesion iniciada
	 */
	public void iniciaVentanaReporteEmpleados(Empleado empleado) {
		controlPrincipalEncargado.inicia(empleado);
	}

}
