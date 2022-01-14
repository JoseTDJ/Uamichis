package mx.uam.ayd.proyecto.negocio.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * Entidad de negocio Detalle de Pedido a proveedor
 * 
 * @author AKarina
 *
 */
@Entity
@Data
public class DetallePedidoProveedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPedido;
	private int numeroPiezas;
	private float precioTotalXProducto;

//	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	private Producto producto;

	public DetallePedidoProveedor() {
	}

	public DetallePedidoProveedor(int numeroPiezas, float precioTotalXProducto) {
		this.numeroPiezas = numeroPiezas;
		this.precioTotalXProducto = precioTotalXProducto;
	}

}
