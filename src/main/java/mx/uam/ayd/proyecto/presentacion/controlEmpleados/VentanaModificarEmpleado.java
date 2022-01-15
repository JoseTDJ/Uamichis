package mx.uam.ayd.proyecto.presentacion.controlEmpleados;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import java.awt.SystemColor;

public class VentanaModificarEmpleado extends JFrame {

	private ControlEmpleados control;
	private Empleado empleadoSesion;
	private Empleado empleado2;
	
	private JPanel contentPane;
	private JTextField txtNivel;
	private JTextField txtNombreEmpleado;
	private JTextField txtId;
	private JPanel panel2;
	private JLabel lblId;
	private JTextField txtId2;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblApellidos;
	private JTextField txtApellidos;
	private JLabel lblEdad;
	private JTextField txtEdad;
	private JLabel lblDireccion;
	private JTextField txtDireccion;
	private JLabel lblTelefono;
	private JTextField txtTelefono;
	private JLabel lblContrasena;
	private JTextField txtContrasena;
	private JPanel panel3;
	private JButton btnCancelar;
	private JButton btnGuardar;
	
	private JTable tabla_empleado;
	
	private DefaultTableModel modeloEmpleados = new DefaultTableModel();
	
	private JLabel lblNivel2;
	private JTextField txtNivel2;
	private JTextField txtUsuario;
	private JLabel lblusuario;

	
	public VentanaModificarEmpleado() {
	setResizable(false);
	setTitle("Farmapass - Modificar Empleado");
	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	setBounds(100, 100, 807, 469);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JPanel panel = new JPanel();
	panel.setBounds(5, 5, 776, 34);
	contentPane.add(panel);
	panel.setLayout(null);
	
	txtNivel = new JTextField();
	txtNivel.setEditable(false);
	txtNivel.setBounds(44, 7, 86, 20);
	panel.add(txtNivel);
	txtNivel.setColumns(10);
	
	txtNombreEmpleado = new JTextField();
	txtNombreEmpleado.setEditable(false);
	txtNombreEmpleado.setBounds(269, 6, 254, 22);
	panel.add(txtNombreEmpleado);
	txtNombreEmpleado.setColumns(10);
	
	txtId = new JTextField();
	txtId.setEditable(false);
	txtId.setBounds(650, 7, 95, 19);
	panel.add(txtId);
	txtId.setColumns(10);
	
	btnGuardar = new JButton("Guardar");
	btnGuardar.setEnabled(false);
	btnGuardar.setBackground(new Color(50, 205, 50));
	btnGuardar.setBounds(161, 10, 85, 21);
	panel2 = new JPanel();
	panel2.setBounds(29, 63, 606, 252);
	contentPane.add(panel2);
	panel2.setLayout(null);
	
	lblId = new JLabel("ID:");
	lblId.setBounds(22, 10, 36, 13);
	panel2.add(lblId);
	
	txtId2 = new JTextField();
	txtId2.setEditable(false);
	txtId2.setBounds(98, 7, 167, 19);
	panel2.add(txtId2);
	txtId2.setColumns(10);
	
	lblNombre = new JLabel("Nombre:");
	lblNombre.setBounds(22, 55, 60, 13);
	panel2.add(lblNombre);
	
	txtNombre = new JTextField();
	txtNombre.setEditable(false);
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
	
	txtNombre.setBounds(98, 52, 167, 19);
	panel2.add(txtNombre);
	txtNombre.setColumns(10);
	
	lblApellidos = new JLabel("Apellidos:");
	lblApellidos.setBounds(22, 106, 73, 13);
	panel2.add(lblApellidos);
	
	txtApellidos = new JTextField();
	txtApellidos.setEditable(false);
	txtApellidos.getDocument().addDocumentListener(new DocumentListener() {
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
	txtApellidos.setColumns(10);
	txtApellidos.setBounds(98, 103, 167, 19);
	panel2.add(txtApellidos);
	
	lblEdad = new JLabel("Edad:");
	lblEdad.setToolTipText("");
	lblEdad.setBounds(336, 13, 73, 13);
	panel2.add(lblEdad);
	
	txtEdad = new JTextField();
	txtEdad.getDocument().addDocumentListener(new DocumentListener() {
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
	txtEdad.addKeyListener(new KeyAdapter() {
		@Override
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
				e.consume();
			}
		}
	});
	txtEdad.setColumns(10);
	txtEdad.setBounds(412, 10, 167, 19);
	panel2.add(txtEdad);
	
	lblDireccion = new JLabel("Direccion:");
	lblDireccion.setBounds(336, 52, 73, 13);
	panel2.add(lblDireccion);
	
	txtDireccion = new JTextField();
	txtDireccion.getDocument().addDocumentListener(new DocumentListener() {
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
	txtDireccion.setColumns(10);
	txtDireccion.setBounds(412, 49, 167, 19);
	panel2.add(txtDireccion);
	
	lblTelefono = new JLabel("Telefono:");
	lblTelefono.setBounds(336, 103, 73, 13);
	panel2.add(lblTelefono);
	
	txtTelefono = new JTextField();
	txtTelefono.getDocument().addDocumentListener(new DocumentListener() {
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
	
	txtTelefono.addKeyListener(new KeyAdapter() {
		@Override
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
				e.consume();
			}
		}
	});
	txtTelefono.setColumns(10);
	txtTelefono.setBounds(412, 100, 167, 19);
	panel2.add(txtTelefono);
	
	lblContrasena = new JLabel("Contrasena:");
	lblContrasena.setBounds(336, 158, 73, 13);
	panel2.add(lblContrasena);
	
	txtContrasena = new JTextField();
	txtContrasena.getDocument().addDocumentListener(new DocumentListener() {
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
	
	txtContrasena.setColumns(10);
	txtContrasena.setBounds(412, 155, 167, 19);
	panel2.add(txtContrasena);
	
	lblNivel2 = new JLabel("Nivel:");
	lblNivel2.setBounds(22, 161, 73, 13);
	panel2.add(lblNivel2);
	
	txtNivel2 = new JTextField();
	txtNivel2.setColumns(10);
	txtNivel2.setBounds(98, 158, 167, 19);
	panel2.add(txtNivel2);
	
	txtUsuario = new JTextField();
	txtUsuario.setColumns(10);
	txtUsuario.setBounds(98, 207, 167, 19);
	panel2.add(txtUsuario);
	
	lblusuario = new JLabel("Usuario:");
	lblusuario.setBounds(22, 210, 73, 13);
	panel2.add(lblusuario);
	
	panel3 = new JPanel();
	panel3.setLayout(null);
	panel3.setBounds(212, 339, 287, 59);
	contentPane.add(panel3);
	
	btnCancelar = new JButton("Cancelar");
	btnCancelar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			control.inicia(empleadoSesion);
			oculta();
		}
	});
	btnCancelar.setBackground(Color.RED);
	btnCancelar.setBounds(10, 10, 85, 21);
	panel3.add(btnCancelar);
	panel3.add(btnGuardar);
	
	JPanel panel_1 = new JPanel();
	panel_1.setBackground(SystemColor.textHighlight);
	panel_1.setBounds(637, 39, 154, 391);
	contentPane.add(panel_1);
	
	btnGuardar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (txtNombre.getText().equals("") || txtApellidos.getText().equals("") || txtEdad.getText().equals("") || 
					txtDireccion.getText().equals("") || txtTelefono.getText().equals("") || txtContrasena.getText().equals("")) {
				muestraDialogoConMensaje("Hay un campo sin llenar");
			} else {
				char caracter = txtContrasena.getText().charAt(0);
				if (Character.isUpperCase(caracter) == false ) {
					muestraDialogoConMensaje("La contraseña debe de tener la primera letra en mayuscula");
				} else if(txtContrasena.getText().length() < 8) {
					muestraDialogoConMensaje("La contraseña debe de tener 8 caracteres ");						
				} else {
					empleado2 = control.validarContrasena(txtContrasena.getText());
					if (empleado2 != null) {
						muestraDialogoConMensaje("La contraseña ya le pertenece a otro usuario");						

					} else {
						empleado2 = control.validarUsuario(txtUsuario.getText());
						if (empleado2 != null) {
							muestraDialogoConMensaje("El usuario ya le pertenece a otro empleado");						
						} else {
							if (!txtNivel2.getText().equals("empleado") && !txtNivel2.getText().equals("encargado")) {
								muestraDialogoConMensaje("Solo hay dos cargos [empleado] o [encargado] no puedes poner otra cosa");						

							} else {
								int edad = Integer.parseInt(txtEdad.getText());
//								control.actualizarEmpleado(txtNombre.getText(), txtApellidos.getText(), txtNivel2.getText(), edad, 
//									txtDireccion.getText(), txtTelefono.getText(), txtUsuario.getText(),txtContrasena.getText());
								oculta();
								control.inicia(empleadoSesion);
								control.recuperaEmpleados(empleadoSesion);
							}
							
						}
						
					}
					
				}
					
				
				
				
			}
		}
	});
	
	
	}
	
