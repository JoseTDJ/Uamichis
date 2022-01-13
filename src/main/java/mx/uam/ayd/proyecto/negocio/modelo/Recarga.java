package mx.uam.ayd.proyecto.negocio.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.Data;

@Entity
@Data
@Component
public class Recarga {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String celular;
	private int monto;
	private String compania;
	public Recarga() {}
	public Recarga(String celular, int monto, String compania) {
		this.celular = celular;
		this.compania = compania;
		this.monto = monto;
	}
}

