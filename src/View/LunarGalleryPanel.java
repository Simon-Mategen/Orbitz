package View;

import Model.Planet;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Manna Manojlovic
 * @author Lanna Maslo
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
    private Planet planet;
    private ArrayList<ImageIcon> imageList;

    private JLabel lblGallery;


    public LunarGalleryPanel(Planet planet)
    {
        this.planet = planet;
        createPanel();
    }

    /**
     * Who wrote this?
     */
    public void createPanel()
    {
        setBackground(Color.black);

        lblGallery = new JLabel();
        lblGallery.setSize(320, 220);

        add(lblGallery);

        //imageList = new ArrayList<>();
        //addImages();
    }

  /*  public void addImages()
    {
        if (planet.getName().equals("Earth"))
        {
            readImages("src/Images/moon1.jpg");
            readImages("src/Images/moon2.jpg");
            readImages("src/Images/moon3.jpg");
            readImages("src/Images/moon4.jpg");
        }
        else if (planet.getName().equals("Mars"))
        {
            readImages();
        }
        else if (planet.getName().equals("Jupiter"))
        {
            readImages();
        }
        else if (planet.getName().equals("Saturn"))
        {
            readImages();
        }
        else if (planet.getName().equals("Uranus"))
        {
            readImages();
        }
        else if (planet.getName().equals("Neptune"))
        {
            readImages();
        }


        lblGallery.setIcon(imageList.get(0));
    }

    public void readImages(String filePath)
    {
        imageList.add(new ImageIcon(
                new ImageIcon(filePath).getImage()));

    }
    
   */
}
