package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.ReporteEmpleadosRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.ReporteEmpleados;

@Service
public class ServicioReporteEmpleados {
	
	@Autowired
	private ReporteEmpleadosRepository reporteEmpleadosRepository;
	
	/**
	 * Metodo que genera un reporte guardando el nombre del empleado
	 * que esta en turno levantando el reporte
	 * @param fecha fecha en la que se creo el reporte
	 * @param comentario comentario que especifica la causa del reporte
	 * @param empleado empleado al cual se le levanto el repote
	 * @param empleadoQueReporta encargado en turno quien realiza el reporte  
	 */
	public void registroReporteConEmpleado(String fecha, String comentario, Empleado empleado, String empleadoQueReporta) {
		ReporteEmpleados reporte = new ReporteEmpleados();
		reporte.setFecha(fecha);
		reporte.setComentario(comentario);
		reporte.setEmpleado(empleado);
		reporte.setEmpleadoQueReporta(empleadoQueReporta);
		reporteEmpleadosRepository.save(reporte);
	}
	
	/**
	 * Metodo que recupera todos los reportes realizados
	 * por el empleado con nivel encargado
	 * @return una lista de reportes
	 */
	public List<ReporteEmpleados> recuperaReportes(){
		List<ReporteEmpleados> reportes = new ArrayList<>();
		for (ReporteEmpleados reporte : reporteEmpleadosRepository.findAll()) {
			reportes.add(reporte);
		}
		return reportes;
	}
	
	/**
	 * Metodo que recupera todos los reportes realizados
	 * por el empleado con nivel encargado
	 * @return una lista de reportes de un empleado en especifico 
	 */
	public List<ReporteEmpleados> recuperaReportesPorEmpleado(Empleado empleado){//@
		List<ReporteEmpleados> reportes = new ArrayList<>();
		
		for (ReporteEmpleados reporte : reporteEmpleadosRepository.findByEmpleado(empleado)) {
			reportes.add(reporte);
		}
		
		return reportes;
	}
	

	
}
