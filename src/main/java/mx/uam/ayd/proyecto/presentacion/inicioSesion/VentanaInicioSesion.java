package mx.uam.ayd.proyecto.presentacion.inicioSesion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.reporteEmpleados.ControlNumReporte;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.SystemColor;

@SuppressWarnings("serial")
@Component
public class VentanaInicioSesion extends JFrame {

	private JPanel contentPane;

	private ControlInicioSesion control;
	private JTextField txtUsuario;
	private JPasswordField passwordField;
	private String usuario;
	private String password;
	
	private ControlNumReporte controlNumReporte;

	/**
	 * Launch the application.
	 *
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { VentanaPrincipal frame = new
	 * VentanaPrincipal(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public VentanaInicioSesion() {
		setTitle("Farmapass");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 592, 428);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 588, 42);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblFarmapass = new JLabel("FARMAPASS");
		lblFarmapass.setFont(new Font("Josefin Sans", Font.BOLD, 16));
		lblFarmapass.setBounds(144, 10, 309, 22);
		panel.add(lblFarmapass);
		lblFarmapass.setHorizontalAlignment(SwingConstants.CENTER);

		JButton btnIniciaSesion = new JButton("INICIA SESION");
		btnIniciaSesion.setFont(new Font("Josefin Sans", Font.PLAIN, 13));
		btnIniciaSesion.setBounds(129, 292, 137, 30);
		btnIniciaSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validaCamposVacios()) {
					control.validaUsuario(usuario, password);
				} else {
					JOptionPane.showMessageDialog(null, "Verfique que los campos estan correctamente llenos!",
							"Error de inicio de sesión!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnIniciaSesion.setBackground(new Color(50, 205, 50));
		contentPane.add(btnIniciaSesion);
				
						JLabel lblLeyenda = new JLabel("Ingresa usuario y contraseña para iniciar sesion: ");
						lblLeyenda.setBounds(23, 69, 360, 42);
						contentPane.add(lblLeyenda);
						lblLeyenda.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
						lblLeyenda.setHorizontalAlignment(SwingConstants.CENTER);
						
								JLabel lblUsuario = new JLabel("Usuario:");
								lblUsuario.setBounds(54, 139, 62, 14);
								contentPane.add(lblUsuario);
								lblUsuario.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
								
										txtUsuario = new JTextField();
										txtUsuario.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
										txtUsuario.setBounds(146, 133, 204, 28);
										contentPane.add(txtUsuario);
										txtUsuario.setColumns(10);
										
												JLabel lblPassword = new JLabel("Contraseña:");
												lblPassword.setBounds(45, 201, 97, 26);
												contentPane.add(lblPassword);
												lblPassword.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
												
														passwordField = new JPasswordField();
														passwordField.setFont(new Font("Josefin Sans", Font.PLAIN, 16));
														passwordField.setBounds(148, 199, 201, 28);
														contentPane.add(passwordField);
														
														JPanel panel_1 = new JPanel();
														panel_1.setBackground(SystemColor.textHighlight);
														panel_1.setBounds(399, 42, 189, 358);
														contentPane.add(panel_1);
														panel_1.setLayout(null);
	}

	private boolean validaCamposVacios() {
		if (txtUsuario.getText().isEmpty() || passwordField.getPassword().length < 8) {
			return false;
		} else {
			this.usuario = txtUsuario.getText();
			this.password = new String(passwordField.getPassword());
			return true;

		}

	}

	public void muestra(ControlInicioSesion control) {

		this.control = control;

		setVisible(true);

	}

	public void muestraErrorPassword(Exception e) {
		JOptionPane.showMessageDialog(null, e.getMessage(), "Error de inicio de sesión!", JOptionPane.ERROR_MESSAGE);
	}

	public void oculta() {
		setVisible(false);
		
	}
}
