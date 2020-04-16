package View;

import Model.Planet;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: Manna Manojlovic, Albin Ahlbeck
 * @version: 1.0
 *
 * This is the main frame for the planetary information GUI.
 * It has the MainInfoPanel main panel placed on it and the FX panel for lunar orbit.
 *
 */
public class MainInfoFrame extends JInternalFrame
{
    private MainInfoPanel panel;
    private JFXPanel planetaryPanel;
    private StackPane root;
    private Planet planet;

    /**
    Adds a planet to be displayed when creating a MainInfoFrame
     **/
    public MainInfoFrame (Planet planet)
    {
        this.planet = planet;
        frame(planet);
    }

    public void frame (Planet planet)
    {
        setLayout(new BorderLayout());
        panel = new MainInfoPanel ();
        planetaryPanel = new JFXPanel();
        setSize (1200, 800);
        setVisible (true);

        add(panel, BorderLayout.NORTH);
        planetaryPanel.setPreferredSize(new Dimension(600, 400));
        add(planetaryPanel);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(planetaryPanel, planet); // starts on the Java FX thread
            }
        });

    }

    /**
     * Creates a new scene from createScene and adds it to the Java FX window
     *
     * @param fxPanel The JavaFX panel to be created
     * @param planet to be displayed
     * @author Albin Ahlbeck
     * @version 1.0
     */
    private void initFX(JFXPanel fxPanel, Planet planet)
    {
        // This method is invoked on JavaFX thread
        Scene scene = createScene(); // default background
        fxPanel.setScene(scene);
    }

    public void placePlanets()
    {
            root.getChildren().add(planet.getSphereFromPlanet()); //Adds planets
            //root.getChildren().add(planet.getPlanetOrbit().getEllipseFromOrbit());//Add orbits
            /*planet.getPlanetOrbit().getEllipseFromOrbit().toBack();//Moves orbits behind planets
            StackPane.setMargin(planet.getPlanetOrbit().getEllipseFromOrbit(),
                    new javafx.geometry.Insets(0, 0, 0, planet.getPlanetOrbit().getXCord() * 2));

             */
    }

    /**
     * Creates the Java-FX scene
     *
     * @author Albin Ahlbeck
     * @version 1.0
     */
    private Scene createScene()
    {
        root = new StackPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.BLACK);
        placePlanets();
        //startOrbit();

        VBox vbox = new VBox();
        vbox.setLayoutX(20);
        vbox.setLayoutY(20);
        return scene;
    }

    /**
     * Starts the the planets movement
     *
     * @author Albin Ahlbeck
     * @version 1.0
     */
    public void startOrbit() {
            planet.getPathTransiton().play(); // starts orbits
    }

}
