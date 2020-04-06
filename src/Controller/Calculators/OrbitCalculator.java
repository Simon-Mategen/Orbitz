package Controller.Calculators;

import Model.Orbit;
import Model.Planet;
import Model.Sun;


public class OrbitCalculator
{

    public Orbit calculatePlanetSunOrbit(Sun sun, Planet planet)
    {
        long orbitWidth = (planet.getSemiMajorAxis())*2;
        long orbitHeight = (long)((Math.sqrt(((double)planet.getAphelion()*(double)planet.getPerihelion()))))*2;

        long orbitOffsetFromSun = planet.getAphelion() - planet.getSemiMajorAxis();

        long orbitXCord = sun.getXCord() + orbitOffsetFromSun;
        int orbitYCord = sun.getYCord();

        return new Orbit(orbitWidth, orbitHeight, orbitXCord, orbitYCord);
    }

/*    public long calculatePlanetOrbitCircumference(Planet planet)
    {
        if (planet.getPlanetOrbit().getWidth() == planet.getPlanetOrbit().getHeight()) //If orbit is a circle
        {
            return (long)Math.abs(2*(Math.PI*(planet.getPlanetOrbit().getRealWidth()/2)));
        }

        long a = planet.getPlanetOrbit().getRealHeight()/2;
        long b = planet.getPlanetOrbit().getRealWidth()/2;

        return (long)Math.abs(Math.PI*((3*(a-b))-(Math.sqrt(((3*a)+b)*(a+(3*b))))));
    }*/

    //Ska läggas till en metod för PlanetMoonOrbit


}
