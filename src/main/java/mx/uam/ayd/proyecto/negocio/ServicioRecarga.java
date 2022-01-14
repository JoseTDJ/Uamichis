package mx.uam.ayd.proyecto.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.RecargaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Recarga;

@Service
public class ServicioRecarga {
	@Autowired
	private RecargaRepository recargaRepository;
	
	public Recarga ingresaDatosRecarga(String numero, int monto, String compania) {
		// TODO Auto-generated method stub
		Recarga newRecarga = new Recarga(numero, monto, compania);
		recargaRepository.save(newRecarga);
		return newRecarga;
	}
	
	
	
}
