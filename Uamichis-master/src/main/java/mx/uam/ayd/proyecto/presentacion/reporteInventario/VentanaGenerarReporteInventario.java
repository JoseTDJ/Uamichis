package mx.uam.ayd.proyecto.presentacion.reporteInventario;

/**
 * @author VICTOR_SOSA
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

@SuppressWarnings("serial")
@Component
public class VentanaGenerarReporteInventario extends JFrame {

	private JPanel contentPane;
	private JTextField textCargo;
	private JTextField textEmpleado;
	private JTextField textId;
	private JTextField textIdProducto;
	private JTextField textNombre;
	private JTextField textCompuesto;
	private JTextField textPrecio;
	private JTextArea textAreaComentario;
	
	private Empleado empleado;
	private ControlGenerarReporteInventario controlGenerarReporteInventario;
	
	/**
	 * Create the frame.
	 */
	public VentanaGenerarReporteInventario() {
		setTitle("FARMAPASS");
		
		setBounds(100, 100, 500, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 464, 40);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textCargo = new JTextField();
		textCargo.setEditable(false);
		textCargo.setBounds(10, 11, 86, 20);
		panel.add(textCargo);
		textCargo.setColumns(10);
		
		textEmpleado = new JTextField();
		textEmpleado.setEditable(false);
		textEmpleado.setBounds(106, 11, 252, 20);
		panel.add(textEmpleado);
		textEmpleado.setColumns(10);
		
		textId = new JTextField();
		textId.setEditable(false);
		textId.setBounds(368, 11, 86, 20);
		panel.add(textId);
		textId.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 62, 464, 242);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblTitulo = new JLabel("El producto al cual se le levantara un reporte es:");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 12));
		lblTitulo.setBounds(50, 11, 315, 16);
		panel_1.add(lblTitulo);
		
		textIdProducto = new JTextField();
		textIdProducto.setEditable(false);
		textIdProducto.setBounds(175, 36, 86, 20);
		panel_1.add(textIdProducto);
		textIdProducto.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setEditable(false);
		textNombre.setBounds(175, 64, 170, 20);
		panel_1.add(textNombre);
		textNombre.setColumns(10);
		
		textCompuesto = new JTextField();
		textCompuesto.setEditable(false);
		textCompuesto.setBounds(175, 95, 170, 20);
		panel_1.add(textCompuesto);
		textCompuesto.setColumns(10);
		
		textPrecio = new JTextField();
		textPrecio.setEditable(false);
		textPrecio.setBounds(175, 126, 170, 20);
		panel_1.add(textPrecio);
		textPrecio.setColumns(10);
		
		textAreaComentario = new JTextArea();
		textAreaComentario.setBounds(114, 170, 326, 61);
		panel_1.add(textAreaComentario);
		
		JLabel lblIdEmpleadoReportado = new JLabel("ID: ");
		lblIdEmpleadoReportado.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIdEmpleadoReportado.setBounds(102, 38, 46, 14);
		panel_1.add(lblIdEmpleadoReportado);
		
		JLabel lblProducto = new JLabel("Nombre: ");
		lblProducto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProducto.setBounds(90, 67, 75, 14);
		panel_1.add(lblProducto);
		
		JLabel lblCompuesto = new JLabel("Compuesto: ");
		lblCompuesto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCompuesto.setBounds(90, 98, 75, 14);
		panel_1.add(lblCompuesto);
		
		JLabel lblPrecio = new JLabel("Precio: ");
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrecio.setBounds(90, 129, 46, 14);
		panel_1.add(lblPrecio);
		
		JLabel lblComentario = new JLabel("Comentario: ");
		lblComentario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblComentario.setBounds(10, 175, 94, 14);
		panel_1.add(lblComentario);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(10, 315, 464, 55);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnGenerarReporte = new JButton("Generar Reporte");
		btnGenerarReporte.setForeground(Color.BLACK);
		btnGenerarReporte.setBackground(Color.GREEN);
		btnGenerarReporte.setBounds(274, 11, 134, 23);
		panel_2.add(btnGenerarReporte);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setBackground(Color.RED);
		btnCancelar.setBounds(56, 11, 89, 23);
		panel_2.add(btnCancelar);
		
		/***********LISTENER**********/
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlGenerarReporteInventario.iniciaVentanaReporteInventario(empleado);
				oculta();
			}
		});
		
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textAreaComentario.getText().equals("")) {
					muestraDialogoConMensaje("El comentario no puede estar vacio!");
				}else {
					controlGenerarReporteInventario.mandaReporteInventario(empleado,textNombre.getText(), textAreaComentario.getText());
					oculta();  // Oculta la ventana actual
					muestraDialogoConMensaje("Se realizo el reporte al Producto: " + textNombre.getText());
					//controlGenerarReporte.iniciaPrincipalEncargado(empleadoSesion);
				}
			}
		});
	}

	public void muestra(ControlGenerarReporteInventario controlGenerarReporteInventario, Empleado empleado,Producto producto) {
		this.controlGenerarReporteInventario = controlGenerarReporteInventario;
		this.empleado = empleado;
		//Datos del empleado con sesion iniciada
		this.textCargo.setText(empleado.getNivel());
		this.textEmpleado.setText(empleado.getNombre() + " " + empleado.getApellido());
		this.textId.setText(String.valueOf(empleado.getIdEmpleado()));
		//Datos del producto a reportar
		this.textIdProducto.setText(String.valueOf(producto.getIdProducto()));
		this.textNombre.setText(producto.getNombre());
		this.textCompuesto.setText(producto.getCompuesto());
		this.textPrecio.setText(String.valueOf(producto.getPrecio()));
		this.textAreaComentario.setText("");
		setVisible(true);
	}
	
	public void muestraDialogoConMensaje(String mensaje ) {
		JOptionPane.showMessageDialog(this , mensaje);
	}
	
	private void oculta() {
		setVisible(false);	
	}
}
