package View;

import Model.Planet;
import Model.Theme;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * @Author: Manna Manojlovic, Albin Ahlbeck
 * @version: 1.0
 *
 * This is the main frame for the planetary information GUI.
 * It has the MainInfoPanel main panel placed on it and the FX panel for lunar orbit.
 *
 */
public class MainInfoFrame extends JFrame
{
    private MainInfoPanel panel;
    private Planet planet;
    private JPanel pnlTitle;
    private JLabel lblTitle;

    //Controller controller;

    /**
     * Adds a planet to be displayed when creating a MainInfoFrame
     **/
    public MainInfoFrame(Planet planet, Theme theme)
    {
        // this.controller = controller;
        this.planet = planet;
        frame(planet, theme);

    }

    public void frame(Planet planet, Theme theme)
    {
        setLayout(new BorderLayout());

        panel = new MainInfoPanel(planet);

        setSize(1000, 700);
        setBackground(Color.BLACK);
        setResizable(false);
        setTitle(planet.getName());
        setIconImage(new ImageIcon("src/Images/" + planet.getName() + "Icon.png").getImage());

        pnlTitle = new JPanel();
        pnlTitle.setPreferredSize(new Dimension(getWidth(), 100));
        pnlTitle.setBackground(theme.getMainColor());

        lblTitle = new JLabel(planet.getName());
        lblTitle.setFont(new Font("Earth Orbiter", Font.PLAIN, 40));
        lblTitle.setForeground(theme.getSecondaryColor());
        lblTitle.setPreferredSize(new Dimension(getWidth() / 2, 100));

        pnlTitle.add(lblTitle);
        add(pnlTitle);
        add(panel, BorderLayout.NORTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                panel.stopMp3();
            }
        });
        setVisible(true);
    }
}
