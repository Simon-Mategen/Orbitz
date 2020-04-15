package View;

import Controller.Controller;
import Model.Planet;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

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
    private final int MAX_SLIDER_VALUE = 100;

    private ArrayList<Planet> guiPlanetList;

    private JFXPanel orbitPanel;
    private JPanel overheadPanel;
    private MainInfoFrame mainInfoFrame;

    private StackPane root;
    private JSlider timeSlider;
    private JLabel timeLabel;
    private JButton changeBackgroundBtn = new JButton("Change Background");
    private JButton speedBtn = new JButton("SPEED/10");
    private JButton infoButton = new JButton("Test info");

    private ChangeBackgroundListener changeBackgroundListener;
    private SliderListener sliderListener;
    private ChangeSpeedListener changeSpeedListener;
    private InfoButtonListener infoButtonListener;

    private Controller controller;

    private int durationModifier;

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
        timeLabel = new JLabel("1");
        timeSlider = new JSlider(1, 100);
        changeSpeedListener = new ChangeSpeedListener();
        changeBackgroundListener = new ChangeBackgroundListener();
        infoButtonListener = new InfoButtonListener();
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
        timeLabel.setPreferredSize(new Dimension(50, 50));
        timeSlider.setPreferredSize(new Dimension(400, 50));
        timeSlider.addChangeListener(sliderListener);

        // Sets up overheadPanel
        overheadPanel.setPreferredSize(new Dimension(1400, 60));
        overheadPanel.setBackground(java.awt.Color.WHITE);
        overheadPanel.add(timeLabel);
        overheadPanel.add(timeSlider);
        changeBackgroundBtn.setPreferredSize(new Dimension(200, 40));
        overheadPanel.add(changeBackgroundBtn);
        speedBtn.setPreferredSize(new Dimension(200, 40));
        infoButton.setPreferredSize(new Dimension(200, 40));
        infoButton.addActionListener(infoButtonListener);
        overheadPanel.add(speedBtn);
        overheadPanel.add(infoButton);
        overheadPanel.setBorder(BorderFactory.createLineBorder(java.awt.Color.WHITE));

        changeBackgroundBtn.addActionListener(changeBackgroundListener);

        add(orbitPanel, BorderLayout.WEST);
        add(overheadPanel, BorderLayout.NORTH);

        speedBtn.addActionListener(changeSpeedListener);

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
        Scene scene = createScene("https://www.solarsystemscope.com/textures/download/8k_stars.jpg", guiPlanetList); // default background
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
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(javafx.scene.paint.Color.BLACK);
        root.setBackground(createBackground(backgroundURL));

        setupCamera(scene);
        placePlanets(root, planetArrayList);
        startOrbits(planetArrayList);

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

    public void setupCamera(Scene scene)
    {
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000);
        camera.setNearClip(0.01);
        camera.setFarClip(2000.0);
        camera.setFieldOfView(35);
        camera.setTranslateX(650);
        camera.setTranslateY(300);
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



    /**
     * Listens to change in timeSlider and then changes the text in timeLabel
     *
     * @author Albin Ahlbeck
     * @version 1.0
     */
    private class SliderListener implements ChangeListener
    {
        @Override
        public void stateChanged(ChangeEvent changeEvent)
        {
            timeLabel.setText(Integer.toString(timeSlider.getValue()));
            durationModifier = timeSlider.getValue();

/*            ArrayList<Planet> newPlanets = controller.createPlanetArray(durationModifier);

            orbitPanel.setScene(createScene("https://www.solarsystemscope.com/textures/download/8k_stars.jpg", newPlanets));*/
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
                                    new javafx.scene.image.Image("https://i0.wp.com/metro.co.uk/wp-content/uploads/2018/10/sei_36554009-212f.jpg?quality=90&strip=all&zoom=1&resize=644%2C483&ssl=1",
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
     * Listens to the changeSpeedBtn and changes the speed on click.
     *
     * @author Albin Ahlbeck
     * @author Simon Måtegen
     * @version 1.0
     */
    private class ChangeSpeedListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            Platform.runLater(new Runnable()
            {
                @Override
                public void run()
                {
                    durationModifier += 10;

                    ArrayList<Planet> newPlanets = controller.createPlanetArray(durationModifier);//Planets that move 10 times slower for every click on the button

                    orbitPanel.setScene(createScene("https://www.solarsystemscope.com/textures/download/8k_stars.jpg", newPlanets));

                }
            });

        }
    }

    private class InfoButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            // test
            mainInfoFrame = new MainInfoFrame(guiPlanetList.get(5));
            JOptionPane.showInternalMessageDialog(null, mainInfoFrame,
                    "Planetary Window", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
