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
 * Entidad de negocio Asistencias
 * 
 *
 */
@Entity
@Data

public class Asistencia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idAsistencia;
	private String horaInicial;
	private String horaFinal;
	private String fecha;
	
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Empleado empleado;
	
	public Asistencia() {}
	public Asistencia(String horaInicial, String horaFinal,String fecha) {
		this.horaInicial= horaInicial;
		this.horaFinal= horaFinal;
		this.fecha=fecha;
		
	}
	
}