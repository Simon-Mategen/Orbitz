package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @Author: Manna Manojlovic
 *
 * Panel for the gallery of images.
 * 4 images of the planet and/or its' moon/moons.
 * 1 enlarged image shows up when user clicks one of the small images.
 *
 */

public class ImageGalleryPanel extends JPanel
{

    private JPanel panel;
    private JLabel lblimage1, lblimage2, lblimage3, lblimage4;

    public ImageGalleryPanel()
    {
        createPanel();
    }


    public void createPanel()
    {
        GridLayout layout = new GridLayout (1,4,4,4);

        panel = new JPanel (layout);

        lblimage1 = new JLabel();
        lblimage2 = new JLabel();
        lblimage3 = new JLabel();
        lblimage4 = new JLabel();

        BufferedImage img1 = null;
        BufferedImage img2 = null;
        BufferedImage img3 = null;
        BufferedImage img4 = null;

        try
        {
            img1 = ImageIO.read (new File("images/image1.jpg"));
            img2 = ImageIO.read (new File ("images/image2.jpg"));
            img3 = ImageIO.read (new File ("images/image3.jpg"));
            img4 = ImageIO.read (new File ("images/image4.jpg"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        ImageIcon icon1 = new ImageIcon (img1);
        ImageIcon icon2 = new ImageIcon (img2);
        ImageIcon icon3 = new ImageIcon (img3);
        ImageIcon icon4 = new ImageIcon (img4);

        lblimage1.setIcon (icon1);
        lblimage2.setIcon (icon2);
        lblimage3.setIcon (icon3);
        lblimage4.setIcon (icon4);

        panel.setPreferredSize (new Dimension (600,200));
        panel.setBackground (Color.black);

        setBackground (Color.black);

        panel.add (lblimage1);
        panel.add (lblimage2);
        panel.add (lblimage3);
        panel.add (lblimage4);

        add (panel, BorderLayout.EAST);
    }

}
