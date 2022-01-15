package mx.uam.ayd.proyecto.presentacion.venta;

/**
 * VictorSosa
 * 
 */

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.Recarga;
import java.awt.Color;
import java.awt.SystemColor;

@SuppressWarnings("serial")
@Component
public class VentanaVenta extends JFrame {

	private JPanel contentPane;
	private JTextField txtIngresaProducto;
	private JTextField textTotal;
	private ControlVenta controlVenta;
	private JTable table;
	float total = 0;
	Producto producto;
	private Empleado empleado;
	private DefaultTableModel modelo = new DefaultTableModel() {
		@Override 
		public boolean isCellEditable(int row, int column) { 
			return false; 
			} 
	};
	private JTextField txtNivel;
	private JTextField txtNombreEmpleado;
	private JTextField txtNumEmpleado;
	private ControlVenta control;
	private JTextField txtNombreCliente; //JTextField que se usara para mostrar el nombre de un cliente registrado
	Cliente cliente;
	

	public VentanaVenta() {
		setTitle("Farmapass - Venta");
		setResizable(false);
		setBounds(100, 100, 675, 438);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		btnBuscar.setEnabled(false);
		btnBuscar.setBounds(208, 48, 89, 26);
		contentPane.add(btnBuscar);

		txtIngresaProducto = new JTextField();
		txtIngresaProducto.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		txtIngresaProducto.setText("");
		txtIngresaProducto.setBounds(21, 49, 171, 25);
		contentPane.add(txtIngresaProducto);
		txtIngresaProducto.setColumns(10);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		lblTotal.setBounds(415, 263, 51, 23);
		contentPane.add(lblTotal);

		textTotal = new JTextField();
		textTotal.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		textTotal.setBackground(Color.WHITE);
		textTotal.setEditable(false);
		textTotal.setBounds(473, 260, 110, 26);
		contentPane.add(textTotal);
		textTotal.setColumns(10);

		JButton btnCobrar = new JButton("Cobrar");
		btnCobrar.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		btnCobrar.setBounds(473, 308, 89, 33);
		contentPane.add(btnCobrar);

		JButton btnQuitarDeLista = new JButton("Quitar de lista");
		btnQuitarDeLista.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		btnQuitarDeLista.setBounds(154, 308, 163, 33);
		contentPane.add(btnQuitarDeLista);

		JButton btnRecarga = new JButton("Recarga");
		btnRecarga.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		btnRecarga.setBounds(31, 308, 96, 33);
		contentPane.add(btnRecarga);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 93, 562, 150);
		contentPane.add(scrollPane);

		table = new JTable(modelo);
		

		modelo.addColumn("Nombre");
		modelo.addColumn("Compuesto");
		modelo.addColumn("Precio del Prodcuto");
		modelo.addColumn("Seleccionar");

		scrollPane.setViewportView(table);
		
		txtNivel = new JTextField();
		txtNivel.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		txtNivel.setBackground(Color.WHITE);
		txtNivel.setEditable(false);
		txtNivel.setBounds(21, 10, 155, 28);
		contentPane.add(txtNivel);
		txtNivel.setColumns(10);
		
		txtNombreEmpleado = new JTextField();
		txtNombreEmpleado.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		txtNombreEmpleado.setBackground(Color.WHITE);
		txtNombreEmpleado.setEditable(false);
		txtNombreEmpleado.setColumns(10);
		txtNombreEmpleado.setBounds(190, 10, 290, 28);
		contentPane.add(txtNombreEmpleado);
		
		txtNumEmpleado = new JTextField();
		txtNumEmpleado.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		txtNumEmpleado.setBackground(Color.WHITE);
		txtNumEmpleado.setEditable(false);
		txtNumEmpleado.setColumns(10);
		txtNumEmpleado.setBounds(490, 10, 163, 28);
		contentPane.add(txtNumEmpleado);
		
		txtNombreCliente = new JTextField();
		txtNombreCliente.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		txtNombreCliente.setBackground(Color.WHITE);
		txtNombreCliente.setEditable(false);
		txtNombreCliente.setBounds(307, 49, 276, 25);
		contentPane.add(txtNombreCliente);
		txtNombreCliente.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(593, 48, 68, 352);
		contentPane.add(panel);

		// -------------LISTENER-------------------
		
