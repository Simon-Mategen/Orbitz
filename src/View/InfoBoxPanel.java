package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * @Author Manna Manojlovic
 *
 * This class is the table with information about the selected planet.
 * Shows information about planet's mass, size, number of moons, length of year in earth years, distance from sun
 */
public class InfoBoxPanel extends JPanel
{
    private final Font font1 = new Font ("Nasalization Rg", Font.BOLD,14);
    private final Font font2 = new Font ("Nasalization Rg", Font.PLAIN,12);

    private JTable table;
    private Object[] cols;

    /**
     * @author Manna Manojlovic
     * Constructor
     */
    public InfoBoxPanel()
    {
        setupTable();
    }

    /**
     * @author Manna Manojlovic
     * Setting up the table with columns for mass, size, year length, distance from sun
     */
    public void setupTable()
    {
        JPanel pnlTable = new JPanel();
        pnlTable.setBackground(Color.black);

        table = new JTable();

        JScrollPane s = new JScrollPane(table);

        cols = new String[]{"Mass", "Size", "Moons", "Year Length", "Distance From Sun"};

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(cols);

        table.getColumnModel().getColumn(0).setPreferredWidth(5);
        table.getColumnModel().getColumn(1).setPreferredWidth(5);
        table.getColumnModel().getColumn(2).setPreferredWidth(10);
        table.getColumnModel().getColumn(3).setPreferredWidth(85);
        table.getColumnModel().getColumn(4).setPreferredWidth(115);

        table.setShowGrid(false);
        table.setRowMargin(0);
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(false);
        table.setIntercellSpacing(new Dimension(40, 0));

        table.setPreferredScrollableViewportSize(new Dimension(500,50));
        table.setFillsViewportHeight(true);

        table.getTableHeader().setFont(font1);
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(Color.black);
        table.getTableHeader().setForeground(Color.WHITE);

        table.setFont(font2);
        table.setBackground(Color.black);
        table.setForeground(Color.orange);
        table.setRowHeight(25);


        pnlTable.add (s);

        addRows();

        add(s,BorderLayout.CENTER);

    }

    /**
     * @Author Manna Manojlovic
     * For now hard coded info for testing for the rows
     */

    public void addRows()
    {
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        ///if planet equals == Earth, Mercury bla bla, set rows genom nedan beroende på vilken planet det är.
        var colArr = new String[5];
        colArr[0] = "100";        //planet.getMass() eller nåt
        colArr[1] = "strl";         //getCircumference osv.
        colArr[2] = "14";
        colArr[3] = "12";
        colArr[4] = "54km";

        model.addRow(colArr);
    }
}
