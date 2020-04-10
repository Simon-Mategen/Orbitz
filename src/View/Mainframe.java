package View;

import Model.Orbit;
import Model.Planet;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Sphere;

import javax.swing.*;
import java.util.Collections;
import java.util.LinkedList;

public class Mainframe extends JFrame
{

    private LinkedList<Planet> planetList = new LinkedList<>();
    private LinkedList<Orbit> orbitList = new LinkedList<>();
    private  JFXPanel orbitPanel;
    private final int WIDTH = 1200;
    private final int HEIGHT = 700;
    private StackPane root;

    public Mainframe()
    {
        orbitPanel = new JFXPanel();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(orbitPanel);
            }
        });
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //pack();
        setVisible(true);

        setSize(1400, 900);
        add(orbitPanel);


    }


    private void initFX(JFXPanel fxPanel) {
        // This method is invoked on JavaFX thread
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }

    private Scene createScene()
    {
        root = new StackPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        root.setBackground(new Background(
                Collections.singletonList(new BackgroundFill(
                        Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)),
                Collections.singletonList(new BackgroundImage(
                        new Image("https://www.wallpaperup.com/uploads/wallpapers/2014/04/12/330551/185c99304364bba58fe8bfe3765fcf64.jpg",
                                WIDTH, HEIGHT, false, true),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        BackgroundSize.DEFAULT))));


        placePlanets();
        placeOrbits();
        startOrbits();

        return scene;
    }

    public void placePlanets()
    {
        for (int i = 0; i < planetList.size()  ; i++)
        {
            root.getChildren().add(planetList.get(i).getSphereFromPlanet());
            root.getChildren().add(planetList.get(i).getPlanetOrbit().getEllipseFromOrbit());
            planetList.get(i).getPlanetOrbit().getEllipseFromOrbit().toBack();
            StackPane.setMargin(planetList.get(i).getPlanetOrbit().getEllipseFromOrbit(),
                    new Insets(0, 0, 0, planetList.get(i).getPlanetOrbit().getXCord() * 2 ));

        }
    }

    public void startOrbits()
    {
        for (int i = 0; i < planetList.size() ; i++)
        {
            planetList.get(i).getPathTransiton().play(); // starts orbits
        }
    }

    public void placeOrbits()
    {
        for (int i = 0; i < orbitList.size() ; i++)
        {
            root.getChildren().add(orbitList.get(i).getEllipseFromOrbit());
        }
    }

    public void addSun(Sphere sun)
    {
        root.getChildren().add(sun);
    }

    public void addPlanet(Planet planet)
    {
        planetList.add(planet);
    }
    
    public void addOrbit(Ellipse orbit)
    {
        root.getChildren().add(orbit);
    }

    public void init()
    {
        initFX(orbitPanel);
    }

}

