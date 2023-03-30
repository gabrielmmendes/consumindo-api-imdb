import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class PosterWithMedal {

    public void putMedalOnPoster(InputStream inputStream, float nota, String nomeFilme) throws IOException {
        BufferedImage original = ImageIO.read(inputStream);

        BufferedImage goldMedal = ImageIO.read(new File("src/resources/images/input/gold-medal.png"));
        BufferedImage silverMedal = ImageIO.read(new File("src/resources/images/input/silver-medal.png"));
        BufferedImage bronzeMedal = ImageIO.read(new File("src/resources/images/input/bronze-medal.png"));

        BufferedImage medal;

        if (nota > 8.5) {
            medal = goldMedal;
        } else if (nota > 7.5) {
            medal = silverMedal;
        } else {
            medal = bronzeMedal;
        }

        var heightWidthRatio = medal.getHeight() / medal.getWidth();
        var medalNewWidth = original.getWidth() / 3;
        var medalNewHeight = medalNewWidth * heightWidthRatio;

        Graphics2D graphics = (Graphics2D) original.getGraphics();
        graphics.drawImage(medal,
                0,
                0,
                medalNewWidth,
                medalNewHeight,
                null);

        ImageIO.write(original, "png", new File("src/resources/images/output/" + nomeFilme.replace(" ", "-").toLowerCase() + ".png"));
    }
}
