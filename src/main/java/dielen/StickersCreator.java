package dielen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class StickersCreator {
    /*Recebe a lista já pronta. 
    * Lê a url de cada imagem
    * Cria nova imagem(de diferentes dimensões)
    * sobrescreve imagens
    * salva imagem no dir*/
    public void creator(InputStream inputStream, String fileName, String destFolder) throws IOException {
        
        /*Ler a imagem: 
        * Recebe um endereço de arquivo local:
        * - arquivo local: InputStream inputStream = new FileInputStream(new File("data/images/input/ShawshankRedemption.jpg_"));
        * OU
        * - pega direto da internet: InputStream inputStream = new URL
        * ("https://m.media-amazon.com/images/M/MV5BNDE3ODcxYzMtY2YzZC00NmNlLWJiNDMtZDViZWM2MzIxZDYwXkEyXkFqcGdeQXVyNjAwNDUxODI@.jpg").openStream();
        * */
       
        
       /*Cria nova imagem a partir das dimensões da original*/        
        
        BufferedImage imageOriginal = ImageIO.read(inputStream);
        int newImageWidth = imageOriginal.getWidth();
        int newImageHeight =imageOriginal.getHeight() + 400; // a nova precisa ser maior porque iremos sobrescrever a 
        //original nela
        
        BufferedImage newImage = new BufferedImage(newImageWidth, newImageHeight, BufferedImage.TRANSLUCENT);
        //copia a imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(imageOriginal,0,0,null);
        
        //Se nasa: output/pictures 
        //Se imDb: output/movies
        File file = new File("data/images/output/" + destFolder + "/" + fileName); // 
        file.getParentFile().mkdir();
        
        //Configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 90);
        graphics.setFont(fonte);
        graphics.setColor(Color.yellow);
        graphics.setBackground(Color.BLUE);
        //Configura a mensagem
        graphics.drawString("Oi!!", 200, newImageHeight-200);
        
        
        //Finalmente, temos o produto final das imagens:
        ImageIO.write(newImage, "png", file);
    }
}
