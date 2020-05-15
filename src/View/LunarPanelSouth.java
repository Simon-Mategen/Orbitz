package View;

import Model.Planet;

import javax.swing.*;
import java.awt.*;

public class LunarPanelSouth extends JPanel
{
    private Planet planet;

    public LunarPanelSouth(Planet planet)
    {
        this.planet = planet;

        setBackground(Color.black);

        JLabel lblMoons = new JLabel();

        if (planet.getName().equals("Mars"))
        {
            lblMoons.setIcon(new ImageIcon("src/Images/mars_moons.jpg"));
        }
        else if(planet.getName().equals("Saturn"))
        {
            lblMoons.setIcon(new ImageIcon("src/Images/titan4.jpg"));
        }
        else if(planet.getName().equals("Jupiter"))
        {
            lblMoons.setIcon(new ImageIcon("src/Images/jupiter_moons.jpg"));
        }

        add(lblMoons);
    }
}
