package com.mario.game.entities;

import java.awt.*;

public class Player {
    private int x, y;
    private int width, height;
    private int yVelocity;
    private int speed;
    private boolean jumping;
    private boolean onGround;

    public Player(int startX, int startY){
        this.x = startX;
        this.y = startY;
        this.width = 20;
        this.height = 100;
        this.speed = 5;
        this.jumping = false;
        this.onGround = false;
    }

    public void update(boolean left, boolean right, boolean jump) {
        if (left) x -= speed;
        if (right) x += speed;

        if (y >= 500) { // ground level
            y = 500;
            yVelocity = 0;
            onGround = true;
            jumping = false;
        }

        if (jump && onGround) {
            yVelocity = -15;
            onGround = false;
            jumping = true;
        }

        if (!onGround) {
            yVelocity += 1; // gravity
            y += yVelocity;
        }
    }

    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }

}
