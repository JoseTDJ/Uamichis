package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.EmpleadoRepository;
import mx.uam.ayd.proyecto.datos.ReporteEmpleadosRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.ReporteEmpleados;

@ExtendWith(MockitoExtension.class)
public class ServicioReporteEmpleadosTest {
	
	@Mock
	private ReporteEmpleadosRepository reporteEmpleadosRepository;
	
	@Mock
	private EmpleadoRepository empleadoRepository;
	
	@InjectMocks
	private ServicioReporteEmpleados servicioReporteEmpleados;
	
	@InjectMocks
	private ServicioEmpleado servicioEmpleado;
	
	//private Empleado empleadoQueReporta = new Empleado();
	//private Empleado empleadoReportado = new Empleado();
	private ReporteEmpleados reporte1 = new ReporteEmpleados();
	
	@BeforeEach
	void setUp() throws Exception {
		
		Empleado pruebaEncargado = new Empleado("Ximena", "Pereda Rodriguez", 22, "Av. Ballena", "ximena@gmail.com", "5587388642", "encargado", "alma", "987654321");
		Empleado pruebaEmpleado2 = new Empleado("Alfonso", "Jimenez Guzman", 21, "Av. Patito #43","alfonso@gmail.com", "5587384356", "empleado", "alfonso", "Alfonso1234");
		
		//creando los reportes
		reporte1.setFecha("31/1/2021");
		reporte1.setEmpleadoQueReporta(pruebaEncargado.getNombre() + " " + pruebaEncargado.getApellido());
		reporte1.setComentario("Llego tarde");
		reporte1.setEmpleado(pruebaEmpleado2);
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testRecuperaReportes() {
		//1.- Corroborar que regresa una lista vacia si no hay reportes
		List<ReporteEmpleados> reportes = servicioReporteEmpleados.recuperaReportes();
		assertTrue(reportes.isEmpty());
		
		//2.- Corroborar que regresa una lista con los reportes si existe al menos un reporte
		reportes.add(reporte1);
		when(reporteEmpleadosRepository.findAll()).thenReturn(reportes);
		reporteEmpleadosRepository.save(reporte1);
		reportes = servicioReporteEmpleados.recuperaReportes();
		assertEquals(1, reportes.size());
		
	}
	
	@Test
	void testRecuperaReportesPorEmpleado() {
		//1.- Corroborar que regresa una lista vacia si no hay reportes
		Empleado empleado = new Empleado();
		List<ReporteEmpleados> reportes = servicioReporteEmpleados.recuperaReportesPorEmpleado(empleado);
		assertTrue(reportes.isEmpty());
		
		//2.- Corroborar que regresa una lista con los reportes si existe al menos un reporte
		reportes.add(reporte1);
		when(reporteEmpleadosRepository.findByEmpleado(empleado)).thenReturn(reportes);
		reporteEmpleadosRepository.save(reporte1);
		reportes = servicioReporteEmpleados.recuperaReportesPorEmpleado(empleado);
		assertEquals(1, reportes.size());
		
	}
	
	@Test
	void testRegistroReporteConEmpleado() {
		//1- Guarda un reporte
		Empleado pruebaEmpleado = new Empleado("Karina", "Vergara Guzman", 20, "Av. Patito","karina@gmail.com", "5587388643", "empleado", "anver", "123456789");
		Empleado pruebaEncargado2 = new Empleado("Raul", "Gomez Rodriguez", 22, "Av. Ballena#45", "raul@gmail.com", "5587434355", "encargado", "raul", "Raul12345");
		ReporteEmpleados reporte2 = new ReporteEmpleados();
		reporte2.setFecha("31/1/2021");
		reporte2.setComentario("No llego");
		reporte2.setEmpleado(pruebaEmpleado);
		reporte2.setEmpleadoQueReporta(pruebaEncargado2.getNombre());
		
		servicioReporteEmpleados.registroReporteConEmpleado("31/1/2021", "No llego", pruebaEmpleado, pruebaEncargado2.getNombre());
		List<ReporteEmpleados> reportes2 = servicioReporteEmpleados.recuperaReportes();
		reportes2.add(reporte2);
		reporteEmpleadosRepository.save(reporte2);
		reportes2 = servicioReporteEmpleados.recuperaReportes();
		assertTrue(reporte2 != null);
		
	}
	
}
