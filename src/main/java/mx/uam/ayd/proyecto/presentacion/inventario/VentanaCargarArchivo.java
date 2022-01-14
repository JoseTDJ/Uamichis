package mx.uam.ayd.proyecto.presentacion.inventario;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.cierreVenta.RowsRenderer;

/**
 * Ventana de cargar archivo
 * 
 * @author Ana Karina Vergara Guzmán
 *
 */
@Slf4j
@SuppressWarnings("serial")
@Component
public class VentanaCargarArchivo extends JFrame {

	private JPanel contentPane;
	private ControlCargarArchivo control;
	private Empleado empleado;
	private JTextField txtNombreEmpleado;
	private JTextField txtNivel;
	private JPanel panel_3;
	private JPanel panel_4;
	private JScrollPane scrollPane_1;
	private JTable tabla_productos;
	private TableColumn checkCol;
	private JTextField txtLeyendaProducto;
	private Producto producto;
	private JButton btnGuardar;
	private JButton btnCargar;
	private JButton btnElegirArchivo;
	private JTextField txtNombreArchivo;
	private String headerFile = "nombre|compuesto|receta|ubicación|precio|piezas";
	private String ruta;
	private ArrayList<Producto> listaProductos = new ArrayList<Producto>();
	private JPanel panel_1;
	private JButton btnEliminar;
	private JLabel lblTotalDeProductos;
	private JTextField txtTotalProd;
	private int fallos = 0;
	@SuppressWarnings("unused")
	private int totalLineas = 0;
	private DefaultTableModel modelo = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			if (column == 6) {
				return true;
			} else {
				return false;
			}
		}
	};
	private JTextField txtIdEmp;

	public VentanaCargarArchivo() {
		setResizable(false);
		setTitle("Farmapass - Cargar archivo");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 496, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBounds(15, 5, 461, 34);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(15, 359, 461, 53);
		panel_2.setLayout(null);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		contentPane.setLayout(null);
		panel.setLayout(null);

		txtNombreEmpleado = new JTextField();
		txtNombreEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombreEmpleado.setEditable(false);
		txtNombreEmpleado.setBounds(106, 6, 265, 22);
		panel.add(txtNombreEmpleado);
		txtNombreEmpleado.setColumns(10);

		txtNivel = new JTextField();
		txtNivel.setEditable(false);
		txtNivel.setBounds(10, 7, 86, 20);
		panel.add(txtNivel);
		txtNivel.setColumns(10);
		contentPane.add(panel);
		
		txtIdEmp = new JTextField();
		txtIdEmp.setEditable(false);
		txtIdEmp.setColumns(10);
		txtIdEmp.setBounds(383, 6, 66, 21);
		panel.add(txtIdEmp);
		contentPane.add(panel_2);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setEnabled(false);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.agregarProductos(listaProductos);
			}
		});
		btnGuardar.setBackground(new Color(153, 204, 102));
		btnGuardar.setBounds(350, 12, 99, 27);
		panel_2.add(btnGuardar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.inciaInventario(empleado);
			}
		});
		btnCancelar.setBackground(Color.LIGHT_GRAY);
		btnCancelar.setBounds(248, 12, 90, 27);
		panel_2.add(btnCancelar);

		panel_3 = new JPanel();
		panel_3.setBounds(15, 51, 461, 53);
		panel_3.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		btnCargar = new JButton("Cargar");
		btnCargar.setBackground(new Color(102, 204, 204));
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtNombreArchivo.getText().equals("")) {
					muestraError("¡Debe elegir un archivo!");
				} else {
					if (validaArchivo(ruta)) {
						try {
							txtNombreArchivo.setText("");
							llenarTabla(listaProductos);
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						muestraError("El archivo no esta en el formato indicado! Valide el archivo");
					}
				}
			}
		});
		btnCargar.setBounds(344, 12, 105, 27);
		panel_3.add(btnCargar);

		btnElegirArchivo = new JButton("Elegir archivo");
		btnElegirArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Archivos de texto", "txt");
				fileChooser.setFileFilter(txtFilter);
				fileChooser.showOpenDialog(fileChooser);
				try {
					String nombreArchivo = fileChooser.getSelectedFile().getName();
					ruta = fileChooser.getSelectedFile().getAbsolutePath();
					txtNombreArchivo.setText(nombreArchivo);
				} catch (NullPointerException e) {
					muestraError("No se ha seleccionado ningún archivo");
				} catch (Exception e) {
					log.warn("> ERROR EN EL ARCHIVO: " + e);
					muestraError("Hubo un error al tratar de acceder al archivo, intente nuevamente.");
				}
			}
		});
		btnElegirArchivo.setBounds(12, 12, 116, 27);
		panel_3.add(btnElegirArchivo);

		txtNombreArchivo = new JTextField();
		txtNombreArchivo.setEditable(false);
		txtNombreArchivo.setBounds(129, 12, 214, 27);
		panel_3.add(txtNombreArchivo);
		txtNombreArchivo.setColumns(10);

		panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(15, 116, 461, 166);
		contentPane.add(panel_4);

		JLabel lblVistaPrevia = new JLabel("VISTA PREVIA");
		lblVistaPrevia.setHorizontalAlignment(SwingConstants.CENTER);
		lblVistaPrevia.setBounds(0, 0, 461, 27);
		panel_4.add(lblVistaPrevia);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 28, 439, 126);
		panel_4.add(scrollPane_1);

		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.setBounds(15, 294, 461, 53);
		contentPane.add(panel_1);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					for (int i = 0; i < tabla_productos.getRowCount(); i++) {
						if (String.valueOf(tabla_productos.getValueAt(i, 6)) == "true") {
							listaProductos.remove(i);
						}
					}
					llenarTabla(listaProductos);
				} catch (Exception e) {
					System.out.println(e);
					JOptionPane.showMessageDialog(null, "Error al leer la tabla.");
				}
			}
		});
		btnEliminar.setBackground(new Color(255, 204, 153));
		btnEliminar.setBounds(12, 12, 90, 27);
		panel_1.add(btnEliminar);

		lblTotalDeProductos = new JLabel("Total de productos:");
		lblTotalDeProductos.setBounds(245, 17, 117, 17);
		panel_1.add(lblTotalDeProductos);

		txtTotalProd = new JTextField();
		txtTotalProd.setEditable(false);
		txtTotalProd.setColumns(10);
		txtTotalProd.setBounds(380, 15, 69, 21);
		panel_1.add(txtTotalProd);

		txtLeyendaProducto = new JTextField();
		txtLeyendaProducto.setEditable(false);

		tabla_productos = new JTable(modelo);

		modelo.addColumn("Nombre");
		modelo.addColumn("Descripción");
		modelo.addColumn("Precio del Producto");
		modelo.addColumn("Piezas");
		modelo.addColumn("Ubicación");
		modelo.addColumn("Receta");
		modelo.addColumn("Seleccionar");

		RowsRenderer rr = new RowsRenderer(3);
		tabla_productos.setDefaultRenderer(Object.class, rr);

		checkCol = tabla_productos.getColumnModel().getColumn(6);
		checkCol.setCellEditor(tabla_productos.getDefaultEditor(Boolean.class));
		checkCol.setCellRenderer(tabla_productos.getDefaultRenderer(Boolean.class));

	}
	
	/**
	 * Muestra la ventana de cargar archivo
	 * @param controlCargarArchivo control que lleva el flujo
	 * @param empleado empleado que inicia sesión
	 */
	public void muestra(ControlCargarArchivo controlCargarArchivo, Empleado empleado) {
		this.control = controlCargarArchivo;
		this.empleado = empleado;
		this.txtNombreEmpleado
				.setText(empleado.getNombre() + " " + empleado.getApellido());
		this.txtNivel.setText(empleado.getNivel() + ":");
		this.txtIdEmp.setText(String.valueOf(empleado.getIdEmpleado()));
		
		try {
			if (listaProductos.size() > 0)
				listaProductos.removeAll(listaProductos);
			limpiaTabla();
			llenarTabla(listaProductos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		setVisible(true);
	}
	
	/**
	 * Oculta la ventana de cargar archivo
	 */
	public void oculta() {
		setVisible(false);
	}

	/**
	 * Llena la tabla de vista previa del archivo
	 * 
	 * @param listaProductos lista de productos del archivo
	 * @throws IOException en caso error al leer el archivo
	 */
	private void llenarTabla(ArrayList<Producto> listaProductos) throws IOException {
		panel_4.add(scrollPane_1);
		boolean hayDatos = false;
		if (ruta != null) {
			FileReader f = new FileReader(ruta);
			BufferedReader b = new BufferedReader(f);
			String cadena;
			StringTokenizer st;
			String delimitador = "|";
			while ((cadena = b.readLine()) != null) {
				if (!cadena.equals(headerFile)) {
					producto = new Producto();
					st = new StringTokenizer(cadena, delimitador);
					ArrayList<String> productoDatos = new ArrayList<String>();
					while (st.hasMoreTokens()) {
						productoDatos.add(st.nextToken());
					}
					if (productoDatos.size() == 6) {
						producto.setNombre(productoDatos.get(0));
						producto.setCompuesto(productoDatos.get(1));
						producto.setReceta(productoDatos.get(2));
						producto.setUbicacion(productoDatos.get(3));
						producto.setPrecio(Float.parseFloat(productoDatos.get(4)));
						producto.setPiezas(Integer.parseInt(productoDatos.get(5)));
						listaProductos.add(producto);
					} else {
						fallos++;
					}
					totalLineas++;
				}

			}
			ruta = null;
			b.close();
		}
		if (listaProductos.isEmpty()) {
			limpiaTabla();
			txtLeyendaProducto.setColumns(10);
			txtLeyendaProducto.setHorizontalAlignment(SwingConstants.CENTER);
			txtLeyendaProducto.setText("NO HAY PRODUCTOS EN EL INVENTARIO");
			scrollPane_1.setViewportView(txtLeyendaProducto);
			txtTotalProd.setText("0");
		} else {
			@SuppressWarnings("unused")
			int contProductos = 0;
			for (Producto producto : listaProductos) {
				modelo.addRow(new Object[] { producto.getNombre(), producto.getCompuesto(),
						String.valueOf(producto.getPrecio()), String.valueOf(producto.getPiezas()),
						producto.getUbicacion(), producto.getReceta(), false });
				contProductos++;
				tabla_productos.setModel(modelo);
			}
			scrollPane_1.setViewportView(tabla_productos);
			txtTotalProd.setText(String.valueOf(listaProductos.size()));
			hayDatos = true;
			if (fallos > 0) {
				muestraError(fallos + " productos no tenian el formato indicado en el archivo.");
			}
		}
		activaBoton(hayDatos);
	}
	
	/**
	 * Muestra un mensaje de error
	 * 
	 * @param mensaje mensaje con el error que se generó
	 */
	public void muestraError(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Activa el boton de guardar.
	 * @param hayDatos valor que debe tomar el boton para ser habilitado.
	 */
	private void activaBoton(boolean hayDatos) {
		btnGuardar.setEnabled(hayDatos);
	}
	
	/**
	 * 
	 */
	public void muestraConfirmacion() {
		int res = JOptionPane.showOptionDialog(null, "El producto ha sido agregado!", "Confrimación",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
		if (res == 0) {
			control.inciaInventario(empleado);
		}

	}
	
	/**
	 * Valida si el archivo elegido cuenta con el formato indicado
	 * @param ruta ruta del archivo a validar
	 * @return true si el archivo es correcto, false si no lo está
	 */
	private boolean validaArchivo(String ruta) {
		Scanner entrada = null;
		int contFilas = 0;
		boolean valida = false;
		File f = new File(ruta);
		try {
			entrada = new Scanner(f);
			if (contFilas == 0) {
				if (entrada.nextLine().equals(headerFile)) {
					valida = true;
					entrada.nextLine();
				} else {
					return valida;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (entrada != null) {
				entrada.close();
			}
		}
		return valida;
	}
	
	/**
	 * limpia la tabla
	 */
	private void limpiaTabla() {
		txtTotalProd.setText("");
		int filas = tabla_productos.getRowCount();
		try {
			for (int i = 0; filas > i; i++) {
				modelo.removeRow(0);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
		}
	}
	
	/**
	 * Muestra los resultados de la carga del archivo
	 * @param listaProductosFallo lista de los productos duplicados
	 * @param contExitos total de productos agregados
	 * @param contFallos total de productos duplicados
	 */
	public void muestraResultados(ArrayList<Producto> listaProductosFallo, int contExitos, int contFallos) {
		int fallosTotal = contFallos + fallos;
		int res = JOptionPane.showOptionDialog(null,
				"Se agregaron: " + contExitos + " productos.\nError al agregar: " + fallos
						+ " productos del archivo. \nProductos duplicados: " + contFallos + "\nErrores totales: "
						+ fallosTotal,
				"Confrimación", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
		if (res == 0) {
			control.inciaInventario(empleado);
		}
	}
}
