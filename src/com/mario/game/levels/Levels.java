package com.mario.game.levels;

import java.awt.*;
import java.util.ArrayList;

public class Levels {
    private ArrayList<Tile> tiles;

    public Levels(){
        tiles = new ArrayList<Tile>();

        // Render Ground
        for(int i = 0; i < 25; i++){
            tiles.add(new Tile(i * 50, 600, 50, 50, true));
        }

        // Render Floating Blocks
        tiles.add(new Tile(100, 440, 50, 50, true));
        tiles.add(new Tile(150, 440, 50, 50, true));

        // Render A Wall
        tiles.add(new Tile(300, 450, 50, 50, true));
        tiles.add(new Tile(300, 500, 50, 50, true));
        tiles.add(new Tile(300, 550, 50, 50, true));
    }

    public ArrayList<Tile> getTiles(){
        return tiles;
    }

    public void renderLevel(Graphics g){
        for(Tile tile: tiles){
            tile.draw(g);
        }
    }
}
