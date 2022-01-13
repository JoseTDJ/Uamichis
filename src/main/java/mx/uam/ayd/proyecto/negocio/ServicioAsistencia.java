package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.AsistenciaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Asistencia;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

@Slf4j
@Service
public class ServicioAsistencia {

	@Autowired
	private AsistenciaRepository asistenciaRepository;


	public void registroAsistencia(String horaInicial, String horaFinal, String fecha, Empleado empleado) {
		Asistencia asistencia = new Asistencia();
		asistencia.setHoraInicial(horaInicial);
		asistencia.setHoraFinal(horaFinal);
		asistencia.setFecha(fecha);
		asistencia.setEmpleado(empleado);
		asistenciaRepository.save(asistencia);
	}

	/**
	 * 
	 * Recupera todos las Asistencias
	 * 
	 * @return
	 */

	public List<Asistencia> recuperarasistencia() {
		List<Asistencia> asistencias = new ArrayList<>();

		for (Asistencia asistencia : asistenciaRepository.findAll()) {
			asistencias.add(asistencia);
		}

		return asistencias;
	}
	/**
	 * 
	 * Recupera todos las asistenciasPorEmpelado
	 * 
	 * @return
	 */

	public List<Asistencia> obtenerAsistenciasPorEmpleado(Empleado empleado) {
		return asistenciaRepository.findByEmpleado(empleado);
	}

	public void actualizar(Asistencia asistenciaAEditar) {
		 asistenciaRepository.save(asistenciaAEditar);
	}

	public void eliminarAsistencia(Empleado empleado) {
		asistenciaRepository.deleteByEmpleado(empleado);
		
	}


}
