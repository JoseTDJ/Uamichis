package mx.uam.ayd.proyecto.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.VentaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;
/**
 * Servicio para el repositorio de venta
 * @author AKarina
 *
 */
@Slf4j
@Service
public class ServicioVenta {
	
	@Autowired
	private VentaRepository ventaRepository;
	
	/**
	 * Obtiene las ventas por una fecha dada
	 * @param fechaF fecha a partir de la cual se obtiene las ventas
	 * @return lista de ventas de acuerdo a la fecha dada 
	 */	
	public List<Venta> obtenerVentasPorFecha(String fechaF) {
		List<Venta> ventas = ventaRepository.findByFecha(fechaF);
		return ventas;
	}
}
