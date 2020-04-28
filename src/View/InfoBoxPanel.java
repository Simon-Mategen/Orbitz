package View;

import Model.Planet;

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

    private Planet planet;

    /**
     * @author Manna Manojlovic
     * Constructor
     */
    public InfoBoxPanel(Planet planet)
    {
        this.planet = planet;
        setupTable();
    }

    /**
     * @author Manna Manojlovic
     * Setting up the table with columns for travel time, latest visit, next visit and possibility of life.
     */
    public void setupTable()
    {
        setBackground(Color.black);     //background to panel

        table = new JTable();

        JScrollPane s = new JScrollPane(table);

        cols = new String[]{"TRAVEL TIME", "LATEST VISIT", "NEXT VISIT", "POSSIBLE LIFE"};

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(cols);

        table.getColumnModel().getColumn(0).setCellRenderer(new CellRenderer());
        table.getColumnModel().getColumn(1).setCellRenderer(new CellRenderer());
        table.getColumnModel().getColumn(2).setCellRenderer(new CellRenderer());
        table.getColumnModel().getColumn(3).setCellRenderer(new CellRenderer());

        //setting the column width for each column
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(1).setPreferredWidth(20);
        table.getColumnModel().getColumn(2).setPreferredWidth(20);
        table.getColumnModel().getColumn(3).setPreferredWidth(20);

        //hiding gridlines between rows
        table.setShowGrid(false);
        table.setRowMargin(0);
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(false);

        //TODO custom cell renderer to remove grid lines between the columns for a modern look

        //setting the margin for where the data begins to display
        table.setIntercellSpacing(new Dimension(30, 0));

        //setting size for the table
        table.setPreferredScrollableViewportSize(new Dimension(500,100));
        table.setFillsViewportHeight(true);

        //setting colors and fonts to the columns
        table.getTableHeader().setFont(font1);
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(Color.black);
        table.getTableHeader().setForeground(Color.WHITE);

        //setting fonts and color to the rows
        table.setFont(font2);
        table.setBackground(Color.black);
        table.setForeground(Color.yellow);
        table.setRowHeight(150);

        addRows();                       //adding the rows to the columns respectively

        add(s,BorderLayout.CENTER);     //placing the table on the layout


    }

    /**
     * @Author Manna Manojlovic
     * Hard coded info for each planet
     */

    public void addRows()
    {
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        var colArr = new String[5];

        if (planet.getName().equals("Mercury"))
        {
            colArr[0] = "3.4 years";
            colArr[1] = "2013, Mariner10";
            colArr[2] = "2029: ESA+Japan";
            colArr[3] = "No";
        }

        else if (planet.getName().equals("Venus"))
        {
            colArr[0] = "1.3 years";
            colArr[1] = "2010, IKAROS";
            colArr[2] = "2021: NASA";
            colArr[3] = "Maybe";
        }

        else if (planet.getName().equals("Earth"))
        {
            colArr[0] = "3 days (Moon)";
            colArr[1] = "2019, Chandrayaan2";
            colArr[2] = "2024, NASA";
            colArr[3] = "Yes";
        }

        else if (planet.getName().equals("Mars"))
        {
            colArr[0] = "7-9 months";
            colArr[1] = "2018, InSight";
            colArr[2] = "2020, NASA";
            colArr[3] = "Maybe";
        }

        else if (planet.getName().equals("Jupiter"))
        {
            colArr[0] = "2-6 years";
            colArr[1] = "Now: Juno orbits";
            colArr[2] = "2022, ESA";
            colArr[3] = "Maybe on moons";
        }

        else if (planet.getName().equals("Saturn"))
        {
            colArr[0] = "3-6 years";
            colArr[1] = "2017, Cassini";
            colArr[2] = "2034 to Titan";
            colArr[3] = "Maybe on moons";
        }


        else if (planet.getName().equals("Uranus"))
        {
            colArr[0] = "9.5 years";
            colArr[1] = "1986, Voyager2";
            colArr[2] = "None";
            colArr[3] = "No";
        }

        else if (planet.getName().equals("Neptune"))
        {
            colArr[0] = "12 years";
            colArr[1] = "1989, Voyager2";
            colArr[2] = "None";
            colArr[3] = "No";
        }

        model.addRow(colArr);       //add the rows above to the table model
    }
}
