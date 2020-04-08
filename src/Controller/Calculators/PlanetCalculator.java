package Controller.Calculators;

import Model.Planet;
import Model.Sun;

public class PlanetCalculator
{
    private final double G = 6.67430E-11;
    private final double SCALE_VALUE = 10000000;

    public double calculatePlanetSunOrbitTime(Sun sun, Planet planet)//Returns time in seconds
    {
        double my = G*sun.getMass();

        return ((2*Math.PI)*(Math.sqrt((Math.pow(((double) planet.getSemiMajorAxis()*1000), 3))/my)))/SCALE_VALUE;
    }
}
