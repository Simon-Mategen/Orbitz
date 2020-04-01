package Controller;

import Controller.Calculators.OrbitCalculator;
import Model.Orbit;
import Model.Planet;
import Model.Sun;
import Enum.*;

import java.util.ArrayList;

public class Controller
{
    private APIReader reader;
    private OrbitCalculator orbitCalculator;

    private Sun sun;

    private ArrayList<Orbit> orbits = new ArrayList<>();


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

        orbitCalculator = new OrbitCalculator();

        this.sun = new Sun(reader.readBodyFromAPI(Stars.soleil.toString()));
        sun.setYCord(0);
        sun.setXCord(0);

        readAllPlanetsOrbits();
    }

    private void readAllPlanetsOrbits()
    {
        for(Planets p : Planets.values())
        {
            orbits.add(orbitCalculator.scaleOrbit(orbitCalculator.calculatePlanetOrbit(sun, new Planet(reader.readBodyFromAPI(p.toString())))));
        }
    }

/*    private void printAllOrbits()
    {
        for (Orbit o : orbits)
        {
            System.out.println(o.toString());
        }
    }*/


}
