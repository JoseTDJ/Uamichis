package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.DetallePedidoProveedorRepository;
import mx.uam.ayd.proyecto.negocio.modelo.DetallePedidoProveedor;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoProveedor;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

@Service
public class ServicioDetallePedidoProveedor {
	@Autowired
	private DetallePedidoProveedorRepository detallePedidoProveedorRepository;
	
	@Autowired
	private ServicioPedidoProveedor servicioPedidoProveedor;
	
	@Autowired
	private ServicioProducto servicioProducto;
	
	/**
	 * Agrega el detalle de pedido a un pedido con sus respectivos productos 
	 * @param pedidoProveedor pedido al que se va a agregar
	 * @param producto producto al que se va a agregar
	 * @param piezas piezas por producto
	 * @param precioT indica el precio pagado por ese articulo
	 */
	public boolean agregarDetallePedidoProveedor(PedidoProveedor pedidoProveedor, Producto producto, int piezas, float precioT) {
		boolean result = false;
		if(!(pedidoProveedor == null || producto == null || precioT == 0)) {
			// creamos detallepedido, seteamos y guardamos
			DetallePedidoProveedor detallePedidoProveedor = new DetallePedidoProveedor();
			detallePedidoProveedor.setNumeroPiezas(piezas); 
			detallePedidoProveedor.setPrecioTotalXProducto(precioT);
			detallePedidoProveedorRepository.save(detallePedidoProveedor);
			
			// agregamos detalle pedido y guardamos
			result = pedidoProveedor.addDetallePedidoProveedor(detallePedidoProveedor);
			System.out.println(result);
			servicioPedidoProveedor.guardar(pedidoProveedor);
			
			
			// hacemos lo mismo para el producto
			result = producto.addDetallePedidoProveedor(detallePedidoProveedor);
			System.out.println(result);
			servicioProducto.guardar(producto);
			
			//guardamos el detalle
			detallePedidoProveedorRepository.save(detallePedidoProveedor);
			return result;
		} else {
			throw new IllegalArgumentException("Verifique que el pedido o producto es correcto.");
		}
		
	}

	public ArrayList<Producto> obtenerProductosPorPedido(PedidoProveedor pedido) {
		List<DetallePedidoProveedor> detallesDePedido = pedido.getDetallesPedidoProveedor();
		ArrayList<Producto> productos = new ArrayList<Producto>();
		System.out.println("El pedido es: " + pedido);
		for (DetallePedidoProveedor detallePedidoProveedor : detallesDePedido) {
			System.out.println("El detalle de pedido es: " + detallePedidoProveedor);
			productos.add(servicioProducto.obtenerProductoPorPedidoProveedor(detallePedidoProveedor));
		}
		return productos;
	}
}
