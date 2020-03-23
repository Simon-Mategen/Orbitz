package test;

import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;

public class Omlopp extends Application {
    private PathTransition pathTransitionMe;
    private PathTransition pathTransitionVe;
    private PathTransition pathTransitionTe;
    private PathTransition pathTransitionMa;
    private PathTransition pathTransitionJu;
    private PathTransition pathTransitionSa;
    private PathTransition pathTransitionUr;
    private PathTransition pathTransitionNe;

    private void init(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 900, 600);
        //Image universePic = new Image("https://i.pinimg.com/originals/ac/5d/da/ac5dda2fda9686850d36c0dc2fa6effc.jpg");
        //BackgroundImage universe = new BackgroundImage(universePic, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        //scene.setFill(universe);
        scene.setFill(Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Omloppsbanor");
        primaryStage.getIcons().add(new Image("https://cdn.iconscout.com/icon/free/png-256/solar-system-1552739-1314457.png"));

        Image sunPic = new Image("http://icons.iconarchive.com/icons/zairaam/bumpy-planets/96/01-sun-icon.png");
        ImageView sun = new ImageView();
        sun.setImage(sunPic);
        sun.setPreserveRatio(true);
        sun.setSmooth(false);
        sun.setCache(true);
        root.getChildren().add(sun);
        sun.setX(405);
        sun.setY(270);

        Image mercuryPic = new Image("http://icons.iconarchive.com/icons/zairaam/bumpy-planets/24/02-mercury-icon.png");
        ImageView mercury = new ImageView();
        mercury.setFitWidth(15);
        mercury.setFitHeight(15);
        mercury.setImage(mercuryPic);
        mercury.setPreserveRatio(true);
        mercury.setSmooth(false);
        mercury.setCache(true);
        root.getChildren().add(mercury);

        Path pathMe = createEllipsePath(590, 315, 140, 70, 0);
        root.getChildren().add(pathMe);

        pathTransitionMe = new PathTransition();
        pathTransitionMe.setDuration(Duration.seconds(7));
        pathTransitionMe.setPath(pathMe);
        pathTransitionMe.setNode(mercury);
        pathTransitionMe.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransitionMe.setCycleCount(Timeline.INDEFINITE);
        pathTransitionMe.setAutoReverse(false);

        Image venusPic = new Image("http://icons.iconarchive.com/icons/zairaam/bumpy-planets/32/03-venus-icon.png");
        ImageView venus = new ImageView();
        venus.setFitWidth(25);
        venus.setFitHeight(25);
        venus.setImage(venusPic);
        venus.setPreserveRatio(true);
        venus.setSmooth(false);
        venus.setCache(true);
        root.getChildren().add(venus);

        Path pathVe = createEllipsePath(620, 315, 170, 85, 0);
        root.getChildren().add(pathVe);

        pathTransitionVe = new PathTransition();
        pathTransitionVe.setDuration(Duration.seconds(8));
        pathTransitionVe.setPath(pathVe);
        pathTransitionVe.setNode(venus);
        pathTransitionVe.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransitionVe.setCycleCount(Timeline.INDEFINITE);
        pathTransitionVe.setAutoReverse(false);

        Image tellusPic = new Image("http://icons.iconarchive.com/icons/zairaam/bumpy-planets/32/04-earth-icon.png");
        ImageView tellus = new ImageView();
        tellus.setFitWidth(25);
        tellus.setFitHeight(25);
        tellus.setImage(tellusPic);
        tellus.setPreserveRatio(true);
        tellus.setSmooth(false);
        tellus.setCache(true);
        root.getChildren().add(tellus);

        Path pathTe = createEllipsePath(650, 315, 200, 100, 0);
        root.getChildren().add(pathTe);

        pathTransitionTe = new PathTransition();
        pathTransitionTe.setDuration(Duration.seconds(10));
        pathTransitionTe.setPath(pathTe);
        pathTransitionTe.setNode(tellus);
        pathTransitionTe.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransitionTe.setCycleCount(Timeline.INDEFINITE);
        pathTransitionTe.setAutoReverse(false);

        Image marsPic = new Image("http://icons.iconarchive.com/icons/zairaam/bumpy-planets/32/06-mars-icon.png");
        ImageView mars = new ImageView();
        mars.setFitWidth(20);
        mars.setFitHeight(20);
        mars.setImage(marsPic);
        mars.setPreserveRatio(true);
        mars.setSmooth(false);
        mars.setCache(true);
        root.getChildren().add(mars);

        Path pathMa = createEllipsePath(690, 315, 240, 120, 0);
        root.getChildren().add(pathMa);

        pathTransitionMa = new PathTransition();
        pathTransitionMa.setDuration(Duration.seconds(9));
        pathTransitionMa.setPath(pathMa);
        pathTransitionMa.setNode(mars);
        pathTransitionMa.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransitionMa.setCycleCount(Timeline.INDEFINITE);
        pathTransitionMa.setAutoReverse(false);

        Image jupiterPic = new Image("http://icons.iconarchive.com/icons/zairaam/bumpy-planets/48/07-jupiter-icon.png");
        ImageView jupiter = new ImageView();
        jupiter.setFitWidth(39);
        jupiter.setFitHeight(39);
        jupiter.setImage(jupiterPic);
        jupiter.setPreserveRatio(true);
        jupiter.setSmooth(false);
        jupiter.setCache(true);
        jupiter.setOnMouseClicked(mouseEvent -> getNewStage());
        root.getChildren().add(jupiter);

        Path pathJu = createEllipsePath(730, 315, 280, 140, 0);
        root.getChildren().add(pathJu);

        pathTransitionJu = new PathTransition();
        pathTransitionJu.setDuration(Duration.seconds(10.5));
        pathTransitionJu.setPath(pathJu);
        pathTransitionJu.setNode(jupiter);
        pathTransitionJu.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransitionJu.setCycleCount(Timeline.INDEFINITE);
        pathTransitionJu.setAutoReverse(false);

        Image saturnPic = new Image("http://icons.iconarchive.com/icons/zairaam/bumpy-planets/48/08-saturn-icon.png");
        ImageView saturn = new ImageView();
        saturn.setFitWidth(37);
        saturn.setFitHeight(37);
        saturn.setImage(saturnPic);
        saturn.setPreserveRatio(true);
        saturn.setSmooth(false);
        saturn.setCache(true);
        root.getChildren().add(saturn);

        Path pathSa = createEllipsePath(770, 315, 320, 160, 0);
        root.getChildren().add(pathSa);

        pathTransitionSa = new PathTransition();
        pathTransitionSa.setDuration(Duration.seconds(11));
        pathTransitionSa.setPath(pathSa);
        pathTransitionSa.setNode(saturn);
        pathTransitionSa.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransitionSa.setCycleCount(Timeline.INDEFINITE);
        pathTransitionSa.setAutoReverse(false);

        Image uranusPic = new Image("https://upload.wikimedia.org/wikipedia/commons/1/16/Uranus2-transparent.png");
        ImageView uranus = new ImageView();
        uranus.setFitWidth(33);
        uranus.setFitHeight(33);
        uranus.setImage(uranusPic);
        uranus.setPreserveRatio(true);
        uranus.setSmooth(false);
        uranus.setCache(true);
        root.getChildren().add(uranus);

        Path pathUr = createEllipsePath(810, 315, 360, 180, 0);
        root.getChildren().add(pathUr);

        pathTransitionUr = new PathTransition();
        pathTransitionUr.setDuration(Duration.seconds(10));
        pathTransitionUr.setPath(pathUr);
        pathTransitionUr.setNode(uranus);
        pathTransitionUr.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransitionUr.setCycleCount(Timeline.INDEFINITE);
        pathTransitionUr.setAutoReverse(false);

        Image neptunePic = new Image("https://icon-icons.com/icons2/1434/PNG/64/10-neptune_98589.png");
        ImageView neptune = new ImageView();
        neptune.setFitWidth(35);
        neptune.setFitHeight(35);
        neptune.setImage(neptunePic);
        neptune.setPreserveRatio(true);
        neptune.setSmooth(false);
        neptune.setCache(true);
        root.getChildren().add(neptune);

        Path pathNe = createEllipsePath(850, 315, 400, 200, 0);
        root.getChildren().add(pathNe);

        pathTransitionNe = new PathTransition();
        pathTransitionNe.setDuration(Duration.seconds(12));
        pathTransitionNe.setPath(pathNe);
        pathTransitionNe.setNode(neptune);
        pathTransitionNe.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransitionNe.setCycleCount(Timeline.INDEFINITE);
        pathTransitionNe.setAutoReverse(false);

        mercury.toFront();
        venus.toFront();
        tellus.toFront();
        mars.toFront();
        jupiter.toFront();
        saturn.toFront();
        uranus.toFront();
        neptune.toFront();
    }

