package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.PedidoClienteRepository;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoCliente;

/**
 * Servicio para el repository de Pedido Cliente
 * @author AKarina
 *
 */
@Service
public class ServicioPedidoCliente {
	@Autowired
	private PedidoClienteRepository pedidoClienteRepository;
	
	/**
	 * Recupera todos los pedidos para clientes
	 * @return una lista con todos los pedidos para clientes
	 */
	public List<PedidoCliente> recuperaPedidosCliente() {

		List<PedidoCliente> pedidosCliente = new ArrayList<>();

		for (PedidoCliente pedidoCliente : pedidoClienteRepository.findAll()) {
			pedidosCliente.add(pedidoCliente);
		}

		return pedidosCliente;
	}
	
	/**
	 * Guarda cambios en un pedido para cliente
	 * @param pedidoCliente entidad de pedido cliente a guardar
	 */
	public void guardar(PedidoCliente pedidoCliente) {
		pedidoClienteRepository.save(pedidoCliente);
	}
	
	/**
	 * Obtiene todos los pedidos para clientes de acuerdo a una fecha dada
	 * @param fechaF fecha sobre la cual se realiza la busqueda
	 * @return lista con los pedidos para clientes realizados en la fecha dada
	 */
	public List<PedidoCliente> obtenerPedidosPorFechaCreacion(String fechaF) {
		List<PedidoCliente> pedidosCliente = pedidoClienteRepository.findByFechaDeCreacion(fechaF);
		return pedidosCliente;
	}
}
