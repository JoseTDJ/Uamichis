package mx.uam.ayd.proyecto.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.DetallePedidoClienteRepository;
import mx.uam.ayd.proyecto.negocio.modelo.DetallePedidoCliente;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoCliente;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

/**
 * Servicio para entidad de detalle de pedido cliente
 * 
 * @author AJ
 *
 */
@Service
public class ServicioDetallePedidoCliente {
	@Autowired
	private DetallePedidoClienteRepository detallePedidoClienteRepository;

	@Autowired
	private ServicioPedidoCliente servicioPedidoCliente;

	@Autowired
	private ServicioProducto servicioProducto;

	/**
	 * Se agregan los detalles de pedido cliente y el pedido
	 * 
	 * @param pedidoCliente  pedido de cliente a asociar
	 * @param listaProductos productos correspondientes al pedido cliente
	 * @param piezas         piezas por producto en el pedido
	 */
	public void agregarDetallePedidoCliente(PedidoCliente pedidoCliente, Producto producto, int piezas) {
		DetallePedidoCliente detallePedidoCliente = new DetallePedidoCliente();
		detallePedidoCliente.setProducto(producto);
		detallePedidoCliente.setNumeroPiezas(piezas);
		detallePedidoClienteRepository.save(detallePedidoCliente);
		pedidoCliente.addDetallePedidoCliente(detallePedidoCliente);
		servicioPedidoCliente.guardar(pedidoCliente);
		servicioProducto.guardar(producto);

	}
}
