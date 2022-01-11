package mx.uam.ayd.proyecto.presentacion.reporteEmpleados;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

@Slf4j
@SuppressWarnings("serial")
@Component
public class VentanaReporteEmpleados extends JFrame {

	private JPanel contentPane;
	private ControlReporteEmpleados controlReporteEmpleados;
	private Empleado empleado;
	private JTextField textCargo;
	private JTextField textEmpleado;
	private JTextField textIdEmpleado;
	
	DefaultTableModel modeloEmpleados = new DefaultTableModel();
	private JTable tabla_Empleados;
	
	
	/**
	 * Create the frame.
	 */
	public VentanaReporteEmpleados() {
		setTitle("FARMAPASS");
		
		
		setBounds(100, 100, 530, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 494, 45);
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
		textIdEmpleado.setBounds(375, 11, 86, 20);
		panel.add(textIdEmpleado);
		textIdEmpleado.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 67, 494, 39);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEmpleados = new JLabel("Empleados");
		lblEmpleados.setFont(new Font("Arial", Font.BOLD, 14));
		lblEmpleados.setBounds(194, 11, 82, 14);
		panel_1.add(lblEmpleados);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 117, 494, 150);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 494, 150);
		panel_2.add(scrollPane);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(10, 278, 494, 42);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnGenerarReporte = new JButton("Generar reporte");
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int contSel = 0;
				int contNoSel = 0;
				String nombre = null;
				try {
					for(int i = 0; i < tabla_Empleados.getRowCount(); i++) {
						if(String.valueOf(tabla_Empleados.getValueAt(i, 6)) != "false" && String.valueOf(tabla_Empleados.getValueAt(i, 6)) != "null") {
							nombre = (String) tabla_Empleados.getValueAt(i, 1);
							contSel++;
						}else {
							contNoSel++;
						}
					}
					if(contSel == 1) {
						controlReporteEmpleados.iniciaGeneraReporte(empleado, nombre);
						oculta();
					}else {
						if(nombre == null) {
							System.out.println(nombre);
							JOptionPane.showMessageDialog(null, "No has seleccionado ningun empleado", "Error", JOptionPane.WARNING_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null, "Solo puedes reportar un empleado a la vez", "Error", JOptionPane.WARNING_MESSAGE);
						}
					}
				}catch(Exception e2) {
					System.out.println(e2);
					JOptionPane.showConfirmDialog(null, "Error al leer la tabla");
				}
			}
		});
		btnGenerarReporte.setBackground(Color.GREEN);
		btnGenerarReporte.setBounds(148, 11, 150, 23);
		panel_3.add(btnGenerarReporte);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  controlReporteEmpleados.regresar(empleado);
				  oculta();
				  controlReporteEmpleados.regresar(empleado);
				 
			}
		});
		btnRegresar.setBounds(384, 11, 89, 23);
		panel_3.add(btnRegresar);
		
		JButton btnVerReportes = new JButton("Reportes");
		btnVerReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlReporteEmpleados.muestraReportes(empleado);
				oculta();
			}
		});
		btnVerReportes.setBounds(10, 11, 89, 23);
		panel_3.add(btnVerReportes);
		
		tabla_Empleados = new JTable(modeloEmpleados) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				if(vColIndex == 6) {
					return true;
				}else {
					return false;
				}
			}
		};
		
		modeloEmpleados.addColumn("ID");
		modeloEmpleados.addColumn("Nombre");
		modeloEmpleados.addColumn("Apellidos");
		modeloEmpleados.addColumn("Edad");
		modeloEmpleados.addColumn("Direccion");
		modeloEmpleados.addColumn("Telefono");
		modeloEmpleados.addColumn("Seleccionar");
		
		scrollPane.setViewportView(tabla_Empleados);
		
	}

	public void muestra(ControlReporteEmpleados controlReporteEmpleados, Empleado empleado, List<Empleado> listaEmpleados) {
		this.controlReporteEmpleados = controlReporteEmpleados;
		this.empleado = empleado;
		this.textCargo.setText(empleado.getNivel());
		this.textEmpleado.setText(empleado.getNombre() + " " + empleado.getApellido());
		String id = String.valueOf(empleado.getIdEmpleado());
		this.textIdEmpleado.setText(id);
		limpiaTabla();
		for (Empleado empleado2 : listaEmpleados) {
			if(!empleado2.getNombre().equals(this.empleado.getNombre())) {
				llenaTabla(empleado2);
			}
		}
		addCheckBox(6, tabla_Empleados);
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
	
	public void llenaTabla(Empleado empleado2) {
		Object a[] = new Object[7];
		a[0] = empleado2.getIdEmpleado();
		a[1] = empleado2.getNombre();
		a[2] = empleado2.getApellido();
		a[3] = empleado2.getEdad();
		a[4] = empleado2.getDireccion();
		a[5] = empleado2.getTelefono();
		modeloEmpleados.addRow(a);
		tabla_Empleados.setModel(modeloEmpleados);
	}
	private void limpiaTabla() {
		int filas = tabla_Empleados.getRowCount();
		try {
			for(int i = 0; filas > i; i++) {
				modeloEmpleados.removeRow(0);
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
