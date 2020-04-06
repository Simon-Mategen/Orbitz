package Controller.Calculators;

import Model.Orbit;
import Model.Planet;
import Model.Sun;

/**
 *
 * @author Simon and Marcus
 */

public class OrbitCalculator
{
    private static final long SCALE_VALUE = 10000000;

    public Orbit calculatePlanetSunOrbit(Sun sun, Planet planet)
    {
        long orbitWidth = (planet.getSemiMajorAxis())*2;
        long orbitHeight = (long)((Math.sqrt(((double)planet.getAphelion()*(double)planet.getPerihelion()))))*2;

        long orbitOffsetFromSun = planet.getAphelion() - planet.getSemiMajorAxis();

        long orbitXCord = sun.getXCord() + orbitOffsetFromSun;
        int orbitYCord = sun.getYCord();

        return new Orbit(orbitWidth, orbitHeight, orbitXCord, orbitYCord);
    }

    //Ska läggas till en metod för PlanetMoonOrbit

    public Orbit scaleOrbit(Orbit orbit)
    {
        Orbit scaledOrbit = new Orbit(
                orbit.getWidth()/SCALE_VALUE, orbit.getHeight()/SCALE_VALUE,
                orbit.getXCord()/SCALE_VALUE, orbit.getYCord()/SCALE_VALUE);

        return scaledOrbit;
    }

}