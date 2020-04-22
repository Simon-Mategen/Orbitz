package View;

import Model.Planet;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;


import javax.sound.sampled.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import java.util.ArrayList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


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

    private JLabel lblimage1, lblimage2, lblimage3, lblimage4;
    private JLabel lblImage;

    private ArrayList<ImageIcon> imageList;

    private Button btnSound;
    private Button btnMute;
    private JButton btnNext;
    private JButton btnPrevious;

    private PreviousListener previousListener;
    private NextListener nextListener;

    private MediaPlayer mediaPlayer;

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
        //setBorder(BorderFactory.createLineBorder(Color.RED));
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

        btnNext = new JButton(">");
        btnPrevious = new JButton("<");
        btnNext.setFont(new Font("Arial", Font.BOLD, 20));
        btnPrevious.setFont(new Font("Arial", Font.BOLD, 20));
        btnNext.setPreferredSize(new Dimension(60, 50));
        btnPrevious.setPreferredSize(new Dimension(60, 50));
        btnNext.addActionListener(nextListener);
        btnPrevious.addActionListener(previousListener);

        lblImage.setSize(336, 280);

        imageList = new ArrayList<>();

        panel.setPreferredSize (new Dimension (410,280));
        panel.setBackground (Color.black);

        panelBtn.setPreferredSize(new Dimension(540, 200));

        setBackground (Color.BLACK);

        panel.add(lblImage, BorderLayout.CENTER);
        panel.add(btnPrevious, BorderLayout.WEST);
        panel.add(btnNext, BorderLayout.EAST);
        lblImage.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        panelBtn.add(jfx);
        panelBtn.setBackground(Color.black);

        add(panel, BorderLayout.EAST);
        add(panelBtn, BorderLayout.WEST);

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
               addImage("src/Images/earthWhole.png");
               addImage("src/Images/earthMountains.jpg");
               addImage("src/Images/earthOceanAndSand.jpg");
                addImage("src/Images/meteorCrater.jpg");
            }
            else if (planet.getName().equals("Jupiter")) {
                addImage("src/Images/image1.jpg");
                addImage("src/Images/image2.jpg");
                addImage("src/Images/image3.jpg");
                addImage("src/Images/image4.jpg");
            }
            else if (planet.getName().equals("Mars")) {
                addImage("src/Images/marsGround.jpg");
                addImage("src/Images/marsSunset.jpg");
                addImage("src/Images/marsIce.jpg");
            }
            else if (planet.getName().equals("Mercury")) {
                addImage("src/Images/MercuryBasin.jpg");
                addImage("src/Images/mercuryCrater.jpg");
            }
            else if (planet.getName().equals("Neptune")) {
                addImage("src/Images/neptuneDramatic.jpg");
                addImage("src/Images/neptuneWhole.jpg");
            }
            else if (planet.getName().equals("Saturn")) {
                addImage("src/Images/saturnFront.jpg");
                addImage("src/Images/saturnMoons.jpg");
                addImage("src/Images/saturnRings.jpg");
                addImage("src/Images/saturnWhole.png");
            }
            else if (planet.getName().equals("Uranus")) {
                addImage("src/Images/uranusWhole.jpg");
                addImage("src/Images/uranusFront.jpg");
                addImage("src/Images/uranusSide.jpg");
            }

            else if (planet.getName().equals("Venus")) {
                addImage("src/Images/venusWhole.jpg");
                addImage("src/Images/VenusFloor.jpg");
                addImage("src/Images/venusCrater.jpg");
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
                new ImageIcon(filePath).getImage().getScaledInstance(lblImage.getWidth(),
                        lblImage.getHeight(), java.awt.Image.SCALE_DEFAULT)));
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
        Image soundOn = new Image("https://cdn3.iconfinder.com/data/icons/eightyshades/512/29_Sound_alt-64.png");
        Image mute = new Image("https://cdn3.iconfinder.com/data/icons/eightyshades/512/30_Sound_off-64.png");
        Image info = new Image("Images/JupiterInfobox.png");

        ImageView soundIcon = new ImageView(soundOn);
        ImageView muteIcon = new ImageView(mute);
        ImageView infoBox = new ImageView(info);

        javafx.scene.paint.Color fxColor = new javafx.scene.paint.Color(0,0,0,1);

        Group root = new Group();

        Scene scene = new Scene(root);

        soundIcon.setFitHeight(17);
        soundIcon.setFitWidth(17);
        muteIcon.setFitHeight(17);
        muteIcon.setFitWidth(17);
        infoBox.setFitHeight(289);
        infoBox.setFitWidth(512);

        btnSound = new Button("", soundIcon);
        btnSound.setTooltip(new Tooltip("press to hear the sound of this planet"));
        btnMute = new Button("", muteIcon);
        btnMute.setTooltip(new Tooltip("press to mute it :("));
        btnSound.setMinSize(35, 35);
        btnMute.setMinSize(35,35);
        btnSound.setOnAction(event -> planetSounds());

        root.getChildren().add(btnSound);
        root.getChildren().add(btnMute);
        root.getChildren().add(infoBox);

        btnSound.setLayoutY(0);
        btnSound.setLayoutX(10);
        btnMute.setLayoutY(40);
        btnMute.setLayoutX(10);

        infoBox.setLayoutY(0);
        infoBox.setLayoutX(45);

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
