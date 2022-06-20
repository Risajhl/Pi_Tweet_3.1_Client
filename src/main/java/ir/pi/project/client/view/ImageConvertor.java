package ir.pi.project.client.view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class ImageConvertor {


    public String toString(BufferedImage bufferedImage, String format) {
        if(bufferedImage == null)
            return null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, format, byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }

    public BufferedImage toBufferedImage(String encoded) {
        if(encoded == null)
            return null;
        byte[] bytes = Base64.getDecoder().decode(encoded);
        InputStream inputStream = new ByteArrayInputStream(bytes);
        BufferedImage bufferedImage=null;
        try {
            bufferedImage =ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedImage;
    }
}
