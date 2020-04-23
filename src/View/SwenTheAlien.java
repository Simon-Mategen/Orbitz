package View;

import Model.Planet;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.awt.*;

import java.io.*;
import java.util.*;

/**
 * @Author: Manna Manojlovic
 * @author: Lanna Maslo
 *
 * Funfact panel/area for Swen The Alien.
 * When user clicks on the alien a new fun fact about the planet/its moon/moons displays in a textarea.
 */

public class SwenTheAlien extends JPanel
{
    private JPanel panel;
    private BorderLayout layout;

    private JTextArea funFactArea;

    private final Font funFactAreaFont = new Font ("Nasalization Rg", Font.PLAIN, 20);

    private final Font titleFont = new Font ("Nasalization Rg", Font.PLAIN, 12);

    private final Color color = Color.white;

    private int currentIndex = 0;

    private Planet planet;

    /**
     * Constructor
     */
    public SwenTheAlien (Planet planet)
    {
        this.planet = planet;
        createPanel();
    }

    /**
     * @author: Manna Manojlovic
     * @author: Lanna Maslo
     *
     * @version: 1.0
     *
     * JavaFX-panel for Swen The Alien
     * Sets text from file containing funfacts to funfact area on button click.
     * Every button click generates a new fun fact.
     */
    private void initFX(JFXPanel swenFX)
    {
        Image swenImage = new Image("Images/Swen.png");
        ImageView swen = new ImageView(swenImage);

//      Image tip = new Image("Images/tip2.jpg");
        Tooltip tooltip = new Tooltip("CLICK ON MY FOREHEAD!");
        tooltip.setStyle("-fx-font-size: 20");                   //CSS stylesheet, Oracle doc.
//      tooltip.setGraphic(new ImageView(tip));
        tooltip.setShowDelay(Duration.millis(1));               //sets time before text appears after hovering over image

        swen.setPickOnBounds(true);
        Tooltip.install(swen, tooltip);

        swen.setFitWidth(170);
        swen.setFitHeight(220);

        Group swenRoot = new Group();

        swenRoot.getChildren().add(swen);

        Scene swenScene = new Scene(swenRoot);


        swenFX.setPreferredSize(new Dimension(171, 220));
        swenScene.setFill(new javafx.scene.paint.Color(0, 0, 0, 1));
        swen.setCursor(Cursor.HAND);
        swenFX.setScene(swenScene);
        
            swen.setOnMouseClicked(event ->
            {
                readFunFacts();

            });

        }

    /**
     * @Author: Manna Manojlovic
     * @version: 1.0
     *
     * Reads funfacts from file depending on which planet user has selected
     * Go to setFunFacts() for the code for the function
     */
    public void readFunFacts()
    {
        if (planet.getName().equals("Mercury"))
        {
            setFunFacts("funfacts/mercury.txt");
        }
        else if (planet.getName().equals("Venus"))
        {
            setFunFacts("funfacts/venus.txt");
        }
        else if (planet.getName().equals("Earth"))
        {
            setFunFacts("funfacts/earth.txt");
        }
        else if (planet.getName().equals("Mars"))
        {
            setFunFacts("funfacts/mars.txt");
        }
        else if (planet.getName().equals("Jupiter"))
        {
            setFunFacts("funfacts/jupiter.txt");
        }
        else if (planet.getName().equals("Saturn"))
        {
            setFunFacts("funfacts/saturn.txt");
        }
        else if(planet.getName().equals("Uranus"))
        {
            setFunFacts("funfacts/uranus.txt");
        }
        else if (planet.getName().equals("Neptune"))
        {
            setFunFacts("funfacts/neptune.txt");
        }
    }

    /**
     * @author Manna Manojlovic
     * @version 1.0
     * @param filePath takes a path to file as parameter
     * buffers a file and reads it
     * takes each line from file and places in ArrayList
     * with each button click from user, a new line from the list is displayed to textarea.
     */
    public void setFunFacts(String filePath)
    {
        try (BufferedReader in = new BufferedReader(new FileReader(filePath)))
        {
            ArrayList<String> lines = new ArrayList<String>();
            String line;

            while ((line = in.readLine()) != null)
            {
                lines.add(line);
            }

            if (currentIndex >= lines.size()-1)
            {
                currentIndex = 0;

            }
            currentIndex++;

            funFactArea.setText(String.valueOf(lines.get(currentIndex))); 


        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    /**
     * @Author: Manna Manojlovic
     * @author: Lanna Maslo
     *
     * @version: 1.0
     *
     * Panel for Swen The Alien and the fun fact textarea.
     * Creates a panel and initializes the components: image of Swen and the textArea
     * The Image is read through BufferedImage, set as an ImageIcon,
     * which in turn is set as a JLabel, a swing component.
     * In order to show an ImageIcon it has to be set as a Swing component.
     *
     */
    public void createPanel ()
    {
        TitledBorder titledBorder = BorderFactory.createTitledBorder(null, " DID YOU KNOW...",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, titleFont, color);

        panel = new JPanel(new BorderLayout());
        layout = new BorderLayout();

        JFXPanel swenFX = new JFXPanel();

        setLayout(layout);
        setBackground(Color.black);
        setBorder(titledBorder);

        panel.setPreferredSize(new Dimension(402, 220));
        panel.setBackground (Color.black);

        funFactArea = new JTextArea();//(100, 100);
        funFactArea.setLineWrap(true);
        funFactArea.setWrapStyleWord(true);
        funFactArea.setEditable(false);
        funFactArea.setAlignmentX(10);
        funFactArea.setAlignmentY(120);
        funFactArea.setPreferredSize(new Dimension(245, 100));
        funFactArea.setFont(funFactAreaFont);
        funFactArea.setBackground(Color.black);
        funFactArea.setForeground(color);
        funFactArea.setForeground(Color.YELLOW);
        JPanel pnl = new JPanel();
        pnl.setPreferredSize(new Dimension(40, 100));
        pnl.setBackground(Color.black);

        panel.add(pnl, BorderLayout.WEST);
        panel.add(funFactArea, BorderLayout.CENTER);
        panel.add(swenFX, BorderLayout.EAST);

        add(panel, BorderLayout.SOUTH);

        Platform.runLater(new Runnable()
        {
            public void run(){
                initFX(swenFX);
            }
        });
    }

}



