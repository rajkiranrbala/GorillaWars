package com.drravns.gowars.modes.classic;

import com.drravns.gowars.actors.Obstacle;
import com.drravns.gowars.weapons.Weapon;
import com.drravns.gowars.controllers.Player1KeyControlHandler;
import com.drravns.gowars.controllers.Player2KeyControlHandler;
import com.drravns.gowars.modes.AbstractMode;
import com.drravns.gowars.observers.IPlayerObserver;
import com.drravns.gowars.world.GamePlay;

import java.util.Random;

public class ClassicMode extends AbstractMode {

    private ClassicCompletedState completedState;

    private ClassicPlayer1TransitState player1TransitState;
    private ClassicPlayer2TransitState player2TransitState;
    private ClassicPlayer1TurnState player1TurnState;
    private ClassicPlayer2TurnState player2TurnState;
    private Player1KeyControlHandler player1KeyController;
    private Player2KeyControlHandler player2KeyController;

    private LifeTracker player1LifeTracker;
    private LifeTracker player2LifeTracker;

    private IClassicState state = null;
    private IPlayerObserver observer = null;

    private double player1Health = 1.0;
    private double player2Health = 1.0;

    private static Random r = new Random();
    public ClassicMode() {

    }

    @Override
    public void buildWorld(GamePlay world) {
        super.buildWorld(world);
        completedState = new ClassicCompletedState(this);
        player1TransitState = new ClassicPlayer1TransitState(this);
        player2TransitState = new ClassicPlayer2TransitState(this);
        player1TurnState = new ClassicPlayer1TurnState(this);
        player2TurnState = new ClassicPlayer2TurnState(this);
        observer = new PlayerObserver();
        player1LifeTracker = new LifeTracker();
        player2LifeTracker = new LifeTracker();
        player1.attach(observer);
        player1.attach(player1LifeTracker);
        player2.attach(observer);
        player2.attach(player2LifeTracker);
        if (r.nextBoolean()) {
            state = player1TurnState;
            disablePlayer2Controller();
            enablePlayer1Controller();

        } else {
            state = player2TurnState;
            disablePlayer1Controller();
            enablePlayer2Controller();
        }
        world.addObject(player1LifeTracker,105,40);
        world.addObject(player2LifeTracker,920,40);
    }

    public void setState(IClassicState state) {
        this.state = state;
    }

    public ClassicCompletedState getCompletedState() {
        return completedState;
    }

    public ClassicPlayer1TransitState getPlayer1TransitState() {
        return player1TransitState;
    }

    public ClassicPlayer2TransitState getPlayer2TransitState() {
        return player2TransitState;
    }

    public ClassicPlayer1TurnState getPlayer1TurnState() {
        return player1TurnState;
    }

    public ClassicPlayer2TurnState getPlayer2TurnState() {
        return player2TurnState;
    }

    public IClassicState getState() {
        return state;
    }

    private class PlayerObserver implements IPlayerObserver {

        @Override
        public void onVelocityChanged(int id, int velocity) {

        }

        @Override
        public void onAngleChanged(int id, int angle) {

        }

        @Override
        public void onWeaponChanged(int id, String weapon) {

        }

        @Override
        public void onDamageReceived(int id, double value) {
            if (player1.getId() == id) {
                player1Health -= value;
            } else {
                player2Health -= value;
            }
        }

        @Override
        public void onWeaponFired(int id, Weapon weapon) {
            state.onWeaponFired(id, weapon);
        }

    }

    private int winner = 0;

    public boolean checkEndGame() {

        if (player1Health <= 0) {
            winner = 2;
            return true;
        } else if (player2Health <= 0) {
            winner = 1;
            return true;
        }
        return false;
    }


    public void endGame() {
        super.endGame();
        showResult(winner);
    }

    @Override
    public Obstacle getObstacle() {
        return new Obstacle();
    }

}
