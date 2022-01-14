package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.DetallePedidoCliente;

public interface DetallePedidoClienteRepository extends CrudRepository<DetallePedidoCliente, Long> {

}
