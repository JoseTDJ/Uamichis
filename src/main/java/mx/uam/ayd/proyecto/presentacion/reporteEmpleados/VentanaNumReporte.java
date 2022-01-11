package mx.uam.ayd.proyecto.presentacion.reporteEmpleados;

/**
*	Muestra el numero de reportes que tiene el empleado
*	@author	Gonzalo Olvera Monroy 
*   @since  16/02/2021
*/

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioReporteEmpleados;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.ReporteEmpleados;

import java.util.List;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

@SuppressWarnings("serial")
@Component
public class VentanaNumReporte  extends JFrame{
	
	@Autowired
	private ServicioReporteEmpleados servicioReporteEmpleados;
	
	private JPanel contentPane;
	private Empleado empleado;

	private ControlNumReporte controlNumReporte;

	private JTextField txtEmpleado;
	
	public VentanaNumReporte() {
		
		setTitle("FARMAPASS");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 389, 176);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(27, 10, 338, 188);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnVerReporte = new JButton("Ver Reporte");
		btnVerReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlNumReporte.inicia2(empleado);
				oculta();
			}
		});
		btnVerReporte.setBounds(115, 77, 103, 21);
		panel.add(btnVerReporte);
		
		txtEmpleado = new JTextField();
		txtEmpleado.setEditable(false);
		txtEmpleado.setBounds(10, 24, 318, 19);
		panel.add(txtEmpleado);
		txtEmpleado.setColumns(10);
	}
	
	
	public void muestra(ControlNumReporte controlNum, Empleado empleado) {
		this.controlNumReporte = controlNum;
		this.empleado = empleado;

		List<ReporteEmpleados> reportes = servicioReporteEmpleados.recuperaReportesPorEmpleado(empleado);
	
		this.txtEmpleado.setText("Tienes "+ reportes.size() + " reportes " + empleado.getNombre() + " " + empleado.getApellido() );
		setVisible(true);
	}
		
	public void oculta() {
		setVisible(false);
	}
}
