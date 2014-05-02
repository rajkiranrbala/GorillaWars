package com.drravns.gowars.modes;

import com.drravns.gowars.actors.Gorilla;
import com.drravns.gowars.actors.Obstacle;
import com.drravns.gowars.actors.PlayerControlViewer;
import com.drravns.gowars.actors.Result;
import com.drravns.gowars.controllers.KeyController;
import com.drravns.gowars.controllers.Player1KeyControlHandler;
import com.drravns.gowars.controllers.Player2KeyControlHandler;
import com.drravns.gowars.controllers.kinect.KinectController;
import com.drravns.gowars.world.GamePlay;

import java.util.ArrayList;
import java.util.Random;

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


    private static Random r = new Random();

    private Obstacle findMaxIndex(ArrayList<Obstacle> obstacles, int... indices) {
        int maxIndex = indices[0];
        for (int i : indices) {
            if (obstacles.get(maxIndex).getY() > obstacles.get(i).getY()) {
                maxIndex = i;
            }
        }
        return obstacles.get(maxIndex);
    }

    private void initialize() {
        ArrayList<Obstacle> myObstacles = new ArrayList<Obstacle>();
        for (int i = 0; i < 10; i++) {
            int y = 384 + 300 + r.nextInt(400);
            Obstacle o = getObstacle();
            myObstacles.add(o);
            world.addObject(o, 50 + 100 * i + 3, y);
        }
        player1 = new Gorilla(false, findMaxIndex(myObstacles, 0, 1, 2));
        player2 = new Gorilla(true, findMaxIndex(myObstacles, 9, 8, 7));
        world.addObject(player1, 0, 0);
        world.addObject(player2, 0, 0);

        player1KeyControlHandler = new Player1KeyControlHandler(player1);
        player2KeyControlHandler = new Player2KeyControlHandler(player2);
        player1KeyControlHandler.setNext(player2KeyControlHandler);
        keyController = new KeyController(player1KeyControlHandler);
        kinectController = KinectController.getKinectController();
        kinectController.setHandler(player1KeyControlHandler);
        world.addObject(keyController, -1000, -1000);

        player1ControlViewer = new PlayerControlViewer();
        player2ControlViewer = new PlayerControlViewer();

        player1.attach(player1ControlViewer);
        player2.attach(player2ControlViewer);
        player1ControlViewerPositionX = 317;
        player1ControlViewerPositionY = 55;
        player2ControlViewerPositionX = 719;
        player2ControlViewerPositionY = 55;
        world.addObject(player1ControlViewer, player1ControlViewerPositionX, player1ControlViewerPositionY);
        world.addObject(player2ControlViewer, player2ControlViewerPositionX, player2ControlViewerPositionY);
        kinectController.enableController();
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
        kinectController.disableController();
    }

    protected void showResult(int winner) {
        Result r = new Result(winner);
        world.addObject(r, 512, 384);
    }

    public abstract Obstacle getObstacle();

}
