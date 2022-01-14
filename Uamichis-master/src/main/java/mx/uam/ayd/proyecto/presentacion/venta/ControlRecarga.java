package mx.uam.ayd.proyecto.presentacion.venta;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.negocio.ServicioVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.presentacion.principal.empleado.ControlPrincipalEmpleados;
import mx.uam.ayd.proyecto.presentacion.principal.encargado.ControlPrincipalEncargado;

/**
 * 
 * @author Luis Cristofer Alvarado Gabriel
 * Se lleva el flujo de la ventana de recarga.
 *
 */
@Component
public class ControlRecarga {
	
	@Autowired
	private VentanaRecarga ventana;
	@Autowired
	private ControlVenta controlVenta;
	@Autowired
	private ControlPrincipalEmpleados controlEmpleado;
	@Autowired
	private ControlPrincipalEncargado controlEncargado;
	@Autowired
	private ControlConfirmaRecarga controlConfirmarRecarga;
	@Autowired
	private ServicioVenta servicioVenta;
	
	private String numeroTel;
	private int montoS;
	private String companiaS;
	
	public void inicia(Empleado empleado) {
		ventana.muestra(this, empleado);
	}
	
	/**
	 * Se regresa a la ventana de venta cuando el cliente 
	 * oprimne "cancelar" en la ventana de recarga"
	 * @param empleado
	 */
	public void iniciaVenta(Empleado empleado) {
		ventana.oculta();
		controlVenta.inicia(empleado);
	}

	/**
	 * MÃ©todo que pasa los datos de la recarga 
	 * al control de confirmar recarga
	 * @param empleado
	 * @param numTel
	 * @param montoS2
	 * @param compS
	 */
	public void ingresaDatos(Empleado empleado, String numTel, int montoS2, String compS) {
		// TODO Auto-generated method stub
		this.numeroTel = numTel;
		this.montoS = montoS2;
		this.companiaS = compS;
		controlConfirmarRecarga.inicia(empleado, numeroTel, montoS, companiaS);
	}
	
	
}

