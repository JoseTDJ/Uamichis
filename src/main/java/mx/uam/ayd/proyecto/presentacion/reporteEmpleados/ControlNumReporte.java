package mx.uam.ayd.proyecto.presentacion.reporteEmpleados;

/**
*	Clase que arranca la HU-13
*	@author	Gonzalo Olvera Monroy 
*   @since  16/02/2021
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioReporteEmpleados;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.presentacion.principal.empleado.ControlPrincipalEmpleados;

@Component
public class ControlNumReporte {
	
	@Autowired
	private ControlPrincipalEmpleados controlPrincipalEmpleado;
	
	@Autowired
	private ControlMuestraReportes controlMuestraReportes;
	
	public void inicia(Empleado empleado) {
		controlMuestraReportes.inicia(empleado);
	}
	
	
	/*
	 * Muestar la ventana con todos los reportes
	 * que tiene el empleado
	 */
	public void inicia2(Empleado empleado) {
		controlMuestraReportes.reportePorEmpleado(empleado);
	}
	
	
	
	public void regresar(Empleado empleado) {
		controlPrincipalEmpleado.inicia(empleado);
	}
}
