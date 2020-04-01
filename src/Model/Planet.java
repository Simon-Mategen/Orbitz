package Model;


import org.json.simple.JSONObject;

public class Planet
{
    private long semimajorAxis;
    private long perihelion;
    private long aphelion;
    private JSONObject planetInfo;

    public Planet(JSONObject object)
    {
        this.planetInfo = object;
    }

    public long getSemiMajorAxis()
    {
        semimajorAxis= (long)planetInfo.get("semimajorAxis");
        return semimajorAxis;
    }

    public long getPerihelion()
    {
        perihelion = (long)planetInfo.get("perihelion");
        return perihelion;
    }

    public long getAphelion()
    {
        aphelion = (long)planetInfo.get("aphelion");
        return aphelion;
    }

    public String getName()
    {
        return planetInfo.get("englishName").toString();
    }

}
