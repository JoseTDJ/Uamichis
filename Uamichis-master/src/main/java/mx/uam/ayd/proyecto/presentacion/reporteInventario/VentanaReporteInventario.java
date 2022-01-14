package mx.uam.ayd.proyecto.presentacion.reporteInventario;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;

/**
 * @author VICTOR_SOSA
 */

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

@Slf4j
@SuppressWarnings("serial")
@Component
public class VentanaReporteInventario extends JFrame {

	private JPanel contentPane;
	private ControlReporteInventario controlReporteInventario;
	private Empleado empleado;
	private JTextField textCargo;
	private JTextField textEmpleado;
	private JTextField textIdEmpleado;
	
	DefaultTableModel modeloInventario = new DefaultTableModel();
	private JTable tablaProductos;
	private JTextField textFieldBuscar;
	private JScrollPane scrollPane;
	
	
	/**
	 * Create the frame.
	 */
	public VentanaReporteInventario() {
		setTitle("FARMAPASS");
		setResizable(false);
		
		setBounds(100, 100, 589, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 546, 45);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textCargo = new JTextField();
		textCargo.setEditable(false);
		textCargo.setBounds(10, 11, 86, 20);
		panel.add(textCargo);
		textCargo.setColumns(10);
		
		textEmpleado = new JTextField();
		textEmpleado.setEditable(false);
		textEmpleado.setBounds(117, 11, 220, 20);
		panel.add(textEmpleado);
		textEmpleado.setColumns(10);
		
		textIdEmpleado = new JTextField();
		textIdEmpleado.setEditable(false);
		textIdEmpleado.setBounds(405, 11, 86, 20);
		panel.add(textIdEmpleado);
		textIdEmpleado.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 56, 546, 33);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEmpleados = new JLabel("Productos");
		lblEmpleados.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpleados.setFont(new Font("Arial", Font.BOLD, 14));
		lblEmpleados.setBounds(226, 11, 82, 14);
		panel_1.add(lblEmpleados);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 89, 546, 225);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 41, 546, 173);
		panel_2.add(scrollPane);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(10, 325, 546, 42);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnGenerarReporte = new JButton("Generar reporte");
		btnGenerarReporte.setBackground(Color.GREEN);
		btnGenerarReporte.setBounds(357, 11, 150, 23);
		panel_3.add(btnGenerarReporte);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setBackground(Color.RED);
		btnRegresar.setBounds(55, 11, 89, 23);
		panel_3.add(btnRegresar);
		
		tablaProductos = new JTable(modeloInventario) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				if(vColIndex == 4) {
					return true;
				}else {
					return false;
				}
			}
		};
		
		modeloInventario.addColumn("ID");
		modeloInventario.addColumn("Nombre");
		modeloInventario.addColumn("Compuesto");
		modeloInventario.addColumn("Precio");
		modeloInventario.addColumn("Seleccionar");
		
		scrollPane.setViewportView(tablaProductos);
		
		textFieldBuscar = new JTextField();
		textFieldBuscar.setBounds(10, 11, 96, 20);
		panel_2.add(textFieldBuscar);
		textFieldBuscar.setColumns(10);
		
		JLabel lblBuscar = new JLabel("Escribe el nombre del producto.");
		lblBuscar.setEnabled(false);
		lblBuscar.setBounds(135, 14, 209, 14);
		panel_2.add(lblBuscar);
		
		/*********LISTENER*******/
		
		/**
		 * Permite filtrar los datos de la tabla para encontrar a un empleado especifico
		 */
		textFieldBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(modeloInventario);
				tablaProductos.setRowSorter(elQueOrdena);
				elQueOrdena.setRowFilter(RowFilter.regexFilter(textFieldBuscar.getText(), 1));
			}
		});
		
		
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  controlReporteInventario.regresar(empleado);
				  limpiaTabla();
				  textFieldBuscar.setText("");
				  oculta();
				  controlReporteInventario.regresar(empleado);
				 
			}
		});
		
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int contSel = 0;
				int contNoSel = 0;
				String nombre = null;
				try {
					for(int i = 0; i < tablaProductos.getRowCount(); i++) {
						if(String.valueOf(tablaProductos.getValueAt(i, 4)) != "false" && String.valueOf(tablaProductos.getValueAt(i, 4)) != "null") {
							nombre = (String) tablaProductos.getValueAt(i, 1);
							contSel++;
						}else {
							contNoSel++;
						}
					}
					if(contSel == 1) {
						controlReporteInventario.iniciaGeneraReporte(empleado, nombre);
						oculta();
					}else {
						if(nombre == null) {
							System.out.println(nombre);
							JOptionPane.showMessageDialog(null, "No has seleccionado ningun producto", "Error", JOptionPane.WARNING_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null, "Solo puedes reportar un producto a la vez", "Error", JOptionPane.WARNING_MESSAGE);
						}
					}
				}catch(Exception e2) {
					System.out.println(e2);
					JOptionPane.showConfirmDialog(null, "Error al leer la tabla");
				}
			}
		});
	}

	public void muestra(ControlReporteInventario controlReporteInventario, Empleado empleado, List<Producto> listaProductos) {
		this.controlReporteInventario = controlReporteInventario;
		this.empleado = empleado;
		this.textCargo.setText(empleado.getNivel());
		this.textEmpleado.setText(empleado.getNombre() + " " + empleado.getApellido());
		String id = String.valueOf(empleado.getIdEmpleado());
		this.textIdEmpleado.setText(id);
		limpiaTabla();
		for (Producto producto : listaProductos) {
				llenaTabla(producto);	
		}
		addCheckBox(4, tablaProductos);
		setVisible(true);
	}
	
	public void addCheckBox(int column, JTable table) {
		TableColumn tc = table.getColumnModel().getColumn(column);
		tc.setCellEditor(table.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	}
	
	public boolean isSelected(int row, int column, JTable table) {
		return table.getValueAt(row, column) != null;
	}
	
	public void llenaTabla(Producto producto) {
		Object a[] = new Object[5];
		a[0] = producto.getIdProducto();
		a[1] = producto.getNombre();
		a[2] = producto.getCompuesto();
		a[3] = producto.getPrecio();
		modeloInventario.addRow(a);
		tablaProductos.setModel(modeloInventario);
	}
	private void limpiaTabla() {
		int filas = tablaProductos.getRowCount();
		try {
			for(int i = 0; filas > i; i++) {
				modeloInventario.removeRow(0);
			}
		}catch(Exception e) {
			log.warn("## ERROR AL LIMPIAR LA TABLA: " + e);
			JOptionPane.showMessageDialog(null, "No se pudo limpiar la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void muestraDialogoConMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
	
	public void oculta() {
		setVisible(false);
	}
}
