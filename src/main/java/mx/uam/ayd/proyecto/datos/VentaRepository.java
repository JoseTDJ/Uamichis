package mx.uam.ayd.proyecto.datos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Venta;


public interface VentaRepository extends CrudRepository<Venta, Long> {
	public List<Venta> findByFecha(String fechaF);
}
