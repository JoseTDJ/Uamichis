package mx.uam.ayd.proyecto.presentacion.pedidoRealizado;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioPedidoProveedor;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoProveedor;
import mx.uam.ayd.proyecto.presentacion.principal.empleado.ControlPrincipalEmpleados;
import mx.uam.ayd.proyecto.presentacion.principal.encargado.ControlPrincipalEncargado;

@Component
public class ControlPedidoRealizado {
	
	@Autowired
	private VentanaPedidoRealizado ventanaPedidoRealizado;
	
	@Autowired
	private ControlPrincipalEmpleados controlPrincipalEmpleado;
	
	@Autowired
	private ControlPrincipalEncargado controlPrincipalEncargado;
	
	@Autowired
	private ServicioPedidoProveedor servicioPedidoProveedor;
	
	List<PedidoProveedor> pedidos = new ArrayList<>();
	
	public void inicia(Empleado empleado) {
		pedidos = servicioPedidoProveedor.obtenerPedidos();
		//pedidos = servicioPedidoProveedor.obtenerPedidoSinFechaDeRecepcion();
		ventanaPedidoRealizado.muestra(this, empleado, pedidos);
	}

	public void regresar(Empleado empleado) {
		if(empleado.getNivel().equals("encargado")) {
			controlPrincipalEncargado.inicia(empleado);
			ventanaPedidoRealizado.oculta();
		}else if(empleado.getNivel().equals("empleado")) {
			controlPrincipalEmpleado.inicia(empleado);
			ventanaPedidoRealizado.oculta();
		}
	}

	/*public void obtenerPedido() {
			
	}*/

}
