package View;

import javax.swing.*;
import java.awt.*;

public class InfoBoxPanel extends JPanel
{
    private JLabel massLbl;
    private JLabel sizeLbl;
    private JLabel moonsLbl;
    private JLabel yearLbl;
    private JLabel distanceLbl;

    private JLabel massInfo;
    private JLabel sizeInfo;
    private JLabel moonsInfo;
    private JLabel yearInfo;
    private JLabel distanceInfo;

    public InfoBoxPanel()
    {
        setupPanel();
    }

    public void setupPanel()
    {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(layout);

        massLbl = new JLabel("Mass");
        sizeLbl = new JLabel("Circumference");
        moonsLbl = new JLabel("Number of moons");
        yearLbl = new JLabel("Length of year");
        distanceLbl = new JLabel("Distance from Sun");

        massInfo = new JLabel("");
        sizeInfo = new JLabel("");
        moonsInfo = new JLabel("");
        yearInfo = new JLabel("");
        distanceInfo = new JLabel("");

        add(massLbl, c);
        add(sizeLbl, c);
        add(moonsLbl, c);
        add(yearLbl, c);
        add(distanceLbl, c);
    }
}
