package com.drravns.gowars.modes.timed;

import com.drravns.gowars.actors.*;
import com.drravns.gowars.modes.AbstractMode;
import com.drravns.gowars.observers.IPlayerObserver;
import com.drravns.gowars.weapons.Weapon;
import com.drravns.gowars.world.GamePlay;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class TimedMode extends AbstractMode {

    private TimedCompletedState timedCompletedState;
    private TimedInProgressState timedInProgressState;

    private PointsTracker player1PointsTracker;
    private PointsTracker player2PointsTracker;

    private Ticker ticker;
    private ITimedModeState state = null;
    private IPlayerObserver observer = null;
    private int player1Points = 0;
    private int player2Points = 0;
    private AtomicInteger time = new AtomicInteger(60);
    private Timer t = new Timer();

    public TimedMode() {

    }

    public TimedCompletedState getTimedCompletedState() {
        return timedCompletedState;
    }

    public TimedInProgressState getTimedInProgressState() {
        return timedInProgressState;
    }

    public Ticker getTicker() {
        return ticker;
    }

    @Override
    public void buildWorld(GamePlay world) {
        super.buildWorld(world);
        observer = new PlayerObserver();
        player1PointsTracker = new PointsTracker();
        player2PointsTracker = new PointsTracker();
        ticker = new Ticker(60);
        timedCompletedState = new TimedCompletedState();
        timedInProgressState = new TimedInProgressState(this);
        player1.attach(observer);
        player1.attach(player2PointsTracker);
        player2.attach(observer);
        player2.attach(player1PointsTracker);
        world.addObject(player1PointsTracker, 105, 40);
        world.addObject(player2PointsTracker, 920, 40);
        world.addObject(ticker, 512, 60);
        enablePlayer1Controller();
        enablePlayer2Controller();
        state = timedInProgressState;
        startTimer();

    }

    public void setState(ITimedModeState state) {
        this.state = state;
    }

    public void endGame() {
        super.endGame();
        world.removeObject(ticker);
        if (player1Points == player2Points) {
            showResult(0);
        } else {
            showResult((player1Points > player2Points) ? 1 : 2);
        }
    }

    @Override
    public Obstacle getObstacle() {
        return new MovingObstacle();
    }

    private void startTimer() {
        t.scheduleAtFixedRate(new ElapsedTime(), Calendar.getInstance()
                .getTime(), 1000);
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
                player2Points++;
            } else {
                player1Points++;
            }
        }

        @Override
        public void onWeaponFired(int id, Weapon weapon) {

        }

    }

    private class ElapsedTime extends TimerTask {

        @Override
        public void run() {
            int val = time.get() - 1;
            time.set(val);
            if (time.get() < 0) {
                t.cancel();
                state.onTimerExpired();
            } else {
                getTicker().setTime(val);
            }
        }

    }

}
