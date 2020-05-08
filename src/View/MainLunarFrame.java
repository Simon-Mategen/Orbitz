package View;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class MainLunarFrame extends JFrame
{
    private MainLunarPanel panel;
    
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

        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                panel.stopSound();
            }
        });
    }

    public void playSound(String filePath) {
        panel.playSound(filePath);
    }
}
