package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;


import mx.uam.ayd.proyecto.negocio.modelo.Recarga;

public interface RecargaRepository extends CrudRepository<Recarga, Long> {
	
}
