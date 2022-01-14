package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.EmpleadoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

@Slf4j
@Service
public class ServicioEmpleado {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	private ServicioAsistencia servicioAsistencia;
	
	/**
	 * Se agrega a un empleado al repositorio
	 * @param nombre
	 * @param apellido
	 * @param edad
	 * @param direccion
	 * @param telefono
	 * @param contrasena
	 * @return 
	 */
	public Empleado agregarEmpleado(String nombre, String apellido, String nivel, int edad, String direccion, String telefono, String usuario,String contrasena) {
		
		Empleado empleado = empleadoRepository.findByNombreAndApellidoAndTelefono(nombre, apellido, telefono);
		@SuppressWarnings("deprecation")
		Integer b = new Integer(edad);
		if (empleado != null) {
			throw new IllegalArgumentException("Ese empleado ya existe");
		}
		
		if (nombre == null && apellido == null && nivel == null  &&b == null && direccion == null && telefono == null && usuario == null  && contrasena == null) {
			throw new IllegalArgumentException("Error al crear a un empleado");

		}
		
		log.info("Agregando usuario nombre: "+nombre+" apellido: "+apellido + " edad: " + edad + " direccion: " + " telefono: " + telefono + " contrasena: " + contrasena);
		
		empleado = new Empleado();
		
		empleado.setNombre(nombre);
		empleado.setApellido(apellido);
		empleado.setNivel(nivel);
		empleado.setEdad(edad);
		empleado.setDireccion(direccion);
		empleado.setTelefono(telefono);
		empleado.setUsuario(usuario);
		empleado.setPassword(contrasena);
		
		empleadoRepository.save(empleado);
	
		return empleado;
	}
	
	/**
	 * Actualiza un empleado en el repositorio
	 * @param nombre
	 * @param apellido
	 * @param edad
	 * @param direccion
	 * @param telefono
	 * @param contrasena
	 * @return 
	 */
	
	public Empleado actualizarEmpleado(String nombre, String apellido, String nivel, int edad, String direccion, String telefono, String usuario ,String contrasena) {
		Empleado empleado = empleadoRepository.findByNombre(nombre);
		
		if(empleado == null) {
			throw new IllegalArgumentException("Este empleado no existe");
		}
		
		log.info("Usuario modificado nombre: "+nombre+" apellido: "+apellido + " edad: " + edad + " direccion: " + " telefono: " + telefono + " contrasena: " + contrasena);

		
		empleado.setNombre(nombre);
		empleado.setApellido(apellido);
		empleado.setNivel(nivel);
		empleado.setEdad(edad);
		empleado.setDireccion(direccion);
		empleado.setTelefono(telefono);
		empleado.setUsuario(usuario);
		empleado.setPassword(contrasena);
		
		empleadoRepository.save(empleado);
		
		return empleado;
	}
	
	
	/**
	 * Elimina a un empleado del repositorio 
	 * @param listaNombres nombres de los empleados a eliminar
	 * @return true en caso de eliminar al empleado, false en caso de que
	 *         alguno no pueda ser eliminado
	 */
	public boolean eliminarEmpleado(List<String> listaNombres) {
		Empleado empleado;
		String nombreEmpleado = null;
		log.info("Encargado: " + listaNombres.get(0));
		
		try {
			for (String nombre : listaNombres) {
				nombreEmpleado = nombre;
				empleado = empleadoRepository.findByNombre(nombre);
				servicioAsistencia.eliminarAsistencia(empleado);
				empleadoRepository.delete(empleado);
			}
			return true;
		} catch (IllegalArgumentException e) {
			
			return false;
		}
	}
	
	/**
	 * Valida las contraseñas de los empleados
	 * para que no sean iguales 
	 * @param contrasena
	 * @return empleado con la contraseña que tiene asignada 
	 */
	public Empleado validarContrasena(String contrasena) {
		Empleado empleado = empleadoRepository.findByPassword(contrasena);
		if (contrasena == null) {
			throw new IllegalArgumentException("Algun empleado ya tiene la contrasena: " + contrasena);
		} 
		
		if (empleado == null) {
			return null;
		}

		return empleado;
	}
	
	public Empleado validaUsuario(String usuario) {
		Empleado empleado = empleadoRepository.findByUsuario(usuario);
		if (usuario == null) {
			throw new IllegalArgumentException("Algun empleado ya tiene ese usuario: " + usuario);
		} 
		
		if (empleado == null) {
			return null;
		}

		return empleado;
	}
	
	/**
	 * Busca a un empleado por nombre
	 * @param nombre
	 * @return empleado encontrado
	 */
	public Empleado buscarEmpleado(String nombre) {
		Empleado empleado = empleadoRepository.findByNombre(nombre);
		
		if (nombre == null) {
			throw new IllegalArgumentException("No se encontro al empleado");

		}
		
		if (empleado == null) {
			return null;
		} else {
			log.info("El empleado es: " + empleado.getIdEmpleado() + ": " + empleado.getNombre());
		}
		return empleado;


	}
	
	
	
	public List<Empleado> recuperaEmpleados() {
		
		List<Empleado> empleados = new ArrayList<>();
		for (Empleado empleado2 : empleadoRepository.findAll()) {
			empleados.add(empleado2);
		}

		return empleados;
	}

	public Empleado validaUsuario(String usuario, String password) {
		Empleado empleado = empleadoRepository.findByUsuario(usuario);
		if (validaPassword(password, empleado.getPassword())) {
			return empleado;
		} else {
			throw new IllegalArgumentException("La contraseña es incorrecta");
		}
	}

	
	
	private boolean validaPassword(String passwordIngresada, String password) {
		if (password.equals(passwordIngresada)) {
			return true;
		} else {
			return false;
		}
	}
	
	public Empleado guardarEmpleado(Empleado empleado) {
		return empleadoRepository.save(empleado);
	}



}
