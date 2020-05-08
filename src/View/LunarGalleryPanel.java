package View;

import javax.swing.*;
import java.awt.*;

public class LunarGalleryPanel extends JPanel {
    public LunarGalleryPanel(){
        createPanel();
    }

    public void createPanel(){
        JLabel lblGallery = new JLabel();
        lblGallery.setSize(320, 220);
        add(lblGallery);
        setBackground(Color.black);
    }
}
