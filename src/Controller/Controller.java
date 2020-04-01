package Controller;

import Model.Orbit;
import Model.Planet;
import Model.PlanetarySystem;
import Model.Solarsystem;
import boundary.OrbitWindow;
import boundary.PlanetWindow;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.LinkedList;

public class Controller extends Application
{
    private APIReader reader;
    PlanetWindow planetWindow;
    OrbitWindow orbitWindow;
    Solarsystem solarsystem = new Solarsystem();
    LinkedList<PlanetarySystem> planetarySystems;

    @Override
    public void start(Stage stage) throws Exception
    {
        reader = new APIReader();
        orbitWindow = new OrbitWindow();
        orbitWindow.start(stage);
        orbitWindow.changeBackground(); // testing changing during runtime


        // add orbits
        Planet testPlanet = new Planet(0, 0, 50, Color.BLUE);

        addOrbit(new Orbit(testPlanet, 500, 500, 250, 200));
        orbitWindow.addOrbit(getOrbit(0)); // adds orbit to view
    }

    public void addOrbit(Orbit orbit)
    {
        solarsystem.addOrbit(orbit);
    }

    public Orbit getOrbit(int index)
    {
        return solarsystem.getOrbit(index);
    }


    public static void main(String[] args)
    {
        launch(args);
    }


}
