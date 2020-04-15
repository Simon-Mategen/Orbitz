package View;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author: Manna Manojlovic
 * @version: 1.0
 * Panel for the gallery of images.
 * 4 images of the planet and/or its' moon/moons.
 * 1 enlarged image shows up when user clicks one of the small images.
 *
 */

public class ImageGalleryPanel extends JPanel implements ActionListener
{
    private JPanel panel;
    private JLabel lblimage1, lblimage2, lblimage3, lblimage4;
    private JButton play;

    public ImageGalleryPanel()
    {
        createPanel();
    }

    public void createPanel()
    {
        GridLayout layout = new GridLayout (1,5,4,4);

        panel = new JPanel (layout);

        play = new JButton();

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
            img1 = ImageIO.read (new File("src/Images/image1.jpg"));
            img2 = ImageIO.read (new File ("src/Images/image2.jpg"));
            img3 = ImageIO.read (new File ("src/Images/image3.jpg"));
            img4 = ImageIO.read (new File ("src/Images/image4.jpg"));
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

        play.setPreferredSize(new Dimension(20,20));


        panel.add (lblimage1);
        panel.add (lblimage2);
        panel.add (lblimage3);
        panel.add (lblimage4);
        panel.add (play);

        add (panel, BorderLayout.EAST);
        play.addActionListener( this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        if (play == actionEvent.getSource())
        {
            playSound();
        }
    }

    public void playSound()
    {
        File file = new File("sound/Jupiter2001.wav");
        AudioInputStream ais = null;
        Clip clip = null;

        try
        {
            clip = AudioSystem.getClip();
        }
        catch (LineUnavailableException e)
        {
            e.printStackTrace();
        }
        
        try
        {
            ais = AudioSystem.getAudioInputStream(file);
        }
        catch (UnsupportedAudioFileException | IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            clip.open(ais);
        }
        catch (LineUnavailableException | IOException e)
        {
            e.printStackTrace();
        }

        //play the sound until user stops it
        clip.loop(Clip.LOOP_CONTINUOUSLY);

        SwingUtilities.invokeLater(() ->
        {
           //leave empty or click to exit, fix later
        });
    }
}
