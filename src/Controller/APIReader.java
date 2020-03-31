package Controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIReader
{
    public JSONObject readBodyFromAPI(String body)
    {
        HttpClient client = HttpClient.newHttpClient();
        JSONObject jObject = new JSONObject();
        try
        {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://api.le-systeme-solaire.net/rest/bodies/"))
                    .build();

            HttpResponse<String> reply = client.send(request, HttpResponse.BodyHandlers.ofString());

            String replyContent = reply.body();

            JSONParser parser = new JSONParser();

            jObject = (JSONObject) parser.parse(replyContent);

        }
        catch (Exception e)
        {
            System.out.println("ReadBodyFromAPI " + e.getMessage());
        }

        return jObject;
    }
}
