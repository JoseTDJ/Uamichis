package mx.uam.ayd.proyecto.presentacion.cierreVenta;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;

import mx.uam.ayd.proyecto.negocio.ServicioCliente;
import mx.uam.ayd.proyecto.negocio.ServicioPedidoCliente;
import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.ServicioVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.DetallePedidoCliente;
import mx.uam.ayd.proyecto.negocio.modelo.DetalleVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoCliente;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;
import mx.uam.ayd.proyecto.presentacion.inicioSesion.ControlInicioSesion;
import mx.uam.ayd.proyecto.presentacion.monitoreo.ControlMonitoreo;
import mx.uam.ayd.proyecto.presentacion.principal.empleado.ControlPrincipalEmpleados;
import mx.uam.ayd.proyecto.presentacion.principal.encargado.ControlPrincipalEncargado;

@Component
public class ControlCierreVenta {
	@Autowired
	private VentanaCierreVenta ventana;

	@Autowired
	private ServicioVenta servicioVenta;

	@Autowired
	private ServicioProducto servicioProducto;
	
	@Autowired
	private ServicioPedidoCliente servicioPedidoCliente;
	
	@Autowired
	private ServicioCliente servicioCliente;
	
	@Autowired
	private ControlPrincipalEmpleados controlPrincipalEmpleado;
	
	@Autowired
	private ControlPrincipalEncargado controlPrincipalEncargado;
	
	@Autowired
	private ControlMonitoreo controlMonitoreo;
	
	@Autowired
	private ControlInicioSesion controlInicioSesion;

	/**
	 * Inicia el flujo de control de la ventana de cierre de venta
	 * 
	 * @param empleado contiene los datos del empleado que ha iniciado sesion
	 * 
	 */
	public void inicia(Empleado empleado) {
		ventana.muestra(this, empleado);
	}

	public void obtenerVentasDia(String fechaF) {
		List<Venta> ventasDia = servicioVenta.obtenerVentasPorFecha(fechaF);
		if(ventasDia.isEmpty()) {
			ventana.sinProductos("No hay ventas para mostrar");
		} else {
			for (Venta venta : ventasDia) {
				List<Producto> productos = new ArrayList<Producto>();
				for (DetalleVenta detalleVenta : venta.getVentas()) {
					productos.add(servicioProducto.obtenerProductoPorVenta(detalleVenta).get(0));
				}
				ventana.agregaVentas(venta, productos.size());
			}

		}
	}

	public void obtenerProductos() {
		List<Producto> productos = servicioProducto.obtenerProductos();
		if(productos.isEmpty()) {
			ventana.sinProductos("No hay productos para mostrar");
		} else {
			for (Producto producto : productos) {
				ventana.agregaProductos(producto);
			}
		}
	}

	public void obtenerPedidosClienteDelDia(String fechaF) {
		List<PedidoCliente> pedidosClienteDia = servicioPedidoCliente.obtenerPedidosPorFechaCreacion(fechaF);
		if(pedidosClienteDia.isEmpty()) {
			ventana.sinProductos("No hay pedidos para mostrar");
		} else {
			for (PedidoCliente pedidoCliente : pedidosClienteDia) {
				List<Cliente> cliente = servicioCliente.obtenerClientePorPedido(pedidoCliente);
				List<Producto> productos = new ArrayList<Producto>();
				for (DetallePedidoCliente detallePedidoCliente : pedidoCliente.getDetallesPedidoCliente()) {
					productos.add(detallePedidoCliente.getProducto());
				}
				ventana.agregarPedido(pedidoCliente, productos, cliente);
			}
		}
	}

	public void cancelaCierreVenta(Empleado empleado) {
		if (empleado.getNivel().equals("empleado")) {
			controlPrincipalEmpleado.inicia(empleado);
			ventana.oculta();
		} else if (empleado.getNivel().equals("encargado")) {
			controlPrincipalEncargado.inicia(empleado);
			ventana.oculta();
		}
		
	}

	public void cerrarSesion(Empleado empleado) {
		controlMonitoreo.registrarCerrar(empleado);
		controlInicioSesion.inicia();
		ventana.oculta();
		
	}
	
