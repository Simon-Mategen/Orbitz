package Model;

import javafx.scene.shape.Sphere;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;

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

    public String getMass()
    {
        return sunInfo.get("mass").toString();
    }

    public Sphere getSphereFromSun()
    {
        return new Sphere();
    }

}
