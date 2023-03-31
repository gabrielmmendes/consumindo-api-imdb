import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetImdbApiContent implements GetContent {

    public List<Content> getContent(String json) {
        // faz o parse do json
        JSONArray movies = new JSONObject(json).getJSONArray("items");

        List<Content> contents = new ArrayList<>();

        for (int i = 0; i < movies.length(); i++) {
            String title = movies.getJSONObject(i).getString("title");
            String urlImage = movies.getJSONObject(i).getString("image");
            float imdbRating = movies.getJSONObject(i).getFloat("imDbRating");

            var content = new Content(title, urlImage, imdbRating);
            contents.add(content);
        }

        return contents;
    }

    public static String getImdbHdUrl(String imageUrl) {
        return imageUrl.replace(imageUrl.substring(imageUrl.indexOf(".", 30), imageUrl.lastIndexOf(".jpg")), "");
    }
}
