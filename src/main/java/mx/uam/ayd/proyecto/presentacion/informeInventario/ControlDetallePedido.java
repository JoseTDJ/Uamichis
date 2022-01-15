package mx.uam.ayd.proyecto.presentacion.informeInventario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioDetallePedidoProveedor;
import mx.uam.ayd.proyecto.negocio.ServicioPedidoProveedor;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoProveedor;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

/**
 * Control del flujo de la ventana de pedidos
 * @author anver
 *
 */
@Component
public class ControlDetallePedido {

	@Autowired
	private VentanaDetallePedido ventanaDetallePedido;

	@Autowired
	private ServicioPedidoProveedor servicioPedidoProveedor;
	
	@Autowired
	private ServicioDetallePedidoProveedor servicioDetallePedidoProveedor;
	
	@Autowired
	private ControlPedidos controlPedidos;
	
	/**
	 * Inicia la ventana de pedidos
	 * @param empleado empleado que incia sesión
	 */
	public void inicia(PedidoProveedor pedido, Empleado empleado) {
		ArrayList<Producto> listaProductos = servicioDetallePedidoProveedor.obtenerProductosPorPedido(pedido);
		ventanaDetallePedido.muestra(this, listaProductos, empleado, pedido);
	}

	/**
	 * Obtiene todos los pedidos generados
	 * @return lista de pedidos a proveedor
	 */
	public List<PedidoProveedor> obtenerPedidos() {
		return servicioPedidoProveedor.obtenerPedidos();
	}	
	
	public void iniciarPedidos(Empleado empleado) {
		controlPedidos.inicia(empleado);
		ventanaDetallePedido.oculta();
		
	}

}
