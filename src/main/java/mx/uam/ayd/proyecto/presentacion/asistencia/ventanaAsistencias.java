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
	
}
