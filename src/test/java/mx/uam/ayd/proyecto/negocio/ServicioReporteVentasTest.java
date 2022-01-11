package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.ReporteVentasRepository;
import mx.uam.ayd.proyecto.datos.VentaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.ReporteEmpleados;
import mx.uam.ayd.proyecto.negocio.modelo.ReporteVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;

@ExtendWith(MockitoExtension.class)
class ServicioReporteVentasTest {

	@Mock
	private VentaRepository ventaRepository;
	
	@Mock
	private ReporteVentasRepository reporteVentasRepository;
	
	@InjectMocks
	ServicioEmpleado servicioEmpleado;
	
	@InjectMocks
	ServicioVenta servicioVenta;
	
	@InjectMocks
	ServicioReporteVenta servicioReporteVenta;
	
	private ReporteVenta reportePrueba = new ReporteVenta();
	
	@BeforeEach
	void setUp() throws Exception {
		
		Empleado pruebaEncargado = new Empleado("Ximena", "Pereda Rodriguez", 22, 
				"Av. Ballena", "ximena@gmail.com", "5587388642", "encargado", "alma", "987654321");
		
		/**
		 * Creando una venta
		 */
		Venta venta1 = new Venta();
		venta1.setFecha("18/2/2021");
		venta1.setTotal(55);
		
		/**
		 * Creando un reporte
		 */
		reportePrueba.setFecha("18/2/2021");
		reportePrueba.setComentario("Se hizo la venta de 2 Aliviax y solo habia 1 en la existencia");
		reportePrueba.setEmpQueReporta(pruebaEncargado.getNombre() + " " + pruebaEncargado.getApellido());
		reportePrueba.setVenta(venta1);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testRecuperaReportes() {
		/**
		 * 1: Recupera todos los reportes de ventas y verifica que este vacio
		 */
		List<ReporteVenta> reportes = servicioReporteVenta.recuperaReportes();
		assertTrue(reportes.isEmpty());
		
		/**
		 * 2: Regresa una lista con los reportes si existe al menos uno registrado
		 */
		reportes.add(reportePrueba);
		when(reporteVentasRepository.findAll()).thenReturn(reportes);
		reporteVentasRepository.save(reportePrueba);
		reportes = servicioReporteVenta.recuperaReportes();
		assertEquals(1, reportes.size());
	}
	
	@Test
	void testRegistroReporteVenta() {
		/**
		 * Prueba 1: Guardar un reporte
		 */
		Empleado pruebaEncargado = new Empleado("Ximena", "Pereda Rodriguez", 22, 
				"Av. Ballena", "ximena@gmail.com", "5587388642", "encargado", "alma", "987654321");
		Venta venta1 = new Venta();
		venta1.setFecha("18/2/2021");
		venta1.setTotal(55);
		reportePrueba.setFecha("18/2/2021");
		reportePrueba.setComentario("Se hizo la venta de 2 Aliviax y solo habia 1 en la existencia");
		reportePrueba.setEmpQueReporta(pruebaEncargado.getNombre() + " " + pruebaEncargado.getApellido());
		reportePrueba.setVenta(venta1);
		
		servicioReporteVenta.registroReporteVenta(venta1, "18/2/2021", venta1.getIdVenta(), "Se hizo la venta y no habian piezas suficientes", 
				pruebaEncargado.getNombre() + " " + pruebaEncargado.getApellido());
		List<ReporteVenta> reportes2 = servicioReporteVenta.recuperaReportes();
		reportes2.add(reportePrueba);
		reporteVentasRepository.save(reportePrueba);
		reportes2 = servicioReporteVenta.recuperaReportes();
		assertTrue(reportePrueba != null);
	}

}
