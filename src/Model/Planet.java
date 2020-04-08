package Model;

import javafx.scene.shape.Sphere;
import org.json.simple.JSONObject;

public class Planet
{
    private long semimajorAxis;
    private long perihelion;
    private long aphelion;
    private JSONObject planetInfo;

    private double diameter;

    private Orbit planetOrbit;


    public Planet(JSONObject object)
    {
        this.planetInfo = object;
    }

    public String getID()
    {
        return planetInfo.get("id").toString();
    }

    public long getSemiMajorAxis()
    {
        semimajorAxis= (long)planetInfo.get("semimajorAxis");
        return semimajorAxis;
    }

    public long getPerihelion()
    {
        perihelion = (long)planetInfo.get("perihelion");
        return perihelion;
    }

    public long getAphelion()
    {
        aphelion = (long)planetInfo.get("aphelion");
        return aphelion;
    }


    public String getName()
    {
        return planetInfo.get("englishName").toString();
    }

    public Orbit getPlanetOrbit()
    {
        return planetOrbit;
    }

    public void setPlanetOrbit(Orbit planetOrbit)
    {
        this.planetOrbit = planetOrbit;
    }

    public void setDiameter(double diameter)
    {
        this.diameter = diameter;
    }

    public Sphere getSphereFromPlanet()
    {
        return new Sphere(30);
    }

    public double getMass()
    {
        JSONObject planetMass = (JSONObject )planetInfo.get("mass");

        double massValue = (double) planetMass.get("massValue");
        long massExponent = (long) planetMass.get("massExponent");

        return massValue * (Math.pow(10, massExponent));
    }

    @Override
    public String toString() //Prints information about planets orbit
    {
        return getName() + "\n" + "Height: " + planetOrbit.getHeight() + "\n" + "Width: " + planetOrbit.getWidth() + "\n" + "x: " + planetOrbit.getXCord() + "\n" + "x: " + planetOrbit.getYCord() + "\n"
                + "A: " + getAphelion() + "\n" + "P: " + getPerihelion() + "\n" + "Circumference: " + planetOrbit.getCircumference() + "\n" + planetOrbit.getRealCircumference() + "\n" + "Mass: " + getMass();

    }

}
