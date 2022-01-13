package mx.uam.ayd.proyecto.presentacion.cobro;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode39;
import com.itextpdf.text.pdf.PdfWriter;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.venta.VentanaVenta;

@SuppressWarnings("serial")
@Component
public class VentanaCobro extends JFrame {

	@Autowired
	private VentanaVenta ventanaVenta;

	@Autowired
	private ControlCobro controlCobro;

	private JPanel contentPane;
	private JTextField textFieldTotal;
	private JTextField textFieldCambio;

	private float total;
	private JTextField textFieldNumero;
	private JTextField textFieldRecibi;

	public Empleado empleado;

	Producto producto;

	public VentanaCobro() {
		setTitle("Cobro");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JCheckBox chckbxEfectivo = new JCheckBox("Efectivo");
		chckbxEfectivo.setBounds(21, 22, 99, 29);
		chckbxEfectivo.setSelected(true);
		chckbxEfectivo.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JCheckBox chckbxTarjeta = new JCheckBox("Tarjeta");
		chckbxTarjeta.setBounds(21, 69, 99, 27);
		chckbxTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JPanel panel = new JPanel();
		panel.setBounds(122, 16, 303, 190);

		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setForeground(new Color(255, 255, 255));
		btnFinalizar.setBackground(new Color(50, 205, 50));
		btnFinalizar.setBounds(257, 224, 126, 23);
		btnFinalizar.setEnabled(false);

		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setForeground(new Color(255, 255, 255));
		btnRegresar.setBackground(new Color(255, 0, 0));
		btnRegresar.setBounds(49, 224, 99, 23);

		JButton btnAprobado = new JButton("Aprobar");
		btnAprobado.setForeground(Color.BLACK);
		btnAprobado.setBackground(Color.WHITE);
		btnAprobado.setBounds(40, 166, 113, 23);
		btnAprobado.setVisible(false);
		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(Color.GREEN);
		progressBar.setBackground(Color.WHITE);
		progressBar.setBounds(115, 110, 146, 9);
		progressBar.setVisible(false);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(30, 27, 49, 20);
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblRecibi = new JLabel("Recibí:");
		lblRecibi.setBounds(30, 61, 60, 20);
		lblRecibi.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblCambio = new JLabel("Cambio:");
		lblCambio.setBounds(30, 136, 75, 20);
		lblCambio.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(30, 99, 75, 20);
		lblEstado.setVisible(false);
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblNoTarjeta = new JLabel("No. Tarjeta");
		lblNoTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNoTarjeta.setBounds(30, 58, 83, 20);
		lblNoTarjeta.setVisible(false);

		textFieldTotal = new JTextField();
		textFieldTotal.setBounds(123, 27, 138, 20);
		textFieldTotal.setEditable(false);
		textFieldTotal.setColumns(10);

		textFieldCambio = new JTextField();
		textFieldCambio.setBounds(123, 138, 130, 20);
		textFieldCambio.setVisible(true);
		textFieldCambio.setEditable(false);
		textFieldCambio.setColumns(10);
		textFieldNumero = new JTextField();
		textFieldNumero.setEditable(false);
		textFieldNumero.setVisible(false);
		textFieldNumero.setColumns(10);
		textFieldNumero.setBounds(123, 63, 138, 20);
		contentPane.setLayout(null);
		contentPane.add(chckbxTarjeta);
		contentPane.add(chckbxEfectivo);
		contentPane.add(panel);
		contentPane.add(btnRegresar);
		contentPane.add(btnFinalizar);
		panel.setLayout(null);
		panel.add(lblTotal);
		panel.add(lblCambio);
		panel.add(lblRecibi);
		panel.add(textFieldCambio);
		panel.add(textFieldTotal);
		panel.add(lblEstado);
		panel.add(progressBar);
		panel.add(btnAprobado);
		panel.add(lblNoTarjeta);
		panel.add(textFieldNumero);

		textFieldRecibi = new JTextField();
		textFieldRecibi.setBounds(123, 63, 96, 20);
		panel.add(textFieldRecibi);
		textFieldRecibi.setColumns(10);

		// Listener de la opcion efectivo
		textFieldRecibi.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (textFieldRecibi.getText().length() == 0) {
					textFieldCambio.setText(" ");
					btnFinalizar.setEnabled(false);
				} else {
					if (textFieldRecibi.getText().length() != 0) {
						cambio();
						float a = Float.parseFloat(textFieldCambio.getText());
						if (a >= 0)
							btnFinalizar.setEnabled(true);
					}
				}
			}

			/**
			 * Llena campo cambio
			 */

