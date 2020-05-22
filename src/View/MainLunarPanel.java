package View;

import Model.Planet;

import javafx.application.Platform;

import javafx.embed.swing.JFXPanel;

import javafx.geometry.Point3D;

import javafx.scene.control.Tooltip;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

/**
 * @author Manna Manojlovic
 * @author Lanna Maslo
 *
 * Main panel for all other lunar panels. They are placed on this panel,
 * which is used by MainLunarFrame.java
 *
 * This class also has a
 */
public class MainLunarPanel extends JPanel implements ActionListener
{
    private JButton returnBtn = new JButton("Stop sound");

    private JPanel btnPanel;

    private JLabel headline;

    private JFXPanel lunarModel = new JFXPanel();
    private JFXPanel centerPanel = new JFXPanel();

    private MediaPlayer player;

    private Sphere moon;

    private ImageView phobos;
    private ImageView deimos;

    private Group moonRoot = new Group();

    private Rotate rotate;

    private MainInfoFrame mainInfoFrame;

    private LunarTextPanel lunarTextPanel;

    private LunarGalleryPanel lunarGalleryPanel;

    private MainLunarFrame lunarFrame;

    private LunarPanelSouth lunarPanelSouth;

    private Planet planet;

    private double startDragX;
    private double startDragY;
    private double orgTransY;
    private double orgTransX;


    public MainLunarPanel(Planet planet)
    {
        this.planet = planet;

        lunarPanelSouth = new LunarPanelSouth(planet);
        lunarTextPanel = new LunarTextPanel(planet);
        lunarGalleryPanel = new LunarGalleryPanel(planet);

        setupPanel();
    }

    public void setupPanel()
    {
        setLayout(new BorderLayout());      //sets layout to JPanel which is extendedd
        setBackground(Color.black);         //sets background color to JPanel which is extended

        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        flowLayout.setHgap(70);

        JLabel gifLabel = new JLabel();     //for those moons that have gifs
        gifLabel.setSize(200, 153);
        gifLabel.setIcon(new ImageIcon("funfacts/moonLanding.gif"));

        btnPanel = new JPanel(flowLayout);
        btnPanel.setPreferredSize(new Dimension(1000, 100));
        btnPanel.setBackground(Color.black);

        returnBtn.setPreferredSize(new Dimension(120,25));
        returnBtn.addActionListener(this);

        initializeHeadLine();

        btnPanel.add(returnBtn);
        btnPanel.add(headline);

        lunarModel.setPreferredSize(new Dimension(270, 390));
        handleMouse();

        add(btnPanel,BorderLayout.NORTH);
        add(lunarGalleryPanel,BorderLayout.EAST);
        //add(gifLabel,BorderLayout.CENTER);
        add(lunarTextPanel,BorderLayout.WEST);

        if(!planet.getName().equals("Earth") && !planet.getName().equals("Mars"))       //earth and mars have 3D models, the other moons do not
        {
            add(lunarPanelSouth,BorderLayout.SOUTH);    //add all the other planets' moons images to south
        }
        else
            add(lunarModel,BorderLayout.CENTER); //if planet is earth or mars, add earth's and mars' moon models to south

            Platform.runLater(new Runnable()
            {
                public void run(){
                    initFX(lunarModel);
                }
            });
    }

    /**
     @author Manna Manojlovic
     * This is the headline title for each of the moon windows
     */
    public void initializeHeadLine()
    {
        headline = new JLabel();
        headline.setFont(new Font("Earth Orbiter", Font.BOLD, 55));
        headline.setForeground(Color.YELLOW);

        if (planet.getName().equals("Earth"))
        {
            headline.setText("THE MOON");
        }
        else if (planet.getName().equals("Mars"))
        {
           headline.setText("PHOBOS & DEIMOS");
        }
        else if(planet.getName().equals("Jupiter"))
        {
            headline.setText("JUPITER'S MOONS");
        }
        else if(planet.getName().equals("Saturn"))
        {
            headline.setText("SATURN'S MOONS");
        }
        else if(planet.getName().equals("Uranus"))
        {
            headline.setText("URANUS'S MOONS");
        }
        else if(planet.getName().equals("Neptune"))
        {
            headline.setText("NEPTUNE'S MOONS");
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == returnBtn)
        {
            stopSound();
        }
    }

    public void playSound(String filePath)
    {
        String file = filePath;
        Media media = new Media(new File(file).toURI().toString());
        player = new MediaPlayer(media);
        player.setCycleCount(1);
        player.play();
    }

    public void stopSound(){
        if(player != null){
            player.stop();
        }
    }

