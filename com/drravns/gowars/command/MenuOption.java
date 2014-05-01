package com.drravns.gowars.command;

import greenfoot.Actor;
import greenfoot.Greenfoot;

import java.nio.file.Path;

public class MenuOption extends Actor {

    private ICommand command = null;

    public MenuOption(String name) {

        setImage("images/btn_" + name.toLowerCase() + ".png");

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
