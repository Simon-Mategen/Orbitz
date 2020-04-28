package View;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CellRenderer extends JTextArea implements TableCellRenderer
{
    private final Font font1 = new Font ("Nasalization Rg", Font.BOLD,12);

    public CellRenderer()
    {
        setLineWrap(true);
        setWrapStyleWord(true);
        setBackground(Color.black);
        setFont(font1);
        setForeground(Color.yellow);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        setText(value.toString());
        setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
        if (table.getRowHeight(row) != getPreferredSize().height)
        {
            table.setRowHeight(row, getPreferredSize().height);
        }
        return this;
    }
}
