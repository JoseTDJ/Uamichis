package mx.uam.ayd.proyecto.datos;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;


import mx.uam.ayd.proyecto.negocio.modelo.Asistencia;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

/**
 * Repositorio para Asistencias
 * 
 * @author Cesar
 *
 */
public interface AsistenciaRepository extends CrudRepository<Asistencia, Long> {
	
	public List<Asistencia> findByEmpleado(Empleado empleado);
	@Transactional
	public void deleteByEmpleado(Empleado empleado);

}
