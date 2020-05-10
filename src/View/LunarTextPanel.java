package View;

import javax.swing.*;
import java.awt.*;

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
}
