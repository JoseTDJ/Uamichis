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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

/**
 * Ventana de editar producto
 * 
 * @author Ana Karina Vergara Guzmán
 *
 */
@SuppressWarnings("serial")
@Component
public class VentanaEditarProducto extends JFrame {

	private JPanel contentPane;
	private ControlEditarProducto control;
	private Empleado empleado;
	private JTextField txtNombreEmpleado;
	private JTextField txtNivel;
	private JPanel panel_3;
	private JScrollPane scrollPane;
	private JTable tabla_productos;
	private JTextField txtLeyendaProducto;
	private TableColumn checkCol;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtPiezas;
	private JTextField txtPrecio;
	private JTextField txtUbicacion;
	private JTextArea txtAreaDescripcion;
	private JComboBox<Object> cboxReceta;
	private Producto producto;
	private JButton btnGuardar;
	private DefaultTableModel modelo = new DefaultTableModel() {
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

	public VentanaEditarProducto() {
		setResizable(false);
		setTitle("Farmapass - Editar producto");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 496, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBounds(15, 5, 461, 34);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(15, 249, 461, 53);
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
				producto.setNombre(txtNombre.getText());
				producto.setCompuesto(txtAreaDescripcion.getText());
				producto.setUbicacion(txtUbicacion.getText());
				producto.setPrecio(Float.parseFloat(txtPrecio.getText()));
				producto.setPiezas(Integer.parseInt(txtPiezas.getText()));
				producto.setReceta((String) cboxReceta.getSelectedItem());
				control.actualizarProducto(producto);
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
		panel_3.setBounds(15, 51, 461, 186);
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
		txtNombre.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				try {
					validaCampos(arg0);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				try {
					validaCampos(arg0);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				try {
					validaCampos(arg0);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
		});
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
		txtPiezas.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				try {
					validaCampos(arg0);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				try {
					validaCampos(arg0);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				try {
					validaCampos(arg0);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
		});
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
		txtPrecio.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				try {
					validaCampos(arg0);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				try {
					validaCampos(arg0);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				try {
					validaCampos(arg0);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
		});
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
		txtAreaDescripcion.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				try {
					validaCampos(arg0);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				try {
					validaCampos(arg0);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				try {
					validaCampos(arg0);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
		});
		txtAreaDescripcion.setBounds(293, 58, 114, 51);
		panel_3.add(txtAreaDescripcion);

		JLabel lblUbicacin = new JLabel("Ubicación:");
		lblUbicacin.setBounds(221, 124, 62, 17);
		panel_3.add(lblUbicacin);

		txtUbicacion = new JTextField();
		txtUbicacion.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				try {
					validaCampos(arg0);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				try {
					validaCampos(arg0);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				try {
					validaCampos(arg0);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
		});
		txtUbicacion.setColumns(10);
		txtUbicacion.setBounds(293, 120, 114, 21);
		panel_3.add(txtUbicacion);

		txtLeyendaProducto = new JTextField();
		txtLeyendaProducto.setEditable(false);
		txtLeyendaProducto.setBounds(10, 39, 439, 21);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 439, 135);

		tabla_productos = new JTable(modelo);

		modelo.addColumn("Nombre");
		modelo.addColumn("Descripción");
		modelo.addColumn("Precio del Producto");
		modelo.addColumn("Piezas");
		modelo.addColumn("Ubicación");
		modelo.addColumn("Receta");
		modelo.addColumn("Seleccionar");

		checkCol = tabla_productos.getColumnModel().getColumn(6);
		checkCol.setCellEditor(tabla_productos.getDefaultEditor(Boolean.class));
		checkCol.setCellRenderer(tabla_productos.getDefaultRenderer(Boolean.class));
	}

	/**
	 * Muestra la ventana de editar producto
	 * 
	 * @param controlEditarProducto control que lleva el flujo
	 * @param empleado              empleado que inició sesión
	 * @param producto              producto que se desea editar
	 */
	public void muestra(ControlEditarProducto controlEditarProducto, Empleado empleado, Producto producto) {
		this.control = controlEditarProducto;
		this.empleado = empleado;
		this.producto = producto;
		this.txtNombreEmpleado
				.setText(empleado.getNombre() + " " + empleado.getApellido());
		this.txtNivel.setText(empleado.getNivel() + ":");
		this.txtIdEmp.setText(String.valueOf(empleado.getIdEmpleado()));
		
		this.txtId.setText(String.valueOf(producto.getIdProducto()));
		this.txtNombre.setText(producto.getNombre());
		this.txtPrecio.setText(String.valueOf(producto.getPrecio()));
		this.txtPiezas.setText(String.valueOf(producto.getPiezas()));
		this.txtUbicacion.setText(producto.getUbicacion());
		this.txtAreaDescripcion.setText(producto.getCompuesto());
		if (producto.getReceta().equals("Si")) {
			cboxReceta.setSelectedIndex(0);
		} else {
			cboxReceta.setSelectedIndex(1);
		}
		setVisible(true);
	}

	/**
	 * Oculta la ventana de editar producto
	 */
	public void oculta() {
		setVisible(false);
	}

	/**
	 * Muestra una alerta de error
	 * 
	 * @param mensaje mensaje que se quiere mostrar en la alerta
	 */
	public void muestraError(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Activa el boton de guradar una vez que se han realizado cambios en el
	 * producto y los campos son validados
	 * 
	 * @param evt evento en el docuemnt
	 * @throws BadLocationException detecta si la referencia en el modelo del
	 *                              document es equivocada
	 */
	private void validaCampos(DocumentEvent evt) throws BadLocationException {
		Document doc = evt.getDocument();
		boolean hayCambio = true;
		String valorCadena = doc.getText(0, doc.getLength());
		if (valorCadena.equals(producto.getNombre()) || valorCadena.equals(String.valueOf(producto.getPiezas()))
				|| valorCadena.equals(String.valueOf(producto.getPrecio()))
				|| valorCadena.equals(producto.getCompuesto()) || valorCadena.equals(producto.getUbicacion())
				|| valorCadena.equals(producto.getReceta())) {
			hayCambio = false;
		}
		btnGuardar.setEnabled(hayCambio);
	}

	/**
	 * Muestra una confirmación de que el producto ha sido editado
	 */
	public void muestraConfirmacionEditado() {
		int res = JOptionPane.showOptionDialog(null, "El producto ha sido editado!", "Confirmación",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
		if (res == 0) {
			control.inciaInventario(empleado);
		}
	}
}
