package View;

import javax.swing.*;

public class MainInfoFrame extends JFrame
{
    private MainInfoPanel panel;

    public MainInfoFrame ()
    {
        frame();
    }

    public void frame ()
    {
        panel = new MainInfoPanel ();

        setSize (1000, 600);
        setVisible (true);
        setContentPane (panel);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    }

    //TODO JavaFX panel för omloppsbanesystem för planet + månar i miniatyr typ
    //TODO lägg till panelen nånstans - förslagsvis på en MainPanel som sen läggs på denna frame?

    public static void main (String [] args)
    {
        MainInfoFrame iw = new MainInfoFrame();

    }
}
