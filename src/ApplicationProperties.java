import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {
    public static Properties getProperties() throws IOException {
        var props = new Properties();
        props.load(new FileInputStream("C:\\Users\\Gabriel\\IdeaProjects\\consumindo-api-imdb\\src\\resources\\application.properties"));
        return props;
    }
}
