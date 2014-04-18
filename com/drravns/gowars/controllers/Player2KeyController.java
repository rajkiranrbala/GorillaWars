package com.drravns.gowars.controllers;
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Player2KeyController extends KeyController
{
    public Player2KeyController(IPlayerControlAdapter controller)
    {
        super(controller);
    }

    protected void initializeKeys()
    {
        keyMap.put(VELOCITY_UP,"w");
        keyMap.put(VELOCITY_DOWN,"s");
        keyMap.put(ANGLE_UP,"d");
        keyMap.put(ANGLE_DOWN,"a");
        keyMap.put(WEAPON_PREV,"q");
        keyMap.put(WEAPON_NEXT,"e");
        keyMap.put(WEAPON_LAUNCH,"space");
    }
}
