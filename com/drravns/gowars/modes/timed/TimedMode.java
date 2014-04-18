package com.drravns.gowars.modes.timed;

import com.drravns.gowars.actors.Gorilla;
import com.drravns.gowars.actors.PlayerControlViewer;
import com.drravns.gowars.actors.PointsTracker;
import com.drravns.gowars.actors.Ticker;
import com.drravns.gowars.actors.Weapon;
import com.drravns.gowars.controllers.KeyController;
import com.drravns.gowars.controllers.Player1KeyController;
import com.drravns.gowars.controllers.Player2KeyController;
import com.drravns.gowars.modes.IStrategy;
import com.drravns.gowars.observers.IPlayerObserver;
import com.drravns.gowars.world.GamePlay;

public class TimedMode implements IStrategy {

	private TimedCompletedState timedCompletedState;
	private TimedInProgressState timedInProgressState;

	private KeyController player1KeyController;
	private KeyController player2KeyController;
	private PointsTracker player1PointsTracker;
	private PointsTracker player2PointsTracker;
	private PlayerControlViewer player1ControlViewer;
	private PlayerControlViewer player2ControlViewer;
	private Ticker ticker;
	private int player1ControlViewerPositionX;

	public TimedCompletedState getTimedCompletedState() {
		return timedCompletedState;
	}

	public TimedInProgressState getTimedInProgressState() {
		return timedInProgressState;
	}

	public Ticker getTicker() {
		return ticker;
	}

	private int player1ControlViewerPositionY;
	private int player2ControlViewerPositionX;
	private int player2ControlViewerPositionY;

	private ITimedModeState state = null;
	private IPlayerObserver observer = null;

	private Gorilla player1 = null;
	private Gorilla player2 = null;

	private int player1Points = 0;
	private int player2Points = 0;

	private GamePlay world;

	public TimedMode(GamePlay world) {
		this.world = world;
		observer = new PlayerObserver();
		player1 = new Gorilla(false);
		player2 = new Gorilla(true);
		player1KeyController = new Player1KeyController(player1);
		player2KeyController = new Player2KeyController(player2);
		player1ControlViewer = new PlayerControlViewer();
		player2ControlViewer = new PlayerControlViewer();
		player1PointsTracker = new PointsTracker();
		player2PointsTracker = new PointsTracker();
		ticker = new Ticker(120);
		timedCompletedState = new TimedCompletedState();
		timedInProgressState = new TimedInProgressState(this);
	}

	@Override
	public void buildWorld() {

	}

	public void init() {
		player1.attach(observer);
		player1.attach(player1ControlViewer);
		player1.attach(player1PointsTracker);

		player2.attach(observer);
		player2.attach(player2ControlViewer);
		player2.attach(player2PointsTracker);
	}

	public void setState(ITimedModeState state) {
		this.state = state;
	}

	private class PlayerObserver implements IPlayerObserver {

		@Override
		public void onVelocityChanaged(int id, int velocity) {

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

	public void enablePlayer2Contoller() {
		player2KeyController.enableContoller();
		player2KeyController.setLocation(player2ControlViewerPositionX,
				player2ControlViewerPositionY);

	}

	public void disablePlayer1Controller() {
		player1KeyController.disableController();
		player1KeyController.setLocation(-1000, -1000);

	}

	public void enablePlayer1Contoller() {
		player1KeyController.enableContoller();
		player1KeyController.setLocation(player1ControlViewerPositionX,
				player1ControlViewerPositionY);

	}

	public void disablePlayer2Controller() {
		player2KeyController.disableController();
		player2KeyController.setLocation(-1000, -1000);
	}

	public void endGame() {
		disablePlayer1Controller();
		disablePlayer2Controller();
		world.removeObject(player1KeyController);
		world.removeObject(player2KeyController);
		world.removeObject(player1ControlViewer);
		world.removeObject(player2ControlViewer);
		world.removeObject(ticker);
		showResult();
	}

	public void showResult() {

	}
}
