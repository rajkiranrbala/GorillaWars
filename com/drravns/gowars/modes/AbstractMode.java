package com.drravns.gowars.modes;

import com.drravns.gowars.actors.Gorilla;
import com.drravns.gowars.actors.Obstacle;
import com.drravns.gowars.actors.PlayerControlViewer;
import com.drravns.gowars.controllers.KeyController;
import com.drravns.gowars.controllers.KinectController;
import com.drravns.gowars.controllers.Player1KeyControlHandler;
import com.drravns.gowars.controllers.Player2KeyControlHandler;
import com.drravns.gowars.world.GamePlay;

/**
 * Created by Kitty on 4/29/2014.
 */
public abstract class AbstractMode implements IStrategy {

    protected GamePlay world;

    protected KeyController keyController;
    protected Player1KeyControlHandler player1KeyControlHandler;
    protected Player2KeyControlHandler player2KeyControlHandler;
    protected PlayerControlViewer player1ControlViewer;
    protected PlayerControlViewer player2ControlViewer;
    protected Gorilla player1 = null;
    protected Gorilla player2 = null;
    private KinectController kinectController;
    private int player1ControlViewerPositionX;
    private int player1ControlViewerPositionY;
    private int player2ControlViewerPositionX;
    private int player2ControlViewerPositionY;


    @Override
    public void buildWorld(GamePlay world) {
        this.world = world;
        initialize();
    }

    private void initialize() {
        player1 = new Gorilla(false, null);
        player2 = new Gorilla(true, null);
        player1KeyControlHandler = new Player1KeyControlHandler(player1);
        player2KeyControlHandler = new Player2KeyControlHandler(player2);
        player1KeyControlHandler.setNext(player2KeyControlHandler);
        keyController = new KeyController(player1KeyControlHandler);
        kinectController = new KinectController(player1KeyControlHandler);
        world.addObject(keyController, -1000, -1000);
        player1ControlViewer = new PlayerControlViewer();
        player2ControlViewer = new PlayerControlViewer();
        player1.attach(player1ControlViewer);
        player2.attach(player2ControlViewer);
        try {
            kinectController.start(true, 320, 640);
        } catch (Exception ex) {

        }
    }

    public void enablePlayer2Controller() {
        player2KeyControlHandler.enableController();
        player2ControlViewer.setLocation(player2ControlViewerPositionX,
                player2ControlViewerPositionY);

    }

    public void disablePlayer1Controller() {
        player1KeyControlHandler.disableController();
        player1ControlViewer.setLocation(-1000, -1000);

    }

    public void enablePlayer1Controller() {
        player1KeyControlHandler.enableController();
        player1ControlViewer.setLocation(player1ControlViewerPositionX,
                player1ControlViewerPositionY);

    }

    public void disablePlayer2Controller() {
        player2KeyControlHandler.disableController();
        player2ControlViewer.setLocation(-1000, -1000);
    }

    protected void endGame() {
        disablePlayer1Controller();
        disablePlayer2Controller();
        world.removeObject(player1ControlViewer);
        world.removeObject(player2ControlViewer);
        try {
            kinectController.stop();
        } catch (Exception ex) {

        }
    }

    protected void showResult(String winner) {

    }

    public abstract Obstacle getObstacle();

}
