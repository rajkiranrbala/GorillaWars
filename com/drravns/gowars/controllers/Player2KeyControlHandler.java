package com.drravns.gowars.controllers;

import greenfoot.*;

public class Player2KeyControlHandler extends AbstractKeyHandler {
    public Player2KeyControlHandler(IPlayerControlTarget controller) {
        super(controller);
    }

    protected void initializeKeys() {
        keyMap.put(VELOCITY_UP, "w");
        keyMap.put(VELOCITY_DOWN, "s");
        keyMap.put(ANGLE_UP, "d");
        keyMap.put(ANGLE_DOWN, "a");
        keyMap.put(WEAPON_PREV, "q");
        keyMap.put(WEAPON_NEXT, "e");
        keyMap.put(WEAPON_LAUNCH, "space");
    }

    @Override
    protected String getSpecialKeyCode() {
        return "2";
    }
}