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
        return new Sphere();
    }

    @Override
    public String toString()
    {
        return getName() + "\n" + "Height: " + planetOrbit.getHeight() + "\t" + "Width: " + planetOrbit.getWidth() + "\t" + "X: " + planetOrbit.getXCord() + "\t" + "Y: " + planetOrbit.getYCord() + "\n"
                + "A: " + getAphelion() + "\t" + "P: " + getPerihelion() + "\n";

    }

}