package View;

import Controller.Controller;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.event.EventHandler;
import javax.imageio.ImageIO;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

public class ImageGalleryPanel extends JPanel //implements ActionListener
{
    private JPanel panel;
    private JPanel panelBtn;

    private JLabel lblimage1, lblimage2, lblimage3, lblimage4;

    private Button btnSound;
    private Button btnMute;

    private Controller controller;

    public ImageGalleryPanel()
    {
        this.controller = controller;
        createPanel();
    }

    /**
     * @Author: Manna Manojlovic, Lanna Maslo
     * @version 1.0
     *
     * Method for initializing the components and the panel for images
     * Placing all components on Jpanel.
     * This method also reads images through BufferedImage and sets the images as
     * ImageIcons, which are then set as a swing component: JLabel in order to be
     * shown on the swing panel.
     *
     * This method also has a JavaFX-panel and a JavaFX- sound icon,
     * which plays a planetary sound on click, by calling playSound().
     */
    public void createPanel()
    {
        GridLayout layout = new GridLayout (1,5,4,4);
        JFXPanel jfx = new JFXPanel();

        panel = new JPanel (layout);
        panelBtn = new JPanel();

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

        panelBtn.setPreferredSize(new Dimension(70, 70));

        setBackground (Color.BLACK);

        panel.add (lblimage1);
        panel.add (lblimage2);
        panel.add (lblimage3);
        panel.add (lblimage4);

        panelBtn.add(jfx);
        panelBtn.setBackground(Color.black);

        add (panel, BorderLayout.EAST);
        add (panelBtn, BorderLayout.WEST);

        Platform.runLater(new Runnable(){
            public void run(){
                initFX(jfx);
            }
        });
    }

    /**
     * Creates a sound-button and adds it to a JavaFX panel
     * When btnSound is clicked, the method playSound() starts playing the sound of the chosen planet
     *
     * @param jfxPanel The JavaFX panel to be created
     * @author Lanna Maslo
     * @version 1.0
     */
    private void initFX(JFXPanel jfxPanel)
    {
        Image soundOn = new Image("https://cdn4.iconfinder.com/data/icons/defaulticon/icons/png/256x256/media-volume-1.png");
        Image mute = new Image("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.iconsdb." +
                "com%2Fwhite-icons%2Fmute-2-icon.html&psig=AOvVaw1SNJ28YJ6vOT7qaDDEJZnW&ust=1587125240469000&source=" +
                "images&cd=vfe&ved=0CAIQjRxqFwoTCKjrsLP07OgCFQAAAAAdAAAAABAD");

        ImageView soundIcon = new ImageView(soundOn);
        ImageView muteIcon = new ImageView(mute);

        soundIcon.setFitHeight(40);
        soundIcon.setFitWidth(40);
        muteIcon.setFitHeight(40);
        muteIcon.setFitWidth(40);

        btnSound = new Button ("", soundIcon);
//        btnMute = new Button ("", muteIcon);
        btnSound.setMinSize (50, 50);
//        btnMute.setMinSize(50,50);
        btnSound.setOnAction (event -> controller.startSoundThread());    //starts thread and resumes after pause
//        btnMute.setOnAction(event -> controller.togglePauseSound());                 //pause thread

        Group root = new Group();
        root.getChildren().add(btnSound);
//        root.getChildren().add (btnMute);
        Scene scene = new Scene(root);
        jfxPanel.setBackground(Color.black);
        jfxPanel.setScene(scene);
    }


}
