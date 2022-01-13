package mx.uam.ayd.proyecto.presentacion.venta;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import java.awt.SystemColor;
import javax.swing.UIManager;

@SuppressWarnings("serial")
@Component
public class VentanaConfirmaRecarga extends JFrame {

	private JPanel contentPane;
	
	private JTextField txtNumEmpleado;
	private JTextField numTelefono;
	private JTextField txtNombreEmpleado;
	private JTextField txtNivel;
	private Empleado empleado;
	private ControlConfirmaRecarga control;
	private String numTel;
	private int montoS;
	private String compS;
	private JTextField Compania;
	private JTextField Monto;
	private JPanel panel;
	/**
	 * Create the frame.
	 */
	public VentanaConfirmaRecarga() {
		setResizable(false);
		setTitle("Farmapass - Confirmar recarga");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 680, 508);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelTelefono = new JLabel("Número:");
		labelTelefono.setFont(new Font("Josefin Sans", Font.BOLD, 18));
		labelTelefono.setBounds(131, 126, 94, 28);
		contentPane.add(labelTelefono);
		
		JLabel labelMonto = new JLabel("Monto:");
		labelMonto.setFont(new Font("Josefin Sans", Font.BOLD, 18));
		labelMonto.setBounds(131, 261, 94, 28);
		contentPane.add(labelMonto);
		
		JLabel labelComp = new JLabel("Compañía:");
		labelComp.setFont(new Font("Josefin Sans", Font.BOLD, 18));
		labelComp.setBounds(131, 193, 108, 28);
		contentPane.add(labelComp);
		
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		btnNewButton.setBounds(315, 385, 140, 35);
		contentPane.add(btnNewButton);
		
		
		/**
		 * Verifica que los campos esten llenos correctamente
		 * Si son correctos, llama al servicio de venta
		 */
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numTel = numTelefono.getText();
				montoS = Integer.parseInt(Monto.getText());
				compS = Compania.getText();
				control.ingresaDatos(numTel, montoS, compS);
				setVisible(false);
				JOptionPane.showMessageDialog(null, "Recarga realizada con éxito", "Recarga exitosa", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		JButton btnRegresar = new JButton("Regresar"); /*Boton "Regresar" -> Regresa a la pantalla anterior Ventana Recarga*/
		btnRegresar.setForeground(Color.WHITE);
		btnRegresar.setBackground(Color.RED);
		btnRegresar.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		btnRegresar.setBounds(85, 383, 140, 35);
		contentPane.add(btnRegresar);
		
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.iniciaRecarga(empleado);
				
			}
		});
			
		
		numTelefono = new JTextField();
		numTelefono.setBackground(Color.WHITE);
		numTelefono.setEditable(false);
		numTelefono.setFont(new Font("Josefin Sans", Font.PLAIN, 18));
		numTelefono.setBounds(292, 127, 163, 28);
		contentPane.add(numTelefono);
		numTelefono.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Información:");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Josefin Sans", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(78, 68, 420, 28);
		contentPane.add(lblNewLabel);
		
		txtNivel = new JTextField();
		txtNivel.setBackground(Color.WHITE);
		txtNivel.setEditable(false);
		txtNivel.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		txtNivel.setColumns(10);
		txtNivel.setBounds(10, 10, 177, 28);
		contentPane.add(txtNivel);
		
		txtNombreEmpleado = new JTextField();
		txtNombreEmpleado.setBackground(Color.WHITE);
		txtNombreEmpleado.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombreEmpleado.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		txtNombreEmpleado.setEditable(false);
		txtNombreEmpleado.setColumns(10);
		txtNombreEmpleado.setBounds(197, 10, 290, 28);
		contentPane.add(txtNombreEmpleado);
		
		txtNumEmpleado = new JTextField();
		txtNumEmpleado.setBackground(Color.WHITE);
		txtNumEmpleado.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
		txtNumEmpleado.setEditable(false);
		txtNumEmpleado.setColumns(10);
		txtNumEmpleado.setBounds(500, 10, 163, 28);
		contentPane.add(txtNumEmpleado);
		
		Compania = new JTextField();
		Compania.setBackground(Color.WHITE);
		Compania.setEditable(false);
		Compania.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Compania.setColumns(10);
		Compania.setBounds(292, 191, 163, 28);
		contentPane.add(Compania);
		
		Monto = new JTextField();
		Monto.setBackground(Color.WHITE);
		Monto.setEditable(false);
		Monto.setFont(new Font("Javanese Text", Font.PLAIN, 18));
		Monto.setColumns(10);
		Monto.setBounds(292, 264, 163, 28);
		contentPane.add(Monto);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(534, 48, 126, 418);
		contentPane.add(panel);
	}
	
	/**
	 * MÃ©todo que muestra los datos obtenidos
	 * anteriormente de la recarga
	 * @param control
	 * @param empleado
	 * @param telefono
	 * @param mon
	 * @param comp
	 */
	public void muestra(ControlConfirmaRecarga control, Empleado empleado, String telefono, int mon, String comp) {
		String id = String.valueOf(empleado.getIdEmpleado());
		this.control = control;
		this.empleado = empleado;
		this.txtNombreEmpleado
				.setText("Nombre: " + empleado.getNombre() + " " + empleado.getApellido());
		this.txtNivel.setText("Cargo: " + empleado.getNivel());
		this.txtNumEmpleado.setText("ID: " +id);
		this.Compania.setText(comp);;
		this.Monto.setText(Integer.toString(mon));
		this.numTelefono.setText(telefono);
		setVisible(true);
	}

	/**
	 * MÃ©todo que oculta la ventana de 
	 * confirmar recarga
	 */
	public void oculta() {
		// TODO Auto-generated method stub
		this.numTelefono.setText("");
		this.Compania.setText("");
		this.Monto.setText("");
		setVisible(false);
		
	}	
	
	
}
