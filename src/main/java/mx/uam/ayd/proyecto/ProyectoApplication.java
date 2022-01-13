package mx.uam.ayd.proyecto;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.GrupoRepository;
import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.datos.VentaRepository;
import mx.uam.ayd.proyecto.datos.ClienteRepository;
import mx.uam.ayd.proyecto.datos.DetalleVentaRepository;
import mx.uam.ayd.proyecto.datos.EmpleadoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Grupo;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.inicioSesion.ControlInicioSesion;
import mx.uam.ayd.proyecto.presentacion.inventario.ControlCargarArchivo;
import mx.uam.ayd.proyecto.presentacion.principal.ControlPrincipal;
import mx.uam.ayd.proyecto.presentacion.principal.empleado.ControlPrincipalEmpleados;
import mx.uam.ayd.proyecto.presentacion.asistencia.controlAsistencias;

@Slf4j
@SpringBootApplication
public class ProyectoApplication {

	@Autowired
	ControlInicioSesion controlInicioSesion;

	@Autowired
	ProductoRepository productoRepository;
	
	@Autowired
	EmpleadoRepository empleadoRepository;

	@Autowired
	DetalleVentaRepository detalleVentaRepository;

	@Autowired
	VentaRepository ventaRepository;

	@Autowired
	ControlPrincipal controlPrincipal;

	@Autowired
	GrupoRepository grupoRepository;
	
	@Autowired
	ControlCargarArchivo controlCargarArchivo;

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	private controlAsistencias controlAsistencia;

	public static void main(String[] args) {

		SpringApplicationBuilder builder = new SpringApplicationBuilder(ProyectoApplication.class);

		builder.headless(false);

		builder.run(args);
	}

	@PostConstruct
	public void inicia() {
		
		inicializaBD();
		controlInicioSesion.inicia();
	}

	//Inicia la Base de Datos
	public void inicializaBD() {
		
		String ruta = "./ejemplo-carga.txt";
		try {
			controlCargarArchivo.cargaDefault(ruta);
		} catch (NumberFormatException | IOException e) {
			log.warn(">> EL ARCHIVO DEFAULT NO PUDO SER CARGADO");
		}

		Empleado pruebaEmpleado = new Empleado("Fernando", "Ruiz Cervantes", 20, "Nezayork","fernando@gmail.com", "551345678",
				"empleado", "chepy", "123456789");
		Empleado pruebaEncargado = new Empleado("Cesar", "Guerrero Torres", 22, "Valle del Charco", "cesar@gmail.com", "5587312342",
				"encargado", "chiwuas", "1234512345");
		
		Empleado pruebaEmpleado2 = new Empleado("Yessica", "Santiago Valdes", 21, "Av. mi casa","yessica@gmail.com", "5512385356",
				"empleado", "yess", "1234554321");
		
		Empleado pruebaEncargado2 = new Empleado("Jose", "Treviño de Jesus", 22, "Ecatepunk", "jose@gmail.com", "5587431333",
				"encargado", "jose", "987654321");
		
		empleadoRepository.save(pruebaEmpleado);
		empleadoRepository.save(pruebaEmpleado2);
		empleadoRepository.save(pruebaEncargado);
		empleadoRepository.save(pruebaEncargado2);

		Grupo grupoAdmin = new Grupo();
		grupoAdmin.setNombre("Administradores");
		grupoRepository.save(grupoAdmin);

		Grupo grupoOps = new Grupo();
		grupoOps.setNombre("Operadores");
		grupoRepository.save(grupoOps);
		
		
	}
}
