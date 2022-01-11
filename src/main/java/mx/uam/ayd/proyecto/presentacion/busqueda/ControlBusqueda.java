package mx.uam.ayd.proyecto.presentacion.busqueda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.venta.ControlVenta;




/**
 * Esta clase lleva el flujo de control de la ventana Busqueda
 * 
 * @author CesarGuerrero
 *
 */
@Component
public class ControlBusqueda {

	@Autowired
	private VentanaBusqueda ventanabusqueda;
	
	@Autowired
	private ControlVenta controlventa;
	
	@Autowired
	private ServicioProducto servicioProducto;
	
	
	
	//Inicia la ventana de Busqueda
	public void inicia() {
		ventanabusqueda.muestra(this);
	}
	
	
	//Metodo que buscar el producto por el criterio de nombre 
    public void buscarProducto(String nombre) {
		try {
			ventanabusqueda.muestra(this);
			ventanabusqueda.llena(servicioProducto.buscarProducto(nombre));

		} catch(Exception excep) {
			//Metodo que buscar el producto por el criterio de Compuesto
				try {
					ventanabusqueda.muestra(this);
					ventanabusqueda.llena(servicioProducto.buscarProductoCompuesto(nombre));

				} catch(Exception e) {
					ventanabusqueda.muestraDialogoConMensaje("El producto no existente : "+e.getMessage());
					
				}
		    }
	}
    
}//Fin del metodo controlBusqueda
