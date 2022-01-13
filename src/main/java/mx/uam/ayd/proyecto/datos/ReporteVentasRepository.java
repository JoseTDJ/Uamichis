package mx.uam.ayd.proyecto.datos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.ReporteVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;

public interface ReporteVentasRepository extends CrudRepository<ReporteVenta, Long>{
	
	public List<ReporteVenta> findByVenta(Venta venta);
	public void deleteByVenta(Venta venta);
	public ReporteVenta findByidReporte(long idReporte);
	public List<ReporteVenta> findAll();

}
