package View;

import javax.swing.*;
import java.awt.*;

/**
 * @Author Manna Manojlovic
 * @Author VEM MER LOL???
 *
 * This class is the image gallery class for moon pictures
 * It's called by MainLunarPanel.java and placed on the east side of BorderLayout
 *
 * This class sets images for the moons as JLabels
 * The user can scroll through the images by using two buttons: previous and next
 * The buttons are illustrated as arrows in the GUI.
 *
 * Some planets don't have any moons and won't therefore have anything here.
 * Those planets that have MANY moons will only have the 4-5 innermost moons displayed here.
 */

public class LunarGalleryPanel extends JPanel
{
    public LunarGalleryPanel()
    {
        createPanel();
    }

    /**
     * Who wrote this?
     */
    public void createPanel()
    {
        JLabel lblGallery = new JLabel();
        lblGallery.setSize(320, 220);
        add(lblGallery);
        setBackground(Color.black);
    }
}
