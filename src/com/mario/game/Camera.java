package com.mario.game;

import com.mario.game.entities.Player;

public class Camera {
    private int xOffset, yOffset;
    private int screenWidth, screenHeight;
    private int worldWidth, worldHeight;

    public Camera(int screenWidth, int screenHeight, int worldWidth, int worldHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
    }

    public void update(Player player) {
        // Keeping the player Center
        xOffset = player.getX() - screenWidth / 4;
        yOffset = player.getY() - screenHeight / 4;

        // Edge Case
        if (xOffset < 0) xOffset = 0;
        if (xOffset > worldWidth - screenWidth) xOffset = worldWidth - screenWidth;
        if (yOffset < 0) yOffset = 0;
        if (yOffset > worldHeight - screenHeight) yOffset = worldHeight - screenHeight;
    }

    public int getX() { return xOffset; }
    public int getY() { return yOffset; }
}
