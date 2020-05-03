package View;

import Controller.Controller;
import Model.Planet;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * @Author: Manna Manojlovic, Albin Ahlbeck
 * @version: 1.0
 *
 * This is the main frame for the planetary information GUI.
 * It has the MainInfoPanel main panel placed on it and the FX panel for lunar orbit.
 *
 */
public class MainInfoFrame extends JFrame {
    private MainInfoPanel panel;
    private Planet planet;

    //Controller controller;

    /**
     * Adds a planet to be displayed when creating a MainInfoFrame
     **/
    public MainInfoFrame(Planet planet) {
        // this.controller = controller;
        this.planet = planet;
        frame(planet);

    }

    public void frame(Planet planet) {
        setLayout(new BorderLayout());
        panel = new MainInfoPanel(planet);

        setSize(1000, 600);
        setVisible(true);
        setResizable(false);
        setTitle(planet.getName());
        setIconImage(new ImageIcon("src/Images/" + planet.getName() + "Icon.png").getImage());
        add(panel, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                panel.stopMp3();

            }
        });
    }
}
