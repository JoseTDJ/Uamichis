package mx.uam.ayd.proyecto.presentacion.principal.encargado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.presentacion.controlEmpleados.ControlEmpleados;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;

@SuppressWarnings("serial")
@Component
public class VentanaPrincipalEncargado extends JFrame {

	private JPanel contentPane;
	private ControlPrincipalEncargado control;
	private Empleado empleado;
	private JTextField txtNombreEmpleado;
	private JTextField txtNivel;
	
	private ControlEmpleados control2;

	public VentanaPrincipalEncargado() {
		setTitle("Farmapass");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 439);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(5, 5, 656, 44);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textHighlight);
		panel_1.setBounds(525, 49, 136, 352);
				panel_1.setLayout(null);
		contentPane.setLayout(null);
		panel.setLayout(null);

		txtNombreEmpleado = new JTextField();
		txtNombreEmpleado.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		txtNombreEmpleado.setBackground(Color.WHITE);
		txtNombreEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombreEmpleado.setEditable(false);
		txtNombreEmpleado.setBounds(145, 6, 265, 28);
		panel.add(txtNombreEmpleado);
		txtNombreEmpleado.setColumns(10);
		
		txtNivel = new JTextField();
		txtNivel.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		txtNivel.setBackground(Color.WHITE);
		txtNivel.setEditable(false);
		txtNivel.setBounds(10, 6, 110, 28);
		panel.add(txtNivel);
		txtNivel.setColumns(10);
		contentPane.add(panel);
		contentPane.add(panel_1);
		
		
		JButton btnCierreVenta = new JButton("Inicia cierre de venta");
		btnCierreVenta.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		btnCierreVenta.setBounds(176, 344, 197, 44);
		contentPane.add(btnCierreVenta);
		btnCierreVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.iniciaCierreVenta(empleado);
			}
		});
		btnCierreVenta.setForeground(new Color(255, 255, 255));
		btnCierreVenta.setBackground(new Color(255, 0, 0));
		
		JButton btnRecepcionMercancia = new JButton("Recepcion de Mercancia");
		btnRecepcionMercancia.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		btnRecepcionMercancia.setBounds(61, 141, 249, 39);
		contentPane.add(btnRecepcionMercancia);
		
				JButton btnVenta = new JButton("Venta");
				btnVenta.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
				btnVenta.setBounds(285, 77, 158, 39);
				contentPane.add(btnVenta);
				
				JButton btnNewButtonMonitoreo = new JButton("Monitoreo");
				btnNewButtonMonitoreo.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
				btnNewButtonMonitoreo.setBounds(311, 208, 132, 39);
				contentPane.add(btnNewButtonMonitoreo);
				
				/**
				 * BOTON HU-07 ASISTENCIAS JOSE
				 */
				JButton btnNewButton = new JButton("Asistencias");
				btnNewButton.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
				btnNewButton.setBounds(280, 278, 132, 39);
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//control.asistenciasEmp(empleado);
					}
				});
				//btnNewButton.setBounds(80, 278, 192, 21);
				contentPane.add(btnNewButton);
				
				JButton btnNewButtonBusqueda = new JButton("Busqueda");
				btnNewButtonBusqueda.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
				btnNewButtonBusqueda.setBounds(61, 77, 158, 39);
				contentPane.add(btnNewButtonBusqueda);
				
				JButton btnNuevoPedidoCliente = new JButton("Pedido cliente");
				btnNuevoPedidoCliente.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
				btnNuevoPedidoCliente.setBounds(61, 208, 185, 39);
				contentPane.add(btnNuevoPedidoCliente);
				btnNuevoPedidoCliente.setEnabled(false);
				
				JButton btnRegistroEmpleado = new JButton("Registro de Empleado");
				btnRegistroEmpleado.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
				btnRegistroEmpleado.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						control.agregarEmpleado(empleado);
						oculta();
					
					}
				});
				
				btnRegistroEmpleado.setBounds(61, 278, 197, 39); //Boton Registro Empleado
				contentPane.add(btnRegistroEmpleado);
				/*
				btnNuevoPedidoCliente.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						control.agregarEmpleado(empleado);
						oculta();
					}
				});
				*/
				btnNewButtonBusqueda.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						control.busqueda();
					}
				});
				btnNewButtonMonitoreo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						control.monitoreo();
					}
				});
				btnVenta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						control.agregarProductos(empleado);
					}
				});
		btnRecepcionMercancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.iniciaRecepcionMercancia(empleado);
			}
		});
	}

	public void muestra(ControlPrincipalEncargado control, Empleado empleado) {
		this.control = control;
		this.empleado = empleado;
		this.txtNombreEmpleado
				.setText(empleado.getNombre() + " " + empleado.getApellido());
		this.txtNivel.setText(empleado.getNivel() + ":");
		setVisible(true);

	}

	public void oculta() {
		setVisible(false);
	}
}

