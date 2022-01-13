package mx.uam.ayd.proyecto.presentacion.informeInventario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

@SuppressWarnings("serial")
@Slf4j
@Component
public class VentanaPedidos extends JFrame {

	private JPanel contentPane;
	private JTextField textCargo;
	private JTextField textEmpleado;
	private JTextField textNumEmpleado;

	// orders table
	DefaultTableModel modeloPedidos = new DefaultTableModel();
	private JTable tablaPedidos;
	private ControlPedidos controlPedidos;
	private Empleado empleado;
	private JPanel panel_3 = new JPanel();
	private JScrollPane scrollPanePedidos;
	private JTextField txtLeyendaPedidos;
	private TableColumn checkCol;

	/**
	 * Create the frame.
	 */
	public VentanaPedidos() {

		setTitle("FARMAPASS");
		setResizable(false);
		setBounds(100, 100, 561, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));

		JPanel panel_2 = new JPanel();

		JPanel panel_4 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(panel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
				.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)));
		panel_4.setLayout(null);

		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(442, 11, 89, 23);
		panel_4.add(btnRegresar);

		JButton btnDetallePedido = new JButton("Ver detalles");
		btnDetallePedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int contSel = 0;
				int contNoSel = 0;
				PedidoProveedor pedido = null;
				try {
					for (int i = 0; i < tablaPedidos.getRowCount(); i++) {
						if (String.valueOf(tablaPedidos.getValueAt(i, 6)) != "false") {
							String fecha = (String) tablaPedidos.getValueAt(i, 0);
							int totalProd = (int) tablaPedidos.getValueAt(i, 2);
							float precioTotal = (float) tablaPedidos.getValueAt(i, 3);
							pedido = new PedidoProveedor(fecha, totalProd, precioTotal);
							contSel++;
						} else {
							contNoSel++;
						}
					}
					if (contSel == 1) {
						System.out.println(pedido);
						controlPedidos.verDetallePedido(pedido, empleado);
					} else if (contNoSel == tablaPedidos.getRowCount()) {
						JOptionPane.showMessageDialog(null, "No has seleccionado ningun pedido!", "Error!",
								JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Solo puedes ver un pedido a la vez!", "Error!",
								JOptionPane.WARNING_MESSAGE);
					}

				} catch (Exception e) {
					System.out.println(e);
					JOptionPane.showMessageDialog(null, "Error al leer la tabla.");
				}

			}
		});
		btnDetallePedido.setBounds(12, 12, 162, 23);
		panel_4.add(btnDetallePedido);
		panel_3.setLayout(null);
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// controlInformeInventario.cerrar();
				controlPedidos.iniciaInformeInventario(empleado);
			}
		});

		scrollPanePedidos = new JScrollPane();
		scrollPanePedidos.setBounds(0, 0, 543, 144);
		panel_2.setLayout(null);

		txtLeyendaPedidos = new JTextField();
		txtLeyendaPedidos.setEditable(false);

		JLabel lblInformeInventario = new JLabel("Historial de pedidos");
		lblInformeInventario.setBounds(0, 0, 543, 21);
		panel_2.add(lblInformeInventario);
		lblInformeInventario.setHorizontalAlignment(SwingConstants.CENTER);
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
		tablaPedidos = new JTable(modeloPedidos) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				if (vColIndex == 6) {
					return true;
				} else {
					return false;
				}
			}
		};
		// añadiendo
		modeloPedidos.addColumn("Fecha creación");
		modeloPedidos.addColumn("Estado");
		modeloPedidos.addColumn("Total productos");
		modeloPedidos.addColumn("Precio total");
		modeloPedidos.addColumn("Fecha recepción");
		modeloPedidos.addColumn("Recibió");
		modeloPedidos.addColumn("Seleccionar");
		tablaPedidos.setModel(modeloPedidos);

		checkCol = tablaPedidos.getColumnModel().getColumn(6);
		checkCol.setCellEditor(tablaPedidos.getDefaultEditor(Boolean.class));
		checkCol.setCellRenderer(tablaPedidos.getDefaultRenderer(Boolean.class));
	}

	/**
	 * Muestra la ventana de pedidos
	 * 
	 * @param controlPedidos control que lleva el flujo de la ventana
	 * @param empleado       empleado que inicio sesión
	 */
	public void muestra(ControlPedidos controlPedidos, Empleado empleado) {
		this.controlPedidos = controlPedidos;
		this.empleado = empleado;
		this.textEmpleado.setText(empleado.getNombre() + " " + empleado.getApellido());
		this.textCargo.setText(empleado.getNivel());
		this.textNumEmpleado.setText(Long.toString(empleado.getIdEmpleado()));

		limpiaTabla();
		llenaTabla();

		setVisible(true);
	}

	/**
	 * Llena la tabla de pedidos
	 */
	private void llenaTabla() {
		panel_3.add(scrollPanePedidos);
		List<PedidoProveedor> listaPedidos = controlPedidos.obtenerPedidos();
		System.out.println("ACÁ LA LISTA DE PEDIDOS: " + listaPedidos);
		if (listaPedidos.isEmpty()) {
			txtLeyendaPedidos.setColumns(10);
			txtLeyendaPedidos.setHorizontalAlignment(SwingConstants.CENTER);
			txtLeyendaPedidos.setText("NO SE HAN REALIZADO PEDIDOS.");
			scrollPanePedidos.setViewportView(txtLeyendaPedidos);
		} else {
			String fechaRecepcion, empRecibe;
			for (PedidoProveedor pedido : listaPedidos) {
				if (pedido.getFechaDeRecepcion() == null) {
					fechaRecepcion = "SIN FECHA";
					empRecibe = "NA";
				} else {
					fechaRecepcion = pedido.getFechaDeRecepcion();
					empRecibe = pedido.getEmpleadoRecibe();
				}
				modeloPedidos.addRow(new Object[] { pedido.getFechaDeCreacion(), pedido.getEstado(), pedido.getTotalProductos(),
						pedido.getPrecioTotal(), fechaRecepcion, empRecibe, false });
				
			}
			scrollPanePedidos.remove(txtLeyendaPedidos);
			tablaPedidos.setModel(modeloPedidos);
			scrollPanePedidos.setViewportView(tablaPedidos);
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
		int filas = tablaPedidos.getRowCount();
		try {
			for (int i = 0; filas > i; i++) {
				modeloPedidos.removeRow(0);
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

	public void mostrarDetalles(int fila) {
		System.out.println(fila);
	}

	public void actionPerformed(ActionEvent e) {
		int fila = tablaPedidos.getSelectedRow();// obtengo la fila
		// int col = jTable1.getSelectedColumn();// obtengo la columna
		JOptionPane.showMessageDialog(null, fila);// aqui verifico si esta el mensaje que puse
		controlPedidos.mostrarDetalles(fila);

		// fireEditingStopped();
	}
}
