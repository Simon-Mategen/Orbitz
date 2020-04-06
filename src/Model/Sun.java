package Model;

import javafx.scene.shape.Sphere;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;

import java.io.ObjectInputStream;

public class Sun
{
    private JSONObject sunInfo;

    private int xCord;
    private int yCord;

    public Sun(JSONObject object)
    {
        this.sunInfo = object;
    }

    public int getXCord()
    {
        return xCord;
    }

    public void setXCord(int xCord)
    {
        this.xCord = xCord;
    }

    public int getYCord()
    {
        return yCord;
    }

    public void setYCord(int yCord)
    {
        this.yCord = yCord;
    }

/*    public long getMass()
    {
        JSONObject sunMass = (JSONObject)sunInfo.get("mass");

        JSONObject massValueObject = (JSONObject) sunMass.get("volValue");
        JSONObject massExponentObject = (JSONObject) sunMass.get("volExponent");

        long massValue = (long)massValueObject;
    }*/

    public Sphere getSphereFromSun()
    {
        return new Sphere();
    }

    @Override
    public String toString()
    {
        return sunInfo.get("mass").toString();
    }
}
