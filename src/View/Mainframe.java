package View;

import Controller.Controller;
import Model.Orbit;
import Model.Planet;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Sphere;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    private JPanel overheadPanel;
    private final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private StackPane root;
    private JSlider timeSlider;
    private SliderListener sliderListener;
    private JLabel timeLabel;
    private int MAX_SLIDER_VALUE = 100;

    private JPanel componentPanel = new JPanel();

    private JButton changeBackgroundBtn = new JButton("Byt bakgrund");

    public Mainframe(Controller inController)
    {
        timeLabel = new JLabel("0");
        timeSlider = new JSlider();
        sliderListener = new SliderListener();
        timeSlider.setValue(0);
        timeSlider.setMaximum(MAX_SLIDER_VALUE);
        timeLabel.setPreferredSize(new Dimension(50, 50));
        timeSlider.setPreferredSize(new Dimension(400, 50));
        timeSlider.addChangeListener(sliderListener);
        sliderListener = new SliderListener();
        this.controller = inController;
        overheadPanel = new JPanel();
        overheadPanel.add(timeLabel);
        overheadPanel.add(timeSlider);

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
        // set frame to size of user's screen
        setSize(WIDTH, HEIGHT);
        setVisible(true);

        add(orbitPanel, BorderLayout.WEST);
        orbitPanel.setPreferredSize(new Dimension(getWidth(), getHeight() - 100));
        overheadPanel.setPreferredSize(new Dimension(1400, 60));
        //componentPanel.setPreferredSize(new Dimension(200, 200));
        overheadPanel.setBackground(java.awt.Color.WHITE);
        //componentPanel.setBackground(java.awt.Color.BLUE);
        //add(componentPanel, BorderLayout.EAST);
        add(overheadPanel, BorderLayout.NORTH);
        changeBackgroundBtn.setPreferredSize(new Dimension(200, 40));
        overheadPanel.add(changeBackgroundBtn);
        overheadPanel.setBorder(BorderFactory.createLineBorder(java.awt.Color.WHITE));
        //componentPanel.add(changeBackgroundBtn);
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

    private class SliderListener implements ChangeListener
    {

        @Override
        public void stateChanged(ChangeEvent changeEvent)
        {
            timeLabel.setText(Integer.toString(timeSlider.getValue()));
        }
    }


}

