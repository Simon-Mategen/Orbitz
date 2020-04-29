package View;

import Model.Planet;
;
import javafx.application.Platform;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.embed.swing.JFXPanel;

import javafx.geometry.Orientation;
import javafx.geometry.VerticalDirection;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;


import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;

import javafx.scene.control.Button;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
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

    private JLabel lblimage1, lblimage2, lblimage3, lblimage4;
    private JLabel lblImage;

    private ArrayList<ImageIcon> imageList;

    private BasicArrowButton btnNext;
    private BasicArrowButton btnPrevious;

    private PreviousListener previousListener;
    private NextListener nextListener;

    private MediaPlayer mediaPlayer;

    private int picIndex = 0;

    private InfoBoxPanel infoPanel;

    private Slider soundSlider;

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

        panel.setPreferredSize (new Dimension (410,280));
        panel.setBackground (Color.black);

        panelBtn.setPreferredSize(new Dimension(550, 200));

        panel.add(btnNext, BorderLayout.EAST);
        panel.add(btnPrevious, BorderLayout.WEST);
        panel.add(lblImage, BorderLayout.CENTER);

        panelBtn.add(infoPanel, BorderLayout.CENTER);        //information table
        panelBtn.add(jfx, BorderLayout.WEST);
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
        javafx.scene.paint.Color fxColor = new javafx.scene.paint.Color(0,0,0,1);

        Group root = new Group();
        Scene scene = new Scene(root);

        soundSlider = new Slider();
        soundSlider.setOrientation(Orientation.VERTICAL);
        soundSlider.setPrefHeight(80);
        soundSlider.setMax(100);
        soundSlider.setValue(20);
        soundSlider.setLayoutY(40);

        Image soundOnImage = new Image("Images/soundOn.png");
        ImageView soundOn = new ImageView(soundOnImage);
        soundOn.setFitHeight(20);
        soundOn.setFitWidth(20);
        soundOn.setLayoutY(8);
        soundOn.setCursor(Cursor.HAND);

        Image soundOffImage = new Image("Images/soundOff.png");
        ImageView soundOff = new ImageView(soundOffImage);
        soundOff.setFitHeight(20);
        soundOff.setFitWidth(20);
        soundOff.setLayoutY(8);
        soundOff.setCursor(Cursor.HAND);

        soundOff.setOnMouseClicked(event -> {
            planetSounds();
            root.getChildren().remove(soundOff);
            root.getChildren().add(soundOn);
        });

        soundOn.setOnMouseClicked(event -> {
            stopMp3();
            root.getChildren().remove(soundOn);
            root.getChildren().add(soundOff);
        });

        root.getChildren().add(soundSlider);
        root.getChildren().add(soundOff);

        scene.setFill(fxColor);
        jfxPanel.setPreferredSize(new Dimension(40, 200));
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
            playMp3("sound/Jupiter2001.mp3");
        }
        else if (planet.getName().equals("Sun"))
        {
            playMp3("sound/sun.mp3");
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
     * @author Manna Manojlovic
     * @author Lanna Maslo
     * @param filePath takes mp3 as parameter
     *
     *  This method is for the mp3-files that JavaFX can play when user selects a planet which has recordings in mp3.
     */
    public void playMp3(String filePath)
    {
        String bip = filePath;
        Media hit = new Media(new File(bip).toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        soundSlider.setValue((int)mediaPlayer.getVolume() * 100);

        soundSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(soundSlider.getValue() / 100);
            }
        });

        mediaPlayer.play();
    }

    public void stopMp3(){
        mediaPlayer.stop();
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
