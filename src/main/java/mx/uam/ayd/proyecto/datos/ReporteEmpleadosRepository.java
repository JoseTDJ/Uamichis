package mx.uam.ayd.proyecto.datos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.ReporteEmpleados;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

public interface ReporteEmpleadosRepository extends CrudRepository<ReporteEmpleados, Long> {
	
	public List<ReporteEmpleados> findByEmpleado(Empleado empleado);
	
	public void deleteByEmpleado(Empleado empleado);
	public ReporteEmpleados findByidReporte(long idReporte);
	public List<ReporteEmpleados> findAll();
}
