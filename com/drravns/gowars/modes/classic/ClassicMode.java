package com.drravns.gowars.modes.classic;

import com.drravns.gowars.actors.Gorilla;
import com.drravns.gowars.actors.LifeTracker;
import com.drravns.gowars.actors.PlayerControlViewer;
import com.drravns.gowars.actors.Weapon;
import com.drravns.gowars.controllers.KeyController;
import com.drravns.gowars.controllers.Player1KeyController;
import com.drravns.gowars.controllers.Player2KeyController;
import com.drravns.gowars.modes.IStrategy;
import com.drravns.gowars.observers.IPlayerObserver;
import com.drravns.gowars.world.GamePlay;

public class ClassicMode implements IStrategy {

	private ClassicCompletedState completedState;

	private ClassicPlayer1TransitState player1TransitState;
	private ClassicPlayer2TransitState player2TransitState;
	private ClassicPlayer1TurnState player1TurnState;
	private ClassicPlayer2TurnState player2TurnState;
	private KeyController player1KeyController;
	private KeyController player2KeyController;
	private LifeTracker player1LifeTracker;
	private LifeTracker player2LifeTracker;
	private PlayerControlViewer player1ControlViewer;
	private PlayerControlViewer player2ControlViewer;
	private int player1ControlViewerPositionX;
	private int player1ControlViewerPositionY;
	private int player2ControlViewerPositionX;
	private int player2ControlViewerPositionY;

	private IClassicState state = null;
	private IPlayerObserver observer = null;

	private Gorilla player1 = null;
	private Gorilla player2 = null;

	private float player1Health = 100;
	private float player2Health = 100;

	private GamePlay world;

	private String winner;

	public ClassicMode(GamePlay world) {
		this.world = world;
		completedState = new ClassicCompletedState(this);
		player1TransitState = new ClassicPlayer1TransitState(this);
		player2TransitState = new ClassicPlayer2TransitState(this);
		player1TurnState = new ClassicPlayer1TurnState(this);
		player2TurnState = new ClassicPlayer2TurnState(this);
		observer = new PlayerObserver();
		player1 = new Gorilla(false);
		player2 = new Gorilla(true);
		player1KeyController = new Player1KeyController(player1);
		player2KeyController = new Player2KeyController(player2);
		player1ControlViewer = new PlayerControlViewer();
		player2ControlViewer = new PlayerControlViewer();
		player1LifeTracker = new LifeTracker();
		player2LifeTracker = new LifeTracker();
	}

	@Override
	public void buildWorld() {

	}

	public void init() {
		player1.attach(observer);
		player1.attach(player1LifeTracker);
		player1.attach(player1ControlViewer);

		player2.attach(observer);
		player2.attach(player2ControlViewer);
		player2.attach(player2LifeTracker);
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

	public boolean checkEndGame() {

		if (player1Health <= 0) {
			winner = "Player 2";
			return true;
		} else if (player2Health <= 0) {
			winner = "Player 1";
			return true;
		}
		return false;
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
		showResult();
	}

	public void showResult() {

	}
}
