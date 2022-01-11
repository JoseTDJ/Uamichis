package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.ReporteVentasRepository;
import mx.uam.ayd.proyecto.negocio.modelo.ReporteVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;

@Service
public class ServicioReporteVenta {

	@Autowired
	private ReporteVentasRepository reporteVentasRepository;
	
	/**
	 * Metodo que registra el reporte de la venta con sus datos
	 * y el nombre del empleado que esta haciendo el reporte.
	 * @param venta
	 * @param fecha
	 * @param idVenta
	 * @param comentario
	 * @param empQueReporta
	 */
	public void registroReporteVenta(Venta venta, String fecha, long idVenta, String comentario, String empQueReporta) {
		ReporteVenta reporte = new ReporteVenta();
		reporte.setFecha(fecha);
		reporte.setComentario(comentario);
		reporte.setVenta(venta);
		reporte.setEmpQueReporta(empQueReporta);
		reporte.setIdVenta(idVenta);
		reporteVentasRepository.save(reporte);
	}
	
	/**
	 * Metodo para obtener todos los reportes de ventas que se han hecho.
	 * @return
	 */
	public List<ReporteVenta> recuperaReportes(){
		List<ReporteVenta> reportes = new ArrayList<>();
		for (ReporteVenta reporte : reporteVentasRepository.findAll()) {
			reportes.add(reporte);
		}
		return reportes;
	}
}
