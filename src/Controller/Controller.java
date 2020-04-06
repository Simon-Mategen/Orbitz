package Controller;

import Controller.Calculators.OrbitCalculator;
import Model.Orbit;
import Model.Sun;

import java.util.ArrayList;

import Model.Planet;
import Enum.*;
import View.OrbitaryWindow;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.shape.Ellipse;

public class Controller
{
    private APIReader reader;
    private OrbitCalculator orbitCalculator;
    private OrbitaryWindow orbitaryWindow;

    private Sun sun;

    private ArrayList<Planet> planetArrayList = new ArrayList<>();


    public Controller(OrbitaryWindow gui)
    {
        this.orbitaryWindow = gui;

        reader = new APIReader();
        orbitCalculator = new OrbitCalculator();

        this.sun = new Sun(reader.readBodyFromAPI(Stars.soleil.toString()));
        sun.setYCord(0);
        sun.setXCord(0);

        readAllPlanets();
        addPlanetOrbits();

        System.out.println(sun.getMass());


        //addPlanettoGUI();
    }

    public void setOrbitaryWindow(OrbitaryWindow window)
    {
        this.orbitaryWindow = window;
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

    public Ellipse getEarthOrbit()
    {
        return planetArrayList.get(3).getPlanetOrbit().getEllipseFromOrbit();
    }

    public void addPlanettoGUI()
    {
        Ellipse orbit = planetArrayList.get(3).getPlanetOrbit().getEllipseFromOrbit();

        orbitaryWindow.addOrbit(orbit);
    }

/*    private void printAllOrbits()
    {
        for (Orbit o : orbits)
        {
            System.out.println(o.toString());
        }
    }*/
}
