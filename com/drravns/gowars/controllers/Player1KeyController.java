package com.drravns.gowars.controllers;
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player1Controller here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player1KeyController extends KeyController
{
    public Player1KeyController(IPlayerControlAdapter controller)
    {
        super(controller);
    }

    protected void initializeKeys()
    {
        keyMap.put(VELOCITY_UP,"up");
        keyMap.put(VELOCITY_DOWN,"down");
        keyMap.put(ANGLE_UP,"right");
        keyMap.put(ANGLE_DOWN,"left");
        keyMap.put(WEAPON_PREV,"[");
        keyMap.put(WEAPON_NEXT,"]");
        keyMap.put(WEAPON_LAUNCH,"enter");
    }
}
