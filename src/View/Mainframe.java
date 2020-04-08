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

import javax.swing.*;
import java.util.Collections;
import java.util.LinkedList;

public class Mainframe extends JPanel
{
    private OrbitaryWindow orbitaryWindow = new OrbitaryWindow();
    private Sphere earth;
    private Sphere moon;
    private LinkedList<Planet> planetList = new LinkedList<>();
    private LinkedList<Orbit> orbitList = new LinkedList<>();

    private Circle circle = new Circle(30);
    private StackPane root;

    public Mainframe()
    {
        circle.setFill(Color.RED);
        add(orbitaryWindow);
    }


    private void initFX(JFXPanel fxPanel) {
        // This method is invoked on JavaFX thread
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }

    private Scene createScene()
    {
        root = new StackPane();
        Scene scene = new Scene(root, 1200, 700);
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
        placePlanets();
        addOrbits();
        

        return scene;
    }

    public void addNodeToPane(Node node)
    {
        root.getChildren().add(node);
    }

    public OrbitaryWindow getOrbitaryWindow()
    {
        return orbitaryWindow;
    }

    public void placePlanets()
    {
        for (int i = 0; i < planetList.size()  ; i++)
        {
            root.getChildren().add(planetList.get(i).getSphereFromPlanet());
            planetList.get(i).getPathTransition();

        }
    }

    public void addOrbits()
    {
        for (int i = 0; i < orbitList.size() ; i++)
        {
            root.getChildren().add(orbitList.get(i).getEllipseFromOrbit());
        }
    }

    public void addPlanet(Planet planet)
    {
        planetList.add(planet);
    }

    public void changeColorCircle(Color color)
    {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run()
                    {
                        circle.setFill(color);
                        initFX(orbitaryWindow);
                    }
                });
    }

    public void addOrbit(Ellipse orbit)
    {
        root.getChildren().add(orbit);
    }

    public void init()
    {
        initFX(orbitaryWindow);
    }


}