    /**
      @author Lanna Maslo
      * Creates a JFXPanel that contains a 3D model of the moon and realistic illustrations of the moons of Mars
     */
    public void initFX(JFXPanel lunarModel){
        Scene moonScene = new Scene(moonRoot);
        moonScene.setFill(javafx.scene.paint.Color.BLACK);
        moon = new Sphere();
        moon.setTranslateX(143);
        moon.setTranslateY(70);
        moon.setRadius(70);

        PhongMaterial moonMaterial = new PhongMaterial();
        moonMaterial.setDiffuseMap(new Image("Images/moon.jpg"));

        moon.setMaterial(moonMaterial);
        moon.setOnMouseClicked(event -> playSound("sound/earthMoon.mp3"));
        moon.setCursor(Cursor.HAND);

        Image phobosImg = new Image("Images/phobos.png");
        Image deimosImg = new Image("Images/deimos.png");
        phobos = new ImageView(phobosImg);
        deimos = new ImageView(deimosImg);

        phobos.setPreserveRatio(true);
        phobos.setFitWidth(130);
        deimos.setPreserveRatio(true);
        deimos.setFitWidth(130);
        phobos.setTranslateX(70);
        phobos.setTranslateY(40);
        deimos.setTranslateX(70);
        deimos.setTranslateY(200);

        phobos.setOnMouseClicked(event -> playSound("sound/marsPhobos.mp3"));
        deimos.setOnMouseClicked(event -> playSound("sound/marsDeimos.mp3"));

        phobos.setCursor(Cursor.HAND);
        deimos.setCursor(Cursor.HAND);

        Tooltip phobosT = new Tooltip("Phobos");
        Tooltip deimosT = new Tooltip("Deimos");
        Tooltip.install(phobos, phobosT);
        Tooltip.install(deimos, deimosT);

        Image moonGIF = new Image("https://data.whicdn.com/images/293111167/original.gif");
        ImageView moonLanding = new ImageView(moonGIF);
        moonLanding.setFitWidth(210);
        moonLanding.setPreserveRatio(true);
        moonLanding.setTranslateY(170);
        moonLanding.setTranslateX(40);

        if(planet.getName().equals("Earth")){
            moonRoot.getChildren().add(moon);
            moonRoot.getChildren().add(moonLanding);
        }else if(planet.getName().equals("Mars")){
            moonRoot.getChildren().add(phobos);
            moonRoot.getChildren().add(deimos);
        }

        lunarModel.setScene(moonScene);
        rotate = new Rotate();
        rotate.setPivotX(moon.getRadius());
        rotate.setPivotY(moon.getRadius());
    }

    /**
     @author Albin Ahlbeck
     * Enables rotation by mouse
     */
    public void handleMouse()
    {
        moonRoot.setOnMousePressed(event ->
        {
            startDragX = event.getSceneX();
            startDragY = event.getSceneY();

            orgTransX = moonRoot.getTranslateX();
            orgTransY = moonRoot.getTranslateY();
        });

        moonRoot.setOnMouseDragged(event ->
        {
            double offsetX = event.getSceneX() - startDragX;
            double offsetY = event.getSceneY() - startDragY;

            double newTransX = orgTransX + offsetX;
            double newTransY = orgTransY + offsetY;
            matrixRotateNode(moon, 0,  -newTransY / 100, newTransX / 100);
        });
    }

    /**
      @author Albin Ahlbeck
      * Sets rotation values
     */
    private void matrixRotateNode(Node n, double alf, double bet, double gam)
    {
        double A11 = Math.cos(alf) * Math.cos(gam);
        double A12 = Math.cos(bet) * Math.sin(alf) + Math.cos(alf) * Math.sin(bet) * Math.sin(gam);
        double A13 = Math.sin(alf) * Math.sin(bet) - Math.cos(alf) * Math.cos(bet) * Math.sin(gam);
        double A21 = -Math.cos(gam) * Math.sin(alf);
        double A22 = Math.cos(alf) * Math.cos(bet) - Math.sin(alf) * Math.sin(bet) * Math.sin(gam);
        double A23 = Math.cos(alf) * Math.sin(bet) + Math.cos(bet) * Math.sin(alf) * Math.sin(gam);
        double A31 = Math.sin(gam);
        double A32 = -Math.cos(gam) * Math.sin(bet);
        double A33 = Math.cos(bet) * Math.cos(gam);

        double d = Math.acos((A11 + A22 + A33 - 1d) / 2d);
        if (d != 0d) {
            double den = 2d * Math.sin(d);
            Point3D p = new Point3D((A32 - A23) / den, (A13 - A31) / den, (A21 - A12) / den);
            n.setRotationAxis(p);
            n.setRotate(Math.toDegrees(d));
        }
    }
}
