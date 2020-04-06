package Controller;

import Controller.Calculators.OrbitCalculator;
import Model.Orbit;
import Model.Sun;

import java.util.ArrayList;

import Model.Planet;
import Enum.*;
import View.Mainframe;
import View.OrbitaryWindow;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.shape.Ellipse;

import javax.swing.*;

public class Controller
{
    private APIReader reader;
    private OrbitCalculator orbitCalculator;
    Mainframe mainframe;

    private Sun sun;

    private ArrayList<Planet> planetArrayList = new ArrayList<>();


    public Controller()
    {
        mainframe = new Mainframe();
        JOptionPane.showMessageDialog(null, mainframe);
        reader = new APIReader();
        orbitCalculator = new OrbitCalculator();

        this.sun = new Sun(reader.readBodyFromAPI(Stars.soleil.toString()));
        sun.setYCord(0);
        sun.setXCord(0);

        readAllPlanets();
        addPlanetOrbits();

        //System.out.println(sun.getMass());

        //addPlanettoGUI();
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

/*    private void printAllPlanetsOrbits()
    {
        for (Planet p : planetArrayList)
        {
            System.out.println(p.toString());
        }
    }*/

    public Ellipse getEarthOrbit()
    {
        return planetArrayList.get(3).getPlanetOrbit().getEllipseFromOrbit();
    }

    public void addPlanettoGUI()
    {
        Ellipse orbit = planetArrayList.get(3).getPlanetOrbit().getEllipseFromOrbit();

        mainframe.getOrbitaryWindow().addOrbit(orbit);
    }


public static void main(String[] args)
{
    Controller controller = new Controller();
}
}


