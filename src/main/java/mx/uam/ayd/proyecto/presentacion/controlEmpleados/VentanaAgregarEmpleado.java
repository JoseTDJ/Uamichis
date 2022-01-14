package mx.uam.ayd.proyecto.presentacion.controlEmpleados;


import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.ServicioEmpleado;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.cierreVenta.RowsRenderer;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
@Slf4j
@SuppressWarnings("serial")
@Component
public class VentanaAgregarEmpleado extends JFrame{
	
	private Empleado empleadoSesion;
	private Empleado empleado2;
	
	private JPanel contentPane;
	private ControlEmpleados control;
	private JTextField txtNivel;
	private JTextField txtNombreEmpleado;
	private JTextField txtId;
	private JPanel panel2;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEdad;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtContrasena;

	private JTable tabla_empleado;
	
	DefaultTableModel modeloEmpleados = new DefaultTableModel();
	private JPanel panel4;
	private JButton btnCancelar;
	private JButton btnConfirmar;
	private JTextField txtNIvel2;
	private JTextField txtUsuario;
	private JLabel lblUsuario;
	
	public VentanaAgregarEmpleado() {
		setResizable(false);
		setTitle("FARMAPASS - Agregar Empleado");	
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 757, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 658, 34);
		contentPane.add(panel);
		panel.setLayout(null);
		
		tabla_empleado = new JTable(modeloEmpleados) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		
		
		modeloEmpleados.addColumn("Nombre");
		modeloEmpleados.addColumn("Apellido");
		modeloEmpleados.addColumn("Edad");
		modeloEmpleados.addColumn("Direccion");
		modeloEmpleados.addColumn("Telefono");
		modeloEmpleados.addColumn("Contrasena");
		
		modeloEmpleados = (DefaultTableModel) tabla_empleado.getModel();		
		
		txtNivel = new JTextField();
		txtNivel.setEditable(false);
		txtNivel.setBounds(44, 7, 86, 20);
		panel.add(txtNivel);
		txtNivel.setColumns(10);
		
		txtNombreEmpleado = new JTextField();
		txtNombreEmpleado.setEditable(false);
		txtNombreEmpleado.setBounds(211, 4, 254, 22);
		panel.add(txtNombreEmpleado);
		txtNombreEmpleado.setColumns(10);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(525, 7, 95, 19);
		panel.add(txtId);
		txtId.setColumns(10);
		
		panel2 = new JPanel();
		panel2.setBounds(27, 49, 631, 246);
		contentPane.add(panel2);
		panel2.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(20, 14, 62, 13);
		panel2.add(lblNombre);
		
		txtNombre = new JTextField();
		
		txtNombre.setBounds(87, 10, 142, 19);
		panel2.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellidos:");
		lblApellido.setBounds(317, 10, 62, 13);
		panel2.add(lblApellido);
		
		txtApellido = new JTextField();
		
		txtApellido.setColumns(10);
		txtApellido.setBounds(376, 6, 142, 19);
		panel2.add(txtApellido);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(20, 102, 62, 13);
		panel2.add(lblEdad);
		
		txtEdad = new JTextField();
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
		txtEdad.setBounds(87, 98, 142, 19);
		panel2.add(txtEdad);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(20, 56, 62, 13);
		panel2.add(lblDireccion);
		
		txtDireccion = new JTextField();
		
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(87, 52, 142, 19);
		panel2.add(txtDireccion);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(317, 56, 62, 13);
		panel2.add(lblTelefono);
		
		txtTelefono = new JTextField();
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
		txtTelefono.setBounds(376, 52, 142, 19);
		panel2.add(txtTelefono);
		
		JLabel lblContrasena = new JLabel("Contrasena:");
		lblContrasena.setBounds(165, 197, 78, 13);
		panel2.add(lblContrasena);
		
		txtContrasena = new JTextField();
		
		txtContrasena.setColumns(10);
		txtContrasena.setBounds(255, 193, 142, 19);
		panel2.add(txtContrasena);
		
