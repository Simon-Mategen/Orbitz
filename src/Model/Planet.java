package Model;


import org.json.simple.JSONObject;

public class Planet
{
    private JSONObject planetInfo;

    public Planet(JSONObject object)
    {
        this.planetInfo = object;
    }

    public long getSemiMajorAxis()
    {
        long i = (long)planetInfo.get("semimajorAxis");
        return i;
    }

    public long getPerihelion()
    {
        long i = (long)planetInfo.get("perihelion");

        return i;
    }

    public long getAphelion()
    {
        long i = (long)planetInfo.get("aphelion");

        return i;
    }

    public String getName()
    {
        return planetInfo.get("englishName").toString();
    }

}
