package mx.uam.ayd.proyecto.presentacion.principal.empleado;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.presentacion.reporteEmpleados.ControlNumReporte;
import java.awt.Font;
import java.awt.SystemColor;

@SuppressWarnings("serial")
@Component
public class VentanaPrincipalEmpleados extends JFrame {

	private JPanel contentPane;
	private ControlPrincipalEmpleados control;
	private Empleado empleado;
	private JTextField txtNombreEmpleado;
	private JTextField txtNivel;

	private JTextField txtIdEmpleado;

	
	private ControlNumReporte controlNumReporte;


	public VentanaPrincipalEmpleados() {
		setTitle("Farmapass");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 440);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(5, 5, 645, 43);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textHighlight);
		panel_1.setBounds(524, 50, 126, 342);
		panel_1.setLayout(null);
		contentPane.setLayout(null);
		panel.setLayout(null);

		txtNombreEmpleado = new JTextField();
		txtNombreEmpleado.setBackground(Color.WHITE);
		txtNombreEmpleado.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		txtNombreEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombreEmpleado.setEditable(false);
		txtNombreEmpleado.setBounds(205, 7, 316, 27);
		panel.add(txtNombreEmpleado);
		txtNombreEmpleado.setColumns(10);

		txtNivel = new JTextField();
		txtNivel.setBackground(Color.WHITE);
		txtNivel.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		txtNivel.setEditable(false);
		txtNivel.setBounds(10, 7, 175, 26);
		panel.add(txtNivel);
		txtNivel.setColumns(10);
		contentPane.add(panel);
		
		txtIdEmpleado = new JTextField();
		txtIdEmpleado.setBackground(Color.WHITE);
		txtIdEmpleado.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		txtIdEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdEmpleado.setEditable(false);
		txtIdEmpleado.setBounds(531, 7, 86, 26);
		panel.add(txtIdEmpleado);
		txtIdEmpleado.setColumns(10);
		contentPane.add(panel_1);
		
//		JButton btnReporteInventario = new JButton("Reporte Inventario"); /*  Botón REPORTE DE INVENTARIO */
//		btnReporteInventario.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				control.iniciaReporte(empleado);
//			}
//		});
//		btnReporteInventario.setBounds(49, 106, 321, 23);
//		panel_1.add(btnReporteInventario);
		
				JButton btnCierreVenta = new JButton("Inicia cierre de venta");
				btnCierreVenta.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
				btnCierreVenta.setBounds(195, 345, 196, 35);
				contentPane.add(btnCierreVenta);
				btnCierreVenta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						control.iniciaCierreVenta(empleado);
					}
				});
				btnCierreVenta.setForeground(new Color(255, 255, 255));
				btnCierreVenta.setBackground(new Color(255, 0, 0));
				
				JButton btnNuevoPedidoCliente = new JButton("Pedido cliente");
				btnNuevoPedidoCliente.setBounds(327, 206, 150, 34);
				contentPane.add(btnNuevoPedidoCliente);
				btnNuevoPedidoCliente.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
				btnNuevoPedidoCliente.setEnabled(false);
				
				JButton btnRecepcionMercancia = new JButton("Recepcion de Mercancia");
				btnRecepcionMercancia.setBounds(47, 206, 220, 34);
				contentPane.add(btnRecepcionMercancia);
				btnRecepcionMercancia.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
				
						JButton btnVenta = new JButton("Venta");
						btnVenta.setBounds(195, 142, 196, 34);
						contentPane.add(btnVenta);
						btnVenta.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
						
						JButton btnNewButtonBusqueda = new JButton("Busqueda");
						btnNewButtonBusqueda.setBounds(195, 84, 196, 34);
						contentPane.add(btnNewButtonBusqueda);
						btnNewButtonBusqueda.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
						btnNewButtonBusqueda.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								control.busqueda();
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
				btnNuevoPedidoCliente.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
//						control.agregaPedidoCliente(empleado); /*Todavia no funciona******************/
					}
				});

//				btnReporteVenta.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent arg0) {
//						control.reporteVenta(empleado);
//					}
//				});
	}
	

	public void muestra(ControlPrincipalEmpleados control, Empleado empleado) {
		this.control = control;
		this.empleado = empleado;
		this.txtNombreEmpleado
				.setText(empleado.getNombre() + " " + empleado.getApellido());

		this.txtNivel.setText("Cargo: " + empleado.getNivel());
		this.txtIdEmpleado.setText("ID: " + empleado.getIdEmpleado());

		setVisible(true);

	}
	

	public void oculta() {
		setVisible(false);
	}
}
