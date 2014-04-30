package com.drravns.gowars.actors;

import greenfoot.World;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Kitty on 4/29/2014.
 */
public class MovingObstacle extends Obstacle {

    private static final int MAX_Y = 1000;
    private static final int MIN_Y = 400;
    private static Random r = new Random();
    private static int steps = 5;
    private AtomicBoolean moveTop = new AtomicBoolean();

    public MovingObstacle() {
        moveTop.set(r.nextBoolean());
        steps = 3 + r.nextInt(5);
    }

    @Override
    protected void addedToWorld(World world) {
        super.addedToWorld(world);
    }

    @Override
    public void act() {
        int y = getY();
        if (moveTop.get()) {
            y = y - steps;
            if (y < MIN_Y) {
                moveTop.set(!moveTop.get());
                return;
            }
        } else {
            y = y + steps;
            if (y > MIN_Y) {
                moveTop.set(!moveTop.get());
                return;
            }
        }
        setLocation(getX(), y);
    }
}

