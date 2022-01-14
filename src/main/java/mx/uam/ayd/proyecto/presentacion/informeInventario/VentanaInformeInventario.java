package mx.uam.ayd.proyecto.presentacion.informeInventario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

@Slf4j
@SuppressWarnings("serial")
@Component
public class VentanaInformeInventario extends JFrame {

	private JPanel contentPane;
	private JTextField txtCargo;
	private JTextField txtEmpleado;
	private JTextField txtNumEmpleado;
	
	//tabla de productos escasos
	DefaultTableModel modeloProductosEscasez = new DefaultTableModel();
	private JTable tablaProductosEscasez;
	private ControlInformeInventario controlInformeInventario;
	private Empleado empleado;
	private Producto producto;
	private JScrollPane scrollPaneInformeInventario;
	private JPanel panel_3;
	/**
	 * Create the frame.
	 */
	public VentanaInformeInventario() {
		
		setTitle("FARMAPASS - Informe inventario");
		setResizable(false);
		setBounds(100, 100, 561, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		JPanel panel_2 = new JPanel();
		
		panel_3 = new JPanel();
		
		JPanel panel_4 = new JPanel();
		
		JButton btnPedidos = new JButton("Pedidos");
		btnPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlInformeInventario.iniciaPedidos(empleado);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
				.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
				.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnPedidos, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(372, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPedidos, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_4.setLayout(null);
		
		JButton btnRealizarUnPedido = new JButton("Realizar un pedido");
		btnRealizarUnPedido.setBounds(12, 11, 159, 23);
		panel_4.add(btnRealizarUnPedido);
		btnRealizarUnPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//modeloProductosEscasez = (DefaultTableModel) tablaProductosEscasez.getModel();
				//Contiene los nombres
				List<Producto> productosSeleccionados = new ArrayList<>();
				try {
					for(int i = 0; i < tablaProductosEscasez.getRowCount(); i++) {
						if(String.valueOf(tablaProductosEscasez.getValueAt(i, 3)) != "false" && String.valueOf(tablaProductosEscasez.getValueAt(i, 3)) != "null") {
							producto = controlInformeInventario.obtenerProducto((String) tablaProductosEscasez.getValueAt(i, 0));
							productosSeleccionados.add(producto);
						}
					}
					if(productosSeleccionados.isEmpty()) {
						muestraDialogoConMensaje("Debes de seleccionar un producto");
					}else {
						int confirmar = JOptionPane.showConfirmDialog(null, "¿Quieres realizar un pedido?");
						if(JOptionPane.OK_OPTION == confirmar) {
							controlInformeInventario.iniciaRealizarPedido(empleado, productosSeleccionados);
						}
					}
				}catch(Exception ex) {
					
				}
			}
		});
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setBounds(442, 11, 89, 23);
		panel_4.add(btnCerrar);
		panel_3.setLayout(null);
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//controlInformeInventario.cerrar();
				controlInformeInventario.cerrar(empleado);
			}
		});
		
		scrollPaneInformeInventario = new JScrollPane();
		scrollPaneInformeInventario.setBounds(0, 0, 543, 125);
		panel_2.setLayout(null);
		
		JLabel lblInformeInventario = new JLabel("Informe Inventario");
		lblInformeInventario.setBounds(0, 0, 543, 21);
		panel_2.add(lblInformeInventario);
		lblInformeInventario.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.setLayout(null);
		
		txtCargo = new JTextField();
		txtCargo.setEditable(false);
		txtCargo.setBounds(10, 7, 96, 20);
		panel_1.add(txtCargo);
		txtCargo.setColumns(10);
		
		txtEmpleado = new JTextField();
		txtEmpleado.setEditable(false);
		txtEmpleado.setBounds(118, 7, 350, 20);
		panel_1.add(txtEmpleado);
		txtEmpleado.setColumns(10);
		
		txtNumEmpleado = new JTextField();
		txtNumEmpleado.setEditable(false);
		txtNumEmpleado.setBounds(480, 7, 51, 20);
		panel_1.add(txtNumEmpleado);
		txtNumEmpleado.setColumns(10);
		contentPane.setLayout(gl_contentPane);
		
		//tabla inventario
		tablaProductosEscasez = new JTable(modeloProductosEscasez) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				if(vColIndex == 3) {
					return true;
				}else {
					return false;
				}
			}
		};
		
		//añadiendo columnas
		modeloProductosEscasez.addColumn("Producto");
		modeloProductosEscasez.addColumn("Descripción");
		modeloProductosEscasez.addColumn("Cantidad");
		modeloProductosEscasez.addColumn("Selecciona");
	}
	
	/**
	 * Muestra la ventana
	 * @param controlInformeInventario control que lleva el flujo de la ventana
	 * @param empleado empleado que inicio sesión
	 * @param productos lista de productos con escasez
	 */
	public void muestra(ControlInformeInventario controlInformeInventario, Empleado empleado, List<Producto> productos) {
		this.controlInformeInventario = controlInformeInventario;
		this.empleado = empleado;
		this.txtEmpleado.setText(empleado.getNombre() + " " + empleado.getApellido());
		this.txtCargo.setText(empleado.getNivel());
		this.txtNumEmpleado.setText(Long.toString(empleado.getIdEmpleado()));
		
		limpiaTabla();
		llenaTabla(productos);
		
		setVisible(true);
	}
	
	/**
	 * Llena la tabla de productos con escasez
	 * @param productos lista de productos con los que llena la tabla
	 */
	private void llenaTabla(List<Producto> productos) {
		panel_3.add(scrollPaneInformeInventario);
		for(Producto producto:productos) {
			modeloProductosEscasez.addRow(new Object[] {producto.getNombre(), producto.getCompuesto(), producto.getPiezas()});
		}
		tablaProductosEscasez.setModel(modeloProductosEscasez);
		addCheckBox(3, tablaProductosEscasez);
		scrollPaneInformeInventario.setViewportView(tablaProductosEscasez);	
	}

	/**
	 * Metodo que agrega los checkbox a la tabla del inventario
	 * @param column columna en la que se agrega
	 * @param table tabla sobre la que se generan 
	 */
	public void addCheckBox(int column, JTable table) {
		TableColumn tc = table.getColumnModel().getColumn(column);
		tc.setCellEditor(table.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	}
	
	/**
	 * Este metodo permite identificar si algun producto esta sellecionado o no
	 * @param row numero de la fila donde esta ubicado el checbox
	 * @param column columna donde esta ubicado el checkbox
	 * @param table tabla donde se agregara
	 * @return si esta seleccionado o no
	 */
	public boolean IsSelected(int row, int column, JTable table) {
		return table.getValueAt(row, column) != null;
	}
	
	/**
	 * Limpia tabla
	 */
	private void limpiaTabla() {
		int filas = tablaProductosEscasez.getRowCount();
		try {
			for (int i = 0; filas > i; i++) {
				modeloProductosEscasez.removeRow(0);
			}
			tablaProductosEscasez.setModel(modeloProductosEscasez);
		} catch (Exception e) {
			log.warn("## ERROR AL LIMPIAR LA TABLA: " + e);
			JOptionPane.showMessageDialog(null, "No se pudo limpiar la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Muestra una alerta
	 * @param mensaje mensaje que se muestra en la alerta
	 */
	public void muestraDialogoConMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
	
	/**
	 * Muestra un error
	 * @param mensaje mensaje de error
	 */
	public void muestraDialogoError(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Oculta la ventana
	 */
	public void oculta() {
		limpiaTabla();
		setVisible(false);
	}
}
