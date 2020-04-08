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

    public double getMass()
    {
        JSONObject sunMass = (JSONObject)sunInfo.get("mass");

        double massValue = ((double) sunMass.get("massValue"))-0.5;
        long massExponent = 2* (((long) sunMass.get("massExponent"))-1);

        return massValue * (Math.pow(10, massExponent));
    }

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
