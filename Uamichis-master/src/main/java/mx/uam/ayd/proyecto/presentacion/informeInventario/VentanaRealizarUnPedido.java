package mx.uam.ayd.proyecto.presentacion.informeInventario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoProveedor;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

@Slf4j
@SuppressWarnings("serial")
@Component
public class VentanaRealizarUnPedido extends JFrame {

	private JPanel contentPane;
	private JTextField textCargo;
	private JTextField textEmpleado;
	private JTextField textNumEmpleado;
	private ControlRealizarUnPedido controlRealizarUnPedido;
	private List<Producto> productos;
	private Empleado empleado;
	private PedidoProveedor pedidoProveedor = null;
	private JScrollPane scrollPaneRealizaPedido;
	private JPanel panel_3;

	// tabla de productos para pedido
	DefaultTableModel modeloProductosPedido = new DefaultTableModel();
	private JTable tablaProductosPedido = new JTable(modeloProductosPedido) {
		public boolean isCellEditable(int rowIndex, int vColIndex) {
			if (vColIndex == 3) {
				return true;
			} else {
				return false;
			}
		}
	};

	/**
	 * Create the frame.
	 */
	public VentanaRealizarUnPedido() {
		setResizable(false);
		setTitle("FARMAPASS - Realizar pedido a proveedor");
		setBounds(100, 100, 441, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 414, 41);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		textCargo = new JTextField();
		textCargo.setEditable(false);
		textCargo.setBounds(10, 11, 86, 20);
		panel_1.add(textCargo);
		textCargo.setColumns(10);

		textEmpleado = new JTextField();
		textEmpleado.setEditable(false);
		textEmpleado.setBounds(106, 11, 250, 20);
		panel_1.add(textEmpleado);
		textEmpleado.setColumns(10);

		textNumEmpleado = new JTextField();
		textNumEmpleado.setBounds(366, 11, 34, 20);
		panel_1.add(textNumEmpleado);
		textNumEmpleado.setEditable(false);
		textNumEmpleado.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 63, 414, 32);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblRealizarPedido = new JLabel("Realizar Pedido");
		lblRealizarPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblRealizarPedido.setBounds(0, 0, 414, 32);
		panel_2.add(lblRealizarPedido);

