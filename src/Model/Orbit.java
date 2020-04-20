package Model;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 * A class that sets the orbits' features and values
 * These include width, height, shape and circumference
 */

public class Orbit
{
    private static final long SCALE_VALUE = 25000000; //the scale from the orbits' real values

    private double width;
    private double height;
    private double xCord;
    private double yCord;
    private double radiusX;
    private double radiusY;

    private Ellipse ellipse;

    public Orbit(double inWidth, double inHeight, double inXCord, double inYCord)
    {
        this.width = inWidth;
        this.height = inHeight;
        this.xCord = inXCord;
        this.yCord = inYCord;

        this.radiusX = width/2;
        this.radiusY = height/2;

        ellipse = new Ellipse(getXCord(), getYCord(), getWidth(), getHeight()); 
        ellipse.setStroke(Color.GRAY);
        ellipse.setFill(Color.TRANSPARENT);
    }

    public double getRadiusX()
    {
        return radiusX;
    }

    public double getRadiusY()
    {
        return radiusY;
    }

    public double getWidth()
    {
        return width/SCALE_VALUE;
    }

    public double getHeight()
    {
        return height/SCALE_VALUE;
    }

    public double getXCord()
    {
        return xCord/SCALE_VALUE;
    }

    public double getYCord()
    {
        return yCord/SCALE_VALUE;
    }


    public Ellipse getEllipseFromOrbit()
    {
        return ellipse;
    }
}
