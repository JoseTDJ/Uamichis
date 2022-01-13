package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.ReporteInventarioRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.ReporteInventario;

@ExtendWith(MockitoExtension.class)
class ServicioReporteInventarioTest {
	
	private ReporteInventario r = new ReporteInventario();

	@BeforeEach
	void setUp() throws Exception {
		Producto p = new Producto();
		p.setNombre("Jarabe");
		p.setCompuesto("Miel de abeja");
		p.setReceta("No");
		p.setUbicacion("Estante 2");
		p.setPiezas(2);
		p.setPrecio(50);
	
		r.setFecha("18/02/2021");
		r.setComentario("Esta roto");
		r.setEmpleadoQueReporta("Victor Sosa");
		r.setProducto(p);
		r.setNombreProducto(p.getNombre());
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Mock
	private ReporteInventarioRepository reporteInventarioRepository;
	
	@InjectMocks
	private ServicioReporteInventario servicioReporteInventario; 
	
	@Test
	void testRegistroReporteInventario() {
		//Prueba 1: Guarda bien el reporte
		List<ReporteInventario> reportes = new ArrayList<>();
		ReporteInventario reporte = new ReporteInventario();
		Producto producto = new Producto();
		producto.setNombre("Jarabe");
		producto.setCompuesto("Miel de abeja");
		producto.setReceta("No");
		producto.setUbicacion("Estante 2");
		producto.setPiezas(2);
		producto.setPrecio(50);
	
		reporte.setFecha("18/02/2021");
		reporte.setComentario("Esta roto");
		reporte.setEmpleadoQueReporta("Victor Sosa");
		reporte.setProducto(producto);
		reporte.setNombreProducto(producto.getNombre());
		
		reportes.add(reporte);
		when(reporteInventarioRepository.findAll()).thenReturn(reportes);
		servicioReporteInventario.registroReporteInventario("18/02/2021", "Victor Sosa", producto, "Esta roto");
		reportes = reporteInventarioRepository.findAll();
		assertEquals(1, reportes.size());
		
		//Prueba 2: Se paso un valor nulo.
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			servicioReporteInventario.registroReporteInventario("null", "Victor Sosa", producto, "Esta roto");
		});
	}
	
	@Test
	void testRecuperaReportes() {
		//Prueba 1: Regresa una lista vacia si no hay reportes.
		
		List<ReporteInventario> reportes = servicioReporteInventario.recuperaReportes();
		assertTrue(reportes.isEmpty());
		
		//Prueba 2: Corroborar que regresa una lista con los reportes si existe al menos un reporte
		reportes.add(r);
		when(reporteInventarioRepository.findAll()).thenReturn(reportes);
		reporteInventarioRepository.save(r);
		reportes = servicioReporteInventario.recuperaReportes();
		assertEquals(1, reportes.size());
	}
	
}
