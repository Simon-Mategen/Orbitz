package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import org.json.simple.JSONObject;

/**
 @author Simon Måtegen
 @author Marcus Svensson
 * An object that contains information about the sun
 */
public class Sun
{
    private JSONObject sunInfo;

    private int xCord;
    private int yCord;
    private int radius = 2000;

    private Sphere sunSphere;

    /**
     @author Simon Måtegen
     @author Marcus Svensson
     * Constructs the sun
     @param object a JSONObject that contains the information from the api
     */
    public Sun(JSONObject object)
    {
        this.sunInfo = object;
    }

    /**
     @author Simon Måtegen
     @author Marcus Svensson
     @return returns an integer of the x position
     */
    public int getXCord()
    {
        return xCord;
    }

    /**
     @author Simon Måtegen
     @author Marcus Svensson
     * Sets the value of the x cord
     @param xCord an int of the X cord to be set
     */
    public void setXCord(int xCord)
    {
        this.xCord = xCord;
    }
    /**
     @author Simon Måtegen
     @author Marcus Svensson
     @return an integer of the Y cord
     */
    public int getYCord()
    {
        return yCord;
    }

    /**
     @author Simon Måtegen
     @author Marcus Svensson
     * Sets the Y Cord
     @param 
     */
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
        sunSphere = new Sphere(radius);
        PhongMaterial sunMap = new PhongMaterial();
        sunMap.setDiffuseMap(new Image("Images/Sun.jpg"));
        sunSphere.setMaterial(sunMap);

        return sunSphere;
    }

    @Override
    public String toString()
    {
        return sunInfo.get("mass").toString();
    }
}
