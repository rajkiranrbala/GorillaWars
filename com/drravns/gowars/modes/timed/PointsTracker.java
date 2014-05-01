package com.drravns.gowars.modes.timed;

import com.drravns.gowars.weapons.Weapon;
import greenfoot.Actor;

import com.drravns.gowars.observers.IPlayerObserver;
import greenfoot.GreenfootImage;

import java.awt.*;

// (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LifeTracker here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PointsTracker extends Actor implements IPlayerObserver {
    private int points = 0;
    private boolean changed = false;

    public PointsTracker() {
        setImage(new GreenfootImage("images/PointsTracker.png"));
        getImage().setFont(new Font("Helvetica", Font.BOLD, 20));
        getImage().setColor(Color.BLUE);
        drawImage();
    }

    private void drawImage() {
        GreenfootImage image = getImage();
        image.clear();
        image.drawImage(new GreenfootImage("images/PointsTracker.png"), 0, 0);
        String s = Integer.toString(points);
        image.drawString(s, 70, 45);
    }

    public void act() {
        if (changed) {
            changed = false;
            drawImage();
        }
    }

    @Override
    public void onVelocityChanged(int id, int velocity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAngleChanged(int id, int angle) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onWeaponChanged(int id, String weapon) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDamageReceived(int id, double value) {
        points++;
        changed = true;
    }

    @Override
    public void onWeaponFired(int id, Weapon weapon) {
        // TODO Auto-generated method stub

    }
}
