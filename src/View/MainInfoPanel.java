package View;

import Controller.Controller;
import Model.Planet;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

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

    //Controller controller;

    /**
     * @Author: Manna Manojlovic
     * version 1.0
     *
     * Constructor
     * Initializes the instances, calls method setupPanel()
     */
    public MainInfoPanel (Planet planet)
    {
        //this.controller = controller;
        imgPanel = new ImageGalleryPanel (planet);
        swenPanel = new SwenTheAlien (planet);
        planetaryPanel = new JFXPanel();
        setupPanel(planet);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
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
        add(planetaryPanel, BorderLayout.WEST);
        setBackground(Color.BLACK);
        planetSphere = new Sphere(80);
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
        planetSphere.setRotationAxis(Rotate.Y_AXIS);
        prepareAnimation();
        paintPlanet();
        /*VBox vbox = new VBox();
        vbox.setLayoutX(20);
        vbox.setLayoutY(20);

         */
        return scene;
    }

    public void prepareAnimation()
    {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l)
            {
                planetSphere.rotateProperty().set(planetSphere.getRotate() + 0.2);
            }
        };
        timer.start();
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
}
