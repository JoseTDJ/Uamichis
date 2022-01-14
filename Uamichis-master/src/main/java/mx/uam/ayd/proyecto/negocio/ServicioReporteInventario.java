package mx.uam.ayd.proyecto.negocio;

/**
 * @author VICTOR_SOSA
 */

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.ReporteInventarioRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.ReporteInventario;

@Service
public class ServicioReporteInventario {
	
	@Autowired
	private  ReporteInventarioRepository reporteInventarioRepository;
	
	/**
	 * Metodo que genera un reporte guadando el nombre del producto
	 * el nombre del empleado que reporta y la fecha.
	 * @param fecha: del dia que se lavanto el reporte
	 * @param empleado, el empleado que levanto el reporte
	 * @param nombre del producto
	 * @param comentario el comentario del reporte
	 */
	public void registroReporteInventario(String fecha, String nombreEmpleado, Producto producto, String comentario) {
		ReporteInventario reporte = new ReporteInventario();
		//Si algun valor que llega es null
		if(fecha == "null" || nombreEmpleado == "null" || producto == null || comentario == "null")
			throw new IllegalArgumentException("Algun elemento es null");
		
		//Si no se hace el reporte.
		reporte.setFecha(fecha);
		reporte.setProducto(producto);
		reporte.setNombreProducto(producto.getNombre());
		reporte.setEmpleadoQueReporta(nombreEmpleado);
		reporte.setComentario(comentario);
		reporteInventarioRepository.save(reporte);
	}
	
	
	/**
	 * Metodo que recupera todos los reportes realizados
	 * por el empleado con nivel encargado
	 * @return una lista de reportes
	 */
	public List<ReporteInventario> recuperaReportes(){
		List<ReporteInventario> reportes = new ArrayList<>();
		for (ReporteInventario reporte : reporteInventarioRepository.findAll()) {
			reportes.add(reporte);
		}
		return reportes;
	}
}
