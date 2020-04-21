package View;

import Controller.Controller;
import Model.Planet;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.image.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

/**
 * MainFrame is the main window which contains various  graphical components
 @author Albin Ahlbeck
 @author Simon Måtegen
 @author Lanna Maslo
 @author Manna Manojlovic
 @author Marcus Svensson
 @version 1.0
 @
 */
public class MainFrame extends JFrame
{
    private final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private final int MAX_SLIDER_VALUE = 30;

    private ArrayList<Planet> guiPlanetList;

    private JFXPanel orbitPanel;
    private JPanel overheadPanel;
    private MainInfoFrame mainInfoFrame;
    private LoadingScreen loadingScreen = new LoadingScreen();

    private StackPane root;
    private JSlider timeSlider;
    private JButton changeBackgroundBtn = new JButton("Change Background");

    private ChangeBackgroundListener changeBackgroundListener;
    private SliderListener sliderListener;

    private Controller controller;

    private double durationModifier;

    private double startDragX;
    private double startDragY;
    private double orgTransX;
    private double orgTransY;


    /**
     * Constructs the GUI components and starts the Java-FX window.
     *
     * @param inController gains a reference to controller in order to fetch the planet list
     * @author Albin Ahlbeck
     * @author Simon Måtegen
     * @version 1.0
     */
    public MainFrame(Controller inController)
    {
        this.controller = inController;

        guiPlanetList = controller.getPlanetArrayList(); // copy the planet list from controller
        orbitPanel = new JFXPanel();
        overheadPanel = new JPanel();
        sliderListener = new SliderListener();
        timeSlider = new JSlider();

        changeBackgroundListener = new ChangeBackgroundListener();

        Platform.runLater(new Runnable()
        {
            @Override
            public void run() {
                initFX(orbitPanel); // starts on the Java FX thread
            }
        });
        // Sets up the JFrame
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setVisible(true);

        orbitPanel.setPreferredSize(new Dimension(getWidth(), getHeight() - 100));

        // Sets up the JSlider and components related to it

        timeSlider.setValue(0);
        timeSlider.setMaximum(MAX_SLIDER_VALUE);
        timeSlider.setPaintLabels(true);

        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(0, new JLabel("Real speed"));
        labelTable.put(10, new JLabel("100"));
        labelTable.put(20, new JLabel("1000"));
        labelTable.put(30, new JLabel("10000"));

        timeSlider.setLabelTable(labelTable);

        timeSlider.setPreferredSize(new Dimension(400, 50));
        timeSlider.setPaintTicks(true);
        timeSlider.setMajorTickSpacing(10);
        timeSlider.setForeground(Color.BLUE);
        timeSlider.setSnapToTicks(true);
        timeSlider.addMouseListener(sliderListener);

        // Sets up overheadPanel
        overheadPanel.setPreferredSize(new Dimension(1400, 60));
        overheadPanel.setBackground(Color.BLACK);
        overheadPanel.add(timeSlider);
        changeBackgroundBtn.setPreferredSize(new Dimension(200, 40));
        overheadPanel.add(changeBackgroundBtn);
        overheadPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        changeBackgroundBtn.addActionListener(changeBackgroundListener);

        add(orbitPanel, BorderLayout.WEST);
        add(overheadPanel, BorderLayout.NORTH);

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
        Scene scene = createScene("https://www.solarsystemscope.com/textures/download/8k_stars.jpg",
                guiPlanetList); // default background
        fxPanel.setScene(scene);
    }

