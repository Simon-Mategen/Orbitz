package Model;

public class PlanetInfo
{
    private double mass;                //kg
    private double circumference;
    private int numOfMoons;
    private double lengthOfYear;       //earth years
    private double distanceFromSun;

    public PlanetInfo (double mass, double circumference, int numOfMoons, double lengthOfYear, double distanceFromSun)
    {
        this.mass = mass;
        this.circumference = circumference;
        this.numOfMoons = numOfMoons;
        this.lengthOfYear = lengthOfYear;
        this.distanceFromSun = distanceFromSun;
    }

    public PlanetInfo(){}

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
