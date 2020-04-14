package View;

import Controller.Controller;
import Model.Orbit;
import Model.Planet;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.LinkedList;

public class Mainframe extends JFrame
{
    private Controller controller;

    private LinkedList<Planet> planetList = new LinkedList<>();
    private LinkedList<Orbit> orbitList = new LinkedList<>();
    private JFXPanel orbitPanel;
    private final int WIDTH = 1200;
    private final int HEIGHT = 700;
    private StackPane root;

    private JPanel componentPanel = new JPanel();

    private JButton changeBackgroundBtn = new JButton("Byt bakgrund");

    public Mainframe(Controller inController)
    {
        this.controller = inController;

        orbitPanel = new JFXPanel();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(orbitPanel);
            }
        });

        setLayout(new BorderLayout());

        //setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 900);
        setVisible(true);

        add(orbitPanel, BorderLayout.WEST);

        componentPanel.setBackground(java.awt.Color.BLUE);
        add(componentPanel, BorderLayout.EAST);

        changeBackgroundBtn.setPreferredSize(new Dimension(200, 40));
        componentPanel.add(changeBackgroundBtn);
        changeBackgroundBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                Platform.runLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        root.setBackground(new Background(
                                Collections.singletonList(new BackgroundFill(
                                        Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)),
                                Collections.singletonList(new BackgroundImage(
                                        new Image("https://i0.wp.com/metro.co.uk/wp-content/uploads/2018/10/sei_36554009-212f.jpg?quality=90&strip=all&zoom=1&resize=644%2C483&ssl=1",
                                                WIDTH, HEIGHT, false, true),
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundPosition.CENTER,
                                        BackgroundSize.DEFAULT))));
                    }
                });
            }
        });
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

