package View;

import Controller.Controller;
import Model.Planet;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

/**
 * @Author: Manna Manojlovic
 *
* upper left corner: JavaFX-panel with lunar orbits around planets, in miniature
* bottom left corner: textarea with general facts about the planet
*
* upper right corner: image gallery with 4 images in miniature. Underneath is one big picture which changes after a small is clicked
* bottom right corner: SWEN THE ALIEN AND HIS FUN FACTS!!!
*/

public class MainInfoPanel extends JPanel
{
    private BorderLayout layout;
    private ImageGalleryPanel imgPanel;
    private SwenTheAlien swenPanel;
    private JFXPanel planetaryPanel;

    private Planet planet;

    private StackPane root;

    private Sphere planetSphere; // the sphere that will be shown

    private double startDragX;
    private double startDragY;
    private double orgTransY;
    private double orgTransX;

    private Rotate rotate;

    private AnimationTimer timer = null;

    /**
     * @Author: Manna Manojlovic
     * version 1.0
     *
     * Constructor
     * Initializes the instances, calls method setupPanel()
     */
    public MainInfoPanel (Planet planet)
    {

        imgPanel = new ImageGalleryPanel (planet);
        swenPanel = new SwenTheAlien (planet);
        planetaryPanel = new JFXPanel();
        setupPanel(planet);
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                initFX(planetaryPanel); // starts on the Java FX thread
            }
        });
    }

    /**
     * @Author: Manna Manojlovic
     * @version: 1.0
     *
     * This method initializes a BorderLayout and places the panels from other classes on it.
     * this is the main panel for the Information-GUI
     *
     */
    public void setupPanel (Planet planet)
    {
        int width = 1000;
        int height = 600;

        this.planet = planet;

        Border border = this.getBorder ();
        Border margin = BorderFactory.createEmptyBorder (12, 12, 12, 12);

        layout = new BorderLayout ();
        setLayout (layout);
        setBackground (Color.black);
        setPreferredSize (new Dimension (width, height));
        setBorder (new CompoundBorder (border, margin));
        planetaryPanel.setPreferredSize(new Dimension(500, 300));

        add (imgPanel, BorderLayout.NORTH);
        add (swenPanel, BorderLayout.EAST);
        add (planetaryPanel, BorderLayout.WEST);
        setBackground(Color.BLACK);

        planetSphere = new Sphere(80);

        rotate = new Rotate();
        rotate.setPivotX(planetSphere.getRadius());
        rotate.setPivotY(planetSphere.getRadius());

    }

    /**
     * Creates a new scene from createScene and adds it to the Java FX window
     *
     * @param fxPanel The JavaFX panel to be created
     * @author Albin Ahlbeck
     * @version 1.0
     */
    private void initFX(JFXPanel fxPanel)
    {
        // This method is invoked on JavaFX thread
        Scene scene = createScene(); // default background
        fxPanel.setScene(scene);
    }
    /**
     *  Set up the scene
     * @author Albin Ahlbeck
     * @version 1.0
     */
    private Scene createScene()
    {
        root = new StackPane();
        Scene scene = new Scene(root, planetaryPanel.getWidth(), planetaryPanel.getHeight());
        scene.setFill(javafx.scene.paint.Color.BLACK);
        root.setBackground(null);
        root.getChildren().add(planetSphere);

        Tooltip tooltip = new Tooltip("Rotate me!");
        tooltip.setStyle("-fx-font-size: 20");                   //CSS stylesheet, Oracle doc.
        tooltip.setShowDelay(Duration.millis(1));               //sets time before text appears after hovering over image

        planetSphere.setPickOnBounds(true);
        Tooltip.install(planetSphere, tooltip);
        paintPlanet();
        handleMouse();
        return scene;
    }

    public void prepareAnimationX(double spinvalue)
    {
        planetSphere.setRotationAxis(Rotate.X_AXIS);
        timer = new AnimationTimer() {
            @Override
            public void handle(long l)
            {
                planetSphere.rotateProperty().set(planetSphere.getRotate() + spinvalue);
            }
        };
        timer.start();
    }

    public void prepareAnimationY(double spinvalue)
    {
        planetSphere.setRotationAxis(Rotate.Y_AXIS);
        timer = new AnimationTimer() {
            @Override
            public void handle(long l)
            {
                planetSphere.rotateProperty().set(planetSphere.getRotate() + spinvalue * l);
            }
        };
        timer.start();
    }

    public void handleMouse()
    {
        root.setOnMousePressed(event ->
        {
            startDragX = event.getSceneX();
            startDragY = event.getSceneY();

            orgTransX = root.getTranslateX();
            orgTransY = root.getTranslateY();
        });

        root.setOnMouseDragged(event ->
        {
            double offsetX = event.getSceneX() - startDragX;
            double offsetY = event.getSceneY() - startDragY;

            double newTransX = orgTransX + offsetX;
            double newTransY = orgTransY + offsetY;
            matrixRotateNode(planetSphere, 0,  -newTransY / 100, newTransX / 100);
        });
    }

    private void matrixRotateNode(Node n, double alf, double bet, double gam){
        double A11=Math.cos(alf)*Math.cos(gam);
        double A12=Math.cos(bet)*Math.sin(alf)+Math.cos(alf)*Math.sin(bet)*Math.sin(gam);
        double A13=Math.sin(alf)*Math.sin(bet)-Math.cos(alf)*Math.cos(bet)*Math.sin(gam);
        double A21=-Math.cos(gam)*Math.sin(alf);
        double A22=Math.cos(alf)*Math.cos(bet)-Math.sin(alf)*Math.sin(bet)*Math.sin(gam);
        double A23=Math.cos(alf)*Math.sin(bet)+Math.cos(bet)*Math.sin(alf)*Math.sin(gam);
        double A31=Math.sin(gam);
        double A32=-Math.cos(gam)*Math.sin(bet);
        double A33=Math.cos(bet)*Math.cos(gam);

        double d = Math.acos((A11+A22+A33-1d)/2d);
        if(d!=0d){
            double den=2d*Math.sin(d);
            Point3D p= new Point3D((A32-A23)/den,(A13-A31)/den,(A21-A12)/den);
            n.setRotationAxis(p);
            n.setRotate(Math.toDegrees(d));
        }
    }

    /**
     * Adds material to the planetSphere
     * @author Lanna Maslo
     * @author Albin Ahlbeck
     * @version 1.0
     */

    public void paintPlanet() {
            PhongMaterial map = new PhongMaterial();
            map.setDiffuseMap(new Image("Images/" + planet.getName() + ".jpg"));
            planetSphere.setMaterial(map);
    }

    public void stopMp3(){
        imgPanel.stopMp3();
    }
}