	/**
	 * Muestra la ventana de Modificar Empleado
	 * @param control control que lleva el flujo 
	 * @param empleado empleado que ha iniciado sesion
	 * @param empleados muestra una lista de empleados que estan en la BD
	 * @param empleado3 empleado3 que se desea modificar 
	 */
	public void muestra(ControlEmpleados control, Empleado empleado, List<Empleado> empleados, Empleado empleado3) {
		String id = String.valueOf(empleado.getIdEmpleado());
		this.control = control;
		this.empleadoSesion = empleado;
		this.empleado2 = empleado3;
		this.txtNombreEmpleado.setText(empleado.getNombre() + " " + empleado.getApellido());
		this.txtNivel.setText(empleado.getNivel() + ":");
		this.txtId.setText(id);
		
		String id2 = String.valueOf(empleado3.getIdEmpleado());
		String edad2 = String.valueOf(empleado3.getEdad());
		this.txtId2.setText(id2);
		this.txtNombre.setText(empleado3.getNombre());
		this.txtApellidos.setText(empleado3.getApellido());
		this.txtUsuario.setText(empleado3.getUsuario());
		this.txtEdad.setText(edad2);
		this.txtDireccion.setText(empleado3.getDireccion());
		this.txtTelefono.setText(empleado3.getTelefono());
		this.txtNivel2.setText(empleado3.getNivel());
		this.txtContrasena.setText(empleado3.getPassword());

		setVisible(true);

	}
	
