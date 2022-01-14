package mx.uam.ayd.proyecto.negocio.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

/**
 * Entidad de negocio Pedido Cliente
 * @author AKarina
 *
 */
@Entity
@Data
public class PedidoCliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPedidoCliente;
	private String fechaDeCreacion;
	private String fechaDeRecepcion;
	private String fechaDeEntrega;
	private String estado;
	private int totalProductos;
	private float precioTotal;
	
	@OneToMany(targetEntity = DetallePedidoCliente.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idPedido")
	private final List<DetallePedidoCliente> detallesPedidoCliente = new ArrayList<>();
	
	public PedidoCliente() {}

	public PedidoCliente(String fechaDeCreacion, int totalProductos, float precioTotal) {
		this.fechaDeCreacion = fechaDeCreacion;
		this.totalProductos = totalProductos;
		this.precioTotal = precioTotal;
		this.fechaDeRecepcion = "Sin recibir";
		this.fechaDeEntrega = "Sin entrega";
		this.estado = "Sin entregar";
	}
	
	/**
	 * Agregar un detalle de pedido cliente y lo relaciona con el pedido del cliente
	 * 
	 * @param detallePedidoCliente
	 * @return regresa true si el detalle de pedido cliente se agrego correctamente y false si no
	 * @throws IllegalArgumentException si el detalle de pedido cliente es nulo
	 */
	
	public boolean addDetallePedidoCliente(DetallePedidoCliente detallePedidoCliente) {
		if (detallePedidoCliente == null) {
			throw new IllegalArgumentException("El detalle de venta no puede ser null");
		}
		if (detallesPedidoCliente.contains(detallePedidoCliente)) {
			return false;
		}

		return detallesPedidoCliente.add(detallePedidoCliente);
	}	
}
