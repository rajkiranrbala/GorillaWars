package com.drravns.gowars.controllers;

import greenfoot.*;

public class Player1KeyControlHandler extends AbstractKeyHandler {
    public Player1KeyControlHandler(IPlayerControlTarget controller) {
        super(controller);
    }

    protected void initializeKeys() {
        keyMap.put(VELOCITY_UP, "up");
        keyMap.put(VELOCITY_DOWN, "down");
        keyMap.put(ANGLE_UP, "right");
        keyMap.put(ANGLE_DOWN, "left");
        keyMap.put(WEAPON_PREV, "[");
        keyMap.put(WEAPON_NEXT, "]");
        keyMap.put(WEAPON_LAUNCH, "enter");
    }

    @Override
    protected String getSpecialKeyCode() {
        return "1";
    }


}