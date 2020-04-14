package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Funfact panel/area for Swen The Alien.
 * When user clicks on the alien a new fun fact about the planet/its moon/moons displays in a textarea.
 */

//TODO perhaps divide the alien and the textarea into different classes
// with panels of their own and add them on ONE main with borderLayout? So that we can decide the size on our own
//TODO add listener on the alien so that textArea can set random funfacts on click event (MouseEvent?)


public class SwenTheAlien extends JPanel
{
    private JPanel panel;
    private JLabel lblSwen;
    private  BorderLayout layout;
    private JTextArea funFactArea;

    public SwenTheAlien() throws IOException
    {

        createPanel();

    }

    public void createPanel() throws IOException
    {
        layout = new BorderLayout();
        GridLayout gridLayout = new GridLayout(0,2,4,4);

        panel = new JPanel(gridLayout);
        setLayout(layout);
        setBackground(Color.black);
        panel.setPreferredSize(new Dimension(400,200));
        panel.setBackground(Color.black);
        setBackground(Color.black);


        lblSwen = new JLabel();
        funFactArea = new JTextArea(100,100);
        funFactArea.setLineWrap(true);

        Font font = new Font("Segoe Script", Font.PLAIN, 18);
        funFactArea.setFont(font);
        funFactArea.setBackground(Color.black);
        funFactArea.setForeground(Color.yellow);

        funFactArea.setText("Did you know that the big red spot on Jupiter is actually a storm?");

        BufferedImage img1 = ImageIO.read(new File("images/swen3.jpg"));
        ImageIcon icon1 = new ImageIcon(img1);
        lblSwen.setIcon(icon1);

        //TODO set random funfact to textArea when user clicks Swen.
        lblSwen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });

        panel.add(funFactArea);
        panel.add(lblSwen);

        add(panel,layout.SOUTH);



    }
}

