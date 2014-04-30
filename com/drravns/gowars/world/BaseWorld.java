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
        super(1024, 768, 1);
        wireUp();
    }

    private void wireUp() {
        MenuOption option1 = new MenuOption("Play");
        option1.setCommand(new ICommand() {
            @Override
            public void execute() {
                Greenfoot.setWorld(new StrategySelect());
            }
        });
        addObject(option1, 100, 100);

        MenuOption option2 = new MenuOption("Help");
        option1.setCommand(new ICommand() {
            @Override
            public void execute() {
                Greenfoot.setWorld(new StrategySelect());
            }
        });
        option2.setCommand(new ICommand() {
            @Override
            public void execute() {
                Greenfoot.setWorld(new Help());
            }
        });
        addObject(option2, 100, 100);
        MenuOption option3 = new MenuOption("Options");
        option3.setCommand(new ICommand() {
            @Override
            public void execute() {
                Greenfoot.setWorld(new Options());
            }
        });
        addObject(option3, 100, 100);

        MenuOption option4 = new MenuOption("Exit");
        option4.setCommand(new ICommand() {
            @Override
            public void execute() {
                Greenfoot.stop();
                System.exit(0);
            }
        });
        addObject(option4, 100, 100);
    }

}
