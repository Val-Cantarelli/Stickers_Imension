package dielen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class StickersCreator {
    /* Receives the already prepared list.
     * Reads the URL of each image
     * Creates a new image (with different dimensions)
     * Overwrites images
     * Saves image to the directory */
    public void creator(InputStream inputStream, String fileName, String destFolder) throws IOException {

        /* Read the image:
         * Receives a local file address:
         * - local file: InputStream inputStream = new FileInputStream(new File("data/images/input/ShawshankRedemption.jpg_"));
         * OR
         * - gets directly from the internet: InputStream inputStream = new URL
         * ("https://m.media-amazon.com/images/M/MV5BNDE3ODcxYzMtY2YzZC00NmNlLWJiNDMtZDViZWM2MzIxZDYwXkEyXkFqcGdeQXVyNjAwNDUxODI@.jpg").openStream();
         */

        /* Creates a new image based on the original dimensions */
        BufferedImage imageOriginal = ImageIO.read(inputStream);
        int newImageWidth = imageOriginal.getWidth();
        int newImageHeight = imageOriginal.getHeight() + 400; // The new image needs to be larger because we will overwrite the
        // original in it

        BufferedImage newImage = new BufferedImage(newImageWidth, newImageHeight, BufferedImage.TRANSLUCENT);
        // Copies the original image to the new image (in memory)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(imageOriginal, 0, 0, null);

        // If NASA: output/pictures
        // If IMDb: output/movies
        File file = new File("data/images/output/" + destFolder + "/" + fileName);
        file.getParentFile().mkdir();

        // Set the font
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 90);
        graphics.setFont(fonte);
        graphics.setColor(Color.yellow);
        graphics.setBackground(Color.BLUE);
        // Set the message
        graphics.drawString("Hi!!", 200, newImageHeight - 200);

        // Finally, we have the final product of the images:
        ImageIO.write(newImage, "png", file);
    }
}
