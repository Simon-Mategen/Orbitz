package View;

import Model.Planet;

import javax.swing.*;
import java.awt.*;
/**
 * @author Manna Manoljovic
 * A panel with pictures of the moons
 */
public class LunarPanelSouth extends JPanel
{
    private Planet planet;

    /**
     * Constructs the panel and adds pictures depending on the clicked planet
     * @param planet the planet that is currently being displayed
     */
    public LunarPanelSouth(Planet planet)
    {
        this.planet = planet;

        setBackground(Color.black);

        JLabel lblMoons = new JLabel();

        if(planet.getName().equals("Saturn"))
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
