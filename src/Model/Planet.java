package Model;

import Controller.Calculators.PositionCalculator;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Tooltip;
import javafx.scene.shape.*;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import org.json.simple.JSONObject;

import java.awt.*;

/**
 * Class that represents a planet
 */
public class Planet
{
    private double SCALE_RADIUS_VALUE = 12500;

    private long semiMajorAxis;
    private double perihelion;
    private double aphelion;
    private double meanRadius;

    private JSONObject planetInfo;

    private Duration duration;

    private Sphere sphere;

    private PathTransition pathTransition;

    private Orbit planetOrbit;

    private PositionCalculator pos = new PositionCalculator();

    private int numOfMoons; // TODO

    private int distanceFromSun; // TODO

    /**
     * Constructor that, when created, creates a planet-object by getting specific values
     * for a specific planet from an API.
     * @param object JSONObject that holds the API information for the specific planets
     */
    public Planet(JSONObject object)
    {
        this.planetInfo = object;
        this.perihelion = (long)planetInfo.get("perihelion");
        this.aphelion = (long)planetInfo.get("aphelion");
        this.sphere = new Sphere((double)planetInfo.get("meanRadius")*1000/SCALE_RADIUS_VALUE);
        this.sphere.setId(getName());  // set ID so sphere's planet can be found
        //this.planetTableInfo = new PlanetInfo(getMass(), getMeanRadius(), 0, );
    }
    /**
     * Default constructor
     */
    public Planet()
    {

    }

    /**
     * A get-method that gets semi-major axis value from API and returning the value.
     * The semi-major axis is half the value of the longest diameter of an ellipse.
     * @return a planets semi-major axis in km
     */
    public double getSemiMajorAxis()
    {
        semiMajorAxis = (long)planetInfo.get("semimajorAxis");

        return semiMajorAxis;
    }

    /**
     * A get-method that retuns the value of a planets perihelion.
     * The perihelion is the distance between the sun and the specific planet
     * when the planet is at the closest position to the sun.
     * @return the perihelion distance in km
     */
    public double getPerihelion()
    {
        return perihelion;
    }

    /**
     * A get-method that retuns the value of a planets perihelion.
     * The aphelion is the distance between the sun and the specific planet
     *  when the planet is at the most distant position to the sun.
     * @return the aphelion distance in km
     */
    public double getAphelion()
    {
        return aphelion;
    }

    /**
     * A set-method that sets the value of a planets specific aphelion.
     * @param aphelion the value in km
     */
    public void setAphelion(double aphelion)
    {
        this.aphelion = aphelion;
    }

    /**
     * A set-method that sets the value of a planets specific perihelion.
     * @param perihelion the value in km
     */
    public void setPerihelion(double perihelion)
    {
        this.perihelion = perihelion;
    }

    /**
     * A get-method that returns the english name of a specific planet.
     * @return the english name of a planet with capital first letter
     */
    public String getName()
    {
        return planetInfo.get("englishName").toString();
    }

    /**
     * A get-method that returns a planets specific orbit.
     * The orbit of a planet is the curved path of a celestial object round, for example, a star.
     * @return an Orbit-object for a specific planet
     */
    public Orbit getPlanetOrbit()
    {
        return planetOrbit;
    }

    /**
     * A set-method that sets the orbit for a specific planet.
     * @param planetOrbit a planets specific orbit as an Orbit-object
     */
    public void setPlanetOrbit(Orbit planetOrbit)
    {
        this.planetOrbit = planetOrbit;
    }

    /**
     * A get-method that returns a Sphere-object for a specific planet.
     * @return a Sphere-object
     */
    public Sphere getSphereFromPlanet()
    {
        return sphere;
    }

    /**
     * A get-method that gets information from API and calculates the mass of a specific planet
     * and returns the mass value of that planet.
     * @return the mass value in kg
     */
    public double getMass()
    {
        JSONObject planetMass = (JSONObject )planetInfo.get("mass");

        double massValue = (double) planetMass.get("massValue");
        long massExponent = (long) planetMass.get("massExponent");

        double mass = massValue * (Math.pow(10, massExponent));

        return mass;
    }

    /**
     * A get-method that gets information from and returns the mean radius of a planet.
     * The mean radius is the average value of the planets all radii.
     * @return the mean radius value in km
     */
    public double getMeanRadius()
    {
        meanRadius = (double) planetInfo.get("meanRadius");

        return meanRadius;
    }

    /**
     * A toString-method that returns information about a specific planet.
     * @return a string with different values of a specific planet
     */
    public String toString() //Prints information about planets
    {
        return getName() + "\n" + "Height: " + planetOrbit.getHeight() + "\n"
                + "Width: " + planetOrbit.getWidth() + "\n" + "x: " + planetOrbit.getXCord()
                + "\n" + "y: " + planetOrbit.getYCord() + "\n" + "A: " + getAphelion() + "\n"
                + "P: " + getPerihelion() + "\n" + "Circumference: "
                + planetOrbit.getCircumference() + "\n" + planetOrbit.getRealCircumference()
                + "\n" + "Mass (kg): " + getMass() + "\n" + "Radius (km): " + getMeanRadius();
    }

    /**
     * A method that creates a path transition for a specific planet
     */
    public void createPathTransition()
    {
        pathTransition = new PathTransition();
        double d = pos.setDay(2020, 5, 5);

        planetOrbit.getEllipseFromOrbit().setRotate(-pos.getValues(d, getName()));
        pathTransition.setPath(planetOrbit.getEllipseFromOrbit());
        //sphere.getTransforms().addAll(new Translate(0, planetOrbit.getWidth()-planetOrbit.getXCord()));
        //sphere.getTransforms().addAll(new Translate(pos.getValues(d, getName(), 1), pos.getValues(d, getName(), 2)));
        pathTransition.setNode(sphere);
        pathTransition.setDuration(duration);
        pathTransition.setCycleCount(Animation.INDEFINITE);
        pathTransition.setInterpolator(Interpolator.LINEAR);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
    }

    /**
     * A get-method that returns a path transition of a specific planet
     * @return a path transition
     */
    public PathTransition getPathTransiton()
    {
        return pathTransition;
    }

    /**
     * A set-method that sets the duration of the orbital period for a specific planet.
     * @param duration Duration-object in milliseconds
     */
    public void setDuration(Duration duration)
    {
        this.duration = duration;
    }

    /**
     * Set a tooltip which informs the planets name
     * @author Albin Ahlbeck
     * @version 1.0
     */
   public void setTooltip()
   {
       Tooltip tooltip = new Tooltip(getName());
       tooltip.setStyle("-fx-font-size: 20");                   //CSS stylesheet, Oracle doc.
       tooltip.setShowDelay(Duration.millis(0));               //sets time before text appears after hovering over image

       sphere.setPickOnBounds(true);
       Tooltip.install(sphere, tooltip);
   }



}
