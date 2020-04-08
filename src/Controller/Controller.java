package Controller;

import Controller.Calculators.OrbitCalculator;
import Controller.Calculators.PlanetCalculator;
import Model.Orbit;
import Model.Sun;

import java.util.ArrayList;

import Model.Planet;
import Enum.*;
import View.Mainframe;
import View.OrbitaryWindow;
import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;

import javax.swing.*;

public class Controller
{
    private APIReader reader;
    private OrbitCalculator orbitCalculator;
    private PlanetCalculator planetCalculator;
    private Mainframe mainframe;

    private Sun sun;

    private ArrayList<Planet> planetArrayList = new ArrayList<>();


    public Controller()
    {
        reader = new APIReader();
        orbitCalculator = new OrbitCalculator();
        planetCalculator = new PlanetCalculator();

        this.sun = new Sun(reader.readBodyFromAPI(Stars.soleil.toString()));
        sun.setYCord(0);
        sun.setXCord(0);
        readAllPlanets();
        addPlanetOrbits();
        setPlanetDurations();
        setPathtransitions();
        System.out.println(sun.getMass());
        mainframe = new Mainframe();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                addPlanets();
                mainframe.init();
            }
        });
        //JOptionPane.showMessageDialog(null, mainframe);

        printAllPlanetsOrbits();
        
    }

    private void readAllPlanets()
    {
        for(Planets p : Planets.values())
        {
            planetArrayList.add(new Planet(reader.readBodyFromAPI(p.toString())));
        }
    }

    private void setPathtransitions()
    {
        for (int i = 0; i < planetArrayList.size() ; i++)
        {
            planetArrayList.get(i).setPathTransition();
        }
    }

    private void setPlanetDurations()
    {
        for (Planet planet: planetArrayList)
        {
            planet.setDuration(new Duration(planetCalculator.calculatePlanetSunOrbitTime(sun, planet)*1000));
        }
    }




    private void addPlanets()
    {
        for (int i = 0; i < planetArrayList.size() ; i++)
        {
            mainframe.addPlanet(planetArrayList.get(i));
        }
    }


    private void addPlanetOrbits()
    {
        for (Planet p : planetArrayList)
        {
            p.setPlanetOrbit(orbitCalculator.calculatePlanetSunOrbit(sun, p));//Create orbit
            //p.getPlanetOrbit().setCircumference(orbitCalculator.calculatePlanetOrbitCircumference(p));//Add orbit circumference
        }
    }

    private void printAllPlanetsOrbits()
    {
        for (Planet p : planetArrayList)
        {
            System.out.println(p.toString());
        }
    }

    public Ellipse getEarthOrbit()
    {
        return planetArrayList.get(3).getPlanetOrbit().getEllipseFromOrbit();
    }


public static void main(String[] args)
{
    Controller controller = new Controller();
}
}


