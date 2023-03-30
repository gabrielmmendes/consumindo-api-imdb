import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // TODO: colocar chave em arquivo .properties
        String url = "https://imdb-api.com/en/API/MostPopularMovies/k_o36shslf";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String json = response.body();

        // fazer o parse do json
        var items = new JSONObject(json);
        JSONArray movies = items.getJSONArray("items");

        for (int i = 0; i < movies.length(); i++) {
            // titulo
            String title = movies.getJSONObject(i).getString("title");

            // nota
            float imDbRating;
            try {
                imDbRating = movies.getJSONObject(i).getFloat("imDbRating");
            } catch (JSONException e) {
                imDbRating = 0;
            }

            //poster
            String imageUrl = movies.getJSONObject(i).getString("image");
            imageUrl = imageUrl.replace(imageUrl.substring(imageUrl.indexOf(".", 30), imageUrl.lastIndexOf(".jpg")), "");

            var posterWithMedal = new PosterWithMedal();
            posterWithMedal.putMedalOnPoster(new URL(imageUrl).openStream(), imDbRating, title);
        }
    }
}