import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class PersonalizeImages {

    public void putMedalOnPoster(InputStream inputStream, float imdbRating, String movieName) {
        BufferedImage original;
        BufferedImage goldMedal;
        BufferedImage silverMedal;
        BufferedImage bronzeMedal;

        try {
            original = ImageIO.read(inputStream);
            goldMedal = ImageIO.read(new File("src/resources/images/input/gold-medal.png"));
            silverMedal = ImageIO.read(new File("src/resources/images/input/silver-medal.png"));
            bronzeMedal = ImageIO.read(new File("src/resources/images/input/bronze-medal.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedImage medal;

        if (imdbRating > 9) {
            medal = goldMedal;
        } else if (imdbRating > 8.5) {
            medal = silverMedal;
        } else {
            medal = bronzeMedal;
        }

        int heightWidthRatio;
        if (medal.getWidth() > medal.getHeight()) {
            heightWidthRatio = medal.getWidth() / medal.getHeight();
        } else {
            heightWidthRatio = medal.getHeight() / medal.getWidth();
        }
        var medalNewWidth = original.getWidth() / 3;
        var medalNewHeight = medalNewWidth * heightWidthRatio;

        Graphics2D graphics = (Graphics2D) original.getGraphics();
        graphics.drawImage(medal,
                0,
                0,
                medalNewWidth,
                medalNewHeight,
                null);

        try {
            ImageIO.write(original, "png", new File("src/resources/images/output/imdb/" + movieName.replace(" ", "-").toLowerCase() + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void putNameAndDateOnImage(InputStream inputStream, String date, String name) {
        BufferedImage original;
        try {
            original = ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int width = original.getWidth();
        int height = original.getHeight();
        int newHeight = height + (height / 10);
        var newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);
        var font = new Font(Font.SANS_SERIF, Font.BOLD, 32);
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();

        graphics.drawImage(original, 0, 0, null);
        graphics.setColor(Color.CYAN);
        graphics.setFont(font);
        graphics.drawString(name, 5, height + font.getSize());
        graphics.drawString(date, width - (date.length() * font.getSize() / 2) - 5, newHeight - 5);

        try {
            ImageIO.write(newImage, "png", new File("src/resources/images/output/nasa/" + name + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
