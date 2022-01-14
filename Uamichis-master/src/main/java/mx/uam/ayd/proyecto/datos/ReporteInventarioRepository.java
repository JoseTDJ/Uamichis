package mx.uam.ayd.proyecto.datos;

/**
 * VICTOR_SOSA
 */

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.ReporteInventario;

public interface ReporteInventarioRepository extends CrudRepository<ReporteInventario, Long>  {
	
	public List<ReporteInventario> findByProducto(Producto producto);
	public void deleteByProducto(Producto producto);
	public ReporteInventario findByidReporte(long idReporte);
	public List<ReporteInventario> findAll();
}
