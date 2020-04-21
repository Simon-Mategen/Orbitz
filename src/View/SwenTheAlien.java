package View;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;

import java.io.*;
import java.util.*;

/**
 * @Author: Manna Manojlovic, Lanna Maslo
 *
 * Funfact panel/area for Swen The Alien.
 * When user clicks on the alien a new fun fact about the planet/its moon/moons displays in a textarea.
 */

public class SwenTheAlien extends JPanel
{
    private JPanel panel;
    private BorderLayout layout;

    private JTextArea funFactArea;

    private final Font font = new Font ("SansSerif", Font.PLAIN, 23);
    private final Color color = Color.yellow;

    private Random randomFacts = new Random ();
   // private String [] funFactsTest = new String [] { "...that Jupiter can not become a star?",
       //     "...that Jupiter has 67 moons?",
       //     "...that Jupiter has rings?"};

    /**
     * Constructor
     */
    public SwenTheAlien ()
    {
        createPanel();
    }

    /**
     * @Author: Manna Manojlovic, Lanna Maslo
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
        swen.setFitWidth(170);
        swen.setFitHeight(220);
        Group swenRoot = new Group();
        swenRoot.getChildren().add(swen);
        Scene swenScene = new Scene(swenRoot);
        swenFX.setPreferredSize(new Dimension(171, 220));
        swenScene.setFill(new javafx.scene.paint.Color(0, 0, 0, 1));
        swen.setCursor(Cursor.HAND);
        swenFX.setScene(swenScene);
        final int[] currentIndex = {0};
            swen.setOnMouseClicked(event ->
            {
                    try (BufferedReader in = new BufferedReader(new FileReader("funfacts/jupiter.txt"))) {
                        ArrayList<String> lines = new ArrayList<String>();
                        String line;

                        while ((line = in.readLine()) != null) {
                            lines.add(line);
                        }

                        if (currentIndex[0] >= lines.size()) {
                            currentIndex[0] = 0;

                        }
                        currentIndex[0]++;

                        funFactArea.setText(String.valueOf(lines.get(currentIndex[0])));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
            });
        }


    /**
     * @Author: Manna Manojlovic, Lanna Maslo
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
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, font, color);

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
        funFactArea.setFont(font);
        funFactArea.setBackground(Color.black);
        funFactArea.setForeground(color);

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



