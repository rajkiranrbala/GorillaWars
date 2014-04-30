package com.drravns.gowars.command;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class MenuOption extends Actor {

    private ICommand command = null;

    public MenuOption(String name) {
        setImage(new GreenfootImage(400, 100));
        getImage().drawString(name, 30, 20);
    }

    @Override
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            if (this.command != null) {
                command.execute();
            }
        }
    }

    public void setCommand(ICommand command) {
        this.command = command;
    }

}
