package mx.uam.ayd.proyecto.presentacion.monitoreo;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Asistencia;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;


@SuppressWarnings("serial")
@Component
public class VentanaMonitoreo extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Asistencia asistencia;
	ControlMonitoreo controlmonitoreo;
	
	
	private DefaultTableModel modelo = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) { 
			return false; 
			} 
	};
	
	//Metodo de presentacion de la ventana MOnitoreo
	public VentanaMonitoreo() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Farmapass");
		lblNewLabel.setBounds(0, 0, 71, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabelMonitoreo = new JLabel("Monitoreo Asistencias");
		lblNewLabelMonitoreo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabelMonitoreo.setBounds(197, 11, 154, 14);
		contentPane.add(lblNewLabelMonitoreo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 62, 496, 237);
		contentPane.add(scrollPane);
		
		JButton btnNewButtonRegresar = new JButton("Regresar");
		btnNewButtonRegresar.setBounds(232, 319, 89, 23);
		contentPane.add(btnNewButtonRegresar);
		
		btnNewButtonRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				limpiaTabla();
				//controlmonitoreo.vaciarList();
			}
		});
		
		table = new JTable(modelo);
		
		modelo.addColumn("ID");
		modelo.addColumn("Hora de Inicio");
		modelo.addColumn("Hora de Cerrar");
		modelo.addColumn("Fecha");
		scrollPane.setViewportView(table);
				
	}//Fin del metodo VentanaMonitoreo
	
	
	
	//Metodo que muestra la venta Busqueda
	public void muestra(ControlMonitoreo controlmonitoreo) {
		this.controlmonitoreo = controlmonitoreo;
		//controlmonitoreo.iniciaAsistencias();
		setVisible(true);
	}//fin del metodo muestra
	
	
	public void llenaTabla(Asistencia asistencia) {
		String a[] = new String[4];
		a[0] = String.valueOf(asistencia.getIdAsistencia());
		a[1] = asistencia.getHoraInicial();
		a[2] = asistencia.getHoraFinal();
		a[3] = asistencia.getFecha();
		modelo.addRow(a);
		table.setModel(modelo);
		this.asistencia = asistencia;
	}//Fin del metodo llenaTabla
	
	
	public void limpiaTabla() {
		int filas = table.getRowCount();
		try {
			for (int i = 0; filas>i ; i++) {
				modelo.removeRow(0);
			}
		} catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }	
	}

}//Fin de la clase VentanaMonitoreo
