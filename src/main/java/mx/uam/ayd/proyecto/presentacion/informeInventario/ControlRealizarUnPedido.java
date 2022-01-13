package mx.uam.ayd.proyecto.presentacion.informeInventario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioDetallePedidoProveedor;
import mx.uam.ayd.proyecto.negocio.ServicioPedidoProveedor;
import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoProveedor;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

/**
 * Clase que lleva el flujo de la ventada de realizar pedidos
 * 
 * @author anver
 *
 */
@Component
public class ControlRealizarUnPedido {

	@Autowired
	private VentanaRealizarUnPedido ventanaRealizarUnPedido;

	@Autowired
	private ControlInformeInventario controlInformeInventario;

	@Autowired
	private ServicioPedidoProveedor servicioPedidoProveedor;

	@Autowired
	private ServicioDetallePedidoProveedor servicioDetallePedidoProveedor;

	@Autowired
	private ServicioProducto servicioProducto;

	private Producto producto;

	/**
	 * Inicia la ventana de Realizar pedidos
	 * 
	 * @param empleado  empleado que inicia sesión
	 * @param productos productos con los que realizara el pedido
	 */
	public void inicia(Empleado empleado, List<Producto> productos) {
		ventanaRealizarUnPedido.muestra(this, empleado, productos);
	}

	/**
	 * Agrega el detalle de venta de un pedido
	 * 
	 * @param empleado        empleado que inicia sesion
	 * @param productos       productos del pedido
	 * @param nuevasPiezas    numero de piezas por producto
	 * @param pedidoProveedor datos del pedido realizado
	 * @return true si se agrega el detalle de pedido, false si no.
	 */
	public boolean mandaDetallePedido(Empleado empleado, List<Producto> productos, ArrayList<Integer> nuevasPiezas,
			PedidoProveedor pedidoProveedor) {
		boolean finalResult = true;
		for (Producto producto : productos) {
			int i = 0;
			float precioTotalXProducto = producto.getPrecio() * nuevasPiezas.get(i);
			boolean result = servicioDetallePedidoProveedor.agregarDetallePedidoProveedor(pedidoProveedor, producto,
					nuevasPiezas.get(i), precioTotalXProducto);
			if (!result)
				finalResult = false;
			i++;
		}
		return finalResult;
	}

	public float calculaPrecioTotal(String nombre, int piezas) {
		producto = servicioProducto.buscarProducto(nombre);
		float precioTotal = 0;
		if (producto != null) {
			precioTotal = producto.getPrecio() * piezas;
		}

		return precioTotal;
	}

	public void cancelar(Empleado empleado) {
		controlInformeInventario.inicia(empleado);
		ventanaRealizarUnPedido.oculta();
	}

	// prueba
	public Producto obtenerProducto(String nombre) {
		try {
			return servicioProducto.buscarProducto(nombre);
		} catch (Exception e) {
			return null;
		}
	}

	public PedidoProveedor guardarPedido(PedidoProveedor pedidoProveedor) {
		PedidoProveedor pedidoP = servicioPedidoProveedor.guardar(pedidoProveedor);
		if (pedidoP != null)
			return pedidoP;
		else
			return null;

	}

	public void cancelarPedido(PedidoProveedor pedidoProveedor) {
		servicioPedidoProveedor.eliminaPedido(pedidoProveedor);

	}

	public void iniciaPrincipalEmpleado(Empleado empleado) {
		ventanaRealizarUnPedido.oculta();
		controlInformeInventario.inicia(empleado);

	}

	public void iniciarInformeInventario(Empleado empleado) {
		ventanaRealizarUnPedido.oculta();
		controlInformeInventario.inicia(empleado);

	}

}
