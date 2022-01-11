package mx.uam.ayd.proyecto.presentacion.inventario;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

/**
 * Ventana de agregar producto
 * @author Ana Karina Vergara Guzmán
 *
 */
@Slf4j
@SuppressWarnings("serial")
@Component
public class VentanaAgregarProducto extends JFrame {

	private JPanel contentPane;
	private ControlAgregarProducto control;
	private Empleado empleado;
	private JTextField txtNombreEmpleado;
	private JTextField txtNivel;
	private JPanel panel_3;
	private JPanel panel_4;
	private JScrollPane scrollPane_1;
	private JTable tabla_productos;
	private JTextField txtLeyendaProducto;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtPiezas;
	private JTextField txtPrecio;
	private JTextField txtUbicacion;
	private JTextArea txtAreaDescripcion;
	private JComboBox<Object> cboxReceta;
	private Producto producto;
	private JButton btnGuardar;
	private JButton btnAgregar;
	private DefaultTableModel modelo = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JTextField txtIdEmp;

	public VentanaAgregarProducto() {
		setResizable(false);
		setTitle("Farmapass - Agregar producto");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 496, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBounds(15, 5, 461, 34);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(15, 426, 461, 53);
		panel_2.setLayout(null);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		contentPane.setLayout(null);
		panel.setLayout(null);

		txtNombreEmpleado = new JTextField();
		txtNombreEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombreEmpleado.setEditable(false);
		txtNombreEmpleado.setBounds(106, 6, 265, 22);
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
		contentPane.add(panel_2);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setEnabled(false);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.agregarProducto(producto);
			}
		});
		btnGuardar.setBackground(new Color(153, 204, 102));
		btnGuardar.setBounds(350, 12, 99, 27);
		panel_2.add(btnGuardar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.inciaInventario(empleado);
			}
		});
		btnCancelar.setBackground(new Color(255, 204, 153));
		btnCancelar.setBounds(248, 12, 90, 27);
		panel_2.add(btnCancelar);

		panel_3 = new JPanel();
		panel_3.setBounds(15, 51, 461, 195);
		panel_3.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(78, 23, 114, 21);
		panel_3.add(txtId);
		txtId.setColumns(10);

		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(47, 25, 25, 17);
		panel_3.add(lblId);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(78, 56, 114, 21);
		panel_3.add(txtNombre);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 59, 60, 17);
		panel_3.add(lblNombre);

		JLabel lblPiezas = new JLabel("Piezas:");
		lblPiezas.setBounds(22, 92, 50, 17);
		panel_3.add(lblPiezas);

		txtPiezas = new JTextField();
		txtPiezas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
					e.consume();
				}
			}
		});
		txtPiezas.setColumns(10);
		txtPiezas.setBounds(78, 89, 114, 21);
		panel_3.add(txtPiezas);

		JLabel lblReceta = new JLabel("   Receta:");
		lblReceta.setBounds(12, 124, 60, 17);
		panel_3.add(lblReceta);

		cboxReceta = new JComboBox<Object>();
		cboxReceta.setModel(new DefaultComboBoxModel<Object>(new String[] { "Si", "No" }));
		cboxReceta.setBounds(78, 124, 114, 21);
		panel_3.add(cboxReceta);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(243, 25, 40, 17);
		panel_3.add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
					e.consume();
				}
			}
		});
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(293, 23, 114, 21);
		panel_3.add(txtPrecio);

		JLabel lblDescripcin = new JLabel("Descripción:");
		lblDescripcin.setBounds(210, 58, 73, 17);
		panel_3.add(lblDescripcin);

		txtAreaDescripcion = new JTextArea();
		txtAreaDescripcion.setBounds(293, 58, 114, 51);
		panel_3.add(txtAreaDescripcion);

		JLabel lblUbicacin = new JLabel("Ubicación:");
		lblUbicacin.setBounds(221, 124, 62, 17);
		panel_3.add(lblUbicacin);

		txtUbicacion = new JTextField();
		txtUbicacion.setColumns(10);
		txtUbicacion.setBounds(293, 120, 114, 21);
		panel_3.add(txtUbicacion);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBackground(new Color(102, 204, 204));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!validaCamposVacios()) {
					producto = new Producto();
					producto.setNombre(txtNombre.getText());
					producto.setCompuesto(txtAreaDescripcion.getText());
					producto.setUbicacion(txtUbicacion.getText());
					producto.setPrecio(Float.parseFloat(txtPrecio.getText()));
					producto.setPiezas(Integer.parseInt(txtPiezas.getText()));
					producto.setReceta((String) cboxReceta.getSelectedItem());
					agregarProductoTabla();
				} else {
					muestraError("Todos los campos deben estar llenos!");
				}
			}
		});
		btnAgregar.setBounds(344, 153, 105, 27);
		panel_3.add(btnAgregar);

		panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(15, 258, 461, 156);
		contentPane.add(panel_4);

		JLabel lblVistaPrevia = new JLabel("VISTA PREVIA");
		lblVistaPrevia.setHorizontalAlignment(SwingConstants.CENTER);
		lblVistaPrevia.setBounds(0, 0, 461, 27);
		panel_4.add(lblVistaPrevia);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 28, 439, 116);
		panel_4.add(scrollPane_1);

		txtLeyendaProducto = new JTextField();
		txtLeyendaProducto.setEditable(false);

		tabla_productos = new JTable(modelo);

		modelo.addColumn("Nombre");
		modelo.addColumn("Descripción");
		modelo.addColumn("Precio del Producto");
		modelo.addColumn("Piezas");
		modelo.addColumn("Ubicación");
		modelo.addColumn("Receta");
	}

	/**
	 * Muestra la ventana de agregar producto
	 * 
	 * @param controlAgregarProducto control que lleva el flujo
	 * @param empleado               empleado que inició sesión
	 */
	public void muestra(ControlAgregarProducto controlAgregarProducto, Empleado empleado) {
		this.control = controlAgregarProducto;
		this.empleado = empleado;
		this.txtNombreEmpleado
				.setText(empleado.getNombre() + " " + empleado.getApellido());
		this.txtNivel.setText(empleado.getNivel() + ":");
		this.txtIdEmp.setText(String.valueOf(empleado.getIdEmpleado()));
		limpiaCampos();
		limpiaTabla();
		agregarProductoTabla();
		setVisible(true);
	}
	
	/**
	 * Limpia tabla
	 */
	private void limpiaTabla() {
		producto = null;
		int filas = tabla_productos.getRowCount();
		try {
			for (int i = 0; filas > i; i++) {
				modelo.removeRow(0);
			}
		} catch (Exception e) {
			log.warn("## ERROR AL LIMPIAR LA TABLA: " + e);
			JOptionPane.showMessageDialog(null, "No se pudo limpiar la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Limipia los campos para agregar un producto
	 */
	private void limpiaCampos() {
		txtNombre.setText("");
		txtPiezas.setText("");
		txtPrecio.setText("");
		txtAreaDescripcion.selectAll(); 
		txtAreaDescripcion.replaceSelection(""); 
		txtUbicacion.setText("");	
	}

	/**
	 * Oculta la ventana agregar producto
	 */
	public void oculta() {
		setVisible(false);
	}

	/**
	 * Validamos que los campos no esten vacios
	 * 
	 * @return true si estan vacios, false si no lo están
	 */
	private boolean validaCamposVacios() {
		if (txtNombre.getText().equals("") || txtPiezas.getText().equals("") || txtPrecio.getText().equals("")
				|| txtAreaDescripcion.getText().equals("") || txtUbicacion.getText().equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * Agregamos los datos del producto en la tabla
	 */
	private void agregarProductoTabla() {
		panel_4.add(scrollPane_1);
		boolean hayDatos = false;
		if (producto == null) {
			txtLeyendaProducto.setColumns(10);
			txtLeyendaProducto.setHorizontalAlignment(SwingConstants.CENTER);
			txtLeyendaProducto.setText("NO SE HA AGREGADO NINGÚN PRODUCTO");
			scrollPane_1.setViewportView(txtLeyendaProducto);
		} else {
			modelo.addRow(
					new Object[] { producto.getNombre(), producto.getCompuesto(), String.valueOf(producto.getPrecio()),
							String.valueOf(producto.getPiezas()), producto.getUbicacion(), producto.getReceta() });
			tabla_productos.setModel(modelo);
			scrollPane_1.setViewportView(tabla_productos);
			hayDatos = true;
		}
		activaBoton(hayDatos);
	}

	/**
	 * Muestra un mensaje de error
	 * 
	 * @param mensaje mensaje con el error que se generó
	 */
	public void muestraError(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Activa o desactiva el boton de guardar
	 * 
	 * @param hayDatos valor para determinar si se activa el botón
	 */
	private void activaBoton(boolean hayDatos) {
		btnGuardar.setEnabled(hayDatos);
	}

	/**
	 * Muestra un mensaje de confirmación cuando el producto es agregado
	 */
	public void muestraConfirmacion() {
		int res = JOptionPane.showOptionDialog(null, "¡El producto ha sido agregado!", "Confrimación",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
		if (res == 0) {
			control.inciaInventario(empleado);
		}

	}
}
