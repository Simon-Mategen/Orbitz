package View;

import Controller.Controller;
import Model.Planet;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Path;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Collections;
import java.util.LinkedList;

public class OrbitaryWindow extends JFXPanel {
    private Sphere earth;
    private Sphere moon;
    private LinkedList<Planet> planetList = new LinkedList<>();
    final private int WIDTH = 1000;
    final private int HEIGHT = 600;
    private StackPane root = new StackPane();
    //Stage primaryStage;

    public OrbitaryWindow()
    {
        //primaryStage = stage;



        // Button btn = new Button();
        // btn.setText("Say 'Hello World'");
        // btn.setOnAction(new EventHandler<ActionEvent>() {
        //
        // @Override
        // public void handle(ActionEvent event) {
        // System.out.println("Hello World!");
        // }
        // });
        /*Sphere star = new Sphere(45);
        earth = new Sphere(45);
        moon = new Sphere(25);*/

        //Ellipse ellipseEarth = new Ellipse();
        // ellipseEarth.setCenterX(star.getTranslateX());
        // ellipseEarth.setCenterY(star.getTranslateY());
        // ellipseEarth.translateXProperty().bind(star.translateXProperty());
        // ellipseEarth.translateYProperty().bind(star.translateYProperty());
       // ellipseEarth.setRadiusX(star.getBoundsInLocal().getWidth() / 2.0 + 1.01671388 * 170);
       // ellipseEarth.setRadiusY(star.getBoundsInLocal().getHeight() / 2.0 + 0.98329134 * 170);

        /*PathTransition transitionEarth = new PathTransition();
        transitionEarth.setPath(ellipseEarth);
        transitionEarth.setNode(earth);
        transitionEarth.setInterpolator(Interpolator.LINEAR);
        transitionEarth.setDuration(Duration.seconds(10.000017421));
        transitionEarth.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transitionEarth.setCycleCount(Timeline.INDEFINITE);

         */

        //transitionEarth.play();

        //Ellipse ellipseMoon = new Ellipse();
        // ellipseMoon.setCenterX(earth.getTranslateX());
        // ellipseMoon.setCenterY(earth.getTranslateY());
        // ellipseMoon.translateXProperty().bind(earth.translateXProperty());
        // ellipseMoon.translateYProperty().bind(earth.translateYProperty());
       // ellipseMoon.setRadiusX(earth.getBoundsInLocal().getWidth() / 2.0 + 1.01671388 * 70);
        //ellipseMoon.setRadiusY(earth.getBoundsInLocal().getHeight() / 2.0 + 0.98329134 * 70);
        // ellipse.setStrokeWidth(5);

        /*PathTransition transitionMoon = new PathTransition();
        transitionMoon.setPath(ellipseMoon);
        transitionMoon.setNode(moon);
        transitionMoon.setInterpolator(Interpolator.LINEAR);
        transitionMoon.setDuration(Duration.seconds(1.000017421));
        transitionMoon.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transitionMoon.setCycleCount(Timeline.INDEFINITE);
        transitionMoon.play();

         */

        /*
         * Hide the ellipse shadows
         */
        //ellipseMoon.setVisible(false);
        //ellipseEarth.setVisible(false);

        /* here we create a new pane which we bind to earths location, add moon
         * into it and then add the pane to the root pane
         */

        /*StackPane moonPane = new StackPane();
        moonPane.translateXProperty().bind(earth.translateXProperty());
        moonPane.translateYProperty().bind(earth.translateYProperty());
        moonPane.setMaxSize(100, 100);
        //color for the new pane to see it

        root.getChildren().add(star);
        moonPane.getChildren().add(moon);
        root.getChildren().add(moonPane);
        //root.getChildren().add(ellipseEarth);
        root.getChildren().add(earth);

         */

        /*Scene scene = new Scene(root, 1700, 700);
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


        primaryStage.setTitle("Planetarium");
        primaryStage.setScene(scene);
        primaryStage.show();

         */


    }

    private void createScene() {
        Scene scene = new Scene(root);
        setScene(scene);
    }

    public void addSun(Sphere sun)
    {
        root.getChildren().add(sun);
    }

    public void addPlanet(Planet planet)
    {
        PathTransition pathTransition = new PathTransition();
        pathTransition.setPath(planet.getPlanetOrbit().getEllipseFromOrbit());
        pathTransition.setNode(planet.getSphereFromPlanet());
        root.getChildren().add(planet.getSphereFromPlanet());
        planetList.add(planet);

        pathTransition.play();
    }

    public void addOrbit(Ellipse orbit)
    {
        root.getChildren().add(orbit);
    }

}
