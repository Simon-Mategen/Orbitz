package View;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainLunarFrame extends JFrame
{
    private MainLunarPanel panel;
    private MediaPlayer player;
    
    public MainLunarFrame()
    {
        setupFrame();
    }

    public void setupFrame()
    {
        panel = new MainLunarPanel();

        setSize(new Dimension(1000, 500));
        setLocation(100,120);
        setResizable(false);
        add(panel);
    }

    public void playSound(String filePath) {
        String bip = filePath;
        Media hit = new Media(new File(bip).toURI().toString());
        player = new MediaPlayer(hit);
        player.setCycleCount(1);
        player.play();
    }

    /*public void moonSounds() {
        if (planet.getName().equals("Earth")) {
            playSound("sound/oneSmallStep.mp3");
        }
    }*/
}
