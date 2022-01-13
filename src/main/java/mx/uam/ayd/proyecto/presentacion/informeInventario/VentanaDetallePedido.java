package mx.uam.ayd.proyecto.presentacion.informeInventario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoProveedor;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

@SuppressWarnings("serial")
@Slf4j
@Component
public class VentanaDetallePedido extends JFrame {

	private JPanel contentPane;
	private JTextField textCargo;
	private JTextField textEmpleado;
	private JTextField textNumEmpleado;

	// orders table
	DefaultTableModel modeloProductos = new DefaultTableModel();
	private JTable tablaProductos;
	private ControlDetallePedido controlDetallePedido;
	private Empleado empleado;
	private JPanel panel_3 = new JPanel();
	private JScrollPane scrollPaneProductos;
	private JTextField txtLeyendaPedidos;
	private JTextField txtTotalProductos;
	private JTextField txtEstadoPedido;
	private JTextField txtFechaPedido;

	/**
	 * Create the frame.
	 */
	public VentanaDetallePedido() {

		setTitle("FARMAPASS - Detalle de pedido");
		setResizable(false);
		setBounds(100, 100, 561, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));

		JPanel panel_2 = new JPanel();

		JPanel panel_4 = new JPanel();
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		
		JLabel lblProductoDePedido_1 = new JLabel("Datos del pedido");
		lblProductoDePedido_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductoDePedido_1.setBounds(0, 0, 543, 21);
		panel_2_1.add(lblProductoDePedido_1);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_3, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(panel_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_2_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 541, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(panel_2_1, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
		);
		
		txtTotalProductos = new JTextField();
		txtTotalProductos.setEditable(false);
		txtTotalProductos.setColumns(10);
		txtTotalProductos.setBounds(493, 43, 39, 21);
		panel_2_1.add(txtTotalProductos);
		
		JLabel lblTotalDeProductos = new JLabel("Total de productos:");
		lblTotalDeProductos.setBounds(367, 45, 114, 17);
		panel_2_1.add(lblTotalDeProductos);
		
		txtEstadoPedido = new JTextField();
		txtEstadoPedido.setEditable(false);
		txtEstadoPedido.setColumns(10);
		txtEstadoPedido.setBounds(241, 43, 114, 21);
		panel_2_1.add(txtEstadoPedido);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(186, 45, 43, 17);
		panel_2_1.add(lblEstado);
		
		txtFechaPedido = new JTextField();
		txtFechaPedido.setEditable(false);
		txtFechaPedido.setColumns(10);
		txtFechaPedido.setBounds(60, 43, 114, 21);
		panel_2_1.add(txtFechaPedido);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(10, 45, 38, 17);
		panel_2_1.add(lblFecha);
		panel_4.setLayout(null);

		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(442, 11, 89, 23);
		panel_4.add(btnRegresar);
		panel_3.setLayout(null);
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// controlInformeInventario.cerrar();
				controlDetallePedido.iniciarPedidos(empleado);
			}
		});

		scrollPaneProductos = new JScrollPane();
		scrollPaneProductos.setBounds(0, 0, 543, 157);
		panel_3.add(scrollPaneProductos);
		panel_2.setLayout(null);

		txtLeyendaPedidos = new JTextField();
		txtLeyendaPedidos.setEditable(false);

		JLabel lblProductoDePedido = new JLabel("Productos del pedido");
		lblProductoDePedido.setBounds(0, 0, 543, 21);
		panel_2.add(lblProductoDePedido);
		lblProductoDePedido.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.setLayout(null);

		textCargo = new JTextField();
		textCargo.setEditable(false);
		textCargo.setBounds(10, 7, 96, 20);
		panel_1.add(textCargo);
		textCargo.setColumns(10);

		textEmpleado = new JTextField();
		textEmpleado.setEditable(false);
		textEmpleado.setBounds(118, 7, 350, 20);
		panel_1.add(textEmpleado);
		textEmpleado.setColumns(10);

		textNumEmpleado = new JTextField();
		textNumEmpleado.setEditable(false);
		textNumEmpleado.setBounds(480, 7, 51, 20);
		panel_1.add(textNumEmpleado);
		textNumEmpleado.setColumns(10);
		contentPane.setLayout(gl_contentPane);

		// tabla inventario
		tablaProductos = new JTable(modeloProductos) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		// añadiendo
		modeloProductos.addColumn("Nombre");
		modeloProductos.addColumn("Descripción");
		modeloProductos.addColumn("Precio del Producto");
		modeloProductos.addColumn("Piezas");
		modeloProductos.addColumn("Ubicación");
		modeloProductos.addColumn("Receta");
		tablaProductos.setModel(modeloProductos);
	}

	/**
	 * Muestra la ventana de pedidos
	 * 
	 * @param controlDetallePedido control que lleva el flujo de la ventana
	 * @param listaProductos       lista de productos asociados al pedido
	 * @param empleado             empleado que inicio sesión
	 * @param pedido 
	 */
	public void muestra(ControlDetallePedido controlDetallePedido, ArrayList<Producto> listaProductos,
			Empleado empleado, PedidoProveedor pedido) {
		this.controlDetallePedido = controlDetallePedido;
		this.empleado = empleado;
		this.textEmpleado.setText(empleado.getNombre() + " " + empleado.getApellido());
		this.textCargo.setText(empleado.getNivel());
		this.textNumEmpleado.setText(Long.toString(empleado.getIdEmpleado()));
		
		this.txtFechaPedido.setText(pedido.getFechaDeCreacion());
		this.txtEstadoPedido.setText(pedido.getEstado());
		this.txtTotalProductos.setText(String.valueOf(pedido.getTotalProductos()));

		limpiaTabla();
		llenaTabla(listaProductos);

		setVisible(true);
	}

	/**
	 * Llena la tabla de pedidos
	 * 
	 * @param listaProductos
	 */
	private void llenaTabla(ArrayList<Producto> listaProductos) {
		panel_3.add(scrollPaneProductos);
		if (listaProductos.isEmpty()) {
			txtLeyendaPedidos.setColumns(10);
			txtLeyendaPedidos.setHorizontalAlignment(SwingConstants.CENTER);
			txtLeyendaPedidos.setText("NO HAY PRODUCTOS ASOCIADOS AL PEDIDO");
			scrollPaneProductos.setViewportView(txtLeyendaPedidos);
		} else {
			for (Producto producto : listaProductos) {
				modeloProductos.addRow(new Object[] { producto.getNombre(), producto.getCompuesto(),
						String.valueOf(producto.getPrecio()), String.valueOf(producto.getPiezas()),
						producto.getUbicacion(), producto.getReceta()});
				tablaProductos.setModel(modeloProductos);
			}
			scrollPaneProductos.setViewportView(tablaProductos);
		}
	}

	/**
	 * Metodo que agrega los checkbox a la tabla del inventario
	 * 
	 * @param column
	 * @param table
	 */
	public void addCheckBox(int column, JTable table) {
		TableColumn tc = table.getColumnModel().getColumn(column);
		tc.setCellEditor(table.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	}

	/**
	 * Este metodo permite identificar si algun producto esta sellecionado o no
	 * 
	 * @param row    numero de la fila donde esta ubicado el checbox
	 * @param column columna donde esta ubicado el checkbox
	 * @param table  tabla donde se agregara
	 * @return si esta seleccionado o no
	 */
	public boolean IsSelected(int row, int column, JTable table) {
		return table.getValueAt(row, column) != null;
	}

	/**
	 * Limpia la tabla
	 */
	private void limpiaTabla() {
		// txtTotalProductos.setText("");
		int filas = tablaProductos.getRowCount();
		try {
			for (int i = 0; filas > i; i++) {
				modeloProductos.removeRow(0);
			}
		} catch (Exception e) {
			log.warn("## ERROR AL LIMPIAR LA TABLA: " + e);
			JOptionPane.showMessageDialog(null, "No se pudo limpiar la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void muestraDialogoConMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}

	public void oculta() {
		limpiaTabla();
		setVisible(false);
	}
}
