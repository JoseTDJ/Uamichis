package mx.uam.ayd.proyecto.presentacion.venta;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioRecarga;
import mx.uam.ayd.proyecto.negocio.ServicioVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Recarga;
/**
 * 
 * @author Luis Cristofer Alvarado Gabriel
 * Se lleva el flujo de la ventana de confirmar recarga.
 *
 */
@Component
public class ControlConfirmaRecarga {

	private String telefono;
	private int monto;
	private String compania;
	@Autowired
	private VentanaConfirmaRecarga ventana;
	@Autowired
	private ServicioVenta servicioVentaaaaaaa;
	@Autowired
	private ControlRecarga control;
	@Autowired
	private Recarga newRecarga;
	@Autowired
	private ServicioRecarga servicioRecarga;
	@Autowired
	private ControlVenta controlVenta;
	
	/**
	 * Inicia la ventana de confirmaciÃ³n de recarga
	 * con los datos de la recarga obtenidos 
	 * anteriormente
	 * @param empleado
	 * @param numeroTel
	 * @param mont
	 * @param Comp
	 */
	public void inicia(Empleado empleado, String numeroTel, int mont, String Comp) {
		this.telefono = numeroTel;
		this.monto = mont;
		this.compania = Comp;
		ventana.muestra(this, empleado, telefono, monto, compania);
	}
	
	/**
	 * Se regresa a la ventana de recarga cuando
	 * el usuario oprime "cancelar" en la ventana de
	 * confirmar recarga
	 * @param empleado
	 */
	public void iniciaRecarga(Empleado empleado) {
		ventana.oculta();
		control.inicia(empleado);
	}
	
	/**
	 * Los datos se mandan al servicio de venta
	 * @param numero
	 * @param monto
	 * @param compania
	 */
	public void ingresaDatos(String numero, int monto, String compania) {
		this.telefono = numero;
		this.monto = monto;
		this.compania = compania;
		Recarga recarga = servicioRecarga.ingresaDatosRecarga(numero, monto, compania);
		controlVenta.agregaRecargaTabla(recarga);
	}

	
	
}
