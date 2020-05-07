package View;

import javax.swing.*;
import java.awt.*;

public class LunarTextPanel extends JPanel
{
    private JTextArea textAre;
    private JScrollPane scrollPane;

    public LunarTextPanel()
    {
       setupPanel();

    }

    public void setupPanel()
    {
        setBackground(Color.black);

        textAre = new JTextArea(20,30);
        textAre.setLineWrap(true);
        textAre.setWrapStyleWord(true);
        textAre.setBackground(Color.black);
        textAre.setForeground(Color.LIGHT_GRAY);
        textAre.setFont(new Font ("Nasalization Rg", Font.PLAIN, 11));
        textAre.setEditable(false);

        scrollPane = new JScrollPane(textAre);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane);
    }

}
