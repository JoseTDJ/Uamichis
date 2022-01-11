package mx.uam.ayd.proyecto.presentacion.actualizaInventario;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

@SuppressWarnings("serial")
@Component
public class VentanaActualiza extends JFrame{
	DefaultTableModel modeloInventario = new DefaultTableModel();
	private JTable tabla_inventario;
	private JPanel contentPane;
	private JTextField textNivel;
	private JTextField textNombre;
	private JTextField textId;
	private ControlActualiza controlActualiza;
	private Empleado empleado;
	private JPanel panel_2;
	private JScrollPane scrollPaneInventario;
	private JTextField txtError;
	
	public VentanaActualiza() {
		setTitle("Actualizar Inventario");
		setBounds(100, 100, 507, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabla_inventario = new JTable(modeloInventario) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				if(vColIndex == 2 )
					return true;
				else
					return false;
			}
		};

		modeloInventario.addColumn("Nombre");
		modeloInventario.addColumn("Total\n Productos");
		modeloInventario.addColumn("Productos recibidos");
		modeloInventario.addColumn("Precio");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 473, 40);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		textNivel = new JTextField();
		textNivel.setEditable(false);
		textNivel.setBounds(10, 11, 138, 20);
		panel_1.add(textNivel);
		textNivel.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setEditable(false);
		textNombre.setColumns(10);
		textNombre.setBounds(156, 11, 208, 20);
		panel_1.add(textNombre);
		
		textId = new JTextField();
		textId.setEditable(false);
		textId.setColumns(10);
		textId.setBounds(374, 11, 89, 20);
		panel_1.add(textId);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(7, 62, 476, 225);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnInventario = new JButton("Inventario");
		btnInventario.setBackground(Color.CYAN);
		btnInventario.setBounds(10, 17, 113, 23);
		panel_2.add(btnInventario);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(10, 298, 473, 40);
		contentPane.add(panel_3);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setBackground(Color.RED);
		btnRegresar.setForeground(Color.BLACK);
		btnRegresar.setBounds(223, 10, 89, 23);
		panel_3.add(btnRegresar);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setEnabled(false);
		btnFinalizar.setBackground(Color.GREEN);
		btnFinalizar.setBounds(354, 10, 89, 23);
		panel_3.add(btnFinalizar);
		
		scrollPaneInventario = new JScrollPane();
		scrollPaneInventario.setBounds(10, 51, 450, 163);
		panel_2.add(scrollPaneInventario);
		
		txtError = new JTextField();
		scrollPaneInventario.setViewportView(txtError);
		txtError.setColumns(10);
		
		
		JLabel lblNewLabel = new JLabel("Actulizar inventario");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(97, 0, 288, 14);
		panel_2.add(lblNewLabel);
		
		
		/**
		 * Listener
		 */
		
		btnInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controlActualiza.obtenerProductos();
				scrollPaneInventario.setViewportView(tabla_inventario);
				btnFinalizar.setEnabled(true);
				btnInventario.setEnabled(false);
			}	
		});
		
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				limpiaTabla();
			}
		});
		
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmar = JOptionPane.showConfirmDialog(null, "Se actualizara el inventario");
				if(JOptionPane.OK_OPTION==confirmar) {
					actualiza();
					limpiaTabla();
					btnFinalizar.setEnabled(false);
					btnInventario.setEnabled(true);
					setVisible(false);
				}else
					JOptionPane.showMessageDialog(null, "Continue haciendo cambios");
				
			}
		});	
			
		
	}
	
	/**
	 * Metodo que se utiliza para limpiar la tabla
	 */
	public void limpiaTabla() {
		
		int filas = tabla_inventario.getRowCount();
		try {
			for (int i = 0; filas>i ; i++) {
				modeloInventario.removeRow(0);
			}
		} catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
	}

	/**
	 * Actualiza el numero de piezas de un producto
	 */
	
	protected void actualiza() {
		int nuevasPiezas = 0;               // Parametro por pasar con el nuevo numero de Piezas
		Object totalPiezas = null;          // Numero de piezas
		Object nuevo = null;			// Numero de piezas que se van a agregar al inventario
		String a,b,nombre;
		
		for (int i = 0; i < tabla_inventario.getRowCount(); i++) {
			totalPiezas = tabla_inventario.getValueAt(i, 1);		//Se obtiene el total de piezas del inventario
			nuevo = tabla_inventario.getValueAt(i, 2);              
			a = String.valueOf(totalPiezas);
			b = String.valueOf(nuevo);
			int positivo = Integer.parseInt(b);
			
			if( b == "0" || b.length() == 0  || b == "null" || positivo < 0) {
				nuevasPiezas = Integer.valueOf(a);
				nombre = (String) tabla_inventario.getValueAt(i, 0);
				controlActualiza.actualiza(nombre,nuevasPiezas);
			}else {
				nuevasPiezas = Integer.valueOf(a) + Integer.valueOf(b);
				nombre = (String) tabla_inventario.getValueAt(i, 0);
				controlActualiza.actualiza(nombre,nuevasPiezas);
			}
		}
	}



	/**
	 * Metodo que muestra la ventana. 
	 * @param controlActualiza
	 * @param empleado
	 */
	public void muestra(ControlActualiza controlActualiza, Empleado empleado) {
		
		this.controlActualiza = controlActualiza;
		this.empleado = empleado;
		this.textNombre.setText(empleado.getNombre() + " " + empleado.getApellido());
		this.textNivel.setText(empleado.getNivel());
		this.textId.setText(String.valueOf(empleado.getIdEmpleado()));
		setVisible(true);		

	}

	/**
	 * Metodo para mostrar mensaje de error.
	 * @param mensaje
	 */
	public void sinProductos(String mensaje) {
		txtError.setText(mensaje);
	}

	/**
	 * Metodo para llenar la tabla.
	 * @param producto
	 */
	public void agregaProductos(Producto producto) {
		String a[] = new String[4];
		a[0] = producto.getNombre();
		a[1] = String.valueOf(producto.getPiezas());
		a[2] = "0";
		a[3] = String.valueOf(producto.getPrecio());
		modeloInventario.addRow(a);
		tabla_inventario.setModel(modeloInventario);
		
	}
	
}