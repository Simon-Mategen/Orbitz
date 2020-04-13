package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Panel for the gallery of images.
 * 4 images of the planet and/or its' moon/moons.
 * 1 enlarged image shows up when user clicks one of the small images.
 *
 */

//TODO 1 large image that changes depending on which small image is selected
//TODO add listeners - MouseActionEvent?


public class ImageGalleryPanel extends JPanel
{

    private GridLayout layout;
    private JPanel panel;
    private JLabel lblimage1, lblimage2, lblimage3, lblimage4;

    public ImageGalleryPanel() throws IOException
    {
        createPanel();
    }


    public void createPanel() throws IOException
    {
        layout = new GridLayout(1,4,4,4);
        panel = new JPanel(layout);
        panel.setPreferredSize(new Dimension(600,200));
        panel.setBackground(Color.black);
        setBackground(Color.black);

        lblimage1 = new JLabel();
        lblimage2 = new JLabel();
        lblimage3 = new JLabel();
        lblimage4 = new JLabel();

        BufferedImage img1 = ImageIO.read(new File("images/image1.jpg"));
        ImageIcon icon1 = new ImageIcon(img1);
        lblimage1.setIcon(icon1);

        BufferedImage img2 = ImageIO.read(new File("images/image2.jpg"));
        ImageIcon icon2 = new ImageIcon(img2);
        lblimage2.setIcon(icon2);

        BufferedImage img3 = ImageIO.read(new File("images/image3.jpg"));
        ImageIcon icon3 = new ImageIcon(img3);
        lblimage3.setIcon(icon3);

        BufferedImage img4 = ImageIO.read(new File("images/image4.jpg"));
        ImageIcon icon4 = new ImageIcon(img4);
        lblimage4.setIcon(icon4);

        panel.add(lblimage1);
        panel.add(lblimage2);
        panel.add(lblimage3);
        panel.add(lblimage4);

        add(panel, BorderLayout.EAST);
    }

}
