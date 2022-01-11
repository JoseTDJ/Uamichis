package mx.uam.ayd.proyecto.negocio.modelo;
/**
 * Entidad del negocio: venta
 * @author VictorSosa
 */
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

@Entity
@Data
public class Venta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idVenta;
	private String fecha;
	private float total;

	@OneToMany(targetEntity = DetalleVenta.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idVenta")
	private final List<DetalleVenta> ventas = new ArrayList<>();

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

}
