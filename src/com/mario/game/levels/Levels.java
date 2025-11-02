package com.mario.game.levels;

import java.awt.*;
import java.util.ArrayList;

public class Levels {
    private ArrayList<Tiles> tiles;

    public Levels(){
        tiles = new ArrayList<Tiles>();
        for(int i = 0; i < 25; i++){
            tiles.add(new Tiles(i * 50, 600, 50, 50, true));
        }
    }

    public ArrayList<Tiles> getTiles(){
        return tiles;
    }

    public void renderLevel(Graphics g){
        for(int i = 0; i < 25; i++){
            tiles.get(i).draw(g);
        }
    }
}
