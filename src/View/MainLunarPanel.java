package View;

import Model.Planet;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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

public class MainLunarPanel extends JPanel implements ActionListener
{
    private JButton returnBtn = new JButton("< Previous");
    private JPanel btnPanel;
    private JFXPanel lunarModel = new JFXPanel();
    private MediaPlayer player;
    private Sphere moon = new Sphere();
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

//    private Planet planet;

    public MainLunarPanel(Planet planet)
    {
        this.planet = planet;
        setupPanel();

    }

    public void setupPanel()
    {
        setLayout(new BorderLayout());

        lunarPanelSouth = new LunarPanelSouth(planet);

        lunarTextPanel = new LunarTextPanel(planet);
        lunarGalleryPanel = new LunarGalleryPanel(planet);
        setBackground(Color.black);

        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        flowLayout.setHgap(70);

        btnPanel = new JPanel(flowLayout);
        btnPanel.setPreferredSize(new Dimension(1000, 100));
        btnPanel.setBackground(Color.black);

        JLabel headline = new JLabel("THE MOON");
        headline.setFont(new Font("Earth Orbiter", Font.BOLD, 55));
        headline.setForeground(Color.YELLOW);

        JLabel gifLabel = new JLabel();
        gifLabel.setSize(300, 229);
        gifLabel.setIcon(new ImageIcon("funfacts/moonLanding.gif"));

        returnBtn.setPreferredSize(new Dimension(120,25));
        returnBtn.addActionListener(this);

        btnPanel.add(returnBtn);
        btnPanel.add(headline);

        lunarModel.setPreferredSize(new Dimension(1000, 120));
        handleMouse();

        add(btnPanel,BorderLayout.NORTH);
        add(lunarGalleryPanel,BorderLayout.EAST);
        add(gifLabel,BorderLayout.CENTER);
        add(lunarTextPanel,BorderLayout.WEST);

        if(!planet.getName().equals("Earth"))
        {
            add(lunarPanelSouth,BorderLayout.SOUTH);
        }
        else
            add(lunarModel,BorderLayout.SOUTH);

            Platform.runLater(new Runnable()
            {
                public void run(){
                    initFX(lunarModel);
                }
            });

    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == returnBtn) {
            //mainInfoFrame.setVisible(true);
            player.stop();
        }
    }

    public void playSound(String filePath) {
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

    /*public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == moon) {
            playSound("sound/earthMoon.mp3");
        }else if(e.getSource() == deimos) {
            playSound("sound/marsDeimos.mp3");
        }else if(e.getSource() == phobos) {
            playSound("sound/marsPhobos.mp3");
        }
    }*/

    /**
     * Creates a JFXPanel that contains a 3D model of the moon
     * @author Lanna Maslo
     * @version 1.0
     */

    public void initFX(JFXPanel lunarModel){
        Scene moonScene = new Scene(moonRoot);
        moonScene.setFill(javafx.scene.paint.Color.BLACK);
        moon.setTranslateX(500);
        moon.setTranslateY(50);
        moon.setRadius(50);
        PhongMaterial moonMaterial = new PhongMaterial();
        moonMaterial.setDiffuseMap(new Image("Images/moon.jpg"));
        moon.setMaterial(moonMaterial);
        moonRoot.getChildren().add(moon);
        lunarModel.setScene(moonScene);
        rotate = new Rotate();
        rotate.setPivotX(moon.getRadius());
        rotate.setPivotY(moon.getRadius());
    }

    /**
     * Enables rotation by mouse
     * @author Albin Ahlbeck
     * @version 1.0
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
     * Sets rotation values
     * @author Albin Ahlbeck
     * @version 1.0
     */

    private void matrixRotateNode(Node n, double alf, double bet, double gam) {
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
