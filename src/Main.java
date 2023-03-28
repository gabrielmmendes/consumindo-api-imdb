import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
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
            System.out.println(movies.getJSONObject(i).getString("title"));

            // troca rating em notas de 0 a 10 por estrelas de 1 a 5
            float stars;
            try {
                stars = movies.getJSONObject(i).getFloat("imDbRating") / 2;
            } catch (JSONException e) {
                stars = -1;
            }
            if (stars == -1) {
                System.out.print("Sem nota");
            } else {
                for (int j = 1; j < stars; j++) {
                    System.out.print("★");
                }
                for (int j = 0; j < 5 - stars; j++) {
                    System.out.print("☆");
                }
            }

            //poster
            System.out.println();
            System.out.println(movies.getJSONObject(i).getString("image"));
            System.out.println();
        }
        
        // TODO: colocar chave em arquivo .properties
    }
}