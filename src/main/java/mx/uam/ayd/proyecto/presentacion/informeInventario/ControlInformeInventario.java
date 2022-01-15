package mx.uam.ayd.proyecto.presentacion.informeInventario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.principal.encargado.ControlPrincipalEncargado;

@Component
public class ControlInformeInventario {
	
	@Autowired
	private ServicioProducto servicioProducto;
	
	@Autowired
	private ControlRealizarUnPedido controlRealizarUnPedido;
	
	@Autowired
	private VentanaInformeInventario ventanaInformeInventario;
	
	@Autowired
	private ControlPrincipalEncargado controlPrincipalEncargado;
	
	@Autowired
	private ControlPedidos controlPedidos;
	
	public void inicia(Empleado empleado) {
		List<Producto> productos = servicioProducto.recuperarProductosEscazes();
		ventanaInformeInventario.muestra(this, empleado, productos);
	}

	public void iniciaRealizarPedido(Empleado empleado, List<Producto> productos) {
		controlRealizarUnPedido.inicia(empleado, productos);
		ventanaInformeInventario.oculta();
	}
	
	public Producto obtenerProducto(String nombre) {
		try {
			return servicioProducto.buscarProducto(nombre);
		} catch (Exception ex) {
			return null;
		}
	}
	
	public void cerrar(Empleado empleado) {
		if (empleado.getNivel().equals("encargado")) {
			controlPrincipalEncargado.inicia(empleado);
			ventanaInformeInventario.oculta();
		}
	}

	public void iniciaPedidos(Empleado empleado) {
		ventanaInformeInventario.oculta();
		controlPedidos.inicia(empleado);
	}
	
}

