package com.mario.game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    private Thread gameThread;
    private boolean running = false;
    private final int FPS = 60;
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

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

        g.setColor(Color.CYAN);
        g.fillRect(0, 0, WIDTH, HEIGHT); // background

        g.setColor(Color.RED);
        g.fillRect(100, 500, 50 , 50);
    }

    private void update(){

    }

}
