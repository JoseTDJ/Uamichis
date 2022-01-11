package mx.uam.ayd.proyecto.presentacion.inventario;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.cierreVenta.RowsRenderer;

/**
 * Ventana de inventario
 * 
 * @author Ana Karina Vergara Guzmán
 *
 */
@Slf4j
@SuppressWarnings("serial")
@Component
public class VentanaInventario extends JFrame {

	private JPanel contentPane;
	private ControlInventario control;
	private Empleado empleado;
	private JTextField txtNombreEmpleado;
	private JTextField txtNivel;
	private JPanel panel_3;
	private JScrollPane scrollPane;
	private JTable tabla_productos;
	private JTextField txtTotalProductos;
	private JTextField txtLeyendaProducto;
	private TableColumn checkCol;
	// Se definen las columnas editables en el modelo.
	private DefaultTableModel modelo_productos = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			if (column == 6) {
				return true;
			} else {
				return false;
			}
		}
	};
	private JTextField txtIdEmp;
	
	public VentanaInventario() {
		setResizable(false);
		setTitle("Farmapass - Inventario");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 496, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBounds(15, 5, 461, 34);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(15, 45, 461, 53);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(15, 309, 461, 53);
		panel_2.setLayout(null);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.setLayout(null);
		contentPane.setLayout(null);
		panel.setLayout(null);

		txtNombreEmpleado = new JTextField();
		txtNombreEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombreEmpleado.setEditable(false);
		txtNombreEmpleado.setBounds(106, 6, 265, 21);
		panel.add(txtNombreEmpleado);
		txtNombreEmpleado.setColumns(10);

		txtNivel = new JTextField();
		txtNivel.setEditable(false);
		txtNivel.setBounds(10, 7, 86, 20);
		panel.add(txtNivel);
		txtNivel.setColumns(10);
		contentPane.add(panel);
		
		txtIdEmp = new JTextField();
		txtIdEmp.setEditable(false);
		txtIdEmp.setColumns(10);
		txtIdEmp.setBounds(383, 6, 66, 21);
		panel.add(txtIdEmp);
		contentPane.add(panel_1);

		JButton btnCargarArchivo = new JButton("Cargar archivo");
		btnCargarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.iniciaCargarArchivo(empleado);
			}
		});
		btnCargarArchivo.setBounds(12, 12, 121, 27);
		panel_1.add(btnCargarArchivo);

		JButton btnAadirProducto = new JButton("Añadir Producto");
		btnAadirProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.agregarProducto(empleado);
			}
		});
		btnAadirProducto.setBounds(145, 12, 131, 27);
		panel_1.add(btnAadirProducto);
		contentPane.add(panel_2);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int contSel = 0;
				int contNoSel = 0;
				String nombre = null;
				try {
					for (int i = 0; i < tabla_productos.getRowCount(); i++) {
						if (String.valueOf(tabla_productos.getValueAt(i, 6)) != "false") {
							nombre = (String) tabla_productos.getValueAt(i, 0);
							contSel++;
						} else {
							contNoSel++;
						}
					}
					if (contSel == 1) {
						control.iniciaEditar(nombre, empleado);
					} else if (contNoSel == tabla_productos.getRowCount()) {
						JOptionPane.showMessageDialog(null, "No has seleccionado ningun producto!", "Error!",
								JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Solo puedes editar un producto a la vez!", "Error!",
								JOptionPane.WARNING_MESSAGE);
					}

				} catch (Exception e) {
					System.out.println(e);
					JOptionPane.showMessageDialog(null, "Error al leer la tabla.");
				}
			}
		});
		btnEditar.setBackground(new Color(255, 255, 51));
		btnEditar.setBounds(12, 12, 80, 27);
		panel_2.add(btnEditar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<String> listaNombres = new ArrayList<>();
				try {
					for (int i = 0; i < tabla_productos.getRowCount(); i++) {
						if (String.valueOf(tabla_productos.getValueAt(i, 6)) != "false") {
							listaNombres.add((String) tabla_productos.getValueAt(i, 0));
						}
					}
					if (!listaNombres.isEmpty()) {
						int confirmaEliminar = JOptionPane.showConfirmDialog(null,
								"¿Quieres eliminar " + listaNombres.size() + " productos?", "Confirmación",
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
						if (confirmaEliminar == 0) {
							log.info(">> SOLICITUD A ELIMINAR " + listaNombres.size() + "PRODUCTOS");
							control.eliminaProductos(listaNombres);
						}
					} else {
						JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun producto",
								"Error al eliminar", JOptionPane.WARNING_MESSAGE);
					}

				} catch (Exception e) {
					log.warn("## ERROR AL RECORRER LA TABLA: " + e);
					JOptionPane.showMessageDialog(null, "No se pudo leer la tabla, intente nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEliminar.setBackground(new Color(255, 204, 153));
		btnEliminar.setBounds(104, 12, 90, 27);
		panel_2.add(btnEliminar);

		JLabel lblTotalDeProductos = new JLabel("Total de productos:");
		lblTotalDeProductos.setBounds(245, 17, 117, 17);
		panel_2.add(lblTotalDeProductos);

		txtTotalProductos = new JTextField();
		txtTotalProductos.setEditable(false);
		txtTotalProductos.setBounds(380, 15, 69, 21);
		panel_2.add(txtTotalProductos);
		txtTotalProductos.setColumns(10);

		panel_3 = new JPanel();
		panel_3.setBounds(15, 111, 461, 186);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblProductosEnInventario = new JLabel("PRODUCTOS EN INVENTARIO");
		lblProductosEnInventario.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductosEnInventario.setBounds(0, 0, 471, 27);
		panel_3.add(lblProductosEnInventario);

		txtLeyendaProducto = new JTextField();
		txtLeyendaProducto.setEditable(false);
		txtLeyendaProducto.setBounds(10, 39, 439, 21);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 439, 135);

		tabla_productos = new JTable(modelo_productos);

		modelo_productos.addColumn("Nombre");
		modelo_productos.addColumn("Descripción");
		modelo_productos.addColumn("Precio del Producto");
		modelo_productos.addColumn("Piezas");
		modelo_productos.addColumn("Ubicación");
		modelo_productos.addColumn("Receta");
		modelo_productos.addColumn("Seleccionar");
		
		//Se define color para productos que incumplen la RN asociada
		RowsRenderer rr = new RowsRenderer(3);
		tabla_productos.setDefaultRenderer(Object.class, rr);

		checkCol = tabla_productos.getColumnModel().getColumn(6);
		checkCol.setCellEditor(tabla_productos.getDefaultEditor(Boolean.class));
		checkCol.setCellRenderer(tabla_productos.getDefaultRenderer(Boolean.class));

		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.iniciaPrincipalEncargado(empleado);
			}
		});
		btnRegresar.setBounds(371, 375, 105, 27);
		contentPane.add(btnRegresar);
	}

	/**
	 * Muestra la ventana de inventario
	 * 
	 * @param control  controlador que lleca el flujo
	 * @param empleado empleado que inició sesión
	 */
	public void muestra(ControlInventario control, Empleado empleado) {
		this.control = control;
		this.empleado = empleado;
		this.txtNombreEmpleado
				.setText(empleado.getNombre() + " " + empleado.getApellido());
		this.txtNivel.setText(empleado.getNivel() + ":");
		this.txtIdEmp.setText(String.valueOf(empleado.getIdEmpleado()));
		limpiaTabla();
		llenaTabla();
		setVisible(true);
	}

	/**
	 * Oculta la ventana inventario
	 */
	public void oculta() {
		limpiaTabla();
		setVisible(false);
	}

	/**
	 * Llena la tabla de inventario
	 */
	private void llenaTabla() {
		panel_3.add(scrollPane);
		List<Producto> listaProductos = control.obtenerProductos();
		if (listaProductos.isEmpty()) {
			txtLeyendaProducto.setColumns(10);
			txtLeyendaProducto.setHorizontalAlignment(SwingConstants.CENTER);
			txtLeyendaProducto.setText("NO HAY PRODUCTOS EN EL INVENTARIO");
			scrollPane.setViewportView(txtLeyendaProducto);
			txtTotalProductos.setText("0");
		} else {
			int contProductos = 0;
			for (Producto producto : listaProductos) {
				modelo_productos.addRow(new Object[] { producto.getNombre(), producto.getCompuesto(),
						String.valueOf(producto.getPrecio()), String.valueOf(producto.getPiezas()),
						producto.getUbicacion(), producto.getReceta(), false });
				contProductos++;
				tabla_productos.setModel(modelo_productos);
			}
			txtTotalProductos.setText(String.valueOf(contProductos));
			scrollPane.setViewportView(tabla_productos);
		}
	}

	/**
	 * Limpia la tabla de inventario
	 */
	private void limpiaTabla() {
		txtTotalProductos.setText("");
		int filas = tabla_productos.getRowCount();
		try {
			for (int i = 0; filas > i; i++) {
				modelo_productos.removeRow(0);
			}
		} catch (Exception e) {
			log.warn("## ERROR AL LIMPIAR LA TABLA: " + e);
			JOptionPane.showMessageDialog(null, "No se pudo limpiar la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Confirmación despues de haber eliminado algun producto
	 */
	public void muestraConfirmacionEliminar() {
		JOptionPane.showMessageDialog(this, "Los productos fueron eliminados", "Confirmación",
				JOptionPane.INFORMATION_MESSAGE);
		limpiaTabla();
		llenaTabla();

	}

	/**
	 * Muestra un mensaje de error
	 * 
	 * @param mensaje mensaje con el error que se generó
	 */
	public void muestraError(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
