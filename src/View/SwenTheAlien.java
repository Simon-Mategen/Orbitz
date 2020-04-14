package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.Random;

/**
 * Funfact panel/area for Swen The Alien.
 * When user clicks on the alien a new fun fact about the planet/its moon/moons displays in a textarea.
 */

//TODO perhaps divide the alien and the textarea into different classes
// with panels of their own and add them on ONE main with borderLayout? So that we can decide the size on our own



public class SwenTheAlien extends JPanel
{
    private JPanel panel;
    private JLabel lblSwen;
    private  BorderLayout layout;
    private JTextArea funFactArea;

    private final Font font = new Font("SansSerif", Font.PLAIN, 23);
    private final Color color = Color.yellow;

    private Random randomFacts = new Random();
    private String[] funFactsTest = new String[] { "...that Jupiter can not become a star?", "...that Jupiter has 67 moons?",
            "...that Jupiter has rings?"};

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

        TitledBorder titledBorder = BorderFactory.createTitledBorder(null, " DID YOU KNOW...",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, font, color);

        setBorder(titledBorder);

        funFactArea = new JTextArea(100,100);
        funFactArea.setLineWrap(true);
        funFactArea.setEditable(false);

        Font font = new Font("SansSerif", Font.PLAIN, 23);
        funFactArea.setFont(font);
        funFactArea.setBackground(Color.black);
        funFactArea.setForeground(Color.yellow);


        //reads image from images, sets the image as an ImageIcon, which is set as a Jlabel.
        lblSwen = new JLabel();
        BufferedImage img1 = ImageIO.read(new File("images/swen3.jpg"));
        ImageIcon icon1 = new ImageIcon(img1);
        lblSwen.setIcon(icon1);

        //when user clicks on Swen image, he sets random fun facts as String to the textAre funFactArea
        lblSwen.addMouseListener(new MouseAdapter() {
            @Override

            public void mouseClicked(MouseEvent e) {

                super.mouseClicked(e);

                    int index = randomFacts.nextInt(funFactsTest.length);
                        for (int i =0; i<index; i++)
                        {
                            funFactArea.setText(funFactsTest[index]);
                        }

                }

        });

        panel.add(funFactArea);
        panel.add(lblSwen);

        add(panel,layout.SOUTH);


    }
}

