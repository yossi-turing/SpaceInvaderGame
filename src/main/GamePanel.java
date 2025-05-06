package main;

import Pleyer.Pleyer;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalSize = 16;
    final int scale = 3;
    public final int tilesSize = originalSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRaw = 12;
    final int screenWidth = tilesSize * maxScreenCol;
    final int screenHeight = tilesSize * maxScreenRaw;
    public int tileSize = 100;

    int fps = 60;

    KeyHandler key = new KeyHandler();
    Thread gameThread;
    Pleyer pleyer = new Pleyer(this, key);


    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.blue);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {

                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("fps" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        pleyer.update();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics g2 = (Graphics2D) g;
        pleyer.draw((Graphics2D) g);
       // g2.dispose();

    }
}
