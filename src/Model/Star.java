package Model;

import org.json.simple.JSONObject;

public class Star
{
    JSONObject jsonInformationObject = new JSONObject();

    public Star(JSONObject object)
    {
        this.jsonInformationObject = object;
    }
}
