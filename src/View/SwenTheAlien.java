package View;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.Random;

/**
 * @Author: Manna Manojlovic
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
    private String [] funFactsTest = new String [] { "...that Jupiter can not become a star?",
            "...that Jupiter has 67 moons?",
            "...that Jupiter has rings?"};

    public SwenTheAlien ()
    {
        createPanel();

    }

    private void initFX(JFXPanel swenFX)
    {
        Image swenImage = new Image("Images/Swen.png");
        ImageView swen = new ImageView(swenImage);
        swen.setFitWidth(168);
        swen.setFitHeight(218);
        Group swenRoot = new Group();
        swenRoot.getChildren().add(swen);
        Scene swenScene = new Scene(swenRoot);
        swenScene.setFill(new javafx.scene.paint.Color(0,0,0,1));
        swenFX.setScene(swenScene);

        swen.setOnMouseClicked(event -> {
            int index = randomFacts.nextInt (funFactsTest.length);
            for (int i = 0; i < index; i++) {
            funFactArea.setText (funFactsTest[index]);
            }
        });
    }

    /**
     * @Author: Manna Manojlovic
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

        panel.setPreferredSize(new Dimension(402, 240));
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

        Platform.runLater(new Runnable(){
            public void run(){
                initFX(swenFX);
            }
        });
    }
}

