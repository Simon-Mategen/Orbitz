package View;

import Model.Planet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainLunarPanel extends JPanel implements ActionListener
{

    private JButton returnBtn;

    private MainInfoFrame mainInfoFrame;

//    private Planet planet;

    public MainLunarPanel()
    {
        setupPanel();

    }

    public void setupPanel()
    {
        returnBtn = new JButton("< Previous");  //sets window to MainInfoPanel and MainInfoFrame
        setBackground(Color.black);

        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        flowLayout.setHgap(70);

        JPanel btnPanel = new JPanel(flowLayout);
        btnPanel.setBackground(Color.black);

        JLabel headline = new JLabel("THE MOON");
        headline.setFont(new Font("Earth Orbiter", Font.BOLD, 55));
        headline.setForeground(Color.YELLOW);

        JLabel gifLabel = new JLabel();
        gifLabel.setIcon(new ImageIcon("funfacts/moonLanding.gif"));

        returnBtn.setPreferredSize(new Dimension(120,25));

        btnPanel.add(returnBtn);
        btnPanel.add(headline);

        add(btnPanel,BorderLayout.NORTH);
        add(gifLabel,BorderLayout.CENTER);

    }

    public void playGiantLeap()
    {
        //TODO LÄGG LJUDFILEN HÄR LANNNAAAAAAA

        //LANNNNAAAAAA
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == returnBtn)
        {
            mainInfoFrame.setVisible(true);     //doesn't work right nau
        }
    }
}
