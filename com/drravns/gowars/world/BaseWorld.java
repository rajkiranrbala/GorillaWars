package com.drravns.gowars.world;

import com.drravns.gowars.command.ICommand;
import com.drravns.gowars.command.MenuOption;
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BaseWorld here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BaseWorld extends World {
    public BaseWorld() {
        super(1024, 768, 1, false);
        setBackground("images/BG_BaseWorld.png");
        wireUp();
        Greenfoot.start();
    }

    private void wireUp() {
        MenuOption option1 = new MenuOption("Play");
        option1.setCommand(new ICommand() {
            @Override
            public void execute() {
                Greenfoot.setWorld(new StrategySelect());
            }
        });
        addObject(option1, 800, 180);
        MenuOption option2 = new MenuOption("Help");
        option2.setCommand(new ICommand() {
            @Override
            public void execute() {
                Greenfoot.setWorld(new Help());
            }
        });
        addObject(option2, 800, 220 + 100);
        MenuOption option3 = new MenuOption("Exit");
        option3.setCommand(new ICommand() {
            @Override
            public void execute() {
                Greenfoot.stop();
                System.exit(0);
            }
        });
        addObject(option3, 800, 260 + 200);
    }

}
