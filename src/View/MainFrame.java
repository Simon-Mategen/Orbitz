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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.image.Image;
import javafx.scene.transform.Transform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;

/**
 * MainFrame is the main window which contains various  graphical components
 @author Albin Ahlbeck
 @author Simon M�tegen
 @author Lanna Maslo
 @author Manna Manojlovic
 @author Marcus Svensson
 @version 1.0
 @
 */
public class MainFrame extends JFrame {

    private JLabel titleLabel;

    private final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private final int MAX_SLIDER_VALUE = 30;

    private ArrayList<Planet> guiPlanetList;

    private JFXPanel orbitPanel;
    private JFXPanel mediaPanel;
    private JPanel overheadPanel;
    private MainInfoFrame mainInfoFrame;
    private LoadingScreen loadingScreen = new LoadingScreen();

    private StackPane root;
    private JSlider timeSlider;
    private JSlider musicSlider;
    private JButton changeBackgroundBtn = new JButton("Change Background");

    private ChangeBackgroundListener changeBackgroundListener;
    private SliderListener sliderListener;

    private Controller controller;

    private double durationModifier;

    private double startDragX;
    private double startDragY;
    private double orgTransX;
    private double orgTransY;

    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();


    /**
     * Constructs the GUI components and starts the Java-FX window.
     *
     * @param inController gains a reference to controller in order to fetch the planet list
     * @author Albin Ahlbeck
     * @author Simon M�tegen
     * @version 1.0
     */
    public MainFrame(Controller inController) {
        this.controller = inController;
        initFonts();
        guiPlanetList = controller.getPlanetArrayList(); // copy the planet list from controller
        orbitPanel = new JFXPanel();
        mediaPanel = new JFXPanel();
        overheadPanel = new JPanel();
        sliderListener = new SliderListener();
        timeSlider = new JSlider();
        musicSlider = new JSlider();
        changeBackgroundBtn.setFont(new Font("Earth Orbiter", Font.PLAIN, 20));
        titleLabel = new JLabel();
        titleLabel.setPreferredSize(new Dimension(700, 100));
        titleLabel.setText("Orbitz");
        titleLabel.setFont(new Font("Earth Orbiter", Font.PLAIN, 55));
        titleLabel.setOpaque(true);


        changeBackgroundListener = new ChangeBackgroundListener();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFxOrbit(orbitPanel); // starts on the Java FX thread
            }
        });

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFXMedia(mediaPanel); // starts on the Java FX thread
            }
        });
        // Sets up the JFrame
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setTitle("Orbitz");

        ImageIcon solarSystem = new ImageIcon("src/Images/orbitz.png");
        setIconImage(solarSystem.getImage());

        orbitPanel.setPreferredSize(new Dimension(getWidth(), getHeight() - 100));

        Font f = new Font("Arial", Font.BOLD, 8);
        JLabel labelMin = new JLabel("MIN");
        JLabel labelMax = new JLabel("MAX");
        labelMin.setFont(f);
        labelMax.setFont(f);

        Hashtable<Integer, JLabel> labelTableM = new Hashtable<>();
        labelTableM.put(2, labelMin);
        labelTableM.put(19, labelMax);

        musicSlider.setOrientation(JSlider.VERTICAL);
        musicSlider.setPreferredSize(new Dimension(10, 20));
        musicSlider.setMinimum(0);
        musicSlider.setMaximum(20);
        musicSlider.setValue(10);
        musicSlider.setForeground(Color.BLUE);
        musicSlider.setSnapToTicks(true);
        musicSlider.setLabelTable(labelTableM);
        musicSlider.setPaintLabels(true);

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
        overheadPanel.setLayout(new BorderLayout());
        overheadPanel.setPreferredSize(new Dimension(1400, 150));
        //overheadPanel.setBackground(Color.DARK_GRAY);
        //overheadPanel.setForeground(Color.BLACK);
        overheadPanel.add(timeSlider, BorderLayout.EAST);
        //overheadPanel.add(musicSlider, BorderLayout.CENTER);
        changeBackgroundBtn.setPreferredSize(new Dimension(300, 60));
        overheadPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        overheadPanel.add(titleLabel, BorderLayout.WEST);
        mediaPanel.setPreferredSize(new Dimension(1400, 50));
        mediaPanel.setSize(new Dimension(1400, 50));
        overheadPanel.add(mediaPanel, BorderLayout.SOUTH);

        changeBackgroundBtn.addActionListener(changeBackgroundListener);

        add(orbitPanel, BorderLayout.WEST);
        add(overheadPanel, BorderLayout.NORTH);

    }

    /**
     * Creates a new scene from createScene and adds it to the Java FX window
     * Sets background music
     *
     * @param fxPanel The JavaFX panel to be created
     * @author Albin Ahlbeck
     * @author Lanna Maslo
     * @version 1.0
     */
    private void initFxOrbit(JFXPanel fxPanel) {
        // This method is invoked on JavaFX thread
        Scene scene = createScene(guiPlanetList); // default background
        fxPanel.setScene(scene);
    }

    /**
     * Creates a new scene from createScene and adds it to the Java FX window
     * Sets background music
     *
     * @param fxPanel The JavaFX panel to be created
     * @author Albin Ahlbeck
     * @version 1.0
     */
    private void initFXMedia(JFXPanel fxPanel) {
        // This method is invoked on JavaFX thread
        Scene scene = createMedia(); // default background
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
    private Scene createScene(ArrayList<Planet> planetArrayList)
    {
        root = new StackPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        root.setBackground(null);
        scene.setFill(javafx.scene.paint.Color.BLACK);
        setupCamera(scene);
        handleMouse(root);
        addStars();
        placePlanets(root, planetArrayList);
        paintPlanets();
        startOrbits(planetArrayList);
        EventHandler<javafx.scene.input.MouseEvent> eventHandler = new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                openInfoWindow(determinePlanet((Sphere) mouseEvent.getSource()));
            }

        };

        for (int i = 0; i < planetArrayList.size(); i++) {
            planetArrayList.get(i).getSphereFromPlanet().addEventHandler
                    (javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandler);
            planetArrayList.get(i).getSphereFromPlanet().setCursor(Cursor.HAND);
        }

        return scene;
    }

    private Scene createMedia()
    {
        StackPane mediaPane = new StackPane();
        Scene scene = new Scene(mediaPane, mediaPanel.getWidth(), mediaPanel.getHeight());
        initMusic(mediaPane);
        return scene;
    }

    public void initMusic(StackPane mediaPane)
    {
        String musicFile = "sound/spacesound.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer player = new MediaPlayer(sound);
        MediaBar mediaBar = new MediaBar(player);
        mediaBar.setMaxSize(mediaPane.getWidth(),  mediaPane.getHeight());
        mediaPane.getChildren().add(mediaBar);
        /*player.setCycleCount(MediaPlayer.INDEFINITE);
        musicSlider.setValue((int)player.getVolume() * 20);
        musicSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                double value = (double)musicSlider.getValue() / 20;
                player.setVolume(value);
            }
        });

         */
        player.play();
    }

    /**
     * Finds which planet the the sphere is connected to
     *
     * @author Albin Ahlbeck
     * @version 1.0
     */
    public Planet determinePlanet(Sphere sphere) {
        for (int i = 0; i < guiPlanetList.size(); i++) {
            if (sphere.getId().equals(guiPlanetList.get(i).getName())) {
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
    public void paintPlanets() {
        for (int i = 0; i < guiPlanetList.size(); i++) {
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
     * @author Simon M�tegen
     * @version 1.0
     */
    public void placePlanets(Pane root, ArrayList<Planet> planetArrayList) {
        for (int i = 0; i < planetArrayList.size(); i++) {
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
     * @author Simon M�tegen
     * @version 1.0
     */
    public void setupCamera(Scene scene)
    {
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000);
        camera.setNearClip(0.001);
        camera.setFarClip(2000.0);
        camera.setFieldOfView(35);
        camera.setTranslateX((float) orbitPanel.getSize().width / 2);
        camera.setTranslateY((float) orbitPanel.getSize().height / 2);
        scene.setCamera(camera);

        scene.addEventHandler(ScrollEvent.SCROLL, event ->
        {
            double delta = event.getDeltaY();
            root.translateZProperty().set(root.getTranslateZ() + delta);
            printPlanetCurrentPosition();
            printPlanetCurrentPosition();
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

    private void printPlanetCurrentPosition()//To be used during zooming
    {
        LinkedList<double[]> planetXYValues = new LinkedList<>(); //Planet XY value in order from first planet to last

        for (int i = 1; i <= guiPlanetList.size(); i++) //Gets the current X and Y values for the planets
        {
            double[] tempArray = new double[2];
            Transform transform = root.getChildren().get(root.getChildren().size()-i).getLocalToParentTransform();

            tempArray[0] = transform.getTx();
            tempArray[1] = transform.getTy();

            planetXYValues.addFirst(tempArray);
        }

        System.out.println(planetXYValues);


        System.out.println("Klar");

    }

    /**
     * Allows the user to move the solar system by dragging it
     *
     * @author Lanna Maslo
     * @version 1.0
     */
    public void handleMouse(Node root) {
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
    public void startOrbits(ArrayList<Planet> planetArrayList) {
        for (int i = 0; i < planetArrayList.size(); i++) {
            planetArrayList.get(i).getPathTransiton().play(); // starts orbits
        }
    }

    private void speedChangeScene(double inDurationModifier) //Denna funkar inte �n
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                loadingScreen.setVisible(true);

                //Planets that move 10 times slower for every click on the button
                ArrayList<Planet> newPlanets = controller.createPlanetArray(inDurationModifier);
                orbitPanel.setScene(createScene(newPlanets));

                for (int i = 0; i < newPlanets.size(); i++) {
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
     * @author Simon M�tegen
     * @version 1.0
     */
    private class SliderListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {

        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            if (timeSlider.getValue() == 0) {
                speedChangeScene(1);
            } else if (timeSlider.getValue() == 10) {
                speedChangeScene(100);
            } else if (timeSlider.getValue() == 20) {
                speedChangeScene(1000);
            } else if (timeSlider.getValue() == 30) {
                speedChangeScene(10000);
            }
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    }

    /**
     * Adds star at randomized positions on the scene
     *
     * @author Albin Ahlbeck
     * @version 1.0
     */
    public void addStars() {
        Random randomX = new Random();
        Random randomY = new Random();
        Random randomZ = new Random();
        int x;
        int y;
        int z;
        int minX = -WIDTH;
        int maxX = WIDTH;
        int minY = -HEIGHT;
        int maxY = HEIGHT;
        int minZ = 300;
        int maxZ = 1000;
        int radius = 1;

        for (int i = 0; i < 1000; i++) {
            x = randomX.nextInt(maxX - minX + 1) + minX;
            y = randomY.nextInt(maxY - minY + 1) + minY;
            z = randomZ.nextInt(maxZ - minZ + 1) + minZ;

            Star tempStar = new Star(radius, x, y, z);
            root.getChildren().add(tempStar);
        }

    }

    /**
     * Listens to the changeBackgroundBtn on click it changes the background
     *
     * @author Albin Ahlbeck
     * @author Simon M�tegen
     * @version 1.0
     */
    private class ChangeBackgroundListener implements ActionListener {
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
     *
     * @param planet The planet to showcase
     * @author Albin Ahlbeck
     * @version 1.0
     */
    public void openInfoWindow(Planet planet) {
        mainInfoFrame = new MainInfoFrame(planet);
    }


    /**
     * Adds fonts to the GraphicsEnviroment for later use
     *
     * @author Albin Ahlbeck
     * @version 1.0
     */
    public void initFonts() {
        try {
            // register fonts here
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/earth_orbiter/earthorbiter.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/earth_orbiter/earthorbiterbold.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/earth_orbiter/earthorbitertitleital.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/nasalization/nasalization-rg.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/revolution_saji/REVOLUTION.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/Starjedi.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/Starjhol.ttf")));


        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
