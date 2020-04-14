package View;

import javax.swing.*;
import java.io.IOException;

public class MainInfoFrame extends JFrame {

    private MainInfoPanel panel;

    public MainInfoFrame() throws IOException {
        frame();
    }

    public void frame() throws IOException {

        // This method is invoked on the EDT thread
        //final JFXPanel fxPanel = new JFXPanel();
        //add(fxPanel);
        panel = new MainInfoPanel();
        setSize(1000, 600);
        setVisible(true);
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //TODO JavaFX panel f�r omloppsbanesystem f�r planet + m�nar i miniatyr typ
    //TODO l�gg till panelen n�nstans - f�rslagsvis p� en MainPanel som sen l�ggs p� denna frame?

    public static void main (String [] args) throws IOException
    {
        MainInfoFrame iw = new MainInfoFrame();

    }
}
