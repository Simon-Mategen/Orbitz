package View;

import javax.swing.*;
import java.awt.*;

public class ColorPicker extends JFrame
{
    private JPanel north;
    private JPanel south;

    private JColorChooser colorChooser;
    private JLabel mainColor1;
    private JLabel mainColor2;
    private JLabel secondaryColor1;
    private JLabel secondaryColor2;

    private JButton btnAdd;

    public ColorPicker()
    {
        setLayout(new BorderLayout());
        setSize(new Dimension(500, 700));

        north = new JPanel();
        south = new JPanel();

        colorChooser = new JColorChooser();

        mainColor1 = new JLabel("Color 1");
        mainColor2 = new JLabel("Color 2");
        secondaryColor1 = new JLabel("Color 3");
        secondaryColor2 = new JLabel("Color 4");

        north = new JPanel();
        south = new JPanel();

        btnAdd = new JButton("Add selected color");

        setupColors();

        north.setPreferredSize(new Dimension(500, 50));
        south.setPreferredSize(new Dimension(500, 650));
        mainColor1.setPreferredSize(new Dimension(50, 50));
        secondaryColor1.setPreferredSize(new Dimension(50, 50));
        mainColor2.setPreferredSize(new Dimension(50, 50));
        secondaryColor2.setPreferredSize(new Dimension(50, 50));
        btnAdd.setPreferredSize(new Dimension(100, 50));
        colorChooser.setPreferredSize(new Dimension(800, 500));

        north.add(mainColor1);
        north.add(secondaryColor1);
        north.add(mainColor1);
        north.add(secondaryColor2);
        north.add(btnAdd);
        south.add(colorChooser);

        add(north, BorderLayout.NORTH);
        add(south, BorderLayout.CENTER);
        setVisible(true);
    }

    private void setupColors()
    {
        mainColor1.setOpaque(false);
        mainColor2.setOpaque(false);
        secondaryColor1.setOpaque(false);
        secondaryColor2.setOpaque(false);

        mainColor1.setBackground(Color.WHITE);
        mainColor2.setBackground(Color.WHITE);
        secondaryColor1.setBackground(Color.WHITE);
        secondaryColor2.setBackground(Color.WHITE);

        mainColor1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mainColor2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        secondaryColor1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        secondaryColor2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }



}