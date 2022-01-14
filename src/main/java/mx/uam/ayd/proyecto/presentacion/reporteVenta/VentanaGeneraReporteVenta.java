package mx.uam.ayd.proyecto.presentacion.reporteVenta;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
/**
 * Ventana que lleva el flujo del control para generar un reporte
 * de venta.
 * @author Luis Cristofer Alvarado Gabriel
 * @since 18/02/2021
 */
@Component
@SuppressWarnings("serial")
public class VentanaGeneraReporteVenta extends JFrame {

	private JPanel contentPane;
	private JTextField cargo;
	private JTextField nombreEmpleado;
	private JTextField idEmpleado;
	private JTextArea textComentario;
	private JTextField textIdVenta;
	private JTextField fechaV;
	private JTextField textEmpleadoVenta;
	private JTable table;
	private ControlGeneraReporteVenta controlGeneraReporteVenta;
	private Empleado empleado;
	
	private Calendar fech = new GregorianCalendar();
	private int ano = fech.get(Calendar.YEAR);
	private int mes = fech.get(Calendar.MONTH) + 1;
	private int dia = fech.get(Calendar.DAY_OF_MONTH);
	private String fechaF = ano + "/" + mes + "/" + dia;
	private long idVenta;
	private Venta venta;

	private DefaultTableModel modeloDetalleVenta = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) { 
			return false; 
			} 
	};

	/**
	 * Create the frame.
	 */
	public VentanaGeneraReporteVenta() {
		setResizable(false);
		setTitle("FARMAPASS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 720, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelEncabezado = new JPanel();
		panelEncabezado.setBounds(5, 5, 701, 70);
		contentPane.add(panelEncabezado);
		panelEncabezado.setLayout(null);
		
		cargo = new JTextField();
		cargo.setHorizontalAlignment(SwingConstants.CENTER);
		cargo.setEditable(false);
		cargo.setColumns(10);
		cargo.setBounds(0, 0, 200, 28);
		panelEncabezado.add(cargo);
		
		nombreEmpleado = new JTextField();
		nombreEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		nombreEmpleado.setEditable(false);
		nombreEmpleado.setColumns(10);
		nombreEmpleado.setBounds(243, 0, 221, 28);
		panelEncabezado.add(nombreEmpleado);
		
		idEmpleado = new JTextField();
		idEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		idEmpleado.setEditable(false);
		idEmpleado.setColumns(10);
		idEmpleado.setBounds(491, 0, 200, 28);
		panelEncabezado.add(idEmpleado);
		
		JLabel lblReporteVentas = new JLabel("Reporte de Venta");
		lblReporteVentas.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporteVentas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblReporteVentas.setBounds(253, 38, 200, 28);
		panelEncabezado.add(lblReporteVentas);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setBounds(5, 308, 701, 30);
		contentPane.add(panelBotones);
		panelBotones.setLayout(null);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiaTabla();
				textIdVenta.setText("");
				textComentario.setText("");
				fechaV.setText("");
				textEmpleadoVenta.setText("");
				setVisible(false);
			}
		});
		btnCancelar.setBackground(Color.RED);
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnCancelar.setBounds(465, 0, 85, 21);
		panelBotones.add(btnCancelar);
		
		/**
		 * Boton que llama al control para generar a un reporte
		 * Si el campo de comentario del reporte está en blanco
		 * El metodo no se llama.
		 */
		JButton btnGeneraReporte = new JButton("Generar Reporte");
		btnGeneraReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textComentario.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "No se ha agregado un comentario al reporte.", 
							"Atención", JOptionPane.WARNING_MESSAGE);
				}else {
					controlGeneraReporteVenta.generaReporteVenta(venta, idVenta, textComentario.getText(), empleado);
					JOptionPane.showMessageDialog(null, "Se ha hecho el reporte de la venta!", 
							"Éxito", JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
				}
			}
		});
		btnGeneraReporte.setBackground(Color.GREEN);
		btnGeneraReporte.setBounds(560, 0, 131, 21);
		panelBotones.add(btnGeneraReporte);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(5, 84, 701, 214);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 10, 110, 17);
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_2.add(lblId);
		
		JLabel lblFechaDeVena = new JLabel("Fecha de venta:");
		lblFechaDeVena.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaDeVena.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFechaDeVena.setBounds(10, 37, 110, 17);
		panel_2.add(lblFechaDeVena);
		
		JLabel lblArticulos = new JLabel("Artículos:");
		lblArticulos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblArticulos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblArticulos.setBounds(10, 64, 110, 17);
		panel_2.add(lblArticulos);
		
		textComentario = new JTextArea();
		textComentario.setBounds(10, 125, 681, 79);
		panel_2.add(textComentario);
		textComentario.setColumns(10);
		
		JLabel lblComentario = new JLabel("Comentario:");
		lblComentario.setHorizontalAlignment(SwingConstants.LEFT);
		lblComentario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblComentario.setBounds(10, 98, 110, 17);
		panel_2.add(lblComentario);
		
		textIdVenta = new JTextField();
		textIdVenta.setEditable(false);
		textIdVenta.setBounds(130, 11, 150, 17);
		panel_2.add(textIdVenta);
		textIdVenta.setColumns(10);
		
		fechaV = new JTextField();
		fechaV.setEditable(false);
		fechaV.setColumns(10);
		fechaV.setBounds(130, 38, 150, 17);
		panel_2.add(fechaV);
		
		textEmpleadoVenta = new JTextField();
		textEmpleadoVenta.setEditable(false);
		textEmpleadoVenta.setColumns(10);
		textEmpleadoVenta.setBounds(130, 65, 150, 17);
		panel_2.add(textEmpleadoVenta);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(290, 11, 401, 100);
		panel_2.add(scrollPane);
		
		table = new JTable();
		modeloDetalleVenta.addColumn("Nombre");
		modeloDetalleVenta.addColumn("Compuesto");
		modeloDetalleVenta.addColumn("Precio");
		scrollPane.setViewportView(table);
	}
	
	public void muestra(ControlGeneraReporteVenta control, Empleado emp, Venta ventaSel) {
		this.controlGeneraReporteVenta = control;
		this.empleado = emp;
		this.venta = ventaSel;
		//this.fecha.setText(fechaF);
		this.cargo.setText(empleado.getNivel());
		this.nombreEmpleado.setText(empleado.getNombre() + " " + empleado.getApellido());
		this.idEmpleado.setText(String.valueOf(empleado.getIdEmpleado()));
		this.fechaV.setText(fechaF);
		setVisible(true);
	}
	
	/**
	 * Al momento de recibir la venta seleccionada para reportar
	 * Se llena la tabla con los datos de la venta.
	 * @param producto
	 * @param idVenta2
	 * @param articulos
	 * @param empleadoHizoVenta
	 */
	public void llenaDatosVenta(Producto producto, long idVenta2, int articulos, String empleadoHizoVenta) {
		this.textIdVenta.setText(String.valueOf(idVenta2));
		this.textEmpleadoVenta.setText(String.valueOf(articulos));
		this.idVenta = idVenta2;
		String a[] = new String[3];
		a[0] = producto.getNombre();
		a[1] = producto.getCompuesto();
		a[2] = String.valueOf(producto.getPrecio());
		modeloDetalleVenta.addRow(a);
		table.setModel(modeloDetalleVenta);
	}
	
	private void limpiaTabla() {
		int filas = table.getRowCount();
		try {
			for(int i = 0; filas > i; i++) {
				modeloDetalleVenta.removeRow(0);
			}
		}catch(Exception e) {
			//log.warn("## ERROR AL LIMPIAR LA TABLA: " + e);
			JOptionPane.showMessageDialog(null, "No se pudo limpiar la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
