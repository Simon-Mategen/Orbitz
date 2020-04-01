package Model;

public class Orbit
{
    private long width;
    private long height;
    private long xCord;
    private long yCord;
    private Planet planet;

    public Orbit(long inWidth, long inHeight, long inXCord, long inYCord, Planet planet)
    {
        this.width = inWidth;
        this.height = inHeight;
        this.xCord = inXCord;
        this.yCord = inYCord;

        this.planet = planet;
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

    public Planet getPlanet()
    {
        return planet;
    }

    @Override
    public String toString()
    {
        return planet.getName() + "\n" + "Height: " + getHeight() + "\t" + "Width: " + getWidth() + "\t" + "X: " + getXCord() + "\t" + "Y: " + getYCord() + "\n"
                + "A: " + planet.getAphelion() + "\t" + "P: " + planet.getPerihelion() + "\n";
    }
}
