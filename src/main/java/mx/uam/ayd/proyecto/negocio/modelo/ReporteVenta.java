package mx.uam.ayd.proyecto.negocio.modelo;

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
public class ReporteVenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idReporte;
	private String fecha;
	private String comentario;
	private String empQueReporta;
	private long idVenta;
	
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Venta venta;
	
	public ReporteVenta() {
	}
	public ReporteVenta(String fecha, String comentario) {
		this.fecha = fecha;
		this.comentario = comentario;
	}
}