		//Listener para buscar un articulo
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlVenta.buscarProducto(txtIngresaProducto.getText());
				txtIngresaProducto.setText("");

			}
		});
		
		//Listener para habilitar el boton de buscar
		txtIngresaProducto.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				btnBuscar.setEnabled(txtIngresaProducto.getText().length() != 0);

			}

		});
		
		
		btnQuitarDeLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = table.getSelectedRow();
				int y = table.getSelectedColumn();
				
				try {
					Object a = table.getValueAt(x, y - 1);
					float r = Float.parseFloat(a.toString());
					total -= r;
					textTotal.setText(String.valueOf(total));
					modelo.removeRow(x);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun producto para quitar");
				}
				

			}
		});

		btnCobrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlVenta.muentraCobro(Float.parseFloat(textTotal.getText()), empleado);
				btnBuscar.setEnabled(false);
			}
			
		});
		limpia();

		table.addComponentListener(new ComponentListener() {
			@Override
			public void componentResized(ComponentEvent e) {
				if(table.getRowCount() == 0){
					btnQuitarDeLista.setEnabled(false);
					btnCobrar.setEnabled(false);
				}else {
					btnQuitarDeLista.setEnabled(true);
					btnCobrar.setEnabled(true);
				}
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		btnRecarga.addActionListener(new ActionListener() {	
			
			public void actionPerformed(ActionEvent e) {
				controlVenta.recarga(empleado);
			}
		});
	

	}

	// Muestra la ventana
	public void muestra(ControlVenta control, Empleado empleado) {
		String id = String.valueOf(empleado.getIdEmpleado());
		this.control = control;
		this.empleado = empleado;
		this.txtNombreEmpleado
				.setText("Nombre: " + empleado.getNombre() + " " + empleado.getApellido());
		this.txtNivel.setText("Cargo: " + empleado.getNivel());
		this.txtNumEmpleado.setText("ID: " +id);
		setVisible(true);
		this.controlVenta = control;
		this.empleado = empleado;
		txtNombreCliente.setText(""); //se limpia el campo del nombre cliente
		txtNombreCliente.setVisible(false);
		limpia();
		
		setVisible(true);

	}
	
	/**
	 * HU-09 Metodo que muestra la ventana con el nombre del cliente
	 * @param control
	 * @param empleado
	 * @param cliente
	 */
	public void muestraVenta(ControlVenta control, Empleado empleado, Cliente cliente) {
		String id = String.valueOf(empleado.getIdEmpleado());
		this.control = control;
		this.empleado = empleado;
		this.cliente = cliente;
		this.txtNombreEmpleado.setText("Nombre: " + empleado.getNombre() + " " + empleado.getApellido());
		this.txtNivel.setText("Cargo: " + empleado.getNivel());
		this.txtNumEmpleado.setText("ID: " +id);
		this.txtNombreCliente.setText("Cliente: " + cliente.getNombre() + " " + cliente.getApellidos());
		setVisible(true);
		this.controlVenta = control;
		this.empleado = empleado;
		limpia();
		txtNombreCliente.setVisible(true);
		setVisible(true);
	}
	
	// Metodos para la Ventana
	public void llenaTabla(Producto producto) {
		String a[] = new String[4];
		a[0] = producto.getNombre();
		a[1] = producto.getCompuesto();
		a[2] = String.valueOf(producto.getPrecio());
		a[3] = "Selecciona para quitar ";
		modelo.addRow(a);
		table.setModel(modelo);
		this.producto = producto;

	}

	public void muestraDialogoConMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
	
	/**
	 * Metodo que calcula el total a pagar
	 * donde si la venta es a un cliente registrado se le aplica el 15% de descuento
	 * al precio total si es que este es mayor o igual a $80, en otro caso si el cliente
	 * no esta registrado a este no se le hara un descuento
	 * @param precio precio del producto a vender
	 */
	public void textTotal(float precio) {

		total += precio;
		
		if(txtNombreCliente.getText().equals("")) {
			textTotal.setText(String.valueOf(total));
		} else {
			if(total >= 80) { //se aplica el 15% de descuento
				textTotal.setText(String.valueOf(total*0.85));
			} else {
				textTotal.setText(String.valueOf(total));
			}
		}
		
	}

	/**
	 * Metodo que recorre la tabla
	 * 
	 * @return venta de tipo lista
	 */
	public List<Producto> recorrerTabla() {
		List<Producto> lista = new ArrayList<>();
		Producto producto;
		
		try {
			for (int i = 0; i < table.getRowCount(); i++) {
				producto = controlVenta.obtenerProducto((String) table.getValueAt(i, 0));
				lista.add(producto);
			}
		} catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer la tabla.");
        }	

		return lista;

	}
	
	public void limpia() {
		textTotal.setText("");
		txtIngresaProducto.setText("");
		total=0;
		int filas = table.getRowCount();
		try {
			for (int i = 0; filas>i ; i++) {
				modelo.removeRow(0);
			}
		} catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }	
	}
	/**
	 * Agrega los datos a la tabla de venta y obtiene el total
	 * @param recarga
	 */
	public void agregaRecarga(Recarga recarga) {
		// TODO Auto-generated method stub
		String a[] = new String[4];
		a[0] = recarga.getCompania();
		a[1] = recarga.getCelular();
		a[2] = String.valueOf(recarga.getMonto());
		a[3] = "Selecciona para quitar ";
		modelo.addRow(a);
		table.setModel(modelo);
		total = total + recarga.getMonto();
		textTotal.setText(String.valueOf(total));
		
	}
}

