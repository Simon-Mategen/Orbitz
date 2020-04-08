package View;

import Model.Orbit;
import Model.Planet;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

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
                        new Image("https://www.wallpaperup.com/uploads/wallpapers/2013/08/21/136960/e86660f10f06aef664c8d552a7ec487a-700.jpg",
                                WIDTH, HEIGHT, false, true),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        BackgroundSize.DEFAULT))));


        addPlanets();
        addOrbits();
        startOrbits();

        return scene;
    }

    public void addPlanets()
    {
        for (int i = 0; i < planetList.size()  ; i++)
        {
            root.getChildren().add(planetList.get(i).getSphereFromPlanet());
        }
    }

    public void startOrbits()
    {
        for (int i = 0; i < planetList.size() ; i++)
        {
            planetList.get(i).getPathTransiton().play(); // starts orbits
        }
    }

    public void addOrbits()
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

