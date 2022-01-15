package mx.uam.ayd.proyecto.presentacion.controlEmpleados;

import javax.swing.GroupLayout;
import javax.swing.JFrame;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.cierreVenta.RowsRenderer;
import mx.uam.ayd.proyecto.presentacion.principal.empleado.ControlPrincipalEmpleados;
import mx.uam.ayd.proyecto.presentacion.principal.encargado.ControlPrincipalEncargado;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.UIManager;
@Slf4j
@SuppressWarnings("serial")
@Component
public class VentanaControlEmpleados extends JFrame {
	private JPanel contentPane;
	private Empleado empleado;
	private JTextField txtNombreEmpleado;
	private JTextField txtNivel;
	private ControlEmpleados control;
	private JTextField txtId;
	private TableColumn check;

	
	private JTable tabla_empleado;
	private JScrollPane scrollPaneEmpleados;
	private JTextField txtError;
	
	private DefaultTableModel modeloEmpleados = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			if(column == 6) {
				return true;
			} else {
				return false;
			}
		}
	};


	public VentanaControlEmpleados() {
		setResizable(false);
		setTitle("Farmapass - Registro de Empleados");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setBounds(100, 100, 754, 454);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabla_empleado = new JTable(modeloEmpleados) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				if(vColIndex == 6) {
					return true;
				}
				return false;
			}
		};
		
		modeloEmpleados.addColumn("ID");
		modeloEmpleados.addColumn("Nombre");
		modeloEmpleados.addColumn("Apellido");
		modeloEmpleados.addColumn("Edad");
		modeloEmpleados.addColumn("Direccion");
		modeloEmpleados.addColumn("Telefono");
		modeloEmpleados.addColumn("Seleccionar");
		
		check = tabla_empleado.getColumnModel().getColumn(6);
		check.setCellEditor(tabla_empleado.getDefaultEditor(Boolean.class));
		check.setCellRenderer(tabla_empleado.getDefaultRenderer(Boolean.class));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(5, 5, 369, 34);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setLayout(null);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		
		scrollPaneEmpleados = new JScrollPane();
		scrollPaneEmpleados.setBounds(0, 0, 573, 201);
		panel2.add(scrollPaneEmpleados);
		
		JLabel lblEmpleados = new JLabel("Empleados");
		lblEmpleados.setFont(new Font("Josefin Sans", Font.BOLD, 16));
		
		JPanel panel3 = new JPanel();
		panel3.setBackground(Color.WHITE);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setFont(new Font("Josefin Sans", Font.PLAIN, 14));
		btnRegresar.setBackground(UIManager.getColor("Button.background"));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textHighlight);

		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap(10, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(panel2, GroupLayout.PREFERRED_SIZE, 573, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(245)
										.addComponent(lblEmpleados, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)))
								.addGap(18))
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(panel3, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE)
								.addGap(99)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(248)
							.addComponent(btnRegresar, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addGap(4))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblEmpleados, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel2, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel3, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRegresar)
							.addGap(11))
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel3.setLayout(null);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setFont(new Font("Josefin Sans", Font.PLAIN, 14));
		btnAgregar.setBackground(new Color(0, 204, 51));
		btnAgregar.setBounds(10, 10, 108, 21);
		panel3.add(btnAgregar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Josefin Sans", Font.PLAIN, 14));
		btnModificar.setBackground(new Color(255, 204, 0));
		btnModificar.setBounds(140, 10, 108, 21);
		panel3.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Josefin Sans", Font.PLAIN, 14));
		btnEliminar.setBackground(new Color(255, 51, 51));
		btnEliminar.setBounds(273, 10, 108, 21);
		panel3.add(btnEliminar);
		
		txtNombreEmpleado = new JTextField();
		txtNombreEmpleado.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		txtNombreEmpleado.setBackground(Color.WHITE);
		txtNombreEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombreEmpleado.setEditable(false);
		txtNombreEmpleado.setBounds(241, 8, 301, 24);
		panel.add(txtNombreEmpleado);
		txtNombreEmpleado.setColumns(10);

		txtNivel = new JTextField();
		txtNivel.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		txtNivel.setBackground(Color.WHITE);
		txtNivel.setEditable(false);
		txtNivel.setBounds(26, 7, 171, 25);
		panel.add(txtNivel);
		txtNivel.setColumns(10);
		contentPane.add(panel);
		
		txtId = new JTextField();
		txtId.setBackground(Color.WHITE);
		txtId.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		txtId.setEditable(false);
		txtId.setBounds(597, 9, 123, 22);
		panel.add(txtId);
		txtId.setColumns(10);
		contentPane.setLayout(gl_contentPane);
		
		txtError = new JTextField();
		txtError.setHorizontalAlignment(SwingConstants.CENTER);
		txtError.setEditable(false);
		txtError.setBounds(0, 0, 628, 201);
		panel2.add(txtError);
		txtError.setColumns(10);
		
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				control.iniciaAgregar(empleado); 
//				oculta();
			}
		});
		
		
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int contSel = 0;
				int contNoSel = 0;
				String nombre = null;
				try {
					for (int i = 0; i < tabla_empleado.getRowCount(); i++) {
						if (String.valueOf(tabla_empleado.getValueAt(i, 6)) != "false" && String.valueOf(tabla_empleado.getValueAt(i, 6)) != "null") {
							nombre = (String) tabla_empleado.getValueAt(i, 1);
							contSel++;
						} else {
							contNoSel++;
						}
					}
					if (contSel == 1) {
//						control.iniciaModificar(empleado, nombre);
						oculta();
					} else {
						if (nombre == null) {
							System.out.println(nombre);
							JOptionPane.showMessageDialog(null, "No has seleccionado ningun empleado!", "Error!",
									JOptionPane.WARNING_MESSAGE);
						} else {
							

							JOptionPane.showMessageDialog(null, "Solo puedes editar un empleado a la vez!", "Error!",
									JOptionPane.WARNING_MESSAGE);
						}
					}
						
					

				} catch (Exception e2) {
					System.out.println(e2);
					JOptionPane.showMessageDialog(null, "Error al leer la tabla.");
				}
			}
		});
		
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				modeloEmpleados = (DefaultTableModel) tabla_empleado.getModel();
//				int a = tabla_empleado.getSelectedRow();
//				List<String> listaEmpleados = new ArrayList<>();
//				
//				try {
//					for (int i = 0; i < tabla_empleado.getRowCount(); i++) {
//						if (String.valueOf(tabla_empleado.getValueAt(i, 6)) != "false") {
//							listaEmpleados.add((String) tabla_empleado.getValueAt(i, 1));
//						}
//					}
//
//					if (listaEmpleados.isEmpty()) {
//						muestraDialogoConMensaje("Debes de seleccionar a un empleado");
//					} else {
//						System.out.println(listaEmpleados.get(0));
//						int confirmar = JOptionPane.showConfirmDialog(null, "Seguro quieres eliminar a:"  + listaEmpleados);
//							if(JOptionPane.OK_OPTION==confirmar) {
//								
//								control.eliminarEmpleado(listaEmpleados);
//								control.recuperaEmpleados(empleado);
//							}
//						
//						
//
//						
//					}
//				} catch (Exception e2) {
//					// TODO: handle exception
//				}
//				
				
			
			}
		});
		
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.iniciaPrincipal(empleado);
				oculta();
				control.iniciaPrincipal(empleado);
			}
		});
		
	}
	
		
	
	/**
	 * Llena la tabla con los empleados
	 * @param empleado empleado que se va llenando en cada fila de la tabla
	 */
	
	public void agregarEmpleados(Empleado empleado) {
	
			modeloEmpleados.addRow(new Object[] { empleado.getIdEmpleado(), empleado.getNombre(),
					empleado.getApellido(), String.valueOf(empleado.getEdad()),
					empleado.getDireccion(), empleado.getTelefono(), false });
			
			tabla_empleado.setModel(modeloEmpleados);
		
			
			scrollPaneEmpleados.setViewportView(tabla_empleado);
		
	}
	
	
	/**
	 * Muestra la ventana Control empleados
	 * @param control control que lleva el flujo
	 * @param empleado empleado que ha iniciado sesion
	 */
	public void muestra(ControlEmpleados control, Empleado empleado) {
		String id = String.valueOf(empleado.getIdEmpleado());
		this.control = control;
		this.empleado = empleado;
		this.txtNombreEmpleado.setText(empleado.getNombre() + " " + empleado.getApellido());
		this.txtNivel.setText(empleado.getNivel() + ":");
		this.txtId.setText(id);
		limpiarTablas();
		log.info("Ventana muestra al encargado: " + this.empleado.getNombre());
		control.recuperaEmpleados(this.empleado);
	
		scrollPaneEmpleados.setViewportView(tabla_empleado);
		setVisible(true);

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
				JOptionPane.showMessageDialog(null, "Error al limpiar la tabla empleados.");			
				}
		}
	}
	
	/**
	 * Muestra un mensaje en pantalla
	 * @param mensaje mensaje que se quiere mostrar en pantalla
	 */
	public void muestraDialogoConMensaje(String mensaje ) {
	
		JOptionPane.showMessageDialog(this , mensaje);
	}
	
	/**
	 * Oculta la ventana Control Empleados
	 */
	public void oculta() {
		setVisible(false);
	}
}
