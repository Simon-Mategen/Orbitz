package Model;


import org.json.simple.JSONObject;

public class Planet
{
    private JSONObject planetInfo;



    public Planet(JSONObject object)
    {
        this.planetInfo = object;
    }

    public int getSemiMajorAxis()
    {
        int i = Integer.parseInt(planetInfo.get("semimajorAxis").toString());

        return i;
    }

    public int getPerihelion()
    {
        int i = Integer.parseInt(planetInfo.get("perihelion").toString());

        return i;
    }

    public int getAphelion()
    {
        int i = Integer.parseInt(planetInfo.get("aphelion").toString());

        return i;
    }

}
