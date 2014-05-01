package com.drravns.gowars.modes.timed;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.awt.*;

public class Ticker extends Actor {

    private int seconds;

    public Ticker(int seconds) {
        this.seconds = seconds;
        setImage("images/Ticker.png");
        getImage().setFont(new Font("Helvetica", Font.BOLD, 20));
        drawImage();
    }

    private void drawImage() {
        GreenfootImage image = getImage();
        image.clear();
        image.drawImage(new GreenfootImage("images/Ticker.png"), 0, 0);
        image.drawString(Integer.toString(seconds), 52, 75);

    }

    private boolean update = false;

    public void setTime(int seconds) {
        this.seconds = seconds;
        update = true;
    }

    @Override
    public void act() {
        if (update) {
            update = false;
            drawImage();
        }
    }

}
