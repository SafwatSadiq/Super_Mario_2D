package com.mario.game.entities;

import com.mario.game.levels.Tile;

import java.awt.*;
import java.util.ArrayList;

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
        this.width = 75;
        this.height = 100;
        this.speed = 5;
        this.jumping = false;
        this.onGround = false;
    }

    public void update(boolean left, boolean right, boolean jump, ArrayList<Tile> tiles) {
        if (left) x -= speed;
        if (right) x += speed;

//        if (y >= 500) { // ground level
//            y = 500;
//            yVelocity = 0;
//            onGround = true;
//            jumping = false;
//        }

        if (jump && onGround) { // jump
            yVelocity = -20;
            onGround = false;
            jumping = true;
        }

        if (!onGround) {
            yVelocity += 1; // gravity
            y += yVelocity;
        }
        onGround = false;
        checkCollision(tiles);
    }

    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }

    public void checkCollision(ArrayList<Tile> tiles) {
        Rectangle playerBounds = new Rectangle(x, y, width, height);
        for (Tile tile : tiles) {
            if (!tile.isSolid()) continue;
            if (playerBounds.intersects(tile.getBounds())) {
                Rectangle tileBounds = tile.getBounds();

                if (y + height <= tileBounds.y + 10 && y + height >= tileBounds.y) {
                    yVelocity = 0;
                    onGround = true;
                    jumping = false;
                } else if (y >= tileBounds.y + tileBounds.height - 10 && y <= tileBounds.y + tileBounds.height) {
                    yVelocity = 0;
                } else if (x + width >= tileBounds.x && x < tileBounds.x && y == tileBounds.y) {
                    speed = 0;
                } else if (x <= tileBounds.x + tileBounds.width && x > tileBounds.x && y == tileBounds.y) {
                    speed = 0;
                }
            }
        }
    }
}
