package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlanetInfo
{
    private double mass;                //kg
    private double circumference;
    private int numOfMoons;
    private double lengthOfYear;       //earth years
    private double distanceFromSun;
    private SimpleStringProperty test;

    public PlanetInfo (double mass, double circumference, int numOfMoons, double lengthOfYear, double distanceFromSun)
    {
        this.mass = mass;
        this.circumference = circumference;
        this.numOfMoons = numOfMoons;
        this.lengthOfYear = lengthOfYear;
        this.distanceFromSun = distanceFromSun;
    }

    public PlanetInfo(String test)
    {
        this.test = new SimpleStringProperty(test);
    }

    public PlanetInfo(){}

    public String getTest()
    {
        return test.get();
    }
    public void setTest(String test2)
    {
        test.set(test2);
    }

    public StringProperty testProperty()
    {
        return test;
    }

    public double getMass()
    {
        return mass;
    }

    public double getCircumference()
    {
        return circumference;
    }

    public int getNumOfMoons()
    {
        return numOfMoons;
    }

    public double getLengthOfYear()
    {
        return lengthOfYear;
    }

    public double getDistanceFromSun()
    {
        return distanceFromSun;
    }

    public String toString()
    {
       return String.format("%f %f %d %f %f", getMass(), getCircumference(), getNumOfMoons(), getLengthOfYear(), getDistanceFromSun());
    }
}