			private void cambio() {
				float recibi = Float.parseFloat(textFieldRecibi.getText());
				float cambio = recibi - total;
				textFieldCambio.setText(String.valueOf(cambio));
			}

			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (Character.isDigit(caracter)) {
				} else {
					e.consume();
				}
			}

		});

		// Listener de la opción tarjeta

		chckbxEfectivo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (chckbxEfectivo.isSelected() == true) {
					chckbxTarjeta.setSelected(false);
					lblEstado.setVisible(false);
					progressBar.setVisible(false);
					btnAprobado.setVisible(false);
					lblNoTarjeta.setVisible(false);
					textFieldNumero.setVisible(false);
					lblRecibi.setVisible(true);
					lblCambio.setVisible(true);
					textFieldRecibi.setVisible(true);
					textFieldCambio.setVisible(true);
					textFieldRecibi.setText("");
					textFieldCambio.setText("");
					btnFinalizar.setEnabled(false);
				}

			}
		});

		chckbxTarjeta.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (chckbxTarjeta.isSelected() == true) {
					chckbxEfectivo.setSelected(false);
					lblRecibi.setVisible(false);
					lblCambio.setVisible(false);
					textFieldCambio.setVisible(false);
					textFieldRecibi.setVisible(false);
					textFieldNumero.setVisible(true);
					textFieldNumero.setEditable(false);
					lblNoTarjeta.setVisible(true);
					lblEstado.setVisible(true);
					progressBar.setVisible(true);
					btnAprobado.setVisible(true);
					progressBar.setValue(0);
					if (textFieldNumero.getText().length() == 0) {
						btnFinalizar.setEnabled(false);
					} else {
						btnFinalizar.setEnabled(true);
					}
				}
			}
		});

		btnAprobado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread hilo = new Thread() {
					public void run() {
						for (int i = 1; i <= 100; i++) {
							try {
								sleep(10);
								progressBar.setValue(i);
							} catch (InterruptedException ex) {
								JOptionPane.showMessageDialog(null, "No se acepto la tarjeta");
							}
						}
					}
				};
				hilo.start();
				textFieldNumero.setText(numeroTarjeta());
				btnAprobado.setEnabled(false);
				btnAprobado.setForeground(Color.WHITE);
				btnAprobado.setBackground(Color.RED);
				if (textFieldNumero.getText().length() != 0) {
					btnFinalizar.setEnabled(true);
				}
			}
		});

		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldRecibi.setText("");
				textFieldCambio.setText("");
				btnFinalizar.setEnabled(false);
				controlCobro.termina();
			}
		});

		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (chckbxEfectivo.isSelected() == true) {
					float a = Float.parseFloat(textFieldCambio.getText());
					if (a < 0) {
						JOptionPane.showMessageDialog(null,
								"La venta no se puede realizar porque la cantidad Recida es incorrecta");
					} else {
						controlCobro.obtenerLista(total, empleado);
						generarTicketEfectivo();
						controlCobro.limpiarTabla();
						controlCobro.termina();
					}
				} else {
					chckbxEfectivo.setSelected(true);
					chckbxTarjeta.setSelected(false);
					btnAprobado.setBackground(Color.CYAN);
					btnAprobado.setForeground(Color.WHITE);
					progressBar.setValue(0);
					btnAprobado.setEnabled(true);
					controlCobro.obtenerLista(total, empleado);
					generarTicketTarjeta();
					controlCobro.limpiarTabla();
					controlCobro.termina();

				}
			}
		});

	}

	public void generarTicketEfectivo() {

		Rectangle pageSize = new Rectangle(300.0F, 550.0F);

		LocalDateTime ahora = LocalDateTime.now();
		int anio = ahora.getYear();
		int mes = ahora.getMonthValue();
		int dia = ahora.getDayOfMonth();
		int hora = ahora.getHour();
		int minuto = ahora.getMinute();
		int segundo = ahora.getSecond();

		String numeroTicket = numeroTicket();

		String horainical = hora + ":" + minuto + ":" + segundo;
		String fecha = dia + "/" + mes + "/" + anio;

		String header = "FARMAPASS\n" + "AV.PERIFERICO 390\n" + "LA PURISIMA, IZTAPALAPA, CIUDAD DE MEXICO";
		String gracias = "¡GRACIAS POR SU COMPRA!\n";
		String footer = "\nLe Atendio: " + empleado.getNombre() + " " + empleado.getApellido();

		List<Producto> productos = ventanaVenta.recorrerTabla();

		String total = "Total              " + " $          " + textFieldTotal.getText();
		String efectivo = "\nEfectivo         " + " $          " + textFieldRecibi.getText();
		String cambio = "\nCambio          " + " $          " + textFieldCambio.getText();

		Document documento = new Document(pageSize);

		try {

			PdfWriter pdf = PdfWriter.getInstance(documento, new FileOutputStream("Ticket.pdf"));

			documento.open();

			Paragraph parrafoTitulo = new Paragraph();
			Paragraph parrafoProductos = new Paragraph();

			Paragraph parrafoFecha = new Paragraph();
			Paragraph parrafoTotal = new Paragraph();
			Paragraph parrafoEfectivo = new Paragraph();
			Paragraph parrafoCambio = new Paragraph();

			Paragraph parrafoEmpleado = new Paragraph();
			Paragraph parrafoGracias = new Paragraph();

			parrafoTitulo.setAlignment(Paragraph.ALIGN_CENTER);
			parrafoProductos.setAlignment(Paragraph.ALIGN_LEFT);
			parrafoFecha.setAlignment(Paragraph.ALIGN_CENTER);
			parrafoTotal.setAlignment(Paragraph.ALIGN_LEFT);
			parrafoEfectivo.setAlignment(Paragraph.ALIGN_LEFT);
			parrafoCambio.setAlignment(Paragraph.ALIGN_LEFT);
			parrafoEmpleado.setAlignment(Paragraph.ALIGN_LEFT);
			parrafoGracias.setAlignment(Paragraph.ALIGN_CENTER);

			parrafoTitulo.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
			parrafoProductos.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
			parrafoFecha.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
			parrafoTotal.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
			parrafoEfectivo.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
			parrafoCambio.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
			parrafoEmpleado.setFont(FontFactory.getFont("Tahoma", 12, Font.BOLD, BaseColor.BLACK));
			parrafoGracias.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));

			parrafoTitulo.add(header);
			parrafoFecha.add(fecha + "    " + horainical);
			parrafoTotal.add(total);
			parrafoEfectivo.add(efectivo);
			parrafoCambio.add(cambio);
			parrafoEmpleado.add(footer);
			parrafoGracias.add(gracias);
			for (int i = 0; i < productos.size(); i++) {
				parrafoProductos.add("\n" + String.valueOf(productos.get(i).getNombre().toLowerCase())
						+ "          $          " + productos.get(i).getPrecio());
			}

			Barcode39 code = new Barcode39();
			code.setCode(numeroTicket);
			Image img = code.createImageWithBarcode(pdf.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
			img.setAlignment(Chunk.ALIGN_UNDEFINED);
			documento.add(new Paragraph("================================"));

			documento.add(parrafoTitulo);
			documento.add(parrafoFecha);
			documento.add(new Paragraph("================================"));
			documento.add(parrafoProductos);
			documento.add(new Paragraph("   "));

			documento.add(parrafoTotal);
			documento.add(parrafoEfectivo);
			documento.add(parrafoCambio);
			documento.add(new Paragraph("   "));

			documento.add(new Paragraph("================================"));
			documento.add(parrafoGracias);
			documento.add(parrafoEmpleado);
			documento.add(new Paragraph(" "));
			documento.add(img);
			documento.add(new Paragraph("   "));
			documento.add(new Paragraph("\n\n================================"));

			documento.close();

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Error al generar el ticket.");

		}
	}

	public void generarTicketTarjeta() {

		Rectangle pageSize = new Rectangle(300.0F, 550.0F);

		LocalDateTime ahora = LocalDateTime.now();
		int anio = ahora.getYear();
		int mes = ahora.getMonthValue();
		int dia = ahora.getDayOfMonth();
		int hora = ahora.getHour();
		int minuto = ahora.getMinute();
		int segundo = ahora.getSecond();
		String numeroTicket = numeroTicket();

		String horainical = hora + ":" + minuto + ":" + segundo;
		String fecha = dia + "/" + mes + "/" + anio;

		String header = "FARMAPASS\n" + "AV.PERIFERICO 390\n" + "LA PURISIMA, IZTAPALAPA, CIUDAD DE MEXICO";
		String gracias = "¡GRACIAS POR SU COMPRA!\n";
		String footer = "\nLe Atendio: " + empleado.getNombre() + " " + empleado.getApellido();

		List<Producto> productos = ventanaVenta.recorrerTabla();

		String total = "Total              " + " $          " + textFieldTotal.getText();
		String tarjeta = "\nTarjeta:         " + "          " + textFieldNumero.getText();
		String pagoAprobado = "\nPAGO APROBADO";
		Document documento = new Document(pageSize);

		try {

			PdfWriter pdf = PdfWriter.getInstance(documento, new FileOutputStream("TicketTarjeta.pdf"));

			documento.open();

			Paragraph parrafoTitulo = new Paragraph();
			Paragraph parrafoProductos = new Paragraph();

			Paragraph parrafoFecha = new Paragraph();
			Paragraph parrafoTotal = new Paragraph();
			Paragraph parrafoTarjeta = new Paragraph();
			Paragraph parrafoPagoAprobado = new Paragraph();

			Paragraph parrafoEmpleado = new Paragraph();
			Paragraph parrafoGracias = new Paragraph();

			parrafoTitulo.setAlignment(Paragraph.ALIGN_CENTER);
			parrafoProductos.setAlignment(Paragraph.ALIGN_LEFT);
			parrafoFecha.setAlignment(Paragraph.ALIGN_CENTER);
			parrafoTotal.setAlignment(Paragraph.ALIGN_LEFT);
			parrafoTarjeta.setAlignment(Paragraph.ALIGN_LEFT);
			parrafoPagoAprobado.setAlignment(Paragraph.ALIGN_CENTER);
			parrafoEmpleado.setAlignment(Paragraph.ALIGN_LEFT);
			parrafoGracias.setAlignment(Paragraph.ALIGN_CENTER);

			parrafoTitulo.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
			parrafoProductos.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
			parrafoFecha.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
			parrafoTotal.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
			parrafoTarjeta.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
			parrafoPagoAprobado.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
			parrafoEmpleado.setFont(FontFactory.getFont("Tahoma", 12, Font.BOLD, BaseColor.BLACK));
			parrafoGracias.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));

			parrafoTitulo.add(header);
			parrafoFecha.add(fecha + "    " + horainical);
			parrafoTotal.add(total);
			parrafoTarjeta.add(tarjeta);
			parrafoPagoAprobado.add(pagoAprobado);
			parrafoEmpleado.add(footer);
			parrafoGracias.add(gracias);
			for (int i = 0; i < productos.size(); i++) {
				parrafoProductos.add("\n" + String.valueOf(productos.get(i).getNombre().toLowerCase())
						+ "          $          " + productos.get(i).getPrecio());
			}

			Barcode39 code = new Barcode39();
			code.setCode(numeroTicket);
			Image img = code.createImageWithBarcode(pdf.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
			img.setAlignment(Chunk.ALIGN_UNDEFINED);

			documento.add(new Paragraph("================================"));

			documento.add(parrafoTitulo);
			documento.add(parrafoFecha);
			documento.add(new Paragraph("================================"));
			documento.add(parrafoProductos);
			documento.add(new Paragraph("   "));

			documento.add(parrafoTotal);
			documento.add(parrafoTarjeta);
			documento.add(parrafoPagoAprobado);
			documento.add(new Paragraph("   "));

			documento.add(new Paragraph("================================"));
			documento.add(parrafoGracias);
			documento.add(parrafoEmpleado);
			documento.add(new Paragraph(" "));
			documento.add(img);
			documento.add(new Paragraph("   "));
			documento.add(new Paragraph("\n\n================================"));

			documento.close();

		} catch (Exception e2) {
			System.out.println(e2);
			JOptionPane.showMessageDialog(null, "Error al generar el ticket.");

		}
	}

	protected String numeroTarjeta() {
		long number1 = (long) Math.floor(Math.random() * 90_000_000L) + 10_000_000L;
		long number2 = (long) Math.floor(Math.random() * 90_000_000L) + 10_000_000L;
		String cadena = null;
		while (number1 == number2) {
			number1 = (long) Math.floor(Math.random() * 90_000_000L) + 10_000_000L;
		}
		cadena = String.valueOf(number1).concat(String.valueOf(number2));
		return cadena;
	}

	protected String numeroTicket() {
		long number1 = (long) Math.floor(Math.random() * 90_000_000L) + 10_000_000L;
		long number2 = (long) Math.floor(Math.random() * 90_000_000L) + 10_000_000L;
		String cadena = null;
		while (number1 == number2) {
			number1 = (long) Math.floor(Math.random() * 90_000_000L) + 10_000_000L;
		}
		cadena = String.valueOf(number1).concat(String.valueOf(number2));
		return cadena;
	}

	// Métodos que ocupa la ventana
	public void muestra(ControlCobro controlCobro, float total, Empleado empleado) {
		textFieldTotal.setText(String.valueOf(total));
		this.empleado = empleado;

		this.controlCobro = controlCobro;
		this.total = total;
		textFieldRecibi.setText("");
		textFieldCambio.setText("");
		setVisible(true);
	}

	public void muestraDialogo() {
		JOptionPane.showMessageDialog(null, "La venta se realizó con exito");

	}

}