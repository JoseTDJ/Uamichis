package mx.uam.ayd.proyecto.presentacion.principal.empleado;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.ServicioEmpleado;
import mx.uam.ayd.proyecto.negocio.ServicioReporteEmpleados;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.ReporteEmpleados;
import mx.uam.ayd.proyecto.presentacion.actualizaInventario.ControlActualiza;
import mx.uam.ayd.proyecto.presentacion.busqueda.ControlBusqueda;
import mx.uam.ayd.proyecto.presentacion.cierreVenta.ControlCierreVenta;
import mx.uam.ayd.proyecto.presentacion.inicioSesion.ControlInicioSesion;
import mx.uam.ayd.proyecto.presentacion.monitoreo.ControlMonitoreo;
import mx.uam.ayd.proyecto.presentacion.recepcionMercancia.ControlRecepcionMercancia;
import mx.uam.ayd.proyecto.presentacion.reporteEmpleados.ControlNumReporte;
import mx.uam.ayd.proyecto.presentacion.reporteEmpleados.VentanaNumReporte;
import mx.uam.ayd.proyecto.presentacion.reporteEmpleados.VentanaReporte;
import mx.uam.ayd.proyecto.presentacion.reporteVenta.ControlReporteVenta;
import mx.uam.ayd.proyecto.presentacion.reporteInventario.ControlReporteInventario;
import mx.uam.ayd.proyecto.presentacion.venta.ControlVenta;


@Slf4j
@Component
public class ControlPrincipalEmpleados {

	@Autowired
	private ControlVenta controlVenta;

	@Autowired
	private VentanaPrincipalEmpleados ventana;
	
	@Autowired
	private VentanaNumReporte ventanaNumReporte;
	
	@Autowired
	private ControlCierreVenta controlCierreVenta;
	
	@Autowired
	private ControlInicioSesion controlInicioSesion;
	
	@Autowired
	private ControlBusqueda controlBusqueda;
	
	@Autowired
	private ControlMonitoreo controlMonitoreo;
	
	@Autowired
	private ControlRecepcionMercancia controlRecepcionMercancia;
	
	@Autowired
	private ControlActualiza controlActualiza;
		
	@Autowired
	private ControlNumReporte controlNumReporte;
	
	@Autowired
	private ServicioReporteEmpleados servicioReporteEmpleados;
	
	@Autowired
	private ServicioEmpleado servicioEmpleado;
	
	@Autowired
	ControlReporteVenta controlReporteVenta;
	
	@Autowired
	private ControlReporteInventario controlReporteInventario;

	/**
	 * Inicia el flujo de control de la ventana principal
	 * 
	 */
	public void inicia(Empleado empleado) {
		ventana.muestra(this, empleado);
		
	}
	
	public void mostrarReportes(Empleado empleado) {
		List<ReporteEmpleados> reportes = servicioReporteEmpleados.recuperaReportesPorEmpleado(empleado);

		if(reportes.isEmpty()) {
			log.info(">>>NO TIENES NINGUN REPORTE");
		} else {
			ventanaNumReporte.muestra(controlNumReporte, empleado);

		}
	}

	/**
	 * Metodo que arranca la historia de usuario "agregar productos para la venta"
	 * 
	 */
	public void agregarProductos(Empleado empleado) {

		controlVenta.inicia(empleado);
	}

	public void iniciaCierreVenta(Empleado empleado) {
		controlCierreVenta.inicia(empleado);
		ventana.oculta();
		
	}

	public void cerrarSesion(Empleado empleado) {
		controlMonitoreo.registrarCerrar(empleado);
		controlInicioSesion.inicia();
		ventana.oculta();
		
	}

	public void busqueda() {
		controlBusqueda.inicia();
	}

	public void iniciaRecepcionMercancia(Empleado empleado) {
		controlRecepcionMercancia.inicia(empleado);
		ventana.oculta();
		
	}

 	
	public void muestraVentanaActualiza(Empleado empleado) {
		controlActualiza.inicia(empleado);
	}
	
	

	public void reporteVenta(Empleado emp) {
		controlReporteVenta.inicia(emp);
	}

	/**
	 * Metodo que indica al control reporte inveterario
	 * que muestre su ventana.
	 * Comienza la HU-11 "Como empleado generar un reporte 
	 * acerca del inventario"
	 * @param empleado
	 */
	public void iniciaReporte(Empleado empleado) {
		controlReporteInventario.iniciaReporte(empleado);
		
	}
	
	
}
