package com.mario.game.entities;

import com.mario.game.Camera;
import com.mario.game.levels.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import com.mario.game.util.*;

public class Player {
    private int X, Y;
    private int x, y;
    private int width, height;
    private int yVelocity;
    private int speed;
    private boolean jumping;
    private boolean onGround;

    private BufferedImage[] runRightSmallMarioFrames;
    private BufferedImage jumpRightSmallMario;
    private BufferedImage standRightSmallMario;

    private BufferedImage[] runLeftSmallMarioFrames;
    private BufferedImage jumpLeftSmallMario;
    private BufferedImage standLeftSmallMario;

    private BufferedImage marioState;

    private int frameIndex = 0;
    private long lastFrameTime;
    private long frameDelay = 30;

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

        loadImages();
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
        marioState = getFrame(left, right);
        updateFrame(left, right);
    }

    public void draw(Graphics g , Camera camera){
        g.drawImage(marioState, x - camera.getX(), y - camera.getY(), width, height, null);
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

    public void loadImages(){
        runRightSmallMarioFrames = new BufferedImage[22];
        runLeftSmallMarioFrames = new BufferedImage[22];
        for(int i = 0; i <= 21; i++){
            runRightSmallMarioFrames[i] = ImageLoader.load("src/resources/images/mario/smallmario/marioR/" + i + ".png");
        }
        jumpRightSmallMario = ImageLoader.load("src/resources/images/mario/smallmario/marioR/jump.png");
        standRightSmallMario = ImageLoader.load("src/resources/images/mario/smallmario/marioR/stand.png");

        for(int i = 0; i <= 21; i++){
            runLeftSmallMarioFrames[i] = ImageLoader.load("src/resources/images/mario/smallmario/marioL/" + i + ".png");
        }
        jumpLeftSmallMario = ImageLoader.load("src/resources/images/mario/smallmario/marioL/jump.png");
        standLeftSmallMario = ImageLoader.load("src/resources/images/mario/smallmario/marioL/stand.png");
    }

    public BufferedImage getFrame(boolean left, boolean right) {
        if (jumping && right) return jumpRightSmallMario;
        if (jumping && left) return jumpLeftSmallMario;
        if (right) return runRightSmallMarioFrames[frameIndex];
        if (left) return runLeftSmallMarioFrames[frameIndex];
        return standRightSmallMario;
    }

    public void updateFrame(boolean left, boolean right) {
        boolean moving = left || right;
        if (moving && !jumping) {
            long current = System.currentTimeMillis();
            if (current - lastFrameTime > frameDelay) {
                frameIndex = (frameIndex + 1) % runRightSmallMarioFrames.length;
                lastFrameTime = current;
            }
        } else {
            frameIndex = 0;
        }
    }
}
