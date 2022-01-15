package mx.uam.ayd.proyecto.presentacion.recepcionMercancia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioPedidoProveedor;
import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.DetallePedidoProveedor;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoProveedor;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.principal.empleado.ControlPrincipalEmpleados;
import mx.uam.ayd.proyecto.presentacion.principal.encargado.ControlPrincipalEncargado;

/**
 * Esta clase se encarga de llevar el flujo de control de la ventana de Recepción de Mercancía
 * 
 * @author KarinaVergara 
 *
 */
@Component
public class ControlRecepcionMercancia {
	@Autowired
	private VentanaRecepcionMercancia ventana;
	
	@Autowired
	private ServicioPedidoProveedor servicioPedidoProveedor;
	
	@Autowired
	private ControlPrincipalEmpleados controlPrincipalEmpleado;
	
	@Autowired
	private ControlPrincipalEncargado controlPrincipalEncargado;
	
	@Autowired
	private ServicioProducto servicioProducto;
	

	/**
	 * Inicia el flujo de control de la ventana de cierre de venta
	 * 
	 */
	public void inicia(Empleado empleado) {
		ventana.muestra(this, empleado);
	}

	public void obtenerPedido() {
		List<Producto> productos = new ArrayList<>();
		List<Integer> listaPiezas = new ArrayList<>();
		List<PedidoProveedor> pedidosProveedor = servicioPedidoProveedor.obtenerPedidoSinFechaDeRecepcion();
		if(pedidosProveedor.isEmpty()) {
			ventana.mostrarLeyendaSinPedidos();
		} else {
			List<DetallePedidoProveedor> detallesPedidoProveedor = pedidosProveedor.get(0).getDetallesPedidoProveedor();
			for (DetallePedidoProveedor detallePedidoProveedor : detallesPedidoProveedor) {
				//productos.add(detallePedidoProveedor.getProducto());
				listaPiezas.add(detallePedidoProveedor.getNumeroPiezas());
			}
			ventana.agregarDatosTabla(productos, listaPiezas);
		}
	}

	public void cancelarRecepcion(Empleado empleado) {
		if (empleado.getNivel().equals("empleado")) {
			controlPrincipalEmpleado.inicia(empleado);
			ventana.oculta();
		} else if (empleado.getNivel().equals("encargado")) {
			controlPrincipalEncargado.inicia(empleado);
			ventana.oculta();
		}
		
	}
	/**
	 * Obtiene el producto segun el nombre
	 * @param nombre del producto
	 * @return
	 */
	public Producto obtenerProducto(String nombre) {
		return servicioProducto.buscarProducto(nombre);
	}

	public boolean finalizarRecepcion(List<Producto> lista, List<Integer> listaPiezas) {
		int contador = 0;
		for (Producto producto : lista) {
			producto.setPiezas(listaPiezas.get(contador));
			if(!servicioProducto.actualizarProducto(producto)) {
				return false;
			}
		}
		return true;
		
	}
}