		panel_3 = new JPanel();
		panel_3.setBounds(10, 107, 414, 211);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		scrollPaneRealizaPedido = new JScrollPane();
		scrollPaneRealizaPedido.setBounds(0, 0, 414, 200);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 329, 414, 41);
		contentPane.add(panel_4);
		panel_4.setLayout(null);

		// añadiendo
		modeloProductosPedido.addColumn("Nombre");
		modeloProductosPedido.addColumn("Descripción");
		modeloProductosPedido.addColumn("Pzs actuales");
		modeloProductosPedido.addColumn("Cantidad solicitada");

		JButton btnRealizarPedido = new JButton("Realizar pedido");
		btnRealizarPedido.setBounds(141, 11, 160, 23);
		panel_4.add(btnRealizarPedido);

		btnRealizarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeloProductosPedido = (DefaultTableModel) tablaProductosPedido.getModel();

				ArrayList<Integer> nuevasPiezas = new ArrayList<Integer>(); // parametro por pasar con el nuevo # de
																			// pzas
				int totalPiezas = 0; // # de pzas
				int contNoValidos = 0;
				String compruebaCantidad;
				float precioTotalPedido = 0;

				// creamos el pedido de proovedor y guardamos con la fecha actual
				pedidoProveedor = new PedidoProveedor();
				Calendar fecha = new GregorianCalendar();
				String fechaF = fecha.get(Calendar.YEAR) + "/" + fecha.get(Calendar.MONTH) + "/"
						+ fecha.get(Calendar.DAY_OF_MONTH);
				pedidoProveedor.setFechaDeCreacion(fechaF);
				pedidoProveedor = controlRealizarUnPedido.guardarPedido(pedidoProveedor);

				// acá leemos la tabla
				for (int i = 0; i < tablaProductosPedido.getRowCount(); i++) {
					compruebaCantidad = String.valueOf(tablaProductosPedido.getValueAt(i, 3));
					if (esNumero(compruebaCantidad)) {
						nuevasPiezas.add(Integer.valueOf(compruebaCantidad));
					} else {
						contNoValidos++;
						nuevasPiezas.removeAll(nuevasPiezas);
					}
				}

				// Validamos errores
				if (!(contNoValidos > 0)) {
					if (!(controlRealizarUnPedido.mandaDetallePedido(empleado, productos, nuevasPiezas,
							pedidoProveedor))) {
						controlRealizarUnPedido.cancelarPedido(pedidoProveedor);
						controlRealizarUnPedido.iniciarInformeInventario(empleado);
						muestraDialogoConMensaje("El pedido no pudo ser realizado. Intente nuevamente.");
					} else {
						for (Integer piezas : nuevasPiezas) {
							int i = 0;
							totalPiezas = totalPiezas + piezas;
							precioTotalPedido = precioTotalPedido + (piezas * productos.get(i).getPrecio());
						}
						pedidoProveedor.setTotalProductos(totalPiezas);
						pedidoProveedor.setPrecioTotal(precioTotalPedido);
						controlRealizarUnPedido.guardarPedido(pedidoProveedor);
						muestraConfirmacion("Se realizo el pedido con éxito.");
						controlRealizarUnPedido.iniciarInformeInventario(empleado);
					}
				} else {
					controlRealizarUnPedido.cancelarPedido(pedidoProveedor);
					muestraDialogoConMensaje("Verifica el numero de pzas solicitadas.");
				}
			}
		});

		JButton btnCancelar = new JButton("Regresar");
		btnCancelar.setBounds(313, 11, 89, 23);
		panel_4.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlRealizarUnPedido.cancelar(empleado);
			}
		});
	}

	/**
	 * Muestra la ventana
	 * 
	 * @param controlRealizarUnPedido controla el flujo de la ventana
	 * @param empleado                empleado que inicio sesión
	 * @param productos               lista de productos del pedido a realizar
	 */
	public void muestra(ControlRealizarUnPedido controlRealizarUnPedido, Empleado empleado, List<Producto> productos) {
		this.controlRealizarUnPedido = controlRealizarUnPedido;
		this.empleado = empleado;
		this.productos = productos;
		this.textEmpleado.setText(empleado.getNombre() + " " + empleado.getApellido());
		this.textCargo.setText(empleado.getNivel());
		this.textNumEmpleado.setText(Long.toString(empleado.getIdEmpleado()));
		limpiaTabla();
		llenaTabla(productos);

		setVisible(true);
	}

	/**
	 * Llena la tabla de productos para el pedido
	 * 
	 * @param productos lista de productos para el pedido
	 */
	public void llenaTabla(List<Producto> productos) {
		limpiaTabla();
		for (Producto producto : productos) {
			modeloProductosPedido.addRow(new Object[] { producto.getNombre(), producto.getCompuesto(),
					producto.getPiezas(), "Ingrese piezas" });
		}
		panel_3.add(scrollPaneRealizaPedido);
		tablaProductosPedido.setModel(modeloProductosPedido);
		scrollPaneRealizaPedido.setViewportView(tablaProductosPedido);
	}

	/**
	 * Limpia la tabla
	 */
	private void limpiaTabla() {
		int filas = tablaProductosPedido.getRowCount();
		try {
			for (int i = 0; filas > i; i++) {
				modeloProductosPedido.removeRow(0);
			}
			tablaProductosPedido.setModel(modeloProductosPedido);
		} catch (Exception e) {
			log.warn("## ERROR AL LIMPIAR LA TABLA: " + e);
			JOptionPane.showMessageDialog(null, "No se pudo limpiar la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Muestra una ventana de confirmacion
	 * 
	 * @param mensaje mensajea mostrar
	 * @return respuesta del usuario
	 */
	public int muestraConfirmacion(String mensaje) {
		int res = JOptionPane.showOptionDialog(null, mensaje, "Confrimación", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, null, null);
		return res;

	}

	/**
	 * Muestra una alerta
	 * 
	 * @param mensaje mensaje en la alerta
	 */
	public void muestraDialogoConMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}

	/**
	 * Oculta la ventana
	 */
	public void oculta() {
		limpiaTabla();
		setVisible(false);
	}

	/**
	 * Valida si una cadena es numero
	 * 
	 * @param cadena cadena a validar
	 * @return true si es cadena false si no lo es
	 */
	public static boolean esNumero(String cadena) {

		boolean resultado;

		try {
			Integer.parseInt(cadena);
			resultado = true;
		} catch (NumberFormatException excepcion) {
			resultado = false;
		}

		return resultado;
	}
}