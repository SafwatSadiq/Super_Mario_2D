package com.mario.game.levels;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Levels {
    private ArrayList<Tile> tiles;
    private Data data;

    public Levels(){
        tiles = new ArrayList<Tile>();

        if(loadLevelData()){
            for(Block block: data.blocks){
                tiles.add(new Tile(block.x, block.y, block.width, block.height, block.solid));
            }
        }

    }

    public ArrayList<Tile> getTiles(){
        return tiles;
    }

    public void renderLevel(Graphics g){
        for(Tile tile: tiles){
            tile.draw(g);
        }
    }

    public boolean loadLevelData(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(FileReader reader = new FileReader("src/resources/levels/level1.json")){
            data = gson.fromJson(reader, Data.class);
            return true;
        }
        catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
}

class Data {
    int level;
    Block[] blocks;
}

class Block{
    int x, y, width, height;
    boolean solid;
}
