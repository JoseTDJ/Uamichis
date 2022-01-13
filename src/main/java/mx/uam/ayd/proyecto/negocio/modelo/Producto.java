package mx.uam.ayd.proyecto.negocio.modelo;

import java.util.HashSet;
import java.util.Set;

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
 * Entidad de negocio Producto
 * 
 * @author VictorSosa
 *
 */

@Entity
@Data
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idProducto;
	private String nombre;
	private String compuesto;
	private String receta;
	private String ubicacion;
	private float precio;
	private int piezas;
	
	static final int MINIMO = 8;

	@OneToMany(targetEntity = DetalleVenta.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idProducto")
	private final Set<DetalleVenta> ventas = new HashSet<>();
	
	@OneToMany(targetEntity = DetallePedidoProveedor.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idProducto")
	private final Set<DetallePedidoProveedor> detallePedidos = new HashSet<>();
	
	public Producto() {
	}

	public Producto(String nombre, String compuesto, String receta, String ubicacion, float precio, int piezas) {
		this.nombre = nombre;
		this.compuesto = compuesto;
		this.receta = receta;
		this.ubicacion = ubicacion;
		this.precio = precio;
		this.piezas = piezas;
	}
	
	/**
	 * Permite agregar una detalle de venta y la relaciona con la venta
	 * 
	 * @param detalleVenta
	 * @return regresa true si el detalle se agrego correctamente y false si no
	 * @throws IllegalArgumentException si el detalle es nulo
	 */
	
	public boolean addDetalleVenta(DetalleVenta detalleVenta) {

		if (detalleVenta == null) {
			throw new IllegalArgumentException("El detalle de venta no puede ser null");
		}
		if (ventas.contains(detalleVenta)) {
			return false;
		}

		return ventas.add(detalleVenta);
	}
	
	public boolean addDetallePedidoProveedor(DetallePedidoProveedor detallePedido) {
		if (detallePedido == null) {
			throw new IllegalArgumentException("El detalle de venta no puede ser null");
		}
		if (detallePedidos.contains(detallePedido)) {
			return false;
		}
		return detallePedidos.add(detallePedido);
	}


	public Boolean isEscasez() {
		if(this.getPiezas() < MINIMO) { 
			return true;
		}else {
			return false;
		}
	}

}
