package View;

import Controller.Controller;
import Model.Planet;
import Model.Theme;
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
import javafx.scene.shape.Ellipse;
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
 *
 * @author Albin Ahlbeck
 * @author Simon M�tegen
 * @author Lanna Maslo
 * @author Manna Manojlovic
 * @author Marcus Svensson
 * @version 1.0
 * @
 */
public class MainFrame extends JFrame
{

    private JLabel titleLabel;

    private final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private final int MAX_SLIDER_VALUE = 30;

    private ArrayList<Planet> guiPlanetList;

    private Theme currentTheme;

    private JFXPanel orbitPanel;
    private JFXPanel mediaPanel;
    private JPanel overheadPanel;
    private MainInfoFrame mainInfoFrame;
    private LoadingScreen loadingScreen = new LoadingScreen();

    private StackPane root;
    private JSlider timeSlider;
    private JSlider musicSlider;
    private JComboBox<Theme> cbThemes;
    private Theme[] themes;
    private JLabel lblTheme;
    MediaBar mediaBar;

    ArrayList<Planet> newPlanets;

    private SliderListener sliderListener;
    private ComboBoxThemeListener comboBoxThemeListener;

    private Controller controller;


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
    public MainFrame(Controller inController)
    {
        this.controller = inController;

        initFonts();
        guiPlanetList = controller.getPlanetArrayList(); // copy the planet list from controller
        orbitPanel = new JFXPanel();
        mediaPanel = new JFXPanel();
        overheadPanel = new JPanel();
        themes = initThemes();
        sliderListener = new SliderListener();
        timeSlider = new JSlider();
        musicSlider = new JSlider();
        cbThemes = new JComboBox<Theme>(themes);
        lblTheme = new JLabel("Select theme");
        lblTheme.setFont(new Font("Nasalization Rg", Font.PLAIN, 16));
        titleLabel = new JLabel();
        titleLabel.setPreferredSize(new Dimension(700, 80));
        titleLabel.setText("Orbitz");
        titleLabel.setFont(new Font("Earth Orbiter", Font.PLAIN, 55));
        titleLabel.setOpaque(true);

        // Sets up the JFrame
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setTitle("Orbitz");

        ImageIcon solarSystem = new ImageIcon("src/Images/orbitz.png");
        setIconImage(solarSystem.getImage());

        orbitPanel.setPreferredSize(new Dimension(getWidth(), getHeight() - 150));

        Font f = new Font("Arial", Font.BOLD, 8);
        JLabel labelMin = new JLabel("MIN");
        JLabel labelMax = new JLabel("MAX");
        labelMin.setFont(f);
        labelMax.setFont(f);

        Hashtable<Integer, JLabel> labelTableM = new Hashtable<>();
        labelTableM.put(2, labelMin);
        labelTableM.put(19, labelMax);

        comboBoxThemeListener = new ComboBoxThemeListener();

        musicSlider.setOrientation(JSlider.VERTICAL);
        musicSlider.setPreferredSize(new Dimension(10, 20));
        musicSlider.setMinimum(0);
        musicSlider.setMaximum(20);
        musicSlider.setValue(10);
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

        timeSlider.setPreferredSize(new Dimension(700, 80));
        timeSlider.setPaintTicks(true);
        timeSlider.setMajorTickSpacing(10);
        timeSlider.setSnapToTicks(true);
        timeSlider.addMouseListener(sliderListener);

        cbThemes.setPreferredSize(new Dimension(300, 70));
        cbThemes.addItemListener(comboBoxThemeListener);
        cbThemes.setSelectedIndex(0);
        lblTheme.setPreferredSize(new Dimension(100, 70));
        mediaPanel.setPreferredSize(new Dimension(1000, 70));
        mediaPanel.setBackground(null);
        // Sets up overheadPanel
        overheadPanel.setLayout(new FlowLayout());
        // set opaque
        overheadPanel.setOpaque(true);
        timeSlider.setOpaque(true);

        titleLabel.setOpaque(false);
        mediaPanel.setOpaque(true);

        overheadPanel.setPreferredSize(new Dimension(1400, 150));
        overheadPanel.add(titleLabel);
        overheadPanel.add(timeSlider);
        overheadPanel.add(mediaPanel);
        overheadPanel.add(lblTheme);
        overheadPanel.add(cbThemes);

        add(orbitPanel, BorderLayout.CENTER);

        add(overheadPanel, BorderLayout.NORTH);

        currentTheme = new Theme("Black and White", Color.BLACK, Color.WHITE, javafx.scene.paint.Color.BLACK, javafx.scene.paint.Color.WHITE);
        setColors(currentTheme);

        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                initFXMedia(mediaPanel);
                initFxOrbit(orbitPanel);
            }
        });
        setVisible(true);
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
    private void initFxOrbit(JFXPanel fxPanel)
    {
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
    private void initFXMedia(JFXPanel fxPanel)
    {
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
        EventHandler<javafx.scene.input.MouseEvent> eventHandler = new EventHandler<javafx.scene.input.MouseEvent>()
        {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent)
            {
                openInfoWindow(determinePlanet((Sphere) mouseEvent.getSource()));
            }

        };

        for (int i = 0; i < planetArrayList.size(); i++)
        {
            planetArrayList.get(i).getSphereFromPlanet().addEventHandler
                    (javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandler);
            planetArrayList.get(i).getSphereFromPlanet().setCursor(Cursor.HAND);
        }

        return scene;
    }

    public Scene createMedia()
    {
        StackPane mediaPane = new StackPane();
        Scene scene = new Scene(mediaPane, mediaPanel.getWidth(), mediaPanel.getHeight());
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        mediaPane.setBackground(Background.EMPTY);

        mediaBar = new MediaBar(currentTheme);
        mediaPane.getChildren().add(mediaBar);
        return scene;
    }


    /**
     * Finds which planet the the sphere is connected to
     *
     * @author Albin Ahlbeck
     * @version 1.0
     */
    public Planet determinePlanet(Sphere sphere)
    {
        for (int i = 0; i < guiPlanetList.size(); i++)
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
        for (int i = 0; i < guiPlanetList.size(); i++)
        {
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
    public void placePlanets(Pane root, ArrayList<Planet> planetArrayList)
    {
        for (int i = 0; i < planetArrayList.size(); i++)
        {
            root.getChildren().add(planetArrayList.get(i).getSphereFromPlanet()); //Adds planets
            root.getChildren().add(planetArrayList.get(i).getPlanetOrbit().getEllipseFromOrbit());//Add orbits
            planetArrayList.get(i).getPlanetOrbit().getEllipseFromOrbit().toBack();//Moves orbits behind planets
            planetArrayList.get(i).getPlanetOrbit().getEllipseFromOrbit().setStroke(currentTheme.getSecondaryPaint()); // Paint ellipse based on theme
            StackPane.setMargin(planetArrayList.get(i).getPlanetOrbit().getEllipseFromOrbit(),
                    new javafx.geometry.Insets(0, 0, 0, planetArrayList.get(i).getPlanetOrbit().getXCord() * 2));
            System.out.println(currentTheme.toString());
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
        camera.setTranslateZ(-12600);
        camera.setNearClip(0.001);
        camera.setFarClip(200000);
        camera.setFieldOfView(35);
        camera.setTranslateX((float) orbitPanel.getSize().width / 2);
        camera.setTranslateY((float) orbitPanel.getSize().height / 2);
        scene.setCamera(camera);

        scene.addEventHandler(ScrollEvent.SCROLL, event ->
        {
            double delta = event.getDeltaY()*20;
            camera.setTranslateZ(camera.getTranslateZ()+delta);

            System.out.println(camera.getTranslateZ());

            Platform.runLater(new Runnable()
            {
                @Override
                public void run()
                {
                    if (newPlanets == null)
                    {
                        for (int i = 0; i < guiPlanetList.size(); i++)
                        {
                            if (camera.getTranslateZ() <= -12600)
                            {
                                guiPlanetList.get(i).getPlanetOrbit().getEllipseFromOrbit().setStrokeWidth(10);
                                System.out.println(10);
                            }
                            else if (camera.getTranslateZ() <= -53000)
                            {
                                guiPlanetList.get(i).getPlanetOrbit().getEllipseFromOrbit().setStrokeWidth(40);
                                System.out.println(40);
                            }
                            else if (camera.getTranslateZ() <= -247800)
                            {
                                guiPlanetList.get(i).getPlanetOrbit().getEllipseFromOrbit().setStrokeWidth(180);
                                System.out.println(180);
                            }
                        }
                    }
                    else
                    {
                        for (int i = 0; i < newPlanets.size(); i++)
                        {
                            if (camera.getTranslateZ() <= 12600)
                            {
                                newPlanets.get(i).getPlanetOrbit().getEllipseFromOrbit().setStrokeWidth(10);
                            }
                            else if (camera.getTranslateZ() > 12600 && camera.getTranslateZ() <= 53000)
                            {
                                newPlanets.get(i).getPlanetOrbit().getEllipseFromOrbit().setStrokeWidth(40);
                            }
                            else if (camera.getTranslateZ() > 53000 && camera.getTranslateZ() <= 247800)
                            {
                                newPlanets.get(i).getPlanetOrbit().getEllipseFromOrbit().setStrokeWidth(180);
                            }
                        }
                    }
                }
            });

        });


        scene.addEventHandler(KeyEvent.KEY_PRESSED, event ->
        {
            switch (event.getCode())
            {
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
        for (int i = 0; i < planetArrayList.size(); i++)
        {
            planetArrayList.get(i).getPathTransiton().play(); // starts orbits
        }
    }

    private void speedChangeScene(double inDurationModifier) //Denna funkar inte �n
    {
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                SwingUtilities.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        loadingScreen.setVisible(true);
                    }
                });


                //Planets that move 10 times slower for every click on the button
                newPlanets = controller.createPlanetArray(inDurationModifier);
                orbitPanel.setScene(createScene(newPlanets));

                for (int i = 0; i < newPlanets.size(); i++)
                {
                    PhongMaterial map = new PhongMaterial();
                    map.setDiffuseMap(new Image("Images/" + newPlanets.get(i).getName() + ".jpg"));
                    newPlanets.get(i).getSphereFromPlanet().setMaterial(map);
                }
                SwingUtilities.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        loadingScreen.setVisible(false);
                    }
                });

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
            } else if (timeSlider.getValue() == 10)
            {
                speedChangeScene(100);
            } else if (timeSlider.getValue() == 20)
            {
                speedChangeScene(1000);
            } else if (timeSlider.getValue() == 30)
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
     * Adds stars at randomized positions on the scene
     * Uses different Z values to get a 3d effect
     *
     * @author Albin Ahlbeck
     * @version 1.0
     */
    public void addStars()
    {
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
        int maxZ = 800;
        int radius = 1;

        for (int i = 0; i < 1000; i++)
        {
            x = randomX.nextInt(maxX - minX + 1) + minX;
            y = randomY.nextInt(maxY - minY + 1) + minY;
            z = randomZ.nextInt(maxZ - minZ + 1) + minZ;
            Star tempStar = new Star(radius, x, y, z);
            root.getChildren().add(tempStar);

        }

    }

    /**
     * Opens an information window
     *
     * @param planet The planet to showcase
     * @author Albin Ahlbeck
     * @version 1.0
     */
    public void openInfoWindow(Planet planet)
    {
        mainInfoFrame = new MainInfoFrame(planet);
    }


    /**
     * Adds fonts to the GraphicsEnviroment for later use
     *
     * @author Albin Ahlbeck
     * @version 1.0
     */
    public void initFonts()
    {
        try
        {
            // register fonts here
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/earth_orbiter/earthorbiter.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/earth_orbiter/earthorbiterbold.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/earth_orbiter/earthorbitertitleital.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/nasalization/nasalization-rg.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/revolution_saji/REVOLUTION.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/Starjedi.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/Starjhol.ttf")));


        } catch (Exception e)
        {
            System.out.println(e);
        }

    }

    public Theme[] initThemes()
    {
        //TODO: custom fonts for each theme
        Theme[] tempThemes = new Theme[5];
        tempThemes[0] = new Theme("Black and White", Color.BLACK, Color.WHITE, javafx.scene.paint.Color.BLACK, javafx.scene.paint.Color.WHITE);
        tempThemes[1] = new Theme("Midnight", Color.BLACK, new Color(0, 0, 128),
                javafx.scene.paint.Color.BLACK, javafx.scene.paint.Color.DARKBLUE);
        tempThemes[2] = new Theme("Star Wars", Color.BLACK, Color.YELLOW, javafx.scene.paint.Color.BLACK, javafx.scene.paint.Color.YELLOW);
        tempThemes[3] = new Theme("Modern", Color.WHITE, Color.GRAY, javafx.scene.paint.Color.WHITE, javafx.scene.paint.Color.GRAY);
        tempThemes[4] = new Theme("Stranger Things", Color.BLACK, Color.RED, javafx.scene.paint.Color.BLACK, javafx.scene.paint.Color.RED);
        return tempThemes;
    }

    public void setColors(Theme theme)
    {
        currentTheme = theme;
        titleLabel.setForeground(theme.getSecondaryColor());
        timeSlider.setForeground(theme.getSecondaryColor());
        overheadPanel.setBackground(theme.getMainColor());
        titleLabel.setOpaque(true);
        titleLabel.setBackground(null);
        timeSlider.setBackground(null);
        timeSlider.setOpaque(true);
        lblTheme.setForeground(theme.getMainColor());
        overheadPanel.setBorder(BorderFactory.createLineBorder(theme.getSecondaryColor(), 2));

        // Time slider configuration
        JLabel lbl1 = new JLabel("Real speed");
        JLabel lbl2 = new JLabel("x100");
        JLabel lbl3 = new JLabel("x1000");
        JLabel lbl4 = new JLabel("x10000");

        lbl1.setForeground(theme.getSecondaryColor());
        lbl2.setForeground(theme.getSecondaryColor());
        lbl3.setForeground(theme.getSecondaryColor());
        lbl4.setForeground(theme.getSecondaryColor());

        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(0, lbl1);
        labelTable.put(10, lbl2);
        labelTable.put(20, lbl3);
        labelTable.put(30, lbl4);
        timeSlider.setLabelTable(labelTable);
        try
        {
            Thread.sleep(500);
        } catch (Exception e)
        {

        }
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {

                for (int i = 0; i < guiPlanetList.size(); i++)
                {
                    guiPlanetList.get(i).getPlanetOrbit().getEllipseFromOrbit().setStroke(theme.getSecondaryPaint());
                    if (newPlanets != null) // if the scene never have been changed newPlanets will throw nullpointer
                    {
                        newPlanets.get(i).getPlanetOrbit().getEllipseFromOrbit().setStroke(theme.getSecondaryPaint());
                    }
                }
                if (mediaBar != null)
                {
                    mediaBar.addTheme(theme);
                    if (theme.getName().equals("Star Wars"))
                    {
                        mediaBar.changeSong(3);
                    }

                    if (theme.getName().equals("Stranger Things"))
                    {
                        mediaBar.changeSong(2);
                    }
                }


            }
        });

    }

    private class ComboBoxThemeListener implements ItemListener
    {
        @Override
        public void itemStateChanged(ItemEvent event)
        {
            if (event.getStateChange() == ItemEvent.SELECTED)
            {
                //loadingScreen.setVisible(true);
                setColors((Theme) event.getItem());
                //loadingScreen.setVisible(false);
            }
        }
    }
}
