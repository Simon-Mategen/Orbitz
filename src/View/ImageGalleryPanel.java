package View;

import Model.Planet;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


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
    Planet planet;
    private JPanel panel;
    private JPanel panelBtn;

    private JLabel lblimage1, lblimage2, lblimage3, lblimage4;
    private JLabel lblImage;

    private ArrayList<ImageIcon> imageList;

    private Button btnSound;
    private Button btnMute;
    private JButton btnNext;
    private JButton btnPrevious;

    private PreviousListener previousListener;
    private NextListener nextListener;

    private int picIndex = 0;

    /**
     * @Author Manna Manojlovic
     * @version 1.0
     * 
     * Constructor
     * Calls createPanel()
     */
    public ImageGalleryPanel(Planet planet)
    {
        createPanel(planet);
        addImages();
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
    public void createPanel(Planet planet)
    {
        setBorder(BorderFactory.createLineBorder(Color.RED));
        GridLayout layout = new GridLayout (1,5,4,4);
        setLayout(new BorderLayout());
        JFXPanel jfx = new JFXPanel();

        this.planet = planet;

        panel = new JPanel (new BorderLayout());
        panelBtn = new JPanel();

        lblimage1 = new JLabel();
        lblimage2 = new JLabel();
        lblimage3 = new JLabel();
        lblimage4 = new JLabel();
        lblImage = new JLabel();

        previousListener = new PreviousListener();
        nextListener = new NextListener();

        btnNext = new JButton("==>");
        btnPrevious = new JButton("<==");
        btnNext.setFont(new Font("TimesRoman", Font.BOLD, 22));
        btnPrevious.setFont(new Font("TimesRoman", Font.BOLD, 22));
        btnNext.setPreferredSize(new Dimension(100, 50));
        btnPrevious.setPreferredSize(new Dimension(100, 50));
        btnNext.addActionListener(nextListener);
        btnPrevious.addActionListener(previousListener);

        lblImage.setSize(300, 200);

        imageList = new ArrayList<>();

        BufferedImage img1 = null;
        BufferedImage img2 = null;
        BufferedImage img3 = null;
        BufferedImage img4 = null;
/*
        try
        {
            System.out.println("test");
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

        /*lblimage1.setIcon (icon1);
        lblimage2.setIcon (icon2);
        lblimage3.setIcon (icon3);
        lblimage4.setIcon (icon4);

         */


        panel.setPreferredSize (new Dimension (500,200));
        panel.setBackground (Color.black);

        panelBtn.setPreferredSize(new Dimension(300, 200));

        setBackground (Color.BLACK);
/*
        panel.add (lblimage1);
        panel.add (lblimage2);
        panel.add (lblimage3);
        panel.add (lblimage4);

 */

        panel.add(lblImage, BorderLayout.CENTER);
        panel.add(btnPrevious, BorderLayout.WEST);
        panel.add(btnNext, BorderLayout.EAST);
        lblImage.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        panelBtn.add(jfx);
        panelBtn.setBackground(Color.black);

        add (panel, BorderLayout.WEST);
        add (panelBtn, BorderLayout.EAST);

        Platform.runLater(new Runnable(){
            public void run(){
                initFX(jfx);
            }
        });
    }

    public void addImages()
    {
        System.out.println("Planet "  + planet.getName());
        try {

            if (planet.getName().equals("Earth"))
            {
                ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/Images/Earth.jpg").getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), java.awt.Image.SCALE_DEFAULT));
                imageList.add(imageIcon);
            }
            else if (planet.getName().equals("Jupiter")) {
                ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/Images/Jupiter.jpg").getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), java.awt.Image.SCALE_DEFAULT));
                imageList.add(imageIcon);
            }
            else if (planet.getName().equals("Mars")) {
                ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/Images/Mars.jpg").getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), java.awt.Image.SCALE_DEFAULT));
                imageList.add(imageIcon);
            }
            else if (planet.getName().equals("Mercury")) {
                ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/Images/Mercury.jpg").getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), java.awt.Image.SCALE_DEFAULT));
                imageList.add(imageIcon);
            }
            else if (planet.getName().equals("Neptune")) {
                ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/Images/Neptune.jpg").getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), java.awt.Image.SCALE_DEFAULT));
                imageList.add(imageIcon);
            }
            else if (planet.getName().equals("Saturn")) {
                ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/Images/Saturn.jpg").getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), java.awt.Image.SCALE_DEFAULT));
                imageList.add(imageIcon);
            }
            else if (planet.getName().equals("Uranus")) {
                ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/Images/Uranus.jpg").getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), java.awt.Image.SCALE_DEFAULT));
                imageList.add(imageIcon);
            }

            else if (planet.getName().equals("Venus")) {

                ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/Images/Venus.jpg").getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), java.awt.Image.SCALE_DEFAULT));
                imageList.add(imageIcon);
            }
            else
            {
                System.out.println("No planet found");
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        lblImage.setIcon(imageList.get(0));
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
