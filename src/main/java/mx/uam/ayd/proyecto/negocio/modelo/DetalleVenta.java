package mx.uam.ayd.proyecto.negocio.modelo;
/**
 * Entidad de negocio Venta
 * @author VictorSosa
 *
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
import javax.persistence.ManyToMany;
import lombok.Data;



@Entity
@Data
public class DetalleVenta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idDetalleVenta; 
	
}
