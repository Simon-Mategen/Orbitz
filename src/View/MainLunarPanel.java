package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainLunarPanel extends JPanel implements ActionListener
{

    private JButton returnBtn = new JButton("< Previous");

    private MainInfoFrame mainInfoFrame;
    // private Planet planet;

    public MainLunarPanel()
    {
        setupPanel();

    }

    public void setupPanel()
    {

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        setBackground(Color.black);

        returnBtn.setPreferredSize(new Dimension(75,25));

        setBackground(Color.black);

        btnPanel.add(returnBtn);
        add(btnPanel,BorderLayout.NORTH);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == returnBtn)
        {
            mainInfoFrame.setVisible(true);
        }
    }
}
