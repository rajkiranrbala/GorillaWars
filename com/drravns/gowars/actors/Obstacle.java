package com.drravns.gowars.actors;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.Random;

public class Obstacle extends Actor {
    private static final Random r = new Random();

    public Obstacle() {
        setImage(new GreenfootImage(100, 768));
        GreenfootImage m = new GreenfootImage("images/obstacle_" + (1 + r.nextInt(100) % 2) + ".png");
        getImage().drawImage(m, 1, 1);
    }
}
