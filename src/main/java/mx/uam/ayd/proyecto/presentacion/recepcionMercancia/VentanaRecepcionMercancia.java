package mx.uam.ayd.proyecto.presentacion.recepcionMercancia;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.principal.ControlPrincipal;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
@Component
public class VentanaRecepcionMercancia extends JFrame {

	private JPanel contentPane;
	private JButton btnEnviar;
	private ControlRecepcionMercancia control;
	private JTextField txtFechaRecepcion;
	
	private Calendar fecha = new GregorianCalendar();
	private int ano = fecha.get(Calendar.YEAR);
	private int mes = fecha.get(Calendar.MONTH);
	private int dia = fecha.get(Calendar.DAY_OF_MONTH);
	private String fechaRecepcion = ano + "/" + mes + "/" + dia;
	private JTextField txtNombreEmpleado;
	private JTextField txtNivel;
	private Empleado empleado;
	
	DefaultTableModel modeloRecepcion = new DefaultTableModel();
	private JTable tabla_recepcionPedidoProvedor;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTextField txtLeyenda;
	
	public VentanaRecepcionMercancia() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabla_recepcionPedidoProvedor = new JTable(modeloRecepcion) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				if(vColIndex == 2) {
					return true;
				} else {
					return false;
				}
			}
		};
		;

		modeloRecepcion.addColumn("Nombre");
		modeloRecepcion.addColumn("Piezas a recibir");
		modeloRecepcion.addColumn("Recibidos");
		modeloRecepcion.addColumn("Precio");
		modeloRecepcion.addColumn("Selecciona");
		
		

		JLabel lblCierreVenta = new JLabel("RECEPCION DE MERCANCIA");
		lblCierreVenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblCierreVenta.setBounds(0, 50, 434, 16);
		contentPane.add(lblCierreVenta);

		btnEnviar = new JButton("Finalizar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}

			private void leerTabla() {
				List<Producto> lista = new ArrayList<>();
				List<Integer> listaPiezas = new ArrayList<>();
				Producto producto;
				try {
					for (int i = 0; i < tabla_recepcionPedidoProvedor.getRowCount(); i++) {
						producto = control.obtenerProducto((String) tabla_recepcionPedidoProvedor.getValueAt(i, 0));
						lista.add(producto);
						listaPiezas.add(Integer.parseInt((String) tabla_recepcionPedidoProvedor.getValueAt(i, 2)));
					}
					if(control.finalizarRecepcion(lista, listaPiezas)) {
						JOptionPane.showMessageDialog(null, "Se registro con exito!");
						
					} else {
						JOptionPane.showMessageDialog(null, "Error al finalizar.");
					}
				} catch (Exception e) {
		            JOptionPane.showMessageDialog(null, "Error al leer la tabla.");
		        }	

				
			}
		});
		btnEnviar.setForeground(new Color(0, 0, 0));
		btnEnviar.setBounds(335, 267, 89, 23);
		contentPane.add(btnEnviar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.cancelarRecepcion(empleado);
			}
		});
		btnCancelar.setBounds(236, 267, 89, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel = new JLabel("Fecha de recepción:");
		lblNewLabel.setBounds(10, 74, 98, 14);
		contentPane.add(lblNewLabel);
		
		panel = new JPanel();
		panel.setBounds(10, 124, 414, 132);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		txtLeyenda = new JTextField();
		
		
		scrollPane = new JScrollPane();
		
		
		txtFechaRecepcion = new JTextField();
		txtFechaRecepcion.setEditable(false);
		txtFechaRecepcion.setBounds(10, 93, 98, 20);
		contentPane.add(txtFechaRecepcion);
		txtFechaRecepcion.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.setBounds(10, 5, 414, 34);
		contentPane.add(panel_1);
		
		txtNombreEmpleado = new JTextField();
		txtNombreEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombreEmpleado.setEditable(false);
		txtNombreEmpleado.setColumns(10);
		txtNombreEmpleado.setBounds(106, 6, 298, 22);
		panel_1.add(txtNombreEmpleado);
		
		txtNivel = new JTextField();
		txtNivel.setEditable(false);
		txtNivel.setColumns(10);
		txtNivel.setBounds(10, 7, 86, 20);
		panel_1.add(txtNivel);
		
		JButton btnQuitar = new JButton("Quitar producto");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = tabla_recepcionPedidoProvedor.getSelectedRow();
				try {
					modeloRecepcion.removeRow(x);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun producto para quitar");
				}
			}
		});
		btnQuitar.setBounds(10, 267, 145, 23);
		contentPane.add(btnQuitar);
	}

	public void muestra(ControlRecepcionMercancia control, Empleado empleado) {
		this.control = control;
		this.empleado = empleado;
		this.txtNombreEmpleado
				.setText(empleado.getNombre() + " " + empleado.getApellido());
		this.txtNivel.setText(empleado.getNivel());
		this.txtFechaRecepcion.setText(fechaRecepcion);
		llenarTabla();
		setVisible(true);
	}

	private void llenarTabla() {
		control.obtenerPedido();
	}

	public void mostrarLeyendaSinPedidos() {
		btnEnviar.disable();
		panel.add(txtLeyenda);
		txtLeyenda.setColumns(10);
		txtLeyenda.setHorizontalAlignment(SwingConstants.CENTER);
		txtLeyenda.setText("No hay pedidos pendientes");
		
	}

	public void agregarDatosTabla(List<Producto> productos, List<Integer> listaPiezas) {
		panel.add(scrollPane);
		int contador = 0;
		for (Producto producto : productos) {
			String a[] = new String[5];
			a[0] = producto.getNombre();
			a[1] = String.valueOf(listaPiezas.get(contador));
			a[2] = "";
			a[3] = String.valueOf(producto.getPrecio());
			a[4] = "Selecciona para quitar";
			modeloRecepcion.addRow(a);
			tabla_recepcionPedidoProvedor.setModel(modeloRecepcion);
		}
		
	}

	public void oculta() {
		setVisible(false);
		
	}
}
