package Model;

import org.json.simple.JSONObject;

public class Moon
{
    JSONObject jsonInformationObject = new JSONObject();

    public Moon(JSONObject object)
    {
        this.jsonInformationObject = object;
    }
}
