package mx.uam.ayd.proyecto.presentacion.asistencia;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioEmpleado;
import mx.uam.ayd.proyecto.negocio.modelo.Asistencia;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.presentacion.controlEmpleados.ControlEmpleados;

import java.awt.Dialog.ModalExclusionType;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.time.LocalDateTime;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

@Component
@SuppressWarnings("serial")
public class ventanaAsistencias extends JFrame {
	private JPanel contentPane;
	private JTextField encargo;
	private JTextField nombreEmpleado;
	private JTextField idEmpleado;
	private JTextField fecha;
	private JTextField barrabuscar;
	private JTable tablaAsistencias;
	private controlAsistencias control;
	private Empleado empleado;
	private ServicioEmpleado servicioEmpleado;
	
	private DefaultTableModel modelo = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) { 
			return false; 
			} 
	};
	
	
	
	private Asistencia asistencia;
	private JLabel lblNewLabel;
	private JLabel lblNombre;
	//Creacion del frame
	
	public ventanaAsistencias() {
		setTitle("Farmapass - Control de Asistencias");
		setResizable(false);
		setBounds(100, 100, 662, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 636, 44);
		contentPane.add(panel);
		panel.setLayout(null);
		
		encargo = new JTextField();
		encargo.setEditable(false);
		encargo.setBounds(10, 10, 200, 28);
		panel.add(encargo);
		encargo.setColumns(10);
		
		nombreEmpleado = new JTextField();
		nombreEmpleado.setEditable(false);
		nombreEmpleado.setColumns(10);
		nombreEmpleado.setBounds(220, 10, 216, 28);
		panel.add(nombreEmpleado);
		
		idEmpleado = new JTextField();
		idEmpleado.setEditable(false);
		idEmpleado.setColumns(10);
		idEmpleado.setBounds(446, 10, 200, 28);
		panel.add(idEmpleado);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 44, 636, 80);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		fecha = new JTextField();
		fecha.setBounds(10, 10, 200, 28);
		fecha.setEditable(false);
		fecha.setColumns(10);
		panel_1.add(fecha);
		
		barrabuscar = new JTextField();
		barrabuscar.setBounds(222, 45, 404, 25);
		/**
		 * Permite filtrar los datos de la tabla para encontrar a un empleado especifico
		 */
		barrabuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(modelo);
				tablaAsistencias.setRowSorter(elQueOrdena);
				//botonBuscar.setEnabled(barrabuscar.getText().length() != 0);
				elQueOrdena.setRowFilter(RowFilter.regexFilter(barrabuscar.getText(), 1));
			}
		});
		barrabuscar.setColumns(10);
		panel_1.add(barrabuscar);
		
		lblNewLabel = new JLabel("Escriba el nombre de un empleado en la barra de búsqueda para obtener sus datos.");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel.setBounds(222, 10, 404, 28);
		panel_1.add(lblNewLabel);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNombre.setEnabled(false);
		lblNombre.setBounds(0, 46, 210, 23);
		panel_1.add(lblNombre);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 375, 636, 57);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
	}
	
}
