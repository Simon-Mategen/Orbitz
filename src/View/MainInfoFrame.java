package View;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;

public class MainInfoFrame extends JInternalFrame
{
    private MainInfoPanel panel;
    private JFXPanel planetaryPanel;
    private StackPane root;

    public MainInfoFrame ()
    {
        frame();
    }

    public void frame ()
    {
        setLayout(new BorderLayout());
        panel = new MainInfoPanel ();
        planetaryPanel = new JFXPanel();
        setSize (1000, 600);
        setVisible (true);

        //add(panel, BorderLayout.NORTH);
        planetaryPanel.setPreferredSize(new Dimension(600, 400));
        add(planetaryPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(planetaryPanel); // starts on the Java FX thread
            }
        });

    }

    /**
     * Creates a new scene from createScene and adds it to the Java FX window
     *
     * @param fxPanel The JavaFX panel to be created
     * @author Albin Ahlbeck
     * @version 1.0
     */
    private void initFX(JFXPanel fxPanel) {
        // This method is invoked on JavaFX thread
        Scene scene = createScene("https://www.wallpaperup.com/uploads/wallpapers/2014/04/12/330551/185c99304364bba58fe8bfe3765fcf64.jpg"); // default background
        fxPanel.setScene(scene);
    }

    /**
     * Creates the Java-FX scene
     *
     * @author Albin Ahlbeck
     * @author Lanna Maslo
     * @author Manna Manojlovic
     * @version 1.0
     */
    private Scene createScene(String backgroundURL) {
        root = new StackPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.BLUE);
        root.setBackground(createBackground(backgroundURL));
        return scene;
    }

    public Background createBackground(String backgroundURL)
    {
        Background tempBackground = new Background(
                Collections.singletonList(new BackgroundFill(
                        javafx.scene.paint.Color.BLACK, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)),
                Collections.singletonList(new BackgroundImage(
                        new javafx.scene.image.Image(backgroundURL,
                                WIDTH, HEIGHT, false, true),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        BackgroundSize.DEFAULT)));
        return tempBackground;
    }



    //TODO JavaFX panel för omloppsbanesystem för planet + månar i miniatyr typ
    //TODO lägg till panelen nånstans - förslagsvis på en MainPanel som sen läggs på denna frame?

    public static void main (String [] args)
    {
        MainInfoFrame iw = new MainInfoFrame();

    }
}
