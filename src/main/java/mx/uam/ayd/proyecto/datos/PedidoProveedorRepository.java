package mx.uam.ayd.proyecto.datos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.PedidoProveedor;

public interface PedidoProveedorRepository extends CrudRepository<PedidoProveedor, Long> {
	
	public List<PedidoProveedor> findByFechaDeCreacion(String fechaF);
	public PedidoProveedor findByFechaDeCreacionAndTotalProductosAndPrecioTotal(String fechaCreacion, int totalProductos, float precio);

}
