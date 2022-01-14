package mx.uam.ayd.proyecto.presentacion.reporteVenta;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.ReporteVenta;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
@SuppressWarnings("serial")
@Component
public class VentanaMuestraReportesVenta extends JFrame {

	private JPanel contentPane;
	private JTextField textCargo;
	private JTextField textNombreEmpleadoSesion;
	private JTextField textIdEmpleadoSesion;
	private JTable tablaReporteVentas;
	private Empleado empleadoSesion;
	@SuppressWarnings("unused")
	private ControlMuestraReportesVenta controlMuestraReportes;
	
	private DefaultTableModel modeloReporteVenta = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) { 
			return false; 
			} 
	};

	/**
	 * Create the frame.
	 */
	public VentanaMuestraReportesVenta() {
		setTitle("FARMAPASS");
		setResizable(false);
		setBounds(100, 100, 555, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelEncabezado = new JPanel();
		panelEncabezado.setBounds(0, 0, 551, 73);
		contentPane.add(panelEncabezado);
		panelEncabezado.setLayout(null);
		
		textCargo = new JTextField();
		textCargo.setHorizontalAlignment(SwingConstants.CENTER);
		textCargo.setEditable(false);
		textCargo.setBounds(10, 10, 150, 24);
		panelEncabezado.add(textCargo);
		textCargo.setColumns(10);
		
		textNombreEmpleadoSesion = new JTextField();
		textNombreEmpleadoSesion.setHorizontalAlignment(SwingConstants.CENTER);
		textNombreEmpleadoSesion.setEditable(false);
		textNombreEmpleadoSesion.setColumns(10);
		textNombreEmpleadoSesion.setBounds(170, 10, 211, 24);
		panelEncabezado.add(textNombreEmpleadoSesion);
		
		textIdEmpleadoSesion = new JTextField();
		textIdEmpleadoSesion.setHorizontalAlignment(SwingConstants.CENTER);
		textIdEmpleadoSesion.setEditable(false);
		textIdEmpleadoSesion.setColumns(10);
		textIdEmpleadoSesion.setBounds(391, 10, 150, 24);
		panelEncabezado.add(textIdEmpleadoSesion);
		
		JLabel lblNewLabel = new JLabel("Lista de ventas reportadas");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(180, 44, 201, 19);
		panelEncabezado.add(lblNewLabel);
		
		JPanel panelTabla = new JPanel();
		panelTabla.setBounds(0, 73, 551, 160);
		contentPane.add(panelTabla);
		panelTabla.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 531, 140);
		panelTabla.add(scrollPane);
		
		tablaReporteVentas = new JTable();
		modeloReporteVenta.addColumn("ID Reporte");
		modeloReporteVenta.addColumn("Fecha");
		modeloReporteVenta.addColumn("ID de venta");
		modeloReporteVenta.addColumn("Comentario");
		modeloReporteVenta.addColumn("Empleado que reporta");
		scrollPane.setViewportView(tablaReporteVentas);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setBounds(0, 233, 551, 49);
		contentPane.add(panelBotones);
		panelBotones.setLayout(null);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnRegresar.setBounds(210, 10, 130, 20);
		panelBotones.add(btnRegresar);
		
	}

	public void muestra(ControlMuestraReportesVenta control, Empleado empleado,
			List<ReporteVenta> reportesVentas) {
		if(!reportesVentas.isEmpty()) {
			this.controlMuestraReportes = control;
			this.empleadoSesion = empleado;
			this.textCargo.setText(empleadoSesion.getNivel());
			this.textNombreEmpleadoSesion.setText(empleadoSesion.getNombre() + " " 
			+ empleadoSesion.getApellido());
			this.textIdEmpleadoSesion.setText(String.valueOf(empleadoSesion.getIdEmpleado()));
			limpiaTabla();
			for (ReporteVenta repVen : reportesVentas) {
				llenaTabla(repVen);
			}
			setVisible(true);
		}else {
			JOptionPane.showMessageDialog(null, "No hay reportes de venta", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			//setVisible(false);
		}
	}
	
	public void llenaTabla(ReporteVenta reporte) {
		Object r[] = new Object[5];
		r[0] = String.valueOf(reporte.getIdReporte());
		r[1] = reporte.getFecha();
		r[2] = String.valueOf(reporte.getIdVenta());
		r[3] = reporte.getComentario();
		r[4] = reporte.getEmpQueReporta();
		modeloReporteVenta.addRow(r);
		tablaReporteVentas.setModel(modeloReporteVenta);
	}
	
	private void limpiaTabla() {
		int filas = tablaReporteVentas.getRowCount();
		try {
			for(int i = 0; filas > i; i++) {
				modeloReporteVenta.removeRow(0);
			}
		}catch(Exception e) {
			//log.warn("## ERROR AL LIMPIAR LA TABLA: " + e);
			JOptionPane.showMessageDialog(null, "No se pudo limpiar la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