    /**
     * Creates the Java-FX scene
     *
     * @author Albin Ahlbeck
     * @author Lanna Maslo
     * @author Manna Manojlovic
     * @version 1.0
     */
    private Scene createScene(String backgroundURL, ArrayList<Planet> planetArrayList)
    {
        root = new StackPane();
        Scene scene = new Scene(root, WIDTH - 100, HEIGHT - 100);
        scene.setFill(javafx.scene.paint.Color.BLACK);
        root.setBackground(createBackground(backgroundURL));
        setupCamera(scene);
        handleMouse(root);
        placePlanets(root, planetArrayList);
        paintPlanets();
        startOrbits(planetArrayList);
        EventHandler<javafx.scene.input.MouseEvent> eventHandler = new EventHandler<javafx.scene.input.MouseEvent>()
        {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent)
            {
                openInfoWindow(determinePlanet((Sphere)mouseEvent.getSource()));
            }

    };
        for (int i = 0; i < planetArrayList.size() ; i++)
        {
            planetArrayList.get(i).getSphereFromPlanet().addEventHandler
                    (javafx.scene.input.MouseEvent.MOUSE_CLICKED,  eventHandler);
            planetArrayList.get(i).getSphereFromPlanet().setCursor(Cursor.HAND);
        }

        return scene;
    }

    public Background createBackground(String backgroundURL)
    {
        Background tempBackground = new Background(
                Collections.singletonList(new BackgroundFill(
                        javafx.scene.paint.Color.BLACK, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)),
                Collections.singletonList(new BackgroundImage(
                        new javafx.scene.image.Image(backgroundURL,
                                WIDTH, HEIGHT, false, true),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        BackgroundSize.DEFAULT)));
        return tempBackground;
    }

    /**
     * Finds which planet the the sphere is connected to
     * @author Albin Ahlbeck

     * @version 1.0
     */
    public Planet determinePlanet(Sphere sphere)
    {
        for (int i = 0; i < guiPlanetList.size() ; i++)
        {
            if (sphere.getId().equals(guiPlanetList.get(i).getName()))
            {
                return guiPlanetList.get(i);
            }
        }
        return null;
    }

    /**
     * Paints the surface of the planets by calling their individual mappings
     *
     * @author Lanna Maslo

     * @version 1.0
     */
    public void paintPlanets()
    {
        for (int i = 0; i < guiPlanetList.size() ; i++) {
            PhongMaterial map = new PhongMaterial();
            map.setDiffuseMap(new Image("Images/" + guiPlanetList.get(i).getName() + ".jpg"));
            guiPlanetList.get(i).getSphereFromPlanet().setMaterial(map);
        }
    }

    /**
     * Places all the planets and their orbits in the Java-FX scene
     *
     * @author Albin Ahlbeck
     * @author Lanna Maslo
     * @author Manna Manojlovic
     * @author Marcus Svensson
     * @author Simon Måtegen
     * @version 1.0
     */
    public void placePlanets(Pane root, ArrayList<Planet> planetArrayList)
    {
        for (int i = 0; i < planetArrayList.size(); i++)
        {
            root.getChildren().add(planetArrayList.get(i).getSphereFromPlanet()); //Adds planets
            root.getChildren().add(planetArrayList.get(i).getPlanetOrbit().getEllipseFromOrbit());//Add orbits
            planetArrayList.get(i).getPlanetOrbit().getEllipseFromOrbit().toBack();//Moves orbits behind planets
            StackPane.setMargin(planetArrayList.get(i).getPlanetOrbit().getEllipseFromOrbit(),
                    new javafx.geometry.Insets(0, 0, 0, planetArrayList.get(i).getPlanetOrbit().getXCord() * 2));
        }
    }

    /**
     * Sets the viewing perspective and enables a zoom-function
     *
     * @author Lanna Maslo
     * @version 1.0
     */
    public void setupCamera(Scene scene)
    {
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000);
        camera.setNearClip(0.001);
        camera.setFarClip(2000.0);
        camera.setFieldOfView(35);
        camera.setTranslateX((float)orbitPanel.getSize().width / 2);
        camera.setTranslateY((float)orbitPanel.getSize().height / 2);
        scene.setCamera(camera);

        scene.addEventHandler(ScrollEvent.SCROLL, event ->
        {
            double delta = event.getDeltaY();
            root.translateZProperty().set(root.getTranslateZ() + delta);
        });

        scene.addEventHandler(KeyEvent.KEY_PRESSED, event ->
        {
            switch (event.getCode()) {
                case W:
                    root.translateZProperty().set(root.getTranslateZ() + 10);
                    break;
                case S:
                    root.translateZProperty().set(root.getTranslateZ() - 10);
                    break;
            }
        });
    }

    /**
     * Allows the user to move the solar system by dragging it
     *
     * @author Lanna Maslo
     * @version 1.0
     */
    public void handleMouse(Node root)
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

            root.setTranslateX(newTransX);
            root.setTranslateY(newTransY);
        });
    }

    /**
 * Starts the the planets movement
 *
 * @author Albin Ahlbeck
 * @version 1.0
 */
    public void startOrbits(ArrayList<Planet> planetArrayList)
    {
        for (int i = 0; i < planetArrayList.size(); i++) {
            planetArrayList.get(i).getPathTransiton().play(); // starts orbits
        }
    }

    private void speedChangeScene(double inDurationModifier) //Denna funkar inte än
    {
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                loadingScreen.setVisible(true);

                //Planets that move 10 times slower for every click on the button
                ArrayList<Planet> newPlanets = controller.createPlanetArray(inDurationModifier);

                orbitPanel.setScene(createScene("https://www.solarsystemscope.com/textures/download/8k_stars.jpg",
                        newPlanets));

                for (int i = 0; i < newPlanets.size() ; i++) {
                    PhongMaterial map = new PhongMaterial();
                    map.setDiffuseMap(new Image("Images/" + newPlanets.get(i).getName() + ".jpg"));
                    newPlanets.get(i).getSphereFromPlanet().setMaterial(map);
                }

                loadingScreen.setVisible(false);
            }
        });


    }


    /**
     * Listens to change in timeSlider and then changes the text in timeLabel
     *
     * @author Albin Ahlbeck
     * @author Simon Måtegen
     * @version 1.0
     */
    private class SliderListener implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent mouseEvent)
        {

        }

        @Override
        public void mousePressed(MouseEvent mouseEvent)
        {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent)
        {
            if (timeSlider.getValue() == 0)
            {
                speedChangeScene(1);
            }
            else if (timeSlider.getValue() == 10)
            {
                speedChangeScene(100);
            }
            else if (timeSlider.getValue() == 20)
            {
                speedChangeScene(1000);
            }
            else if (timeSlider.getValue() == 30)
            {
                speedChangeScene(10000);
            }
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent)
        {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent)
        {

        }
    }

    /**
     * Listens to the changeBackgroundBtn on click it changes the background
     *
     * @author Albin Ahlbeck
     * @author Simon Måtegen
     * @version 1.0
     */
    private class ChangeBackgroundListener implements ActionListener
    {
        public void actionPerformed(ActionEvent actionEvent) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    root.setBackground(new Background(
                            Collections.singletonList(new BackgroundFill(
                                    javafx.scene.paint.Color.BLACK, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)),
                            Collections.singletonList(new BackgroundImage(
                                    new javafx.scene.image.Image("https://i0.wp.com/metro.co.uk/wp-content/uploads/2018/10/sei_36554009-212f." +
                                            "jpg?quality=90&strip=all&zoom=1&resize=644%2C483&ssl=1",
                                            WIDTH, HEIGHT, false, true),
                                    BackgroundRepeat.NO_REPEAT,
                                    BackgroundRepeat.NO_REPEAT,
                                    BackgroundPosition.CENTER,
                                    BackgroundSize.DEFAULT))));
                }
            });
        }
    }

    /**
     * Opens an information window
     * @param planet The planet to showcase
     * @author Albin Ahlbeck
     * @version 1.0
     */
    public void openInfoWindow(Planet planet)
    {
        mainInfoFrame = new MainInfoFrame(planet);
    }

}