		panel4 = new JPanel();
		panel4.setLocation(186, 306);
		panel4.setSize(283, 43);
		panel4.setLayout(null);
		contentPane.setLayout(null);
		contentPane.add(panel);
		contentPane.add(panel2);
		
		txtNIvel2 = new JTextField();
		txtNIvel2.setColumns(10);
		txtNIvel2.setBounds(376, 98, 142, 19);
		panel2.add(txtNIvel2);
		
		JLabel lblNivel = new JLabel("Nivel:");
		lblNivel.setBounds(317, 102, 62, 13);
		panel2.add(lblNivel);
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(255, 149, 142, 19);
		panel2.add(txtUsuario);
		
		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(183, 153, 62, 13);
		panel2.add(lblUsuario);
		contentPane.add(panel4);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.inicia(empleadoSesion);
				oculta();
			}
		});
		btnCancelar.setBackground(SystemColor.activeCaptionBorder);
		btnCancelar.setBounds(10, 10, 100, 21);
		panel4.add(btnCancelar);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBackground(new Color(50, 205, 50));
		btnConfirmar.setBounds(149, 10, 100, 21);
		panel4.add(btnConfirmar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textHighlight);
		panel_1.setBounds(668, 5, 63, 355);
		contentPane.add(panel_1);
		
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtNombre.getText().equals("") || txtApellido.getText().equals("") || txtEdad.getText().equals("") || 
						txtDireccion.getText().equals("") || txtTelefono.getText().equals("") || txtContrasena.getText().equals("")) {
					muestraDialogoConMensaje("Hay un campo sin llenar");
				} else  {

					char caracter = txtContrasena.getText().charAt(0);
					if(Character.isUpperCase(caracter) == false ) {
						muestraDialogoConMensaje("La contraseña debe de tener la primera letra en mayuscula");
					}  else if( txtContrasena.getText().length() < 8) {
						muestraDialogoConMensaje("La contraseña debe de tener 8 caracteres ");						
					} else {
						empleado2 = control.validarContrasena(txtContrasena.getText());
						if (empleado2 != null) {
							muestraDialogoConMensaje("La contraseña ya le pertenece a otro usuario");						
						} else {
							empleado2 = control.validarUsuario(txtUsuario.getText());
							if (empleado2 != null) {
								muestraDialogoConMensaje("El usuario ya le pertenece a otro empleado");						
							}else {
								if (!txtNIvel2.getText().equals("empleado") && !txtNIvel2.getText().equals("encargado")) {
									muestraDialogoConMensaje("Solo hay dos cargos empleado o encargado no puedes poner otra cosa");						

								} else {
									
									int edad = Integer.parseInt(txtEdad.getText());
									control.agregarEmpleado(txtNombre.getText(), txtApellido.getText(), txtNIvel2.getText(), edad, 
											txtDireccion.getText(), txtTelefono.getText(), txtUsuario.getText(),txtContrasena.getText());
									oculta();
									limpiarTablas();
									log.info("Encargado: " +empleadoSesion.getNombre());
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
	 * Muestra la ventana de Agregar Empleado
	 * @param control control que lleva acabo el flujo
	 * @param empleado empleado que ha iniciado sesion
	 */
	public void muestra(ControlEmpleados control, Empleado empleado) {
		
		this.control = control;
		String id = String.valueOf(empleado.getIdEmpleado());

		this.control = control;
		this.empleadoSesion = empleado;
		this.txtNombreEmpleado.setText(empleado.getNombre() + " " + empleado.getApellido());
		this.txtNivel.setText(empleado.getNivel() + ":");
		this.txtId.setText(id);
		
		txtNombre.setText("");
		txtApellido.setText("");
		txtNIvel2.setText("");
		txtEdad.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");
		txtUsuario.setText("");
		txtContrasena.setText("");
		
		
		setVisible(true);

	}
	
	/**
	 * Activa el boton de confirmar una vez que se han realizado cambios en el empleado
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
		btnConfirmar.setEnabled(hayCambio);
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
	 * Oculta la ventana de agregar empleado
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

	public void actualizado(ControlEmpleados controlEmpleados) {
		this.control = controlEmpleados;
	}
}

