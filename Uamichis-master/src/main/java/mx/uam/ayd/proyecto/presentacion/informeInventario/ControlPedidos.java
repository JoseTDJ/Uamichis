package mx.uam.ayd.proyecto.presentacion.informeInventario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioPedidoProveedor;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoProveedor;

/**
 * Control del flujo de la ventana de pedidos
 * @author anver
 *
 */
@Component
public class ControlPedidos {

	@Autowired
	private VentanaPedidos ventanaPedidos;

	@Autowired
	private ServicioPedidoProveedor servicioPedidoProveedor;
	
	@Autowired
	private ControlInformeInventario controlInformeInventario;
	
	@Autowired
	private ControlDetallePedido controlDetallePedido;
	
	/**
	 * Inicia la ventana de pedidos
	 * @param empleado empleado que incia sesión
	 */
	public void inicia(Empleado empleado) {
		ventanaPedidos.muestra(this, empleado);
	}

	/**
	 * Obtiene todos los pedidos generados
	 * @return lista de pedidos a proveedor
	 */
	public List<PedidoProveedor> obtenerPedidos() {
		return servicioPedidoProveedor.obtenerPedidos();
	}
	
	/**
	 * inicia la ventana de informe de inventario 
	 * @param empleado empleado que inicia sesión
	 */
	public void iniciaInformeInventario(Empleado empleado) {
		controlInformeInventario.inicia(empleado);
		ventanaPedidos.oculta();
	}

	public void mostrarDetalles(int fila) {
		System.out.println(fila);
		
	}

	public void verDetallePedido(PedidoProveedor pedido, Empleado empleado) {
		PedidoProveedor pedidoGuardado = servicioPedidoProveedor.obtenerPedido(pedido);
		controlDetallePedido.inicia(pedidoGuardado, empleado);		
	}

}
