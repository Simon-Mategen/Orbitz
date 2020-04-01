package Model;

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

    public int getxCord()
    {
        return xCord;
    }

    public void setxCord(int xCord)
    {
        this.xCord = xCord;
    }

    public int getyCord()
    {
        return yCord;
    }

    public void setyCord(int yCord)
    {
        this.yCord = yCord;
    }
}
