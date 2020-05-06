package Model;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 * A class that sets the orbits' features and values
 * These include width, height, shape and circumference
 */

public class Orbit
{
    private static final long SCALE_VALUE = 37500; //the scale from the orbits' real values

    private double width;
    private double height;
    private double xCord;
    private double yCord;
    private double circumference;

    private Ellipse ellipse;

    public Orbit(double inWidth, double inHeight, double inXCord, double inYCord)
    {
        this.width = inWidth;
        this.height = inHeight;
        this.xCord = inXCord;
        this.yCord = inYCord;

        ellipse = new Ellipse(getXCord(), getYCord(), getWidth(), getHeight());
        ellipse.setStrokeWidth(40);
        ellipse.setStroke(Color.GRAY);
        ellipse.setFill(Color.TRANSPARENT);
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

    public double getCircumference()
    {
        return circumference/SCALE_VALUE;
    }

    public double getRealCircumference()
    {
        return circumference;
    }

    public Ellipse getEllipseFromOrbit()
    {
        return ellipse;
    }

    public void setEllipseStrokeWidth(double value)
    {
        ellipse.setStrokeWidth(value);
    }
}
