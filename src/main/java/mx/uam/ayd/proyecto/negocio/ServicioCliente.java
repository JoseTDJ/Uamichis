package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.ClienteRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoCliente;
@Slf4j
@Service
public class ServicioCliente {
	@Autowired
	private ClienteRepository clienteRepository;
	
	/**
	 * Guarda cambios en un cliente
	 * @param cliente entidad de cliente a guardar
	 */
	public void guardarCliente(Cliente cliente) {
		clienteRepository.save(cliente); 
	}

	public List<Cliente> obtenerClientePorPedido(PedidoCliente pedidoCliente) {
		return clienteRepository.findByPedidosCliente(pedidoCliente);
	}
	
	
	/**
	 * Metodo encargado de validar si existe un usuario
	 * @param usuario usuario que se validara su existencia
	 * @return el cliente con dicho usuario, en otro caso null
	 */
	public Cliente validaUsuario(String usuario) {
		
		Cliente cliente = clienteRepository.findByUsuario(usuario);
		
		if(usuario == null) {
			throw new IllegalArgumentException("Este usuario ya existe");
		}
		
		if(cliente == null) {
			return null;
		}
		
		return cliente;
		
	}
	
	
	/**
	 * Metodo que se encarga de recuperar todos los clientes
	 * existentes en la BD
	 * @return una lista de clientes registrados
	 */
	public List<Cliente> recuperaClientes(){
		
		List<Cliente> clientes = new ArrayList<>();
		
		for (Cliente cliente : clienteRepository.findAll()) {
			clientes.add(cliente);
		}
		
		return clientes;
	}
	
	
	/**
	 * Metodo que se encarga de buscar un cliente por nombre
	 * @param nombre nombre del cliente, al cual se validara su existencia
	 * @return el cliente encontrado con dicho nombre
	 */
	public Cliente buscaCliente(String nombre) {
		Cliente cliente = clienteRepository.findByNombre(nombre);
		
		if(nombre == null) {
			throw new IllegalArgumentException("No se encontro al empleado");
		}
		
		if(cliente == null) {
			return null;
		} else {
			log.info("El cliente es: " + cliente.getIdCliente() + " : " + cliente.getNombre());
		}
		
		return cliente;
		
	}
	
	
	/**
	 * Metodo que se encarga de eliminar a un cliente si 
	 * es que este existe
	 * @param listaNombres lista de nombres de clientes a eliminar
	 * @return un valor booleano para corroborar si se elimino un cliente
	 */
	public boolean eliminarCliente(List<String> listaNombres) {
		Cliente cliente;
		String nombreCliente = null;
		
		try {
			
			for (String nombre : listaNombres) {
				nombreCliente = nombre;
				cliente = clienteRepository.findByNombre(nombre);
				clienteRepository.delete(cliente);
			}
			return true;
			
		} catch(IllegalArgumentException e) {
			return false;
		}
	
	}
	
	
	/**
	 * Metodo que se encarga de actualizar los datos del cliente
	 * que seran modificados
	 * @param nombre nombre del cliente que sera modificado
	 * @param apellidos apellidos del cliente a modificar
	 * @param correo correo del cliente a modificar
	 * @param telefono telefono del cliente a modificar
	 * @param usuario usuario del cliente a modificar
	 * @return regresa al cliente con los datos actualizados
	 */
	public Cliente actualizaCliente(String nombre, String apellidos, String correo, String telefono, String usuario) {
		Cliente cliente = clienteRepository.findByNombre(nombre);
		
		if(cliente == null) {
			throw new IllegalArgumentException("Este empleado no existe");
		}
		log.info("cliente modificado");
		
		cliente.setNombre(nombre);
		cliente.setApellidos(apellidos);
		cliente.setCorreo(correo);
		cliente.setTelefono(telefono);
		cliente.setUsuario(usuario);
		
		clienteRepository.save(cliente);
		
		return cliente;
	}
	
}
