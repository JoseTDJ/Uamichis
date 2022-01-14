package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.PedidoProveedorRepository;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoProveedor;

/**
 * Servicio para el repository de Pedido Proveedor
 * 
 * @author AKarina
 *
 */
@Service
public class ServicioPedidoProveedor {

	@Autowired
	private PedidoProveedorRepository pedidoProveedorRepository;

	public List<PedidoProveedor> obtenerPedidos() {
		List<PedidoProveedor> pedidos = (List<PedidoProveedor>) pedidoProveedorRepository.findAll();
		return pedidos;
	}

	public PedidoProveedor guardar(PedidoProveedor pedido) {
		if (pedido != null) {
			PedidoProveedor pedidoProveedor = pedidoProveedorRepository.save(pedido);
			if (pedidoProveedor != null) {
				return pedidoProveedor;
			} else {
				return null;
			}
		} else {
			throw new IllegalArgumentException("El pedido no se definio");
		}

	}

	public List<PedidoProveedor> obtenerPedidosPorFechaCreacion(String fechaF) {
		List<PedidoProveedor> pedidos = pedidoProveedorRepository.findByFechaDeCreacion(fechaF);
		return pedidos;
	}

	public List<PedidoProveedor> obtenerPedidoSinFechaDeRecepcion() {
		List<PedidoProveedor> listaPedidosSinFecha = new ArrayList<>();
		List<PedidoProveedor> pedidos = (List<PedidoProveedor>) pedidoProveedorRepository.findAll();
		for (PedidoProveedor pedidoProveedor : pedidos) {
			if (pedidoProveedor.getFechaDeRecepcion() == null) {
				listaPedidosSinFecha.add(pedidoProveedor);
			}
		}
		return listaPedidosSinFecha;
	}

	public void eliminaPedido(PedidoProveedor pedidoProveedor) {
		pedidoProveedorRepository.delete(pedidoProveedor);
	}

	public PedidoProveedor obtenerPedido(PedidoProveedor pedido) {
		PedidoProveedor pedidoGuardado = pedidoProveedorRepository.findByFechaDeCreacionAndTotalProductosAndPrecioTotal(
				pedido.getFechaDeCreacion(), pedido.getTotalProductos(), pedido.getPrecioTotal());
		return pedidoGuardado;
	}
}
