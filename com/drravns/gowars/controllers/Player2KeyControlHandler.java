package com.drravns.gowars.controllers;

import greenfoot.*;

public class Player2KeyControlHandler extends AbstractKeyHandler {
    public Player2KeyControlHandler(IPlayerControlTarget controller) {
        super(controller);
    }

    protected void initializeKeys() {
        keyMap.put(VELOCITY_UP, "o");
        keyMap.put(VELOCITY_DOWN, "l");
        keyMap.put(ANGLE_UP, ";");
        keyMap.put(ANGLE_DOWN, "k");
        keyMap.put(WEAPON_PREV, "i");
        keyMap.put(WEAPON_NEXT, "p");
        keyMap.put(WEAPON_LAUNCH, "enter");
    }

    @Override
    protected String getSpecialKeyCode() {
        return "2";
    }
}