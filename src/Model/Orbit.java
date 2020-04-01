package Model;

public class Orbit
{
    private int width;
    private int height;
    private int xCord;
    private int yCord;

    public Orbit(int inWidth, int inHeight, int inXCord, int inYCord)
    {
        this.width = inWidth;
        this.height = inHeight;
        this.xCord = inXCord;
        this.yCord = inYCord;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public int getxCord()
    {
        return xCord;
    }

    public int getyCord()
    {
        return yCord;
    }
}
