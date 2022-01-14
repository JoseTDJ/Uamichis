package mx.uam.ayd.proyecto.presentacion.venta;
/**
 * @author VictorSosa
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Producto;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;

@SuppressWarnings("serial")
@Component
public class VentanaProducto extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldCompuesto;
	private JTextField textFieldReceta;
	private JTextField textFieldUbicacion;
	private JTextField textFieldPrecio;
	private JTextField textFieldPiezas;
	private ControlVenta controlVenta;
	private Producto producto;

	public VentanaProducto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Caracteristicas del Produco");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));

		JLabel lblNewLabel_1 = new JLabel("Nombre");

		JLabel lblNewLabel_2 = new JLabel("Compuesto");

		JLabel lblNewLabel_3 = new JLabel("Receta");

		JLabel lblNewLabel_4 = new JLabel("Ubicación");

		JLabel lblNewLabel_5 = new JLabel("Precio");

		JLabel lblNewLabel_6 = new JLabel("Piezas");

		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		textFieldNombre.setColumns(10);

		textFieldCompuesto = new JTextField();
		textFieldCompuesto.setEditable(false);
		textFieldCompuesto.setColumns(10);

		textFieldReceta = new JTextField();
		textFieldReceta.setEditable(false);
		textFieldReceta.setColumns(10);

		textFieldUbicacion = new JTextField();
		textFieldUbicacion.setEditable(false);
		textFieldUbicacion.setColumns(10);

		textFieldPrecio = new JTextField();
		textFieldPrecio.setEditable(false);
		textFieldPrecio.setColumns(10);

		textFieldPiezas = new JTextField();
		textFieldPiezas.setEditable(false);
		textFieldPiezas.setColumns(10);

		JButton btnAgregar = new JButton("Agregar");
		JButton btnRegresar = new JButton("Regresar");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
								.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addGap(62)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldPiezas, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
								.addComponent(textFieldCompuesto, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
								.addComponent(textFieldNombre).addComponent(textFieldReceta)
								.addComponent(textFieldUbicacion).addComponent(textFieldPrecio)))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(103).addGroup(gl_contentPane
								.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(btnAgregar)
										.addPreferredGap(ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
										.addComponent(btnRegresar).addPreferredGap(ComponentPlacement.RELATED)))))
				.addContainerGap(65, GroupLayout.PREFERRED_SIZE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addComponent(lblNewLabel).addGap(47)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
								.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_2)
								.addComponent(textFieldCompuesto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_3)
								.addComponent(textFieldReceta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_4)
								.addComponent(textFieldUbicacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_5)
								.addComponent(textFieldPrecio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_6)
								.addComponent(textFieldPiezas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnAgregar)
								.addComponent(btnRegresar))));
		contentPane.setLayout(gl_contentPane);

		// Listener
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlVenta.agregarTabla(producto);
				controlVenta.total(producto.getPrecio());
			}
		});
		
		
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlVenta.termina();
			}
		});
	}
		
	//Muestra la ventana
	public void muestra(ControlVenta controlVenta) {
		this.controlVenta = controlVenta;
		setVisible(true);

	}

	// Metodo que llena los campos de la vista VentaProducto
	public void llena(Producto producto) {

		textFieldNombre.setText(producto.getNombre());
		textFieldCompuesto.setText(producto.getCompuesto());
		textFieldReceta.setText(producto.getReceta());
		textFieldUbicacion.setText(producto.getUbicacion());
		textFieldPrecio.setText(String.valueOf(producto.getPrecio()));
		textFieldPiezas.setText(String.valueOf(producto.getPiezas()));
		this.producto = producto;

	}

}
