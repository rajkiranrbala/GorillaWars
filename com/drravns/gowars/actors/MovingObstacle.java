package com.drravns.gowars.actors;

import greenfoot.World;

import java.util.Random;

/**
 * Created by Kitty on 4/29/2014.
 */
public class MovingObstacle extends Obstacle {

    private static final int MAX_Y = 1048;
    private static final int MIN_Y = 700;
    private static Random r = new Random();
    private int steps = 5;
    private boolean moveTop;

    public MovingObstacle() {
        moveTop = r.nextBoolean();
        steps = 3 + r.nextInt(5);
    }

    @Override
    protected void addedToWorld(World world) {
        super.addedToWorld(world);
    }

    @Override
    public void act() {
        int y = getY();
        if (moveTop) {
            y = y - steps;
            if (y < MIN_Y) {
                moveTop = false;
            }
        } else {
            y = y + steps;
            if (y > MAX_Y) {
                moveTop = true;
            }
        }
        setLocation(getX(), y);
    }
}

