package Controller;

import Controller.Calculators.OrbitCalculator;
import Controller.Calculators.PlanetCalculator;
import Controller.Calculators.PositionCalculator;
import Model.Sun;

import java.util.ArrayList;

import Model.Planet;
import Enum.*;
import View.MainFrame;
import javafx.util.Duration;

public class Controller
{
    private APIReader reader;
    private OrbitCalculator orbitCalculator;
    private PlanetCalculator planetCalculator;
    private PositionCalculator positionCalculator;
    private MainFrame mainframe;

    private Sun sun;

    private ArrayList<Planet> planetArrayList = new ArrayList<>();

    public Controller()
    {


        reader = new APIReader();
        orbitCalculator = new OrbitCalculator();
        planetCalculator = new PlanetCalculator();
        positionCalculator = new PositionCalculator();


        this.sun = new Sun(reader.readBodyFromAPI(Stars.soleil.toString()));
        sun.setYCord(0);
        sun.setXCord(0);

        readAllPlanets();
        addPlanetOrbits();
        setPlanetDurations();
        setPathtransitions();

        mainframe = new MainFrame(this);
        //printAllPlanetsOrbits();
        
    }

    public ArrayList<Planet> getPlanetArrayList()
    {
        return planetArrayList;
    }

    private void readAllPlanets()
    {
        for(Planets p : Planets.values())
        {
            planetArrayList.add(new Planet(reader.readBodyFromAPI(p.toString())));
        }
    }

    public void setPathtransitions()
    {
        for (int i = 0; i < planetArrayList.size() ; i++)
        {
            planetArrayList.get(i).createPathTransition();
        }
    }

    public void setPlanetDurations()
    {
        for (Planet planet: planetArrayList)
        {
            planet.setDuration(new Duration(planetCalculator.calculatePlanetSunOrbitTime(sun, planet)*1000));
        }
    }


    private void addPlanetOrbits()
    {
        for (Planet p : planetArrayList)
        {
            p.setPlanetOrbit(orbitCalculator.calculatePlanetSunOrbit(sun, p));//Create orbit
        }
    }

    private void printAllPlanetsOrbits()
    {
        for (Planet p : planetArrayList)
        {
            System.out.println(p.toString());
        }
    }


    public static void main(String[] args)
{
    Controller controller = new Controller();
}
}


