package com.drravns.gowars.controllers;

import greenfoot.Greenfoot;

import java.util.Hashtable;

public abstract class AbstractKeyHandler implements IKeyHandler {
    protected IPlayerControlTarget controller;
    protected IKeyHandler nextHandler;
    protected Hashtable<Integer, String> keyMap = new Hashtable<Integer, String>();
    public final Integer VELOCITY_UP = 1;
    public final Integer VELOCITY_DOWN = 2;
    public final Integer ANGLE_UP = 3;
    public final Integer ANGLE_DOWN = 4;
    public final Integer WEAPON_NEXT = 5;
    public final Integer WEAPON_PREV = 6;
    public final Integer WEAPON_LAUNCH = 7;

    public AbstractKeyHandler(IPlayerControlTarget controller) {
        this.controller = controller;
        initializeKeys();
    }

    protected abstract void initializeKeys();

    private boolean enabled = false;

    public void enableController() {
        this.enabled = true;
    }

    public void disableController() {
        this.enabled = false;
    }

    @Override
    public void handleKey(String key) {
        if (this.enabled) {
            if (key.equalsIgnoreCase(keyMap.get(VELOCITY_UP))) {
                controller.increaseVelocity();
            } else if (key.equalsIgnoreCase(keyMap.get(VELOCITY_DOWN))) {
                controller.decreaseVelocity();
            } else if (key.equalsIgnoreCase(keyMap.get(ANGLE_UP))) {
                controller.increaseAngle();
            } else if (key.equalsIgnoreCase(keyMap.get(ANGLE_DOWN))) {
                controller.decreaseAngle();
            } else if (key.equalsIgnoreCase(keyMap.get(WEAPON_NEXT))) {
                controller.nextWeapon();
            } else if (key.equalsIgnoreCase(keyMap.get(WEAPON_PREV))) {
                controller.previousWeapon();
            } else if (key.equalsIgnoreCase(keyMap.get(WEAPON_LAUNCH))) {
                controller.launchWeapon();
            } else if (key.startsWith("_")) {
                processSpecialCode(key);
            } else if (nextHandler != null) {
                nextHandler.handleKey(key);
            }
        } else if (nextHandler != null) {
            nextHandler.handleKey(key);
        }
    }


    protected void processSpecialCode(String key) {
        String[] parameters = key.split("\\|");
        if (parameters.length == 4) {
            if (parameters[1].equals(getSpecialKeyCode())) {
                if (parameters[2].equals("v")) {
                    int velocity = Integer.parseInt(parameters[3]);
                    controller.setVelocity(velocity);
                } else if (parameters[2].equals("a")) {
                    int angle = Integer.parseInt(parameters[3]);
                    controller.setAngle(angle);
                }
            } else {
                if (nextHandler != null) {
                    nextHandler.handleKey(key);
                }
            }
        } else {
            if (nextHandler != null) {
                nextHandler.handleKey(key);
            }
        }
    }

    protected abstract String getSpecialKeyCode();

    @Override
    public void setNext(IKeyHandler handler) {
        this.nextHandler = handler;
    }
}