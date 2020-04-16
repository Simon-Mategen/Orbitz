package View;

import Controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.io.IOException;

/**
 * @Author: Manna Manojlovic
 *
* upper left corner: JavaFX-panel with lunar orbits around planets, in miniature
* bottom left corner: textarea with general facts about the planet
*
* upper right corner: image gallery with 4 images in miniature. Underneath is one big picture which changes after a small is clicked
* bottom right corner: SWEN THE ALIEN AND HIS FUN FACTS!!!
*/

public class MainInfoPanel extends JPanel
{
    private BorderLayout layout;
    private ImageGalleryPanel imgPanel;
    private SwenTheAlien swenPanel;
    //Controller controller;

    /**
     * @Author: Manna Manojlovic
     * version 1.0
     *
     * Constructor
     * Initializes the instances, calls method setupPanel()
     */
    public MainInfoPanel ()
    {
        //this.controller = controller;
        imgPanel = new ImageGalleryPanel ();

        swenPanel = new SwenTheAlien ();
        setupPanel ();
    }

    /**
     * @Author: Manna Manojlovic
     * @version: 1.0
     *
     * This method initializes a BorderLayout and places the panels from other classes on it.
     * this is the main panel for the Information-GUI
     *
     */
    public void setupPanel ()
    {
        int width = 1000;
        int height = 600;

        Border border = this.getBorder ();
        Border margin = BorderFactory.createEmptyBorder (12, 12, 12, 12);

        layout = new BorderLayout ();
        setLayout (layout);
        setBackground (Color.black);
        setPreferredSize (new Dimension (width, height));
        setBorder (new CompoundBorder (border, margin));

        add (imgPanel, BorderLayout.NORTH);
        add (swenPanel, BorderLayout.EAST);

    }
}
