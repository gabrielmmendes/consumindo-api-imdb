import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        var imdbKey = ApplicationProperties.getProperties().getProperty("IMDB.API.KEY");
        String imdbUrl = "https://imdb-api.com/en/API/Top250Movies/" + imdbKey;

        var nasaKey = ApplicationProperties.getProperties().getProperty("NASA.API.KEY");
        String nasaUrl = "https://api.nasa.gov/planetary/apod?api_key=" + nasaKey + "&start_date=2023-03-10&end_date=2023-03-12";

        String imdbJson = HttpClient.request(imdbUrl);
        String nasaJson = HttpClient.request(nasaUrl);

        var movies = new GetImdbApiContent().getContent(imdbJson).subList(0, 3); // <-- limita a 5 filmes
        var nasaImages = new GetNasaApiContent().getContent(nasaJson);

        for (Content movie : movies) {
            String title = movie.getTitle();
            String imageUrl = GetImdbApiContent.getImdbHdUrl(movie.getUrlImage());
            float imDbRating;

            try {
                imDbRating = movie.getImdbRating();
            } catch (JSONException e) {
                imDbRating = 0;
            }

            new PersonalizeImages().putMedalOnPoster(new URL(imageUrl).openStream(), imDbRating, title);
        }

        for (Content nasaImage : nasaImages) {
            String title = nasaImage.getTitle();
            String imageUrl = nasaImage.getUrlImage();
            String date = nasaImage.getDate();

            new PersonalizeImages().putNameAndDateOnImage(new URL(imageUrl).openStream(), date, title);
        }
    }
}