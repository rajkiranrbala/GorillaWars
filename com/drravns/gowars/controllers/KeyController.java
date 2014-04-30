package com.drravns.gowars.controllers;

import greenfoot.Actor;
import greenfoot.Greenfoot;

public class KeyController extends Actor {
    private IKeyHandler handler;

    public KeyController(IKeyHandler handler) {
        this.handler = handler;
    }

    public void act() {
        String key = Greenfoot.getKey();
        if (key != null) {
            processKey(key);
        }
    }

    public void processKey(String key) {
        handler.handleKey(key);
    }
}
