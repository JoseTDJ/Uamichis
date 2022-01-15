package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;


import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

public interface EmpleadoRepository extends CrudRepository<Empleado, Long> {
	public Empleado findByNombre(String nombre);
	public Empleado findByUsuario(String usuario);
	public Empleado findByNombreAndApellidoAndTelefono(String nombre, String apellido, String telefono);
	public Empleado findByPassword(String contrasena);
}
