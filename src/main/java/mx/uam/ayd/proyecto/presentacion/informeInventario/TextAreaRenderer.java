package mx.uam.ayd.proyecto.presentacion.informeInventario;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

public class TextAreaRenderer extends JTextArea implements TableCellRenderer {
	
	public TextAreaRenderer() {
		setLineWrap(true);
		setWrapStyleWord(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		setText((String)value);
		return this;
	}

}
