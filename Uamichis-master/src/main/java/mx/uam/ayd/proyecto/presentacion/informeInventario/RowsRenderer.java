package mx.uam.ayd.proyecto.presentacion.informeInventario;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class RowsRenderer extends DefaultTableCellRenderer {
	
	private int columna;
	
	public RowsRenderer(int Colpatron) {
		this.columna = Colpatron;
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
		setBackground(Color.white);
		table.setForeground(Color.black);
		super.getTableCellRendererComponent(table, value, selected, focused, row, column);
		float piezasF = Float.parseFloat((String)table.getValueAt(row, columna));
		if(piezasF < 8) {
			this.setBackground(Color.RED);
		}
		return this;
	}
	
}
