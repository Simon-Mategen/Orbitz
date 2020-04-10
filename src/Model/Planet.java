package Model;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;
import org.json.simple.JSONObject;

public class Planet
{
    private long semimajorAxis;
    private double perihelion;
    private double aphelion;
    private double meanRadius;
    private JSONObject planetInfo;
    private Duration duration;

    private double diameter;

    private Sphere sphere = new Sphere(10); // temp
    private PathTransition pathTransition = new PathTransition();

    private Orbit planetOrbit;


    public Planet(JSONObject object)
    {
        //flyttade hit aphelion och perihelion, innan låg dom i get-metoderna
        this.planetInfo = object;
        this.perihelion = (long)planetInfo.get("perihelion");
        this.aphelion = (long)planetInfo.get("aphelion");

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

    public double getPerihelion()
    {
        return perihelion;
    }

    public double getAphelion()
    {
        return aphelion;
    }

    public void setAphelion(double aphelion) {
        this.aphelion = aphelion;
    }

    public void setPerihelion(double perihelion) {
        this.perihelion = perihelion;
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
        return sphere;
    }

    public double getMass()
    {
        JSONObject planetMass = (JSONObject )planetInfo.get("mass");

        double massValue = (double) planetMass.get("massValue");
        long massExponent = (long) planetMass.get("massExponent");

        return massValue * (Math.pow(10, massExponent));
    }

    public double getMeanRadius() {
        meanRadius = (double) planetInfo.get("meanRadius");
        return meanRadius;
    }

    @Override
    public String toString() //Prints information about planets orbit
    {
        return getName() + "\n" + "Height: " + planetOrbit.getHeight()*10000000 + "\n" + "Width: " + planetOrbit.getWidth()*10000000 + "\n" + "x: " + planetOrbit.getXCord() + "\n" + "y: " + planetOrbit.getYCord() + "\n"
                + "A: " + getAphelion() + "\n" + "P: " + getPerihelion() + "\n" + "Circumference: " + planetOrbit.getCircumference() + "\n" + planetOrbit.getRealCircumference() + "\n" + "Mass (kg): " + getMass()
                + "\n" + "Radius (km): " + getMeanRadius();

    }

    public void setPathTransition(PathTransition pathTransition) {
        this.pathTransition = pathTransition;

    }

    public void setPathTransition()
    {
        pathTransition.setNode(sphere);
        pathTransition.setPath(planetOrbit.getEllipseFromOrbit());
        pathTransition.setDuration(duration);
        pathTransition.setCycleCount(Animation.INDEFINITE);
        pathTransition.setInterpolator(Interpolator.LINEAR);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);


    }

    public PathTransition getPathTransiton()
    {
        return pathTransition;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
