package mx.uam.ayd.proyecto.presentacion.cierreVenta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoCliente;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
@Component
public class VentanaCierreVenta extends JFrame {

	private JPanel contentPane;
	private JButton btnFinalizar;
	private ControlCierreVenta control;

	DefaultTableModel modeloInventario = new DefaultTableModel();
	DefaultTableModel modeloVenta = new DefaultTableModel();
	DefaultTableModel modeloPedido = new DefaultTableModel();
	private JTable tabla_inventario;
	private JTable tabla_venta;
	private JTable tabla_pedidos;
	private JTextField txtNombreEmpleado;
	private Empleado empleado;
	private JTextField txtNivel;

	private Calendar fecha = new GregorianCalendar();
	private int ano = fecha.get(Calendar.YEAR);
	private int mes = fecha.get(Calendar.MONTH);
	private int dia = fecha.get(Calendar.DAY_OF_MONTH);
	private String fechaF = ano + "/" + mes + "/" + dia;
	private JTextField txtError;

	public VentanaCierreVenta() {
		setTitle("Farmapass - Cierre de Venta");
		setBounds(100, 100, 730, 486);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		tabla_inventario = new JTable(modeloInventario) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		;

		modeloInventario.addColumn("Nombre");
		modeloInventario.addColumn("Compuesto");
		modeloInventario.addColumn("Total\n Productos");
		modeloInventario.addColumn("Precio");
		modeloInventario.addColumn("Receta");

		tabla_venta = new JTable(modeloVenta) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		;
		modeloVenta.addColumn("Fecha");
		modeloVenta.addColumn("Total de\n Productos");
		modeloVenta.addColumn("Precio total");

		tabla_pedidos = new JTable(modeloPedido) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		;
		modeloPedido.addColumn("Nombre\n Cliente");
		modeloPedido.addColumn("Fecha");
		modeloPedido.addColumn("Telefono");
		modeloPedido.addColumn("Producto(s)");
		modeloPedido.addColumn("Total");

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(15, 162, 554, 135);
		panel.setLayout(null);

		JScrollPane scrollPaneCierreVenta = new JScrollPane();
		scrollPaneCierreVenta.setBackground(Color.WHITE);
		scrollPaneCierreVenta.setBounds(26, 0, 504, 135);
		panel.add(scrollPaneCierreVenta);

		tabla_inventario = new JTable(
				new DefaultTableModel(new Object[][] {}, new String[] { "Nombre", "Compuesto", "Precio" }));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(5, 5, 701, 44);
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));

		txtNombreEmpleado = new JTextField();
		txtNombreEmpleado.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		txtNombreEmpleado.setBackground(Color.WHITE);
		txtNombreEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombreEmpleado.setEditable(false);
		txtNombreEmpleado.setColumns(10);
		txtNombreEmpleado.setBounds(212, 10, 440, 24);
		panel_1.add(txtNombreEmpleado);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(21, 112, 548, 40);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(579, 59, 127, 380);
		panel_4.setBackground(SystemColor.textHighlight);
		
				btnFinalizar = new JButton("Finalizar");
				btnFinalizar.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
				btnFinalizar.setBounds(327, 391, 104, 30);
				btnFinalizar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						control.cerrarSesion(empleado);
					}
				});
		
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
				btnCancelar.setBounds(445, 391, 104, 30);
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						control.cancelaCierreVenta(empleado);
						limpiarTablas();
						
					}
				});
		
		//Boton para generar PDF de ventas---------------
		JButton btnGenera = new JButton("Ventas PDF");
		btnGenera.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		btnGenera.setBounds(119, 391, 137, 30);
		btnGenera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.generaPDF(tabla_venta);
			}
		});
		
		txtError = new JTextField();
		txtError.setBackground(Color.WHITE);
		txtError.setHorizontalAlignment(SwingConstants.CENTER);
		txtError.setEditable(false);
		txtError.setBounds(10, 0, 464, 135);
		panel.add(txtError);
		txtError.setColumns(10);

		txtNivel = new JTextField();
		txtNivel.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		txtNivel.setBackground(Color.WHITE);
		txtNivel.setEditable(false);
		txtNivel.setColumns(10);
		txtNivel.setBounds(31, 10, 146, 24);
		panel_1.add(txtNivel);
		panel_4.setLayout(null);
		panel_3.setLayout(null);

		JButton btnInventario = new JButton("Inventario");
		btnInventario.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		btnInventario.setBounds(42, 10, 120, 23);
		panel_3.add(btnInventario);

		JButton btnVentas = new JButton("Ventas");
		btnVentas.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		btnVentas.setBounds(226, 10, 120, 23);
		btnVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTablas();
				control.obtenerVentasDia(fechaF);
				scrollPaneCierreVenta.setViewportView(tabla_venta);

			}
		});
		panel_3.add(btnVentas);

		btnInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTablas();
				control.obtenerProductos();
				scrollPaneCierreVenta.setViewportView(tabla_inventario);
			}
		});

		JButton btnPedidos = new JButton("Pedidos");
		btnPedidos.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		btnPedidos.setBounds(401, 10, 120, 23);
		btnPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTablas();
				control.obtenerPedidosClienteDelDia(fechaF);
				scrollPaneCierreVenta.setViewportView(tabla_pedidos);
			}
		});
		panel_3.add(btnPedidos);
		contentPane.setLayout(null);
		contentPane.add(btnFinalizar);
		contentPane.add(btnCancelar);
		contentPane.add(btnGenera);
		contentPane.add(panel_1);
		contentPane.add(panel_3);
		contentPane.add(panel);
		contentPane.add(panel_4);
		
				JLabel lblCierreVenta = new JLabel("DETALLE DE CIERRE DE VENTA");
				lblCierreVenta.setBounds(129, 70, 350, 21);
				contentPane.add(lblCierreVenta);
				lblCierreVenta.setFont(new Font("Josefin Sans", Font.BOLD, 16));
				lblCierreVenta.setBackground(Color.WHITE);
				lblCierreVenta.setHorizontalAlignment(SwingConstants.CENTER);
				
				JLabel lblNewLabel = new JLabel("");
				lblNewLabel.setIcon(new ImageIcon(VentanaCierreVenta.class.getResource("/mx/uam/ayd/proyecto/presentacion/cierreVenta/pdficon.png")));
				lblNewLabel.setBounds(47, 383, 48, 44);
				contentPane.add(lblNewLabel);
				
				JLabel lblNewLabel_1 = new JLabel("");
				lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\yessa\\Desktop\\Farmapass\\src\\main\\java\\mx\\uam\\ayd\\proyecto\\presentacion\\cierreVenta\\pdficon.png"));
				lblNewLabel_1.setBounds(47, 317, 48, 44);
				contentPane.add(lblNewLabel_1);
				
				//Botón que genera pdf de inventario -------------------------
				JButton btnPdfInventario = new JButton("Inventario PDF"); 
				btnPdfInventario.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
				btnPdfInventario.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						control.generaInventarioPdf(tabla_inventario);
					}
				});
				btnPdfInventario.setBounds(119, 326, 153, 30);
				contentPane.add(btnPdfInventario);
	}

	public void muestra(ControlCierreVenta control, Empleado empleado) {
		this.control = control;
		this.empleado = empleado;
		this.txtNombreEmpleado
				.setText(empleado.getNombre() + " " + empleado.getApellido());
		this.txtNivel.setText(empleado.getNivel());
		setVisible(true);
	}

	public void agregaVentas(Venta venta, int size) {
		String a[] = new String[3];
		a[0] = venta.getFecha();
		a[1] = String.valueOf(size);
		a[2] = String.valueOf(venta.getTotal());
		modeloVenta.addRow(a);
		tabla_venta.setModel(modeloVenta);
	}

	public void agregaProductos(Producto producto) {
		String a[] = new String[5];
		a[0] = producto.getNombre();
		a[1] = producto.getCompuesto();
		a[2] = String.valueOf(producto.getPiezas());
		a[3] = String.valueOf(producto.getPrecio());
		a[4] = producto.getReceta();
		modeloInventario.addRow(a);
		tabla_inventario.setModel(modeloInventario);
		RowsRenderer rr = new RowsRenderer(2);
		tabla_inventario.setDefaultRenderer(Object.class, rr);

	}

	public void agregarPedido(PedidoCliente pedidoCliente, List<Producto> productos, List<Cliente> cliente) {
		String a[] = new String[5];
		a[0] = cliente.get(0).getNombre() + " " + cliente.get(0).getApellidos();
		a[1] = pedidoCliente.getFechaDeCreacion();
		a[2] = cliente.get(0).getTelefono();
		a[3] = "";
		for (Producto producto: productos) {
			a[3] += producto.getNombre() +"\n";
		}
		a[4] = String.valueOf(pedidoCliente.getPrecioTotal());
		modeloPedido.addRow(a);
		tabla_pedidos.setModel(modeloPedido);
		tabla_pedidos.getColumnModel().getColumn(3).setCellRenderer(
			      new TextAreaRenderer());
		tabla_pedidos.getColumnModel().getColumn(0).setCellRenderer(
			      new TextAreaRenderer());
		tabla_pedidos.setRowHeight(0, 50);
	    tabla_pedidos.setRowHeight(5, 50);
	}
	
	private void limpiarTablas() {
		if (tabla_venta.getRowCount() > 0) {
			int filas = tabla_venta.getRowCount();
			try {
				for (int i = 0; filas > i; i++) {
					modeloVenta.removeRow(0);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al limpiar la tabla venta.");
			}
		} else if (tabla_inventario.getRowCount() > 0) {
			int filas = tabla_inventario.getRowCount();
			try {
				for (int i = 0; filas > i; i++) {
					modeloInventario.removeRow(0);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al limpiar la tabla inventario.");
			}
		} else if (tabla_pedidos.getRowCount() > 0) {
			int filas = tabla_pedidos.getRowCount();
			try {
				for (int i = 0; filas > i; i++) {
					modeloPedido.removeRow(0);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al limpiar la tabla pedido.");
			}
		}

	}

	public void oculta() {
		setVisible(false);
	}

	public void sinProductos(String mensaje) {
		txtError.setText(mensaje);
		
	}
}
