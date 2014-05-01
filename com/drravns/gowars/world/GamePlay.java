package com.drravns.gowars.world;

import com.drravns.gowars.modes.IStrategy;
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GamePlay here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GamePlay extends World {
    public GamePlay() {
        super(1024, 768, 1, false);
        setBackground("images/BG_GamePlay.png");
    }


    private IStrategy strategy;

    public void setStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void being() {
        strategy.buildWorld(this);
    }
}