	/**
	 * Activa el boton de guardar una vez que se han realizado cambios en el empleado
	 * y los campos son validos 
	 * @param evt evento en el docuemnt
	 * @throws BadLocationException detecta si la referencia en el modelo del document es equivocada
	 */
	private void validaCampos(DocumentEvent evt) throws BadLocationException {
		Document doc = evt.getDocument();
		boolean hayCambio = true;
		String valorCadena = doc.getText(0, doc.getLength());
		if (valorCadena.equals(String.valueOf(empleadoSesion.getIdEmpleado())) || valorCadena.equals(empleadoSesion.getNombre()) || valorCadena.equals(empleadoSesion.getApellido()) 
				|| valorCadena.equals(empleadoSesion.getDireccion()) || valorCadena.equals(String.valueOf(empleadoSesion.getEdad()))
				|| valorCadena.equals(empleadoSesion.getTelefono())|| valorCadena.equals(empleadoSesion.getPassword())) {
			hayCambio = false;
		}
		btnGuardar.setEnabled(hayCambio);
	}
	
	/**
	 * Limpia tabla
	 */
	public void limpiarTablas() {
		if (tabla_empleado.getRowCount() > 0) {
			int filas = tabla_empleado.getRowCount();
			try {
				for (int i = 0; filas > i; i++) {
					modeloEmpleados.removeRow(0);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al limpiar la tabla empleados.");			}
		}
	}
	
	/**
	 * Oculata la ventana de Modificar Empleado
	 */
	public void oculta() {
		setVisible(false);
	}
	
	/**
	 * Muestra un mensaje en pantalla
	 * @param mensaje mensaje que se quiere mostrar en pantalla
	 */
	public void muestraDialogoConMensaje(String mensaje ) {
		JOptionPane.showMessageDialog(this , mensaje);
	}
	
}
