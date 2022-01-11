package mx.uam.ayd.proyecto.negocio.modelo;

/**
 * @author VICTOR_SOSA
 */
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class ReporteInventario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idReporte;
	private String fecha;
	private String nombreProducto;
	private String comentario;
	private String empleadoQueReporta;
	
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Producto producto;
	
	public ReporteInventario() {}
	public ReporteInventario(String fecha, String comentario) {
		this.fecha = fecha;
		this.comentario = comentario;
	}
	
	
}
