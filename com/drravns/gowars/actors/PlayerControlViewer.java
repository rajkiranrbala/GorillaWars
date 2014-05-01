package com.drravns.gowars.actors;

import com.drravns.gowars.observers.IPlayerObserver;
import com.drravns.gowars.weapons.Weapon;
import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.awt.*;

public class PlayerControlViewer extends Actor implements IPlayerObserver {

    private int velocity = 0;
    private int angle = 0;
    private String weapon = "Banana";
    private boolean changed = false;

    public PlayerControlViewer() {
        setImage("images/PlayerController.png");
        getImage().setFont(new Font("Helvetica", Font.BOLD, 20));
        drawImage();
    }


    private void drawImage() {

        GreenfootImage image = getImage();
        image.clear();
        image.drawImage(new GreenfootImage("images/PlayerController.png"), 0, 0);
        image.drawString("Velocity: " + velocity, 5 + 5, 20);
        image.drawString("Angle: " + angle, 5 + 27, 50);
        image.drawString("Weapon: " + weapon, 5 + 5, 80);
    }

    @Override
    public void act() {
        super.act();
        if (changed) {
            changed = false;
            drawImage();
        }
    }

    @Override
    public void onVelocityChanged(int id, int velocity) {
        this.velocity = velocity;
        changed = true;

    }

    @Override
    public void onAngleChanged(int id, int angle) {
        this.angle = angle;
        changed = true;

    }

    @Override
    public void onWeaponChanged(int id, String weapon) {
        this.weapon = weapon;
        changed = true;

    }

    @Override
    public void onDamageReceived(int id, double value) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onWeaponFired(int id, Weapon weapon) {
        // TODO Auto-generated method stub

    }

}
