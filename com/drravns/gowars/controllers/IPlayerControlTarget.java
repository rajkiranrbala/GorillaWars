package com.drravns.gowars.controllers;

/**
 * Write a description of class IPlayerController here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface IPlayerControlTarget
{
    public void increaseVelocity();
    public void decreaseVelocity();
    public void increaseAngle();
    public void decreaseAngle();
    public void setAngle(int angle);
    public void setVelocity(int velocity);
    public void nextWeapon();
    public void previousWeapon();
    public void launchWeapon();
}
