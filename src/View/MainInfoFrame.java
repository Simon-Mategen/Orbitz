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

    //TODO JavaFX panel f�r omloppsbanesystem f�r planet + m�nar i miniatyr typ
    //TODO l�gg till panelen n�nstans - f�rslagsvis p� en MainPanel som sen l�ggs p� denna frame?

    public static void main (String [] args)
    {
        MainInfoFrame iw = new MainInfoFrame();

    }
}
