package Controller.Calculators;

import Controller.Main;
import Model.Orbit;
import Model.Planet;
import Model.Sun;

public class OrbitCalculator
{
    public Orbit calculatePlanetOrbit(Sun sun, Planet planet)
    {
        int orbitWidth = (planet.getSemiMajorAxis())*2;
        int orbitHeight = (int)((Math.sqrt(((double)planet.getAphelion()*(double)planet.getPerihelion()))))*2;

        int orbitOffsetFromSun = planet.getAphelion() - planet.getSemiMajorAxis();

        int orbitXCord = sun.getxCord() + orbitOffsetFromSun;
        int orbitYCord = sun.getyCord();

        return new Orbit(orbitWidth, orbitHeight, orbitXCord, orbitYCord);
    }

}
