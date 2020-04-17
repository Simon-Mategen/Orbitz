package View;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javax.imageio.ImageIO;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


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

    /**
     * @Author Manna Manojlovic
     * @version 1.0
     * 
     * Constructor
     * Calls createPanel()
     */
    public ImageGalleryPanel()
    {

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

        panelBtn.setPreferredSize(new Dimension(300, 200));

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
        Image soundOn = new Image("https://cdn3.iconfinder.com/data/icons/eightyshades/512/29_Sound_alt-128.png");
        Image mute = new Image("https://cdn3.iconfinder.com/data/icons/eightyshades/512/30_Sound_off-128.png");

        ImageView soundIcon = new ImageView(soundOn);
        ImageView muteIcon = new ImageView(mute);

        javafx.scene.paint.Color fxColor = new javafx.scene.paint.Color(0,0,0,1);

        Group root = new Group();

        Scene scene = new Scene(root);

        soundIcon.setFitHeight(20);
        soundIcon.setFitWidth(20);
        muteIcon.setFitHeight(20);
        muteIcon.setFitWidth(20);

        btnSound = new Button ("", soundIcon);
        btnMute = new Button ("", muteIcon);
        btnSound.setMinSize (40, 40);
        btnMute.setMinSize(40,40);
        btnSound.setOnAction (event -> playSound());
//        btnMute.setOnAction(event -> stopSound());

        root.getChildren().add(btnSound);
        root.getChildren().add(btnMute);

        btnSound.setLayoutY(45);
        btnSound.setLayoutX(255);
        btnMute.setLayoutY(103);
        btnMute.setLayoutX(255);

        scene.setFill(fxColor);
        jfxPanel.setBackground(Color.black);
        jfxPanel.setScene(scene);
    }

    /**
     * @Author: Manna Manojlovic
     * @version 1.0
     *
     * When user clicks play button, a thread that calls this method starts.
     *
     * Method for playing a .wav-file. Reads the .wav through AudioInputStream an plays it via Clip.
     * Clip class also has a built in loop for continuously playing the sound until user stops it manually.
     *
     * Stop button currently is NOT WORKING!!! Pressing the soundOn button will increase sound instead,
     * because it creates new instances every time
     */
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

        });
    }

}