	public void generaPDF(JTable tab1) {
			
			String path = " ";
			JFileChooser j = new JFileChooser();
			j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int x = j.showSaveDialog(null);
			
			if(x==JFileChooser.APPROVE_OPTION) {
				path = j.getSelectedFile().getPath();
				JOptionPane.showMessageDialog(null, "PDF generado exitosamente");
			}else
				JOptionPane.showMessageDialog(null,"Acción Cancelada");
			
			Document doc = new Document();
			try {
				PdfWriter.getInstance(doc, new FileOutputStream(path+".pdf"));
				
				doc.open();
				Font fuente = new Font();
				fuente.setSize(20);
				//PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ITALIC); 
				
				doc.add(new Paragraph("\tLista de las ventas realizadas durante el día \n",fuente));
				doc.add(new Paragraph("\n "));
				
				PdfPTable tb1 = new PdfPTable(3);
				
				tb1.addCell("Fecha de venta");
				tb1.addCell("Total de productos");
				tb1.addCell("Precio total");
				
				for (int i = 0; i<tab1.getRowCount(); i++) {
					String fechaVenta = tab1.getValueAt(i, 0).toString();
					String totalProduct = tab1.getValueAt(i, 1).toString();
					String precioTotalp = tab1.getValueAt(i, 2).toString();
					
					tb1.addCell(fechaVenta);
					tb1.addCell(totalProduct);
					tb1.addCell(precioTotalp);
				}
				
				doc.add(tb1);
				
			}catch(FileNotFoundException ex) {
				System.out.println("Error, no se pudo generar PDF");
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			doc.close();
		}
	

	public void generaInventarioPdf(JTable tab1) { //Método que genera un pdf del inventario
		
		String path = " ";
		JFileChooser j = new JFileChooser();
		j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int x = j.showSaveDialog(null);
		
		if(x==JFileChooser.APPROVE_OPTION) {
			path = j.getSelectedFile().getPath();
			JOptionPane.showMessageDialog(null, "PDF generado exitosamente");
		}else
			JOptionPane.showMessageDialog(null,"Acción Cancelada");
			Document doc = new Document(PageSize.A4.rotate()); //Pagina en horizontal
			
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(path+".pdf"));
			
			doc.open();
			
			Paragraph tittle = new Paragraph ("Inventario del día\n\n\n",
					FontFactory.getFont("Josefin Sans", 22, Font.BOLD, BaseColor.RED)
					);
			
			tittle.setAlignment(Element.ALIGN_CENTER); 
			doc.add(tittle); 
			
			
			PdfPTable tb1 = new PdfPTable(5);
			
			Paragraph nom = new Paragraph ("Nombre",
					FontFactory.getFont("Josefin Sans", 14, Font.BOLD, BaseColor.BLACK)
					);
			
			Paragraph comp = new Paragraph ("Compuesto",
					FontFactory.getFont("Josefin Sans", 14, Font.BOLD, BaseColor.BLACK)
					);
			
			Paragraph tot = new Paragraph ("Total de productos",
					FontFactory.getFont("Josefin Sans", 14, Font.BOLD, BaseColor.BLACK)
					);
			
			Paragraph prec = new Paragraph ("Precio",
					FontFactory.getFont("Josefin Sans", 14, Font.BOLD, BaseColor.BLACK)
					);
			
			Paragraph recet = new Paragraph ("Receta",
					FontFactory.getFont("Josefin Sans", 14, Font.BOLD, BaseColor.BLACK)
					);
			
			tb1.addCell(nom);	
			tb1.addCell(comp);
			tb1.addCell(tot);
			tb1.addCell(prec);
			tb1.addCell(recet);
			
			for (int i = 0; i<tab1.getRowCount(); i++) {
				String nombre = tab1.getValueAt(i, 0).toString();
				String compuesto = tab1.getValueAt(i, 1).toString();
				String totalProducto = tab1.getValueAt(i, 2).toString();
				String precio = tab1.getValueAt(i, 3).toString();
				String receta = tab1.getValueAt(i, 4).toString();
				
				tb1.addCell(nombre);
				tb1.addCell(compuesto);
				tb1.addCell(totalProducto);
				tb1.addCell(precio);
				tb1.addCell(receta);
			}
			
			doc.add(tb1);
			
		}catch(FileNotFoundException ex) {
			System.out.println("Error, no se pudo generar PDF");
		} catch (DocumentException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		doc.close();
	}
	
	
}
