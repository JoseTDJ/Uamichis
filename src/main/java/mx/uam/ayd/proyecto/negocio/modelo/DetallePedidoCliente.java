package mx.uam.ayd.proyecto.negocio.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

/**
 * Entidad de negocio para el detalle de pedido de cliente
 * @author AKarina
 *
 */
@Entity
@Data
public class DetallePedidoCliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPedidoCliente;
	private int numeroPiezas;
	
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Producto producto;
	
	public DetallePedidoCliente() {}

	public DetallePedidoCliente(int numeroPiezas) {
		this.numeroPiezas = numeroPiezas;
	}
	
}
