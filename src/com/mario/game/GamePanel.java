package com.mario.game;

import com.mario.game.entities.Player;
import com.mario.game.inputs.KeyboardInput;
import com.mario.game.levels.Levels;
import com.mario.game.util.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    private Thread gameThread;
    private boolean running = false;
    private final int FPS = 60;
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    private Player player;
    private KeyboardInput keyInput;
    private Levels levels;
    private Camera camera;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        keyInput = new KeyboardInput();
        addKeyListener(keyInput);
        setFocusable(true);
        requestFocusInWindow();

        player = new Player(100 , 400);
        levels = new Levels();

        int worldWidth = levels.getWorldWidth();
        int worldHeight = HEIGHT;

        camera = new Camera(WIDTH, HEIGHT, worldWidth, worldHeight);

        startGame();
    }

    private void startGame(){
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){
        double drawInterval = 1000000000.0 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(running){
            update();
            repaint();

            try{
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1_000_000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(ImageLoader.load("src/resources/images/sky.png"), 0, 0, WIDTH, HEIGHT - 100, null);

        levels.renderLevel(g, camera);
        player.draw(g, camera);

    }

    private void update(){
        player.update(keyInput.leftPressed, keyInput.rightPressed, keyInput.jumpPressed, levels.getTiles());
        camera.update(player);
    }

}
