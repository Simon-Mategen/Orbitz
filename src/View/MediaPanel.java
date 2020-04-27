package View;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MediaPanel
{
    JFXPanel jfxPanel;
    StackPane root;

    private int WIDTH;
    private int HEIGHT;

    public MediaPanel()
    {
        jfxPanel = new JFXPanel();
        WIDTH = 1400;
        HEIGHT = 50;
        initFX(jfxPanel);
    }

    private void initFX(JFXPanel jfxPanel) {
        // This method is invoked on JavaFX thread
        Scene scene = createScene();
        jfxPanel.setScene(scene);
    }

    public Scene createScene()
    {
        root = new StackPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        //initMusic();
        return scene;
    }


}
