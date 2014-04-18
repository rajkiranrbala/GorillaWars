package com.drravns.gowars.controllers;
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class IPlayerController here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface IPlayerControlAdapter
{
    public void increaseVelocity();
    public void decreaseVelocity();
    public void increaseAngle();
    public void decreaseAngle();
    public void setAngle(int angle);
    public void setVelocity(int velcoity);
    public void nextWeapon();
    public void previousWeapon();
    public void launchWeapon();
}
