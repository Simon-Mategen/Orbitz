package View;

import Controller.Controller;
import Model.Planet;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Mainframe extends JFrame
{
    private Controller controller;

    private ArrayList<Planet> guiPlanetList;

    private JFXPanel orbitPanel;
    private JPanel overheadPanel;
    private final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private StackPane root;
    private JSlider timeSlider;
    private SliderListener sliderListener;
    private JLabel timeLabel;
    private int MAX_SLIDER_VALUE = 100;

    private JButton changeBackgroundBtn = new JButton("Byt bakgrund");
    private JButton speedBtn = new JButton("SPEED");

    public Mainframe(Controller inController)
    {
        controller = inController;

        guiPlanetList = controller.getPlanetArrayList();

        orbitPanel = new JFXPanel();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(orbitPanel);
            }
        });

        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set frame to size of user's screen
        setSize(WIDTH, HEIGHT);
        setVisible(true);

        orbitPanel.setPreferredSize(new Dimension(getWidth(), getHeight() - 100));
        add(orbitPanel, BorderLayout.WEST);

        overheadPanel = new JPanel();
        overheadPanel.setPreferredSize(new Dimension(1400, 60));
        overheadPanel.setBackground(java.awt.Color.WHITE);
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

        overheadPanel.add(timeLabel);
        overheadPanel.add(timeSlider);



        add(overheadPanel, BorderLayout.NORTH);
        changeBackgroundBtn.setPreferredSize(new Dimension(200, 40));
        overheadPanel.add(changeBackgroundBtn);

        speedBtn.setPreferredSize(new Dimension(200,40));
        overheadPanel.add(speedBtn);

        overheadPanel.setBorder(BorderFactory.createLineBorder(java.awt.Color.WHITE));
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

        speedBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                Platform.runLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        for (Planet p : guiPlanetList)
                        {
                            p.getPathTransiton().stop();

                            p.setDuration(new Duration(1000000));
                            p.createPathTransition();
                        }

                        root = new StackPane();
                        orbitPanel.setScene(new Scene(root, WIDTH, HEIGHT));
                        root.setBackground(new Background(
                                Collections.singletonList(new BackgroundFill(
                                        Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)),
                                Collections.singletonList(new BackgroundImage(
                                        new Image("https://ichef.bbci.co.uk/news/410/cpsprodpb/D6B0/production/_95806945_gettyimages-590147780.jpg",
                                                WIDTH, HEIGHT, false, true),
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundPosition.CENTER,
                                        BackgroundSize.DEFAULT))));


                        placePlanets(root);
                        //startOrbits(root);
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
        scene.setFill(Color.BLACK);
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

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000);
        camera.setNearClip(0.01);
        camera.setFarClip(2000.0);
        camera.setFieldOfView(35);
        camera.setTranslateX(650);
        camera.setTranslateY(300);
        scene.setCamera(camera);

        scene.addEventHandler(ScrollEvent.SCROLL, event ->{
            double delta = event.getDeltaY();
            root.translateZProperty().set(root.getTranslateZ() + delta);
        });

        scene.addEventHandler(KeyEvent.KEY_PRESSED, event ->{
            switch(event.getCode()){
                case W:
                    root.translateZProperty().set(root.getTranslateZ() + 10);
                    break;
                case S:
                    root.translateZProperty().set(root.getTranslateZ() - 10);
                    break;
            }
        });

        placePlanets(root);
        startOrbits(root);

        return scene;
    }

    public void placePlanets(Pane root)
    {
        for (int i = 0; i < guiPlanetList.size()  ; i++)
        {
            root.getChildren().add(guiPlanetList.get(i).getSphereFromPlanet()); //Adds planets
            root.getChildren().add(guiPlanetList.get(i).getPlanetOrbit().getEllipseFromOrbit());//Add orbits
            guiPlanetList.get(i).getPlanetOrbit().getEllipseFromOrbit().toBack();//Moves orbits behind planets
            StackPane.setMargin(guiPlanetList.get(i).getPlanetOrbit().getEllipseFromOrbit(),
                    new Insets(0, 0, 0, guiPlanetList.get(i).getPlanetOrbit().getXCord() * 2 ));

        }
    }

    public void startOrbits(Pane root)
    {
        for (int i = 0; i < guiPlanetList.size() ; i++)
        {
            guiPlanetList.get(i).getPathTransiton().play(); // starts orbits
        }
    }

    public void clearRoot()
    {
        root.getChildren().clear();
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

