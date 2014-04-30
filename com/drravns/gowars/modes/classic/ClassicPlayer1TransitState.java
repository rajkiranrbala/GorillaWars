package com.drravns.gowars.modes.classic;

import com.drravns.gowars.weapons.Weapon;

public class ClassicPlayer1TransitState implements IClassicState {

	private ClassicMode mode;

	public ClassicPlayer1TransitState(ClassicMode classicMode) {
		this.mode = classicMode;
	}

	@Override
	public void onWeaponFlightEnded(Weapon w) {
		if (mode.checkEndGame()) {
			mode.setState(mode.getCompletedState());
			mode.endGame();
		} else {
			mode.setState(mode.getPlayer2TurnState());
			mode.enablePlayer2Controller();
		}
	}

	@Override
	public void onWeaponFired(int id, Weapon weapon) {

	}

}
