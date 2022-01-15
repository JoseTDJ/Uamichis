package mx.uam.ayd.proyecto.datos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.PedidoCliente;

public interface PedidoClienteRepository extends CrudRepository<PedidoCliente, Long> {
	public List<PedidoCliente> findByFechaDeCreacion(String fechaF);

}