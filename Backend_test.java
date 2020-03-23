import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//Hej

public class Backend_test
{
    public void readAndPrintPlanetsFromAPI()
    {
        HttpClient client = HttpClient.newHttpClient();

        try
        {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://api.le-systeme-solaire.net/rest/bodies/jupiter"))
                    .build();

            HttpResponse<String> reply = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(reply.statusCode());

            String body = reply.body();

            String[] bodyContent = body.split(",");

            for (int i = 0; i < bodyContent.length; i++)
            {
                System.out.println( i + "   " + bodyContent[i]);
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getStackTrace());
        }
    }

    public void readAndPrintPlanetsFromAPIIntoJSON()
    {
        HttpClient client = HttpClient.newHttpClient();

        try
        {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://api.le-systeme-solaire.net/rest/bodies/"))
                    .build();

            HttpResponse<String> reply = client.send(request, HttpResponse.BodyHandlers.ofString());

            String replyCOntent = reply.body();

            JSONParser parser = new JSONParser();

            JSONObject jObject = (JSONObject) parser.parse(replyCOntent);

            System.out.println(jObject.get("englishName"));
            System.out.println(jObject.get("escape"));
            System.out.println(jObject.get("perihelion"));
            System.out.println(jObject.get("aphelion"));

        }
        catch (Exception e)
        {
            System.out.println(e.getStackTrace());
        }
    }

    public static void main(String[] args)
    {
        Backend_test prog = new Backend_test();
        prog.readAndPrintPlanetsFromAPI();
        prog.readAndPrintPlanetsFromAPIIntoJSON();
    }
}
