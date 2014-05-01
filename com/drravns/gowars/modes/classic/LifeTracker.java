package com.drravns.gowars.modes.classic;

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
public class LifeTracker extends Actor implements IPlayerObserver {
    private double life = 1.0;
    private boolean changed = false;

    public LifeTracker() {
        setImage("images/LifeTracker.png");
        drawImage();
    }

    private void drawImage() {
        GreenfootImage image = getImage();
        int greenBarWidth = (int) (188 * life);
        image.setColor(Color.GREEN);
        image.fillRect(5, 65, greenBarWidth, 5);
        image.setColor(Color.RED);
        image.fillRect(5 + greenBarWidth, 65, 188 - greenBarWidth, 5);
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
        life -= value;
        if (life < 0) {
            life = 0;
        }
        changed = true;

    }

    @Override
    public void onWeaponFired(int id, Weapon weapon) {
        // TODO Auto-generated method stub

    }

}
