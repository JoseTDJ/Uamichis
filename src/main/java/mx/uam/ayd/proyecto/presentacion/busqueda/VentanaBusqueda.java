package mx.uam.ayd.proyecto.presentacion.busqueda;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;


@SuppressWarnings("serial")
@Component
public class VentanaBusqueda extends JFrame {

	private JPanel contentPane;
	
	private ControlBusqueda controlbusqueda;
	private JTextField textFieldCompuesto;
	private JTextField textFieldPrecio;
	private JTextField textFieldCantidad;
	private JTextField textFieldReceta;
	private JTextField textFieldUbicacion;
	private Producto producto;
	private JTextField textFieldNombre;
	


	//Metodo de presentacion de la ventanaBusqueda
	public VentanaBusqueda() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabelBuscar = new JLabel("Busqueda");
		lblNewLabelBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabelBuscar.setBounds(177, 24, 127, 29);
		contentPane.add(lblNewLabelBuscar);
		
		JButton btnNewButtonBuscar = new JButton("Buscar");
		btnNewButtonBuscar.setEnabled(false);
		btnNewButtonBuscar.setBounds(329, 64, 89, 29);
		contentPane.add(btnNewButtonBuscar);
		
		JTextField textFieldBuscar = new JTextField();
		textFieldBuscar.setText("");
		textFieldBuscar.setBounds(73, 66, 246, 25);
		contentPane.add(textFieldBuscar);
		textFieldBuscar.setColumns(10);
		
		JLabel lblNewLabelCompuesto = new JLabel("Compuesto");
		lblNewLabelCompuesto.setBounds(101, 164, 92, 19);
		contentPane.add(lblNewLabelCompuesto);
		
		JLabel lblNewLabelPrecio = new JLabel("Precio");
		lblNewLabelPrecio.setBounds(101, 194, 92, 22);
		contentPane.add(lblNewLabelPrecio);
		
		JLabel lblNewLabelCantidad = new JLabel("Cantidad");
		lblNewLabelCantidad.setBounds(101, 227, 92, 19);
		contentPane.add(lblNewLabelCantidad);
		
		JLabel lblNewLabelReceta = new JLabel("Receta");
		lblNewLabelReceta.setBounds(101, 257, 92, 18);
		contentPane.add(lblNewLabelReceta);
		
		JLabel lblNewLabelUbicacion = new JLabel("Ubicacion");
		lblNewLabelUbicacion.setBounds(94, 286, 68, 25);
		contentPane.add(lblNewLabelUbicacion);
		
		JButton btnNewButtonRegresar = new JButton("Regresar");
		
		btnNewButtonRegresar.setBounds(170, 355, 89, 23);
		contentPane.add(btnNewButtonRegresar);
		
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(169, 133, 217, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		textFieldNombre.setEditable(false);
		textFieldNombre.setText("");
		
	    
		
		textFieldCompuesto = new JTextField();
		textFieldCompuesto.setBounds(169, 163, 217, 20);
		contentPane.add(textFieldCompuesto);
		textFieldCompuesto.setColumns(10);
		textFieldCompuesto.setEditable(false);
		textFieldCompuesto.setText("");
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setColumns(10);
		textFieldPrecio.setBounds(169, 194, 217, 20);
		contentPane.add(textFieldPrecio);
		textFieldPrecio.setEditable(false);
		textFieldPrecio.setText("");

		
		textFieldCantidad = new JTextField();
		textFieldCantidad.setColumns(10);
		textFieldCantidad.setBounds(169, 226, 217, 20);
		contentPane.add(textFieldCantidad);
		textFieldCantidad.setEditable(false);
		textFieldCantidad.setText("");

		
		textFieldReceta = new JTextField();
		textFieldReceta.setColumns(10);
		textFieldReceta.setBounds(169, 256, 217, 20);
		contentPane.add(textFieldReceta);
		textFieldReceta.setEditable(false);
		textFieldReceta.setText("");

		
		textFieldUbicacion = new JTextField();
		textFieldUbicacion.setColumns(10);
		textFieldUbicacion.setBounds(169, 286, 217, 23);
		contentPane.add(textFieldUbicacion);
		textFieldUbicacion.setEditable(false);
		textFieldUbicacion.setText("");

		JLabel lblNewLabel = new JLabel("Farmapass");
		lblNewLabel.setBounds(10, 11, 83, 19);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabelNombre = new JLabel("Nombre");
		lblNewLabelNombre.setBounds(101, 136, 46, 14);
		contentPane.add(lblNewLabelNombre);
		
		//Accion del boton Buscar
				btnNewButtonBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					//Limpio los campos inicalmente para un nueva busqueda
						textFieldNombre.setText("");
					    textFieldCompuesto.setText("");
						textFieldCantidad.setText("");
						textFieldReceta.setText("");
						textFieldUbicacion.setText("");
						textFieldPrecio.setText("");
						
						controlbusqueda.buscarProducto(textFieldBuscar.getText());
					}
				});
				
				textFieldBuscar.addKeyListener(new KeyAdapter() {
					public void keyReleased(KeyEvent e) {
						btnNewButtonBuscar.setEnabled(textFieldBuscar.getText().length() != 0);

					}

				});
		 //Accion del boton Regresar
				btnNewButtonRegresar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						
					}
				});
	}

	//Metodo que muestra la venta Busqueda
	public void muestra(ControlBusqueda controlbusqueda) {
		this.controlbusqueda = controlbusqueda;
		setVisible(true);
	}
	
	//Metodo que llena los campos de la ventan Busqueda
   public void llena(Producto producto) {
		
		textFieldNombre.setText(producto.getNombre());
		textFieldCompuesto.setText(producto.getCompuesto());
		textFieldCantidad.setText(String.valueOf(producto.getPiezas()));
		textFieldReceta.setText(producto.getReceta());
		textFieldUbicacion.setText(producto.getUbicacion());
		textFieldPrecio.setText(String.valueOf(producto.getPrecio()));
		this.producto=producto;	
	}
   
   
   public void muestraDialogoConMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
}

