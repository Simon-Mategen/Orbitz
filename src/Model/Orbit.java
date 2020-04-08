package Model;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Orbit
{
    private static final long SCALE_VALUE = 10000000;

    private long width;
    private long height;
    private long xCord;
    private long yCord;
    private long circumference;
    private Ellipse ellipse;

    public Orbit(long inWidth, long inHeight, long inXCord, long inYCord)
    {
        this.width = inWidth;
        this.height = inHeight;
        this.xCord = inXCord;
        this.yCord = inYCord;
        ellipse = new Ellipse((double)getXCord(), (double)getYCord(), (double)getWidth(), (double)getHeight());
        ellipse.setStroke(Color.GRAY);
        ellipse.setFill(Color.TRANSPARENT);
    }

    public long getWidth()
    {
        return width/SCALE_VALUE;
    }

    public long getHeight()
    {
        return height/SCALE_VALUE;
    }

    public long getRealHeight()
    {
        return height;
    }

    public long getRealWidth()
    {
        return width;
    }

    public long getXCord()
    {
        return xCord/SCALE_VALUE;
    }

    public long getYCord()
    {
        return yCord/SCALE_VALUE;
    }

    public long getCircumference()
    {
        return circumference/SCALE_VALUE;
    }

    public long getRealCircumference()
    {
        return circumference;
    }

    public void setCircumference(long circumference)
    {
        this.circumference = circumference;
    }

    public Ellipse getEllipseFromOrbit()
    {
        return ellipse;
    }


}
