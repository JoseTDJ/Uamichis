package mx.uam.ayd.proyecto.presentacion.pedidoRealizado;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoProveedor;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.informeInventario.VentanaRealizarUnPedido;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;

@Slf4j
@SuppressWarnings("serial")
@Component
public class VentanaPedidoRealizado extends JFrame {

	private JPanel contentPane;
	private JTextField textCargo;
	private JTextField textEmpleado;
	private JTextField textId;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JButton btnRegresar;
	private ControlPedidoRealizado controlPedidoRealizado;
	private Empleado empleado;
	private Producto producto;
	private PedidoProveedor pedido;
	private List<PedidoProveedor> pedidos;
	private Calendar fecha = new GregorianCalendar();
	private int anio = fecha.get(Calendar.YEAR);
	private int mes = fecha.get(Calendar.MONTH);
	private int dia = fecha.get(Calendar.DAY_OF_MONTH);
	private String fechaF = anio + "/" + mes +"/" + dia;
	JScrollPane scrollPane;
	DefaultTableModel modeloPedidos = new DefaultTableModel();
	private JTable tabla_Pedidos;
	private JTable table;
	/**
	 * Create the frame.
	 */
	public VentanaPedidoRealizado() {
		setTitle("FarmaPass");
		setBounds(100, 100, 450, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 11, 414, 37);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textCargo = new JTextField();
		textCargo.setEditable(false);
		textCargo.setBounds(10, 11, 86, 20);
		panel.add(textCargo);
		textCargo.setColumns(10);
		
		textEmpleado = new JTextField();
		textEmpleado.setEditable(false);
		textEmpleado.setBounds(107, 11, 230, 20);
		panel.add(textEmpleado);
		textEmpleado.setColumns(10);
		
		textId = new JTextField();
		textId.setEditable(false);
		textId.setBounds(346, 11, 58, 20);
		panel.add(textId);
		textId.setColumns(10);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 59, 414, 26);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblPedidosRealizados = new JLabel("Pedidos Realizados");
		lblPedidosRealizados.setBounds(137, 0, 133, 24);
		panel_1.add(lblPedidosRealizados);
		lblPedidosRealizados.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPedidosRealizados.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel_2 = new JPanel();
		panel_2.setBounds(10, 130, 414, 125);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 414, 125);
		panel_2.add(scrollPane);
		
		table = new JTable(modeloPedidos) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		table.setBounds(0, 1, 414, 124);
		panel_2.add(table);
		
		tabla_Pedidos = new JTable(modeloPedidos) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		
		modeloPedidos.addColumn("ID_PedidoProveedor");
		modeloPedidos.addColumn("Fecha de Creacion");
		modeloPedidos.addColumn("Estado");
		modeloPedidos.addColumn("Piezas");
		modeloPedidos.addColumn("$ Total");
		
		panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(10, 282, 414, 48);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(156, 14, 89, 23);
		panel_3.add(btnRegresar);
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlPedidoRealizado.regresar(empleado);
			}
		});
	}
	
	public void muestra(ControlPedidoRealizado controlPedidoRealizado, Empleado empleado, List<PedidoProveedor> pedidos) {
		this.controlPedidoRealizado = controlPedidoRealizado;	
		this.empleado = empleado;
		this.pedidos = pedidos;
		this.textCargo.setText(empleado.getNivel());
		this.textEmpleado.setText(empleado.getNombre() + " " + empleado.getApellido());
		this.textId.setText(Long.toString(empleado.getIdEmpleado()));
		limpiaTabla();
		for (PedidoProveedor pedidoProveedor : pedidos) {
			llenaTabla(pedidoProveedor);
			System.out.println("El pedido es: " + pedidoProveedor);
		}
		scrollPane.setViewportView(tabla_Pedidos);
		setVisible(true);
	}
	
	public void llenaTabla(PedidoProveedor pedido) {
		Object a[] = new Object[5];
		a[0] = pedido.getIdPedidoProveedor();
		a[1] = pedido.getFechaDeCreacion();
		a[2] = pedido.getEstado();
		a[3] = pedido.getTotalProductos();
		a[4] = pedido.getPrecioTotal();
		modeloPedidos.addRow(a);
		tabla_Pedidos.setModel(modeloPedidos);
		scrollPane.setViewportView(tabla_Pedidos);

		//this.pedido = pedido;
	}
	
	public void limpiaTabla() {
		int filas = tabla_Pedidos.getRowCount();
		try {
			for(int i = 0; filas > i; i++) {
				modeloPedidos.removeRow(0);
			}
		}catch(Exception e) {
			log.warn("## ERROR AL LIMPIAR LA TABLA: " + e);
			JOptionPane.showMessageDialog(null, "No se pudo limpiar la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/*public void llenarTabla() {
		controlPedidoRealizado.obtenerPedido();
	}*/
	
	public void agregarPedido(PedidoProveedor pedidoProveedor) {
		Object a[] = new Object[5];
		a[0] = pedidoProveedor.getIdPedidoProveedor();
		a[1] = pedidoProveedor.getFechaDeCreacion();
		a[2] = pedidoProveedor.getEstado();
		a[3] = pedidoProveedor.getTotalProductos();
		a[4] = pedidoProveedor.getPrecioTotal();
		modeloPedidos.addRow(a);
		tabla_Pedidos.setModel(modeloPedidos);
	}
	
	public void muestraDialogoConMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
	
	public void oculta() {
		setVisible(false);	
	}
}
