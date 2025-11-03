package com.mario.game.entities;

import com.mario.game.levels.Tile;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    private int X, Y;
    private int x, y;
    private int width, height;
    private int yVelocity;
    private int speed;
    private boolean jumping;
    private boolean onGround;

    public Player(int startX, int startY){
        this.X = startX;
        this.Y = startY;
        this.x = startX;
        this.y = startY;
        this.width = 75;
        this.height = 100;
        this.speed = 5;
        this.jumping = false;
        this.onGround = false;
    }

    public void update(boolean left, boolean right, boolean jump, ArrayList<Tile> tiles) {
        if(y > 1000){
            x = X;
            y = Y;
        }

        if (jump && onGround) { // jump
            yVelocity = -20;
            onGround = false;
            jumping = true;
        }

        if (!onGround) {
            yVelocity += 1; // gravity
            y += yVelocity;
        }
        checkCollision(left, right , tiles);
    }

    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }

    public void checkCollision(boolean leftPressed, boolean rightPressed, ArrayList<Tile> tiles) {
        Rectangle playerBounds = new Rectangle(x, y, width, height);

        onGround = false;

        // Vertical Collision
        for (Tile tile : tiles) {
            if (!tile.isSolid()) continue;

            Rectangle tileBounds = tile.getBounds();

            if (playerBounds.intersects(tileBounds)) {

                // Moving Down
                if (yVelocity > 0 && y + height > tileBounds.y && y < tileBounds.y) {
                    y = tileBounds.y - height; // put player on top
                    yVelocity = 0;
                    onGround = true;
                    jumping = false;
                }

                // Moving Up
                else if (yVelocity < 0 && y < tileBounds.y + tileBounds.height && y + height > tileBounds.y + tileBounds.height) {
                    y = tileBounds.y + tileBounds.height;
                    yVelocity = 0;
                }
            }
        }

        // Horizontal Collision
        Rectangle newBounds;

        if (leftPressed) {
            x -= speed;
            newBounds = new Rectangle(x, y, width, height);
            for (Tile tile : tiles) {
                if (!tile.isSolid()) continue;
                if (newBounds.intersects(tile.getBounds())) {
                    x = tile.getBounds().x + tile.getBounds().width;
                    break;
                }
            }
        }

        if (rightPressed) {
            x += speed;
            newBounds = new Rectangle(x, y, width, height);
            for (Tile tile : tiles) {
                if (!tile.isSolid()) continue;
                if (newBounds.intersects(tile.getBounds())) {
                    x = tile.getBounds().x - width;
                    break;
                }
            }
        }
    }

    public int getX() {return x;}
    public int getY() {return y;}

    public int getWidth(){return width;}
    public int getHeight(){return height;}

}
