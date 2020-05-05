package View;

import javax.swing.*;
import java.awt.*;

/**
 * This class will show the moons of a planet in more detail and show more info about the planet history etc
 *
 * it will also show the scale of selected planet compared to Earth and the sun.
 *
 * not all planets have moons and won't have this extra frame
 *
 * The frame will act like the loading screen and not appear in a new, extra window, but replace the current one.
 * It will also have a return-button to return to MainInfoFrame.
 *
 */

public class LunarInfoPanel extends JFrame
{
    private JPanel panel = new JPanel();

    private JLabel moon = new JLabel("Här ska månar och extra vara! Ska fungera som LoadingScreen.java! ");

    public LunarInfoPanel ()

    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(500, 500));
        setLocation(100,120);
        setResizable(false);

        moon.setPreferredSize(new Dimension(400,50));
        moon.setForeground(Color.yellow);

        panel.add(moon,BorderLayout.CENTER);
        panel.setBackground(Color.black);

        add(panel);
    }
}
