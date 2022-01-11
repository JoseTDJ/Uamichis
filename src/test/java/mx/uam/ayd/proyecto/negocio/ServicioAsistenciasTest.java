package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.AsistenciaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Asistencia;


@ExtendWith(MockitoExtension.class)
class ServicioAsistenciasTest {
	
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	// Al usar la anotación @Mock, el framework Mockito crea un sustituto
	// de la clase que regresa valores por default
	@Mock
	private AsistenciaRepository asistenciaRepository;
	
	
	// Esta anotación hace que se inyecten todos los Mocks al módulo que quiero
	// probar para que no haya nullPointerException por que las dependencias
	// no están satisfechas en tiempo de pruebas
	@InjectMocks
	private ServicioAsistencia servicio;
	
	@Test
	void testRecuperarAsistencias() {
		
		// Prueba 1: corroborar que regresa una lista vacía si no hay asistencias en la BD
		
				// en este momento, la invocación a asistenciarepository.findAll() regresa una lista vacía
				List <Asistencia> asistencias = servicio.recuperarasistencia();
				
				assertTrue(asistencias.isEmpty());

				// Prueba 2: corroborar que regresa una lista con asistencias
				LinkedList <Asistencia> lista = new LinkedList <> ();

				// Tengo que crear un Iterable <Asistencia> para que el método 
				// asistenciaRepository.findAll() no me regrese una lista vacía
				// cuando lo invoco
				Asistencia asistencia1 = new Asistencia();
				asistencia1.setHoraInicial("17:08:03");
				asistencia1.setHoraFinal("18:12:09");
				asistencia1.setFecha("12/11/2020");


				Asistencia asistencia2 = new Asistencia();
				asistencia1.setHoraInicial("17:08:03");
				asistencia1.setHoraFinal("18:12:09");
				asistencia1.setFecha("12/11/2020");
				
				
				lista.add(asistencia1);
				lista.add(asistencia2);
				
				// Al usar when, lo que hacemos es que definimos un comportamiento
				// para la invoación del método.
				// A partir de este punto, la invocación a asistenciaRepository.findAll() ya
				// no me regresa una lista vacía, si no que me regresa una listaLigada
				// vista como Iterable que tiene dos elementos
				when(asistenciaRepository.findAll()).thenReturn(lista);
				
				asistencias = servicio.recuperarasistencia();
				
				assertEquals(2,asistencias.size()); // Corroboro que tenga dos elementos
	}
	

}
