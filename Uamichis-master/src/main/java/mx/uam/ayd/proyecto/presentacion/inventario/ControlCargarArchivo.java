package mx.uam.ayd.proyecto.presentacion.inventario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

/**
 * 
 * @author Ana Karina Vergara Guzmán Se lleva el flujo de la ventana de cargar
 *         archivo
 *
 */
@Component
public class ControlCargarArchivo {

	@Autowired
	private VentanaCargarArchivo ventana;

	@Autowired
	private ServicioProducto servicioProducto;

	@Autowired
	private ControlInventario controlInventario;

	private String headerFile = "nombre|compuesto|receta|ubicación|precio|piezas";

	private Producto producto;

	private ArrayList<Producto> listaProductos = new ArrayList<Producto>();

	/**
	 * Inicia el flujo de control de la ventana de cargar archivo
	 * 
	 * @param empleado empleado que ha iniciado sesión
	 */
	public void inicia(Empleado empleado) {
		ventana.muestra(this, empleado);
	}

	/**
	 * Inicia la ventana inventario
	 * 
	 * @param empleado empleado que ha iniciado sesión
	 */
	public void inciaInventario(Empleado empleado) {
		ventana.oculta();
		controlInventario.inicia(empleado);
	}

	/**
	 * Solicta al serivcio agregar una lista de productos al repositorio
	 * 
	 * @param listaProductos lista de productos a agregar
	 */
	public void agregarProductos(ArrayList<Producto> listaProductos) {
		servicioProducto.agregarProductos(listaProductos);
	}

	/**
	 * Manda a la ventana de carga los resultados de la carga del archivo.
	 * 
	 * @param listaProductosFallo lista con los productos que no fueron agregados
	 * @param contExitos          total de productos agregados
	 * @param contFallos          total de productos duplicados
	 */
	public void muestraResultados(ArrayList<Producto> listaProductosFallo, int contExitos, int contFallos) {
		ventana.muestraResultados(listaProductosFallo, contExitos, contFallos);
	}
	
	/**
	 * Carga los productos del archivo default
	 * @param ruta ruta del archivo default
	 * @throws NumberFormatException excepcion al momento de convertir cantidades
	 * @throws IOException excepción al momento de leer el archivo
	 */
	public void cargaDefault(String ruta) throws NumberFormatException, IOException {
		FileReader f = new FileReader(ruta);
		BufferedReader b = new BufferedReader(f);
		String cadena;
		StringTokenizer st;
		String delimitador = "|";
		while ((cadena = b.readLine()) != null) {
			if (!cadena.equals(headerFile)) {
				producto = new Producto();
				st = new StringTokenizer(cadena, delimitador);
				ArrayList<String> productoDatos = new ArrayList<String>();
				while (st.hasMoreTokens()) {
					productoDatos.add(st.nextToken());
				}
				if (productoDatos.size() == 6) {
					producto.setNombre(productoDatos.get(0));
					producto.setCompuesto(productoDatos.get(1));
					producto.setReceta(productoDatos.get(2));
					producto.setUbicacion(productoDatos.get(3));
					producto.setPrecio(Float.parseFloat(productoDatos.get(4)));
					producto.setPiezas(Integer.parseInt(productoDatos.get(5)));
					listaProductos.add(producto);
				} else {
				}
			}
		}
		b.close();
		servicioProducto.agregarProductosDefault(listaProductos);
	}
}