    public Stage getNewStage(){
        String title = "";
        Stage planetInfo = new Stage();
        planetInfo.setTitle(title);
        StackPane layout = new StackPane();
        Scene planetScene = new Scene(layout, 350, 350);
        planetInfo.setScene(planetScene);
        Image jupiterPic = new Image("https://www.stickpng.com/assets/images/580b585b2edbce24c47b2707.png");
        ImageView jupiter = new ImageView(jupiterPic);
        layout.getChildren().add(jupiter);
        planetInfo.getIcons().add(new Image("https://i.pinimg.com/originals/95/85/51/9585515df18fc4bc39d783fb7d34075e.png"));
        planetInfo.show();
        return planetInfo;
    }

    private Path createEllipsePath(double centerX, double centerY, double radiusX, double radiusY, double rotate) {
        ArcTo arcTo = new ArcTo();
        arcTo.setX(centerX - radiusX + 1); // to simulate a full 360 degree celcius circle.
        arcTo.setY(centerY - radiusY);
        arcTo.setSweepFlag(false);
        arcTo.setLargeArcFlag(true);
        arcTo.setRadiusX(radiusX);
        arcTo.setRadiusY(radiusY);
        arcTo.setXAxisRotation(rotate);

        Path path = new Path();
        path.getElements().addAll(
                new MoveTo(centerX - radiusX, centerY - radiusY),
                arcTo,
                new ClosePath()); // close 1 px gap.
        path.setStroke(Color.GRAY);
        //path.getStrokeDashArray().setAll(5d, 5d);
        return path;
    }

    public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
        pathTransitionMe.play();
        pathTransitionVe.play();
        pathTransitionTe.play();
        pathTransitionMa.play();
        pathTransitionJu.play();
        pathTransitionSa.play();
        pathTransitionUr.play();
        pathTransitionNe.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}