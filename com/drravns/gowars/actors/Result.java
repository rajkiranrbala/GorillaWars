package com.drravns.gowars.actors;

import com.drravns.gowars.world.BaseWorld;
import greenfoot.Actor;
import greenfoot.Greenfoot;

public class Result extends Actor {
    public Result(int value) {
        switch (value) {
            case 1:
                setImage("images/result_player1.jpg");
                break;
            case 2:
                setImage("images/result_player2.jpg");
                break;
            default:
                setImage("images/result_draw.jpg");
                break;
        }

    }

    @Override
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new BaseWorld());
        }
    }
}
