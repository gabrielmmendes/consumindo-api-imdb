public class Content {
    private final String title;
    private final String urlImage;
    private float imdbRating;
    private String date;

    public Content(String title, String urlImage, float imdbRating) {
        this.title = title;
        this.urlImage = urlImage;
        this.imdbRating = imdbRating;
    }

    public Content(String title, String urlImage, String date) {
        this.title = title;
        this.urlImage = urlImage;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public float getImdbRating() {
        return imdbRating;
    }

    public String getDate() {
        return date;
    }
}
