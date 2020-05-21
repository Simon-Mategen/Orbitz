package View;

import Model.Planet;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

import javafx.scene.Group;
import javafx.scene.Scene;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Manna Manojlovic
 * @author Lanna Maslo
 * @author Albin Ahlbeck
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

    private JPanel panel;
    private JPanel panelBtn;

    private JLabel lblImage;

    private BasicArrowButton btnNext;
    private BasicArrowButton btnPrevious;

    private PreviousListener previousListener;
    private NextListener nextListener;

    private int picIndex = 0;

    public LunarGalleryPanel(Planet planet)
    {
        this.planet = planet;

        createPanel(planet);

        addImages();
    }

    public void createPanel(Planet planet)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); // makes the ui cross platform
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        setLayout(new BorderLayout());
        setBackground (Color.BLACK);

        JFXPanel jfx = new JFXPanel();

        this.planet = planet;

        panel = new JPanel (new BorderLayout());
        panelBtn = new JPanel(new BorderLayout());

        lblImage = new JLabel();

        previousListener = new PreviousListener();
        nextListener = new NextListener();

        btnNext = new BasicArrowButton(BasicArrowButton.EAST);
        btnNext.setPreferredSize(new Dimension(100, 50));
        btnNext.setMinimumSize(new Dimension(100, 50));

        btnPrevious = new BasicArrowButton(BasicArrowButton.WEST);
        btnPrevious.setPreferredSize(new Dimension(100, 50));
        btnNext.addActionListener(nextListener);
        btnPrevious.addActionListener(previousListener);
        btnNext.setFocusPainted(false);
        btnPrevious.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btnPrevious.setBackground(Color.black);
        btnNext.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btnNext.setBackground(Color.black);

        lblImage.setSize(336, 280);
        lblImage.setBorder(BorderFactory.createLineBorder(Color.BLACK, 8, false));

        imageList = new ArrayList<>();

        panel.setPreferredSize (new Dimension (350,280));
        panel.setBackground (Color.black);

        panelBtn.setPreferredSize(new Dimension(550, 200));

        panel.add(btnNext, BorderLayout.EAST);
        panel.add(btnPrevious, BorderLayout.WEST);
        panel.add(lblImage, BorderLayout.CENTER);

        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 30, 10));

        panelBtn.add(jfx, BorderLayout.WEST);
        panelBtn.setBackground(Color.black);

        add(panel);
        //add(panelBtn, BorderLayout.WEST);

        Platform.runLater(new Runnable()
        {
            public void run()
            {
                initFX(jfx);
            }
        });
    }

    private void initFX(JFXPanel jfxPanel)
    {
        javafx.scene.paint.Color fxColor = new javafx.scene.paint.Color(0,0,0,1);

        Group root = new Group();
        Scene scene = new Scene(root);

        scene.setFill(fxColor);
        jfxPanel.setPreferredSize(new Dimension(40, 200));
        jfxPanel.setBackground(Color.black);
        jfxPanel.setScene(scene);
    }

    /**
     * Adds images to each planet
     * @author Albin Ahlbeck
     * @author Manna Manojlovic
     * @version 1.0
     */
    public void addImages()
    {
        if (planet.getName().equals("Earth"))
        {
            readImages("src/Images/moon1.jpg");
            readImages("src/Images/moon2.jpg");
            readImages("src/Images/moon3.jpg");
            readImages("src/Images/moon4.jpg");
            readImages("src/Images/moon5.jpg");
        }
        else if (planet.getName().equals("Mars"))
        {
            readImages("src/Images/phobos1.jpg");
            readImages("src/Images/phobos2.jpg");
            readImages("src/Images/phobos3.jpg");
            readImages("src/Images/deimos1.jpg");
            readImages("src/Images/deimos2.jpg");

        }
        else if (planet.getName().equals("Jupiter"))
        {
            readImages("src/Images/io1.jpg");
            readImages("src/Images/europa1.jpg");
            readImages("src/Images/europa2.jpg");
            readImages("src/Images/ganymedes1.jpg");
            readImages("src/Images/ganymedes2.jpg");
            readImages("src/Images/callisto1.jpg");
            readImages("src/Images/callisto2.jpg");
        }
        else if (planet.getName().equals("Saturn"))
        {
            readImages("src/Images/titan1.jpg");
            readImages("src/Images/titan2.jpg");
            readImages("src/Images/titan3.jpeg");
            readImages("src/Images/dione1.jpg");
            readImages("src/Images/enceladus1.jpg");
            readImages("src/Images/enceladus2.jpg");
        }
        else if (planet.getName().equals("Uranus"))
        {
            readImages("src/Images/ariel1.jpg");
            readImages("src/Images/miranda1.jpg");
            readImages("src/Images/titania1.jpeg");

        }
        else if (planet.getName().equals("Neptune"))
        {
            readImages("src/Images/triton1.jpg");
            readImages("src/Images/triton2.jpg");
        }

        lblImage.setIcon(imageList.get(0));
    }

    public void readImages(String filePath)
    {
        imageList.add(new ImageIcon(
                new ImageIcon(filePath).getImage()));
    }

    /**
     * Listens to the next button
     * Iterates the gallery
     * @author Albin Ahlbeck
     * @version 1.0
     */
    private class NextListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            if (picIndex < imageList.size() - 1)
            {
                picIndex++;
            }
            else
            {
                picIndex = 0;
            }
            lblImage.setIcon(imageList.get(picIndex));
        }
    }

    /**
     * Listens to the previous button
     * Iterates the gallery
     * @author Albin Ahlbeck
     * @version 1.0
     */
    private class PreviousListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            if (picIndex > 0)
            {
                picIndex--;
            }
            else
            {
                picIndex = imageList.size() - 1;
            }
            lblImage.setIcon(imageList.get(picIndex));
        }
    }
}
