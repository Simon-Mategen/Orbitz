package Model;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Orbit
{
    private long width;
    private long height;
    private long xCord;
    private long yCord;

    public Orbit(long inWidth, long inHeight, long inXCord, long inYCord)
    {
        this.width = inWidth;
        this.height = inHeight;
        this.xCord = inXCord;
        this.yCord = inYCord;
    }

    public long getWidth()
    {
        return width;
    }

    public long getHeight()
    {
        return height;
    }

    public long getXCord()
    {
        return xCord;
    }

    public long getYCord()
    {
        return yCord;
    }

    public Ellipse getEllipseFromOrbit()
    {
        return new Ellipse((double)getXCord(), (double)getYCord(), (double)getWidth(), (double)getHeight());
    }


}
