package mx.uam.ayd.proyecto.presentacion.venta;

/**
 * @author VictorSosa
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioCliente;
import mx.uam.ayd.proyecto.negocio.ServicioDetalleVenta;
import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.Recarga;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;
import mx.uam.ayd.proyecto.presentacion.cobro.ControlCobro;

/*
 * Esta clase lleva el flujo de la ventana de venta
 */

@Component
public class ControlVenta {

	@Autowired
	private VentanaVenta ventanaVenta;

	@Autowired
	private VentanaProducto ventanaProducto;

	@Autowired
	private ControlCobro controlCobro;
	
	@Autowired
	private ControlRecarga controlRecarga;

	@Autowired
	private ServicioProducto servicioProducto;

	@Autowired
	private ServicioDetalleVenta servicioDetalleVenta;
	
	@Autowired
	private ServicioCliente servicioCliente;
	
	private List<Producto> listaProductos = new ArrayList<>();
	

	/**
	 * Inicia la historia de usuario: Agregar productos para la venta
	 * 
	 */
	public void inicia(Empleado empleado) {
		ventanaVenta.muestra(this, empleado);
	}

	/**
	 * MÃ©todo que busca invoca al servio de producto para buscar el producto por
	 * nombre.
	 * 
	 * @param nombre
	 */
	public void buscarProducto(String nombre) {

		try {
			ventanaProducto.muestra(this);
			ventanaProducto.llena(servicioProducto.buscarProducto(nombre));

		} catch (Exception ex) {
			termina();
			ventanaVenta.muestraDialogoConMensaje("El nombre del producto esta mal escrito o no esta en el sistema");
		}
	}

	/**
	 * MÃ©todo que invoca al servicio de producto para producto por nombre
	 * 
	 * @param nombre
	 * @return Un objeto de tipo producto si lo encuentra y nulo si lo encuentra.
	 */
	public Producto obtenerProducto(String nombre) {

		try {
			return servicioProducto.buscarProducto(nombre);
		} catch (Exception ex) {
			return null;
		}

	}

	/**
	 * Oculta la venta de descripcion del producto
	 */
	public void termina() {
		ventanaProducto.setVisible(false);
	}

	/**
	 * MÃ©todo que llena la tabla de la ventana venta.
	 * 
	 * @param producto
	 */

	public void agregarTabla(Producto producto) {

		try {
			ventanaVenta.llenaTabla(producto);
			ventanaVenta.muestraDialogoConMensaje("Producto agregado exitosamente");
		} catch (Exception ex) {
			ventanaVenta.muestraDialogoConMensaje("Error al buscar el Producto");
		}
		termina();
	}

	/**
	 * MÃ©todo que actualiza invoca al servicio para actualizar el inventario de un
	 * producto
	 * 
	 * @param nombre
	 */

	public void actulizaInventarioMenos(List<Producto> listaProductos) {
		servicioProducto.actualizaInventarioMenos(listaProductos);
	}

	/**
	 * Termina historia de usuario: Agregar productos para la venta.
	 * 
	 * 
	 */

	/**
	 * 
	 * Inicia historia de forma de cobro
	 */

	/**
	 * MÃ©todo que se comunica con el control cobro para mostrar la ventana.
	 * 
	 * @param total
	 */
	public void muentraCobro(float total, Empleado empleado) {
		controlCobro.inicia(total, empleado);
	}

	/**
	 * MÃ©todo que obtiene el total de la ventana de venta.
	 * 
	 * @param precio
	 */
	public void total(float precio) {
		ventanaVenta.textTotal(precio);
	}

	/**
	 * MÃ©todo que obtien los producto de la venta
	 * 
	 * @param total
	 * @param empleado 
	 */
	public void obtenerLista(float total, Empleado empleado) {

		listaProductos = ventanaVenta.recorrerTabla();
		Venta venta = new Venta();
		Calendar fecha = new GregorianCalendar();
		int ano = fecha.get(Calendar.YEAR);
		int mes = fecha.get(Calendar.MONTH);
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		String fechaF = ano + "/" + mes + "/" + dia;
		venta.setFecha(fechaF);
		venta.setTotal(total);
		actulizaInventarioMenos(listaProductos);
		servicioDetalleVenta.agregarDetalleVenta(venta, listaProductos, empleado);
		System.out.println("Las ventas del empleado: " + empleado.getNombre() + " son: " + empleado.getVentas());
		controlCobro.muestraDialogo();
		System.out.println(empleado.getVentas());
	}

	public void limpiarTabla() {
		ventanaVenta.limpia();

	}
	
	/**
	 * MÃ©todo que llama a iniciar una recarga
	 * @param empleado
	 */
	public void recarga(Empleado empleado) {
		controlRecarga.inicia(empleado);
	}

	/**
	 * MÃ©tdodo que devuelve y muestra la recarga realizada
	 * en la lista de ventas
	 * @param recarga
	 */
	public void agregaRecargaTabla(Recarga recarga) {
		// TODO Auto-generated method stub
		ventanaVenta.agregaRecarga(recarga);
		
	}
	
	/**
	 * HU-09 Metodo que inicia la ventana venta para el cliente registrado
	 * @param empleado empleado que inicia sesion
	 * @param cliente cliente que hara una compra
	 */
	public void iniciaVentaMembresia(Empleado empleado, Cliente cliente) {
		ventanaVenta.muestraVenta(this, empleado, cliente);
	}
	
	public boolean isMiembro(String nombre) {
		Cliente miembro = servicioCliente.buscaCliente(nombre);
		if(miembro != null) {
			return true;
		} else {
			return false;
		}
	}
}

