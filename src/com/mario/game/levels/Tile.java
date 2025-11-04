package com.mario.game.levels;

import com.mario.game.Camera;
import com.mario.game.util.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    private int x, y;
    private int width, height;
    private boolean solid;
    private String textureName;
    private BufferedImage texture;

    public Tile(int x, int y, int width, int height, boolean solid, String textureName){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.solid = solid;
        this.textureName = textureName;
        getTexture();
    }

    public void draw(Graphics graphics, Camera camera){
        graphics.drawImage(texture, x - camera.getX(),
                y - camera.getY(),
                width, height , null);
    }

    public boolean isSolid() {
        return solid;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }

    public void getTexture(){
        texture = ImageLoader.load("src/resources/images/" + textureName + ".png");
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }
}
