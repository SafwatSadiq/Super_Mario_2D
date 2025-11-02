package com.mario.game.levels;

import java.awt.*;

public class Tiles {
    private int x, y;
    private int width, height;
    private boolean solid;

    public Tiles(int x, int y, int width, int height, boolean solid){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.solid = solid;
    }

    public void draw(Graphics graphics){
        if(solid){
            graphics.setColor(Color.DARK_GRAY);
            graphics.fillRect(x, y, width, height);
        }
    }

    public boolean isSolid() {
        return solid;
    }
}
