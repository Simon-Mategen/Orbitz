package Model;

import javafx.scene.paint.Paint;
import org.json.simple.JSONObject;

import java.util.LinkedList;

public class Planet extends Celestialbody
{
    JSONObject jsonInformationObject = new JSONObject();

    LinkedList<Orbit> moonOrbits;

    public Planet(double x, double y, double radius, Paint fill)
    {
        super(x, y, radius, fill);
        //this.jsonInformationObject = object;
    }


}
