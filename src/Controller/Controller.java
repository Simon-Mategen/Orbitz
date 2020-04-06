package Controller;

import Controller.Calculators.OrbitCalculator;
import Model.Orbit;
import Model.Sun;

import java.util.ArrayList;

import Model.Planet;
import Enum.*;
import javafx.scene.shape.Ellipse;

public class Controller
{
    private APIReader reader;
    private OrbitCalculator orbitCalculator;

    private Sun sun;

    private ArrayList<Planet> planetArrayList = new ArrayList<>();


    public Controller()
    {
        reader = new APIReader();
        orbitCalculator = new OrbitCalculator();

        this.sun = new Sun(reader.readBodyFromAPI(Stars.soleil.toString()));
        sun.setYCord(0);
        sun.setXCord(0);

        readAllPlanets();
        addPlanetOrbits();

        System.out.println(sun.getMass());
    }

    private void readAllPlanets()
    {
        for(Planets p : Planets.values())
        {
            planetArrayList.add(new Planet(reader.readBodyFromAPI(p.toString())));
        }
    }

    private void addPlanetOrbits()
    {
        for (Planet p : planetArrayList)
        {
            p.setPlanetOrbit(orbitCalculator.calculatePlanetSunOrbit(sun, p));
        }
    }

    public void addPlanettoGUI()
    {
        Ellipse orbit = planetArrayList.get(3).getPlanetOrbit().getEllipseFromOrbit();
    }

/*    private void printAllOrbits()
    {
        for (Orbit o : orbits)
        {
            System.out.println(o.toString());
        }
    }*/
}
