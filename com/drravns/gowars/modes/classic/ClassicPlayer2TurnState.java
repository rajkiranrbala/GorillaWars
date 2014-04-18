package com.drravns.gowars.modes.classic;

import com.drravns.gowars.actors.Weapon;

public class ClassicPlayer2TurnState implements IClassicState {

	private ClassicMode mode;

	public ClassicPlayer2TurnState(ClassicMode classicMode) {
		this.mode = classicMode;
	}

	@Override
	public void onWeaponFlightEnded(Weapon w) {
		mode.disablePlayer2Controller();
		w.attach(mode.getPlayer2TransitState());
		mode.setState(mode.getPlayer2TurnState());

	}

	@Override
	public void onWeaponFired(int id, Weapon weapon) {

	}

}
