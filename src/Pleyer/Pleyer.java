package Pleyer;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Pleyer extends EntPleyer{
    GamePanel gp;
    KeyHandler key;
    public Pleyer(GamePanel gp, KeyHandler key){
        this.gp = gp;
        this.key =key;
        setDefaultValues();
getPlayerImage();

    }
    BufferedImage playerImage;
    public void getPlayerImage() {
        try {
            playerImage = ImageIO.read(new File("roket/spacecraft.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setDefaultValues(){
        x=100;
        y=100;
        speed = 4;

    }
    public void update() {
        if (key.upPressed == true) {
            y -= speed;
        } else if (key.downPressed == true) {
            y += speed;
        } else if (key.leftPressed == true) {
            x-= speed;
        } else if (key.rightPressed == true) {
            x += speed;
        }
    }
    public void draw(Graphics2D g2 ){
        System.out.println(playerImage);
        g2.drawImage(playerImage, x, y, gp.tileSize, gp.tileSize, null);
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tilesSize, gp.tilesSize);
    }

}
