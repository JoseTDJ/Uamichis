 package mx.uam.ayd.proyecto.presentacion.controlEmpleados;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mx.uam.ayd.proyecto.negocio.ServicioEmpleado;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.principal.encargado.ControlPrincipalEncargado;
import mx.uam.ayd.proyecto.presentacion.principal.encargado.VentanaPrincipalEncargado;

@Component
public class ControlEmpleados {
	
	@Autowired
	private VentanaControlEmpleados ventana;
	
//	@Autowired
//	private VentanaAgregarEmpleado ventanaAgregar;

//	
//	@Autowired
//	private VentanaModificarEmpleado ventanaModificar;
	
	@Autowired
	private ServicioEmpleado servicioEmpleado;
	
	@Autowired
	private ControlPrincipalEncargado controlEncargado;
	
	@Autowired
	private VentanaPrincipalEncargado ventanaEncargado;
	
	
	/**
	 * Inicia la ventanaControlEmpleados
	 * @param empleado que ha iniciado sesion
	 */
	public void inicia(Empleado empleado) {
		ventana.muestra(this, empleado);
	}
	
	public void iniciaPrincipal(Empleado empleado) {
		controlEncargado.inicia(empleado);
	}
	
	/**
	 * Inicia la ventana de Agregar Empleado
	 * @param empleado que ha iniciado sesion
	 */
//	public void iniciaAgregar(Empleado empleado) {
//		ventanaAgregar.muestra(this, empleado);
//	}
//	
	/**
	 * Inicia la ventana de Modificar Empleado
	 * @param empleado que ha iniciado sesion
	 * @param nombre nombre del empleado que se quiere modificar
	 */
//	public void iniciaModificar(Empleado empleado, String nombre) {
//		List<Empleado> empleados = servicioEmpleado.recuperaEmpleados();
//		Empleado empleado2 = servicioEmpleado.buscarEmpleado(nombre);
//
//		ventanaModificar.muestra(this, empleado, empleados, empleado2);
//	}
	
	/**
	 * Se agrega a un empleado nuevo
	 * Y se valida la contraseña para que no sea identica a otro usuario
	 * @param nombre nombre del empleado
	 * @param apellido apellido del empleado
	 * @param edad edad del empleado
	 * @param direccion direccion del empleado
	 * @param telefono telefono del empleado
	 * @param contrasena contrasena del empleado
	 */

	
//	public void agregarEmpleado( String nombre, String apellido, String nivel, int edad, String direccion, String telefono, String usuario ,String contrasena) {
//
//		try {
//			servicioEmpleado.agregarEmpleado(nombre, apellido, nivel, edad, direccion, telefono, usuario,contrasena);
//			ventanaAgregar.muestraDialogoConMensaje("Usuario agregado exitosamente");
//			
//		} catch (Exception e) {
//			ventanaAgregar.muestraDialogoConMensaje("Error al agregar usario: " + e.getMessage());
//		}
//		
//	}
	
	public Empleado validarContrasena(String contrasena) {
		
			Empleado empleado = servicioEmpleado.validarContrasena(contrasena);
			
			if (empleado == null) {
				return null;
			} else {
				return empleado;
			}
		
	}
	
	public Empleado validarUsuario(String usuario) {
		Empleado empleado = servicioEmpleado.validaUsuario(usuario);
		
		if (empleado == null) {
			return null;
		} else {
			return empleado;
		}
	}
	
	public void buscarEmpleado(String nombre) {
		servicioEmpleado.buscarEmpleado(nombre);

	}
	
	/**
	 * Actualiza el empleado 
	 * Y se valida la contraseña para que no sea identica a otro usuario
	 * @param nombre nombre del empleado
	 * @param apellido apellido del empleado
	 * @param edad edad del empleado
	 * @param direccion direccion del empleado
	 * @param telefono telefono del empleado
	 * @param contrasena contrasena del empleado
	 */
//	public void actualizarEmpleado(String nombre, String apellido, String nivel, int edad, String direccion, String telefono,  String usuario, String contrasena) {
//		try {
//				
//				servicioEmpleado.actualizarEmpleado(nombre, apellido, nivel, edad, direccion, telefono, usuario, contrasena);
//				ventanaModificar.muestraDialogoConMensaje("Usuario modificado exitosamente");
//		
//			
//		} catch (Exception e) {
//			
//			ventanaAgregar.muestraDialogoConMensaje("Error al modificar usario: " + e.getMessage());
//		}
//		
//	}
//	
	
	
	
	/**
	 * Elimina a empleados de la BD
	 * @param nombre nombre del empleado a eliminar
	 */
	public void eliminarEmpleado(List<String> nombre) {
	
		try {
			if (servicioEmpleado.eliminarEmpleado(nombre)) {
				ventana.muestraDialogoConMensaje("Usuario eliminado exitosamente");
			}
			
		} catch (Exception e) {

			ventana.muestraDialogoConMensaje("Error al eliminar usario: " + e.getMessage());

		}
	}
	
	
	/**
	 * Se recupera a todos los empleados de la BD
	 */
	
	public void recuperaEmpleados(Empleado empleadoSesion) {
		List<Empleado> empleados = servicioEmpleado.recuperaEmpleados();
		ventana.limpiarTablas();
		
		if (empleados.isEmpty()) {
			ventana.muestraDialogoConMensaje("No hay ningun empleado por mostrar");
		} else {
			for (Empleado empleado : empleados) {
				if (!empleado.getNombre().equals(empleadoSesion.getNombre())) {
					ventana.agregarEmpleados(empleado);
				} 
			}
		}
		
	}
	


	

	

	
	

}
