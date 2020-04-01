package Model;

import org.json.simple.JSONObject;

public class Planet
{
    JSONObject jsonInformationObject = new JSONObject();

    public Planet(JSONObject object)
    {
        this.jsonInformationObject = object;
    }
}
