package com.drravns.gowars.world;

import com.drravns.gowars.command.ICommand;
import com.drravns.gowars.command.MenuOption;
import com.drravns.gowars.modes.classic.ClassicMode;
import com.drravns.gowars.modes.timed.TimedMode;
import greenfoot.Greenfoot;
import greenfoot.World;

/**
 * Created by Kitty on 4/29/2014.
 */
public class StrategySelect extends World {
    GamePlay g;

    public StrategySelect() {
        super(1024, 768, 1, false);
        setBackground("images/BG_BaseWorld.png");
        g = new GamePlay();
        wireUp();
    }

    private void wireUp() {

        MenuOption option1 = new MenuOption("Classic");
        option1.setCommand(new ICommand() {
            @Override
            public void execute() {
                ClassicMode m = new ClassicMode();
                g.setStrategy(m);
                Greenfoot.setWorld(g);
                g.being();

            }
        });
        addObject(option1, 750, 300);

        MenuOption option2 = new MenuOption("Timed");
        option2.setCommand(new ICommand() {
            @Override
            public void execute() {
                TimedMode m = new TimedMode();
                g.setStrategy(m);
                Greenfoot.setWorld(g);
                g.being();
            }
        });
        addObject(option2, 750, 400);
    }


}
