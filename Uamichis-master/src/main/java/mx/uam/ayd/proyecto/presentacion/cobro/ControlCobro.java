package mx.uam.ayd.proyecto.presentacion.cobro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.presentacion.venta.ControlVenta;

/*
 * Esta clase lleva el flujo del cobro
 */
@Component
public class ControlCobro {

	@Autowired
	private VentanaCobro ventanaCobro;
	@Autowired
	private ControlVenta controlVenta;

	/**
	 * 
	 * Inicia historia de usuario: Forma de cobro
	 */
	public void inicia(float total, Empleado empleado) {
		ventanaCobro.muestra(this, total, empleado);
	}

	public void termina() {
		ventanaCobro.setVisible(false);
	}

	/**
	 * Método para obtener un lista de los productos de la venta.
	 * 
	 * @param total
	 * @param empleado 
	 */
	public void obtenerLista(float total, Empleado empleado) {
		controlVenta.obtenerLista(total, empleado);
	}
	/**
	 * Método que muestra los dialogos de la ventana
	 */
	public void muestraDialogo() {
		ventanaCobro.muestraDialogo();
	}
	/**
	 * Método que invoca al método limpiar tabla del control venta, 
	 * para limpiar la tabla de la ventana venta,
	 */
	public void limpiarTabla() {
		controlVenta.limpiarTabla();
		
	}

	/**
	 * Termina historia de usuario: Forma de Cobro
	 */

}