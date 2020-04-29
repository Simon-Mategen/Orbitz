package View;

import Model.Planet;
;
import javafx.application.Platform;

import javafx.embed.swing.JFXPanel;

import javafx.scene.Group;
import javafx.scene.Scene;


import javafx.scene.control.Tooltip;

import javafx.scene.control.Button;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;


import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import java.util.ArrayList;

import javafx.scene.media.Media;

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
    private Planet planet;
    private JPanel panel;
    private JPanel panelBtn;

    private JLabel lblImage;

    private ArrayList<ImageIcon> imageList;

    private Button btnSound;
    private Button btnMute;
    private BasicArrowButton btnNext;
    private BasicArrowButton btnPrevious;

    private PreviousListener previousListener;
    private NextListener nextListener;

    private MediaPlayer mediaPlayer;

    private int picIndex = 0;

    private InfoBoxPanel infoPanel;

    /**
     * @Author Manna Manojlovic
     * @version 1.0
     * 
     * Constructor
     * Calls createPanel(), addImages and creates instance of InfoBoxPanel
     */
    public ImageGalleryPanel(Planet planet)
    {
        infoPanel = new InfoBoxPanel(planet);
        createPanel(planet);
        addImages();
    }

    /**
     * @Author: Manna Manojlovic, Lanna Maslo
     * @version 1.0
     *
     * Method for initializing the components and the panel for images
     * Placing all components on Jpanel.
     *
     * places the table with info about planet on the panel.
     *
     * This method also has a JavaFX-panel and JavaFX- sound icons,
     * which plays a planetary sound on click, by calling playSound().
     */
    public void createPanel(Planet planet)
    {
        try {
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
        panelBtn = new JPanel();

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

        panel.setPreferredSize (new Dimension (410,280));
        panel.setBackground (Color.black);

        panelBtn.setPreferredSize(new Dimension(540, 200));

        panel.add(btnNext, BorderLayout.EAST);
        panel.add(btnPrevious, BorderLayout.WEST);
        panel.add(lblImage, BorderLayout.CENTER);

        panelBtn.add(infoPanel);        //information table
        panelBtn.add(jfx);
        panelBtn.setBackground(Color.black);

        add(panel, BorderLayout.EAST);
        add(panelBtn, BorderLayout.WEST);

        Platform.runLater(new Runnable()
        {
            public void run(){
                initFX(jfx);
            }
        });
    }
    /**
     * Adds images to each planet
     * @author Albin Ahlbeck
     * @author Manna Manojlovic
     * @version 1.0
     */
    public void addImages()
    {
        System.out.println("Planet "  + planet.getName());
        try {

            if (planet.getName().equals("Mercury"))
            {
                addImage("src/Images/m1.jpg");
                addImage("src/Images/m2.jpg");
                addImage("src/Images/m3.jpg");
            }
            else if (planet.getName().equals("Venus"))
            {
                addImage("src/Images/venus1.jpg");
                addImage("src/Images/venus2.jpg");
                addImage("src/Images/venus3.jpg");
                addImage("src/Images/venus4.jpg");
                addImage("src/Images/venus5.jpg");
            }
            else if (planet.getName().equals("Earth"))
            {
                addImage("src/Images/earth1.jpg");
                addImage("src/Images/earth2.jpg");
                addImage("src/Images/earth3.jpg");
                addImage("src/Images/earth4.jpg");
                addImage("src/Images/earth5.jpg");
            }
            else if (planet.getName().equals("Mars"))
            {
                addImage("src/Images/mars1.jpg");
                addImage("src/Images/mars2.jpg");
                addImage("src/Images/mars3.jpg");
                addImage("src/Images/mars4.jpg");
                addImage("src/Images/mars5.jpg");
                addImage("src/Images/mars6.jpg");
            }
            else if (planet.getName().equals("Jupiter"))
            {
                addImage("src/Images/jupiter1.jpg");
                addImage("src/Images/jupiter2.jpg");
                addImage("src/Images/jupiter3.jpg");
                addImage("src/Images/jupiter4.jpg");
                addImage("src/Images/jupiter5.jpg");

            }
            else if (planet.getName().equals("Saturn"))
            {
                addImage("src/Images/saturn1.jpg");
                addImage("src/Images/saturn2.jpg");
                addImage("src/Images/saturn3.jpg");
                addImage("src/Images/saturn4.jpg");
            }
            else if (planet.getName().equals("Uranus"))
            {
                addImage("src/Images/uranus1.jpg");
                addImage("src/Images/uranusFront.jpg");
                addImage("src/Images/uranusSide.jpg");
            }
            else if (planet.getName().equals("Neptune"))
            {
                addImage("src/Images/neptune1.jpg");
                addImage("src/Images/neptune2.jpg");
                addImage("src/Images/neptune3.jpg");
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

    public void addImage(String filePath)
    {
        imageList.add(new ImageIcon(
                new ImageIcon(filePath).getImage()));
    }

    /**
     * Creates a sound-button and adds it to a JavaFX panel
     * When btnSound is clicked, the method playSound() starts playing the sound of the chosen planet
     *
     * @param jfxPanel The JavaFX panel to be created
     * @author Lanna Maslo
     * @author Manna Manojlovic
     * @version 1.0
     */
    private void initFX(JFXPanel jfxPanel)
    {
        Image soundOn = new Image("https://cdn3.iconfinder.com/data/icons/eightyshades/512/29_Sound_alt-64.png");
        Image mute = new Image("https://cdn3.iconfinder.com/data/icons/eightyshades/512/30_Sound_off-64.png");

        ImageView soundIcon = new ImageView(soundOn);
        ImageView muteIcon = new ImageView(mute);

        javafx.scene.paint.Color fxColor = new javafx.scene.paint.Color(0,0,0,1);

        Group root = new Group();

        Scene scene = new Scene(root);

        soundIcon.setFitHeight(17);
        soundIcon.setFitWidth(17);
        muteIcon.setFitHeight(17);
        muteIcon.setFitWidth(17);

        btnSound = new Button("", soundIcon);
        btnSound.setTooltip(new Tooltip("press to hear the sound of this planet"));
        btnMute = new Button("", muteIcon);
        btnMute.setTooltip(new Tooltip("press to mute it :("));
        btnSound.setMinSize(35, 35);
        btnMute.setMinSize(35,35);
        btnSound.setOnAction(event -> planetSounds());

        root.getChildren().add(btnSound);
        root.getChildren().add(btnMute);

        btnSound.setLayoutY(0);
        btnSound.setLayoutX(10);
        btnMute.setLayoutY(40);
        btnMute.setLayoutX(10);

        scene.setFill(fxColor);
        jfxPanel.setBackground(Color.black);
        jfxPanel.setScene(scene);

    }

    /**
     * @Author: Manna Manojlovic
     * @version 1.0
     *
     * This method reads the sound files depending on which planet is selected by user.
     * Go to playSound() for the code for playing the actual sound.
     */
    public void planetSounds()
    {
        if  (planet.getName().equals("Jupiter"))
        {
            playWav("sound/Jupiter2001.wav");
        }
        else if (planet.getName().equals("Sun"))
        {
            playWav("sound/sun.wav");
        }
        else if (planet.getName().equals("Saturn"))
        {
            playMp3("sound/saturn.mp3");
        }
        else if (planet.getName().equals("Earth"))
        {
            playMp3("sound/earth.mp3");
        }

    }

    /**
     * @Author: Manna Manojlovic
     * @version 1.0
     *
     * When user clicks play button, a thread that calls this method starts.
     *
     * Method for playing a .wav-file. Reads the .wav through AudioInputStream an plays it via Clip.
     * Clip class also has a built in loop for continuously playing the sound until user stops it manually.
     */
    public void playWav(String filePath)
    {
        try{
            File file = new File(filePath);

            if(file.exists()){
                AudioInputStream ais = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(ais);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);

                btnMute.setOnAction(event -> {
                    clip.getMicrosecondPosition();
                    clip.stop();
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Manna Manojlovic
     * @param filePath takes mp3 as parameter
     *
     *  This method is for the mp3-files that JavaFX can play when user selects a planet which has recordings in mp3.
     */
    public void playMp3(String filePath)
    {
        String bip = filePath;
        Media hit = new Media(new File(bip).toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.setOnReady(new Runnable()
        {
            public void run()
            {
                mediaPlayer.play();

                btnMute.setOnAction(event -> {

                    mediaPlayer.stop();
                });

            }
        });

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
