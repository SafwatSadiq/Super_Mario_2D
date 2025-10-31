package com.mario.game.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {

    public boolean leftPressed = false;
    public boolean rightPressed = false;
    public boolean jumpPressed = false;

    @Override
    public void keyTyped(KeyEvent e){ }

    @Override
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) leftPressed = true;
        if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) rightPressed = true;
        if(code == KeyEvent.VK_SPACE || code == KeyEvent.VK_W) jumpPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) leftPressed = false;
        if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) rightPressed = false;
        if(code == KeyEvent.VK_SPACE || code == KeyEvent.VK_W) jumpPressed = false;
    }

}


