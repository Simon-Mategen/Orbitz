package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.Random;

/**
 * @Author: Manna Manojlovic
 *
 * Funfact panel/area for Swen The Alien.
 * When user clicks on the alien a new fun fact about the planet/its moon/moons displays in a textarea.
 */

public class SwenTheAlien extends JPanel
{
    private JPanel panel;
    private JLabel lblSwen;
    private BorderLayout layout;

    private JTextArea funFactArea;

    private final Font font = new Font ("SansSerif", Font.PLAIN, 23);
    private final Color color = Color.yellow;

    private Random randomFacts = new Random ();
    private String [] funFactsTest = new String [] { "...that Jupiter can not become a star?",
            "...that Jupiter has 67 moons?",
            "...that Jupiter has rings?"};

    public SwenTheAlien ()
    {
        createPanel();

    }

    /**
     * @Author: Manna Manojlovic
     * @version: 1.0
     *
     * Panel for Swen The Alien and the fun fact textarea.
     * Creates a panel and initializes the components: image of Swen and the textArea
     * The Image is read through BufferedImage, set as an ImageIcon,
     * which in turn is set as a JLabel, a swing component.
     * In order to show an ImageIcon it has to be set as a Swing component.
     *
     */
    public void createPanel ()
    {

        GridLayout gridLayout = new GridLayout (0, 2, 4, 4);
        TitledBorder titledBorder = BorderFactory.createTitledBorder (null, " DID YOU KNOW...",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, font, color);

        panel = new JPanel (gridLayout);
        layout = new BorderLayout ();

        setLayout (layout);
        setBackground (Color.black);
        setBorder (titledBorder);

        panel.setPreferredSize (new Dimension (400, 200));
        panel.setBackground (Color.black);

        funFactArea = new JTextArea (100, 100);
        funFactArea.setLineWrap (true);
        funFactArea.setEditable (false);

        funFactArea.setFont (font);
        funFactArea.setBackground (Color.black);
        funFactArea.setForeground (color);

        lblSwen = new JLabel ();
        BufferedImage img1 = null;

        //reads image from images, sets the image as an ImageIcon, which is set as a Jlabel.
        try
        {
            img1 = ImageIO.read (new File ("src/Images/Swen.png"));
        } catch (IOException e)
        {
            e.printStackTrace ();
        }

        ImageIcon icon1 = new ImageIcon (img1);
        lblSwen.setIcon (icon1);

        //when user clicks on Swen image, he sets random fun facts as String to the textAre funFactArea
        lblSwen.addMouseListener (new MouseAdapter ()
        {
            @Override
            public void mouseClicked (MouseEvent e)
            {
                super.mouseClicked (e);

                int index = randomFacts.nextInt (funFactsTest.length);
                for (int i = 0; i < index; i++) {
                    funFactArea.setText (funFactsTest[index]);
                }
            }
        });

        panel.add (funFactArea);
        panel.add (lblSwen);

        add (panel, BorderLayout.SOUTH);
    }
}

