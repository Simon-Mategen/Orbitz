package View;

import javax.swing.*;
import java.awt.*;

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

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1000, 500));
        setLocation(100,120);
        setResizable(false);
        add(panel);
    }
}
