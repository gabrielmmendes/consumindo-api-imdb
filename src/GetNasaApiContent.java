import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class GetNasaApiContent implements GetContent {
    @Override
    public List<Content> getContent(String json) {
        // faz o parse do json
        var items = new JSONArray(json);

        List<Content> contents = new ArrayList<>();

        for (int i = 0; i < items.length(); i++) {
            String title = items.getJSONObject(i).getString("title");
            String urlImage = items.getJSONObject(i).getString("url");
            String date = items.getJSONObject(i).getString("date");

            var content = new Content(title, urlImage, date);
            contents.add(content);
        }

        return contents;
    }
}
