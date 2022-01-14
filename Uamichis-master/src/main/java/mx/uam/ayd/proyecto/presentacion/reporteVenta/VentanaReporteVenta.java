package mx.uam.ayd.proyecto.presentacion.reporteVenta;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * 
 * Esta ventana lleva el flujo del control de reportes de ventas, mostrando las
 * ventas realizadas durante el dia para reportar alguna anomalia.
 * @author Luis Cristofer Alvarado Gabriel
 * @since 11/02/2021
 *
 */
@Component
@SuppressWarnings("serial")
public class VentanaReporteVenta extends JFrame {

	private JPanel contentPane;
	private JTextField cargo;
	private JTextField nombreEmpleado;
	private JTextField idEmpleado;
	private JPanel panelBotones;
	
	private Empleado empleado;
	private ControlReporteVenta control;
	private JTextField fecha;
	private JTable tablaVentas;
	
	private Calendar fech = new GregorianCalendar();
	private int ano = fech.get(Calendar.YEAR);
	private int mes = fech.get(Calendar.MONTH) + 1;
	private int dia = fech.get(Calendar.DAY_OF_MONTH);
	private String fechaF = ano + "/" + mes + "/" + dia;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Venta> ventas = new ArrayList();

	private DefaultTableModel modeloVenta = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) { 
			return false; 
			} 
	};
	
	/**
	 * Create the frame.
	 */
	public VentanaReporteVenta() {
		setResizable(false);
		setTitle("FARMAPASS");
		setBounds(100, 100, 650, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelDatos = new JPanel();
		panelDatos.setBounds(5, 5, 631, 78);
		contentPane.add(panelDatos);
		panelDatos.setLayout(null);
		
		cargo = new JTextField();
		cargo.setHorizontalAlignment(SwingConstants.CENTER);
		cargo.setEditable(false);
		cargo.setBounds(0, 4, 200, 28);
		panelDatos.add(cargo);
		cargo.setColumns(10);
		
		nombreEmpleado = new JTextField();
		nombreEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		nombreEmpleado.setEditable(false);
		nombreEmpleado.setColumns(10);
		nombreEmpleado.setBounds(220, 4, 200, 28);
		panelDatos.add(nombreEmpleado);
		
		idEmpleado = new JTextField();
		idEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		idEmpleado.setEditable(false);
		idEmpleado.setColumns(10);
		idEmpleado.setBounds(441, 4, 180, 28);
		panelDatos.add(idEmpleado);
		
		JLabel lblVentasRealizadas = new JLabel("Ventas Realizadas");
		lblVentasRealizadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblVentasRealizadas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVentasRealizadas.setBounds(220, 42, 200, 28);
		panelDatos.add(lblVentasRealizadas);
		
		fecha = new JTextField();
		fecha.setHorizontalAlignment(SwingConstants.CENTER);
		fecha.setEditable(false);
		fecha.setColumns(10);
		fecha.setBounds(0, 42, 200, 28);
		panelDatos.add(fecha);
		
		panelBotones = new JPanel();
		panelBotones.setBounds(5, 293, 631, 35);
		contentPane.add(panelBotones);
		panelBotones.setLayout(null);
		
		JButton btnNewButton = new JButton("Reportar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x = tablaVentas.getSelectedRow();
				if(x == -1) {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado una venta.", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					int idVenta = Integer.parseInt(String.valueOf(tablaVentas.getValueAt(x, 0)));
					control.inciaGeneraReporteVenta(empleado, idVenta);
				}
				
			}
		});
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setBounds(536, 9, 85, 21);
		panelBotones.add(btnNewButton);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiaTabla();
				setVisible(false);
			}
		});
		btnRegresar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnRegresar.setBounds(438, 9, 85, 21);
		panelBotones.add(btnRegresar);
		
		JButton btnReportes = new JButton("Reportes");
		btnReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.muestraReportesVenta(empleado);
			}
		});
		btnReportes.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnReportes.setBounds(10, 9, 85, 21);
		panelBotones.add(btnReportes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 93, 631, 190);
		contentPane.add(scrollPane);
		
		tablaVentas = new JTable();
		modeloVenta.addColumn("ID de Venta");
		modeloVenta.addColumn("Fecha");
		modeloVenta.addColumn("Total de productos");
		modeloVenta.addColumn("Precio total");
		modeloVenta.addColumn("...");
		scrollPane.setViewportView(tablaVentas);
	}
	
	public void muestra(ControlReporteVenta controlReporteVenta, Empleado emp) {
		this.control = controlReporteVenta;
		this.empleado = emp;
		this.fecha.setText(fechaF);
		this.cargo.setText(empleado.getNivel());
		this.nombreEmpleado.setText(empleado.getNombre() + " " + empleado.getApellido());
		this.idEmpleado.setText(String.valueOf(empleado.getIdEmpleado()));
		//limpiaTabla();
		setVisible(true);
	}
	
	/**
	 * Llena la tabla con los datos de laas ventas del dia.
	 * @param venta
	 * @param size
	 */
	public void llenaTablaVentas(Venta venta, int size) {
		ventas.add(venta);
		String a[] = new String[5];
		a[0] = String.valueOf(venta.getIdVenta());
		a[1] = venta.getFecha();
		a[2] = String.valueOf(size);
		a[3] = String.valueOf(venta.getTotal());
		a[4] = "Seleccione para reportar";
		modeloVenta.addRow(a);
		tablaVentas.setModel(modeloVenta);
	}
	
	private void limpiaTabla() {
		int filas = tablaVentas.getRowCount();
		try {
			for(int i = 0; filas > i; i++) {
				modeloVenta.removeRow(0);
			}
		}catch(Exception e) {
			//log.warn("## ERROR AL LIMPIAR LA TABLA: " + e);
			JOptionPane.showMessageDialog(null, "No se pudo limpiar la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
