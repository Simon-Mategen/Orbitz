package View;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author Manna Manojlovic
 *
 * This class has the JTextArea for the informative text for each of the moons
 * It's called my MainLunarPanel.java and placed on the west side of the BorderLayout
 */

public class LunarTextPanel extends JPanel
{
    private JTextArea textArea;
    private JScrollPane scrollPane;

    /**
     * @Author Manna Manojlovic
     *
     * Constructor, calls setupPanel()
     */
    public LunarTextPanel()
    {
       setupPanel();
    }

    /**
     * @Author Manna Manojlovic
     *
     * This method sets background to the extended JPanel as black
     * Initializes the textarea and sets size, color, textcolor and linewrap
     * Initializes scrollpane, which the textArea is passed to
     *
     * Adds scrollpanel to the extended JPanel
     */
    public void setupPanel()
    {
        setBackground(Color.black);

        textArea = new JTextArea(20,30);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(Color.black);
        textArea.setForeground(Color.LIGHT_GRAY);
        textArea.setFont(new Font ("Nasalization Rg", Font.PLAIN, 11));
        textArea.setEditable(false);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(320, 229));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane);
    }

    /**
     * @Author Manna Manojlovic
     * This method passes a filepath to readFile(String filePath)
     * depending on which planet is selected different text will be set about the moons
     * to the textArea.
     */
   /* public void setTextToTextArea()
    {

        if (planet.getName().equals("Earth"))
        {
            readFile("lunarFacts/test.txt");
        }
        else if (planet.getName().equals("Mars"))
        {
            readFile("lunarFacts/test.txt");
        }
        else if (planet.getName().equals("Jupiter"))
        {
            readFile("lunarFacts/test.txt");
        }
        else if (planet.getName().equals("Saturn"))
        {
            readFile("lunarFacts/test.txt");
        }
        else if(planet.getName().equals("Uranus"))
        {
            readFile("lunarFacts/test.txt");
        }
        else if (planet.getName().equals("Neptune"))
        {
            readFile("lunarFacts/test.txt");
        }
    }
    
    */

    /**
     * @Author Manna Manojlovic
     * @param filePath takes a filepath as parameter from setTextToTextArea
     *                 the filepath is to a textfile which has info about the moons
     */
    public void readFile(String filePath)
    {
        try (BufferedReader in = new BufferedReader(new FileReader(filePath)))
        {
            String line;

            while ((line = in.readLine()) != null)
            {
                textArea.setText(line);
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
