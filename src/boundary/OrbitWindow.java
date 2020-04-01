package boundary;

import Model.Orbit;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

import javafx.application.Application;

public class OrbitWindow extends Application
{
    private Stage stage;
    private Scene scene;
    private StackPane root;
    private Background bg = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));

    public void start(Stage stage) throws Exception
    {
        this.stage = stage;
        stage.setTitle("Hello World!");
        root = new StackPane();
        //root.getChildren().add(btn);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        scene = new Scene(root, 1920, 1080);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public void changeBackground()
    {
        root.setBackground(bg);
    }

    public void addOrbit(Orbit orbit)
    {
        root.getChildren().add(orbit);
        root.getChildren().add(orbit.getCelestialbody());
    }



}
