package mx.uam.ayd.proyecto.presentacion.reporteInventario;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.ReporteEmpleados;
import mx.uam.ayd.proyecto.negocio.modelo.ReporteInventario;

import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

@Slf4j
@SuppressWarnings("serial")
@Component
public class VentanaMuestraReportesInventario extends JFrame {

	private JPanel contentPane;
	private JTextField textCargo;
	private JTextField textEmpleado;
	private JTextField textIdEmpleado;
	private JScrollPane scrollPane;
	
	private ControlMuestraReportesInventario controlMuestraReportesInventario;
	private Empleado empleado;
	DefaultTableModel modeloReportes = new DefaultTableModel();
	private JTable tabla_Reportes;
	
	/**
	 * Create the frame.
	 */
	public VentanaMuestraReportesInventario() {
		setTitle("FARMAPASS");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 572, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 538, 45);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textCargo = new JTextField();
		textCargo.setHorizontalAlignment(SwingConstants.CENTER);
		textCargo.setEditable(false);
		textCargo.setBounds(10, 11, 86, 20);
		panel.add(textCargo);
		textCargo.setColumns(10);
		
		textEmpleado = new JTextField();
		textEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		textEmpleado.setEnabled(true);
		textEmpleado.setEditable(false);
		textEmpleado.setText("");
		textEmpleado.setBounds(144, 11, 285, 20);
		panel.add(textEmpleado);
		textEmpleado.setColumns(10);
		
		textIdEmpleado = new JTextField();
		textIdEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		textIdEmpleado.setEditable(false);
		textIdEmpleado.setBounds(460, 11, 68, 20);
		panel.add(textIdEmpleado);
		textIdEmpleado.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 67, 538, 35);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblListaProductos = new JLabel("Lista de productos reportados");
		lblListaProductos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblListaProductos.setBounds(138, 11, 201, 14);
		panel_1.add(lblListaProductos);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 105, 538, 170);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 537, 170);
		panel_2.add(scrollPane);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(10, 286, 538, 44);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oculta();
			}
		});
		btnRegresar.setBounds(194, 11, 89, 23);
		panel_3.add(btnRegresar);
		
		tabla_Reportes = new JTable(modeloReportes) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		
		modeloReportes.addColumn("Id_Reporte");
		modeloReportes.addColumn("Fecha");
		modeloReportes.addColumn("Empleado");
		modeloReportes.addColumn("Nombre Producto");
		modeloReportes.addColumn("Comentario");
		
		scrollPane.setViewportView(tabla_Reportes);
		
	}
	
	public void muestra(ControlMuestraReportesInventario controlMuestraReportesInventario, Empleado empleado, List<ReporteInventario> reportes) {
		this.controlMuestraReportesInventario = controlMuestraReportesInventario;
		this.empleado = empleado;
		this.textCargo.setText(empleado.getNivel());
		this.textEmpleado.setText(empleado.getNombre() + " " + empleado.getApellido());
		String id = String.valueOf(empleado.getIdEmpleado());
		this.textIdEmpleado.setText(id);
		
		limpiaTabla();
		for (ReporteInventario reporteInventario : reportes) {
			llenaTabla(reporteInventario);
		}
		
		setVisible(true);
	}
	
	public void llenaTabla(ReporteInventario reporte) {
		Object a[] = new Object[5];
		a[0] = reporte.getIdReporte();
		a[1] = reporte.getFecha();
		a[2] = reporte.getEmpleadoQueReporta();
		a[3] = reporte.getNombreProducto();
		a[4] = reporte.getComentario();
		modeloReportes.addRow(a);
		tabla_Reportes.setModel(modeloReportes);
	}
	
	private void limpiaTabla() {
		int filas = tabla_Reportes.getRowCount();
		try {
			for(int i = 0; filas > i; i++) {
				modeloReportes.removeRow(0);
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
