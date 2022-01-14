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
 * Entidad de negocio Pedido de Proveedor
 * 
 * @author AKarina
 *
 */
@Entity
@Data
public class PedidoProveedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPedidoProveedor;
	private String fechaDeCreacion;
	private String fechaDeRecepcion;
	private String empleadoRecibe;
	private String estado;
	private int totalProductos;
	private float precioTotal;
	
	@OneToMany(targetEntity = DetallePedidoProveedor.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idPedidoProveedor")
	private List<DetallePedidoProveedor> detallesPedidoProveedor = new ArrayList<>();
	
	public PedidoProveedor() {
		this.estado = "PENDIENTE";
	}

	public PedidoProveedor(String fechaDeCreacion, int totalProductos, float precioTotal) {
		this.fechaDeCreacion = fechaDeCreacion;
		this.fechaDeRecepcion = null;
		this.empleadoRecibe = null;
		this.estado = "pendiente";
		this.totalProductos = totalProductos;
		this.precioTotal = precioTotal;
	}
	
	/**
	 * Agregar un detalle de pedido y lo relaciona con el pedido
	 * 
	 * @param detallePedido
	 * @return regresa true si el detalle de pedido se agrego correctamente y false si no
	 * @throws IllegalArgumentException si el detalle es nulo
	 */
	public boolean addDetallePedidoProveedor(DetallePedidoProveedor detallePedido) {
		if(detallePedido == null) {
			throw new IllegalArgumentException("El detalle de pedido al proveedor no puede ser null");
		}
		if(detallesPedidoProveedor.contains(detallePedido)) {
			return false;
		}
		return detallesPedidoProveedor.add(detallePedido);
	}
}
