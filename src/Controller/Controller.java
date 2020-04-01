package Controller;

import Controller.Calculators.OrbitCalculator;
import Model.Orbit;
import Model.Sun;

import java.util.ArrayList;
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


    public Controller()
    {
        reader = new APIReader();
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
