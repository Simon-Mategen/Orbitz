package View;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.Collections;

public class Mainframe extends JPanel
{
    StackPane rootPane = new StackPane();
    private OrbitaryWindow orbitaryWindow = new OrbitaryWindow();

    public Mainframe()
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(orbitaryWindow);
            }
        });

        add(orbitaryWindow);
    }


    private static void initFX(JFXPanel fxPanel) {
        // This method is invoked on JavaFX thread
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }

    private static Scene createScene()
    {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 1700, 700);
        root.setBackground(new Background(
                Collections.singletonList(new BackgroundFill(
                        Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)),
                Collections.singletonList(new BackgroundImage(
                        new Image("https://www.wallpaperup.com/uploads/wallpapers/2013/08/21/136960/e86660f10f06aef664c8d552a7ec487a-700.jpg",
                                WIDTH, HEIGHT, false, true),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        BackgroundSize.DEFAULT))));

        return scene;
    }

    public void addNodeToPane(Node node)
    {
        rootPane.getChildren().add(node);
    }

    public OrbitaryWindow getOrbitaryWindow()
    {
        return orbitaryWindow;
    }



}
