package Model;

import javafx.scene.paint.Paint;
import org.json.simple.JSONObject;

import java.util.LinkedList;

public class Planet extends Celestialbody
{
    private long semimajorAxis;
    private long perihelion;
    private long aphelion;
    private JSONObject planetInfo;

    LinkedList<Orbit> moonOrbits;

    public Planet(double x, double y, double radius, Paint fill) {
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
