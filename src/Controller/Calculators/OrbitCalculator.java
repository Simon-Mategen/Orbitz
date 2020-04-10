package Controller.Calculators;

import Model.Orbit;
import Model.Planet;
import Model.Sun;


public class OrbitCalculator
{


    public Orbit calculatePlanetSunOrbit(Sun sun, Planet planet)
    {
        //Var tvungen att lägga till dessa if-satser eftersom värdena på aphelion och perihelion var felaktiga
        //hos Uranus och Neptunus vilket gav oss fel värden för deras ellipser

        if(planet.getName().equals("Uranus")){
        planet.setAphelion(3003.62E6);
        planet.setPerihelion(2741.30E6);
            long orbitWidth = (planet.getSemiMajorAxis()) * 2;
            long orbitHeight = (long) ((Math.sqrt((planet.getAphelion() * planet.getPerihelion())))) * 2;

            double orbitOffsetFromSun = planet.getAphelion() - planet.getSemiMajorAxis();

            double orbitXCord = sun.getXCord() + orbitOffsetFromSun;
            double orbitYCord = sun.getYCord();


            return new Orbit(orbitWidth, orbitHeight, orbitXCord, orbitYCord);
        }
        if(planet.getName().equals("Neptune")) {
            planet.setAphelion(4545.67E6);
            planet.setPerihelion(4444.45E6);
            long orbitWidth = (planet.getSemiMajorAxis()) * 2;
            long orbitHeight = (long) ((Math.sqrt((planet.getAphelion() * planet.getPerihelion())))) * 2;

            double orbitOffsetFromSun = planet.getAphelion() - planet.getSemiMajorAxis();

            double orbitXCord = sun.getXCord() + orbitOffsetFromSun;
            double orbitYCord = sun.getYCord();


            return new Orbit(orbitWidth, orbitHeight, orbitXCord, orbitYCord);
        }

        long orbitWidth = (planet.getSemiMajorAxis()) * 2;
        long orbitHeight = (long) ((Math.sqrt((planet.getAphelion() * planet.getPerihelion())))) * 2;

        double orbitOffsetFromSun = planet.getAphelion() - planet.getSemiMajorAxis();

        double orbitXCord = sun.getXCord() + orbitOffsetFromSun;
        double orbitYCord = sun.getYCord();

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
