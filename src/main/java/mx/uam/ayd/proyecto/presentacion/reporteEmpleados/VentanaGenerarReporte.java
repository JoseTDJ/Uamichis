package mx.uam.ayd.proyecto.presentacion.reporteEmpleados;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JButton;

@SuppressWarnings("serial")
@Component
public class VentanaGenerarReporte extends JFrame {

	private JPanel contentPane;
	private JTextField textCargo;
	private JTextField textEmpleado;
	private JTextField textId;
	private JTextField textIdEmpleadoReportado;
	private JTextField textNombreEmpleadoReportado;
	private JTextField textApellidos;
	private JTextField textCargoEmpleadoReportado;
	private JTextArea textAreaComentario;
	
	private Empleado empleadoSesion;
	private Empleado empleado2;
	private ControlGenerarReporte controlGenerarReporte;
	
	/**
	 * Create the frame.
	 */
	public VentanaGenerarReporte() {
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
		
		JLabel lblTitulo = new JLabel("El empleado al cual se le levantara un reporte es:");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 12));
		lblTitulo.setBounds(50, 11, 315, 16);
		panel_1.add(lblTitulo);
		
		textIdEmpleadoReportado = new JTextField();
		textIdEmpleadoReportado.setEditable(false);
		textIdEmpleadoReportado.setBounds(175, 36, 86, 20);
		panel_1.add(textIdEmpleadoReportado);
		textIdEmpleadoReportado.setColumns(10);
		
		textNombreEmpleadoReportado = new JTextField();
		textNombreEmpleadoReportado.setEditable(false);
		textNombreEmpleadoReportado.setBounds(175, 64, 170, 20);
		panel_1.add(textNombreEmpleadoReportado);
		textNombreEmpleadoReportado.setColumns(10);
		
		textApellidos = new JTextField();
		textApellidos.setEditable(false);
		textApellidos.setBounds(175, 95, 170, 20);
		panel_1.add(textApellidos);
		textApellidos.setColumns(10);
		
		textCargoEmpleadoReportado = new JTextField();
		textCargoEmpleadoReportado.setEditable(false);
		textCargoEmpleadoReportado.setBounds(175, 126, 170, 20);
		panel_1.add(textCargoEmpleadoReportado);
		textCargoEmpleadoReportado.setColumns(10);
		
		textAreaComentario = new JTextArea();
		textAreaComentario.setBounds(114, 170, 326, 61);
		panel_1.add(textAreaComentario);
		
		JLabel lblIdEmpleadoReportado = new JLabel("ID: ");
		lblIdEmpleadoReportado.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIdEmpleadoReportado.setBounds(102, 38, 46, 14);
		panel_1.add(lblIdEmpleadoReportado);
		
		JLabel lblEmpleadoReportado = new JLabel("Nombre: ");
		lblEmpleadoReportado.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmpleadoReportado.setBounds(90, 67, 75, 14);
		panel_1.add(lblEmpleadoReportado);
		
		JLabel lblApellidos = new JLabel("Apellidos: ");
		lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblApellidos.setBounds(90, 98, 75, 14);
		panel_1.add(lblApellidos);
		
		JLabel lblCargoReportado = new JLabel("Cargo: ");
		lblCargoReportado.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCargoReportado.setBounds(90, 129, 46, 14);
		panel_1.add(lblCargoReportado);
		
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
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textAreaComentario.getText().equals("")) {
					muestraDialogoConMensaje("El comentario no puede estar vacio!");
				}else {
					controlGenerarReporte.mandaReporteConEmpleado(empleadoSesion, empleado2, textAreaComentario.getText());
					oculta();
					muestraDialogoConMensaje("Se realizo el reporte al trabajador: " + empleado2.getNombre() + empleado2.getApellido());
					controlGenerarReporte.iniciaPrincipalEncargado(empleadoSesion);
				}
			}
		});
		btnGenerarReporte.setBounds(166, 11, 134, 23);
		panel_2.add(btnGenerarReporte);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlGenerarReporte.iniciaVentanaReporteEmpleados(empleadoSesion);
				oculta();
			}
		});
		btnCancelar.setBounds(350, 11, 89, 23);
		panel_2.add(btnCancelar);
	}

	public void muestra(ControlGenerarReporte controlGenerarReporte, Empleado empleado, List<Empleado> empleados, Empleado empleado2) {
		this.controlGenerarReporte = controlGenerarReporte;
		this.empleadoSesion = empleado;
		this.empleado2 = empleado2;
		//Datos del empleado con sesion iniciada
		this.textCargo.setText(empleado.getNivel());
		this.textEmpleado.setText(empleado.getNombre() + " " + empleado.getApellido());
		this.textId.setText(String.valueOf(empleado.getIdEmpleado()));
		//Datos del empleado a reportar
		this.textIdEmpleadoReportado.setText(String.valueOf(empleado2.getIdEmpleado()));
		this.textNombreEmpleadoReportado.setText(empleado2.getNombre());
		this.textApellidos.setText(empleado2.getApellido());
		this.textCargoEmpleadoReportado.setText(empleado2.getNivel());
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
