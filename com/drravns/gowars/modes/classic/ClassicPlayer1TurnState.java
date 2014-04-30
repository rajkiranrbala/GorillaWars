package com.drravns.gowars.modes.classic;

import com.drravns.gowars.weapons.Weapon;

public class ClassicPlayer1TurnState implements IClassicState {

	private ClassicMode mode;

	public ClassicPlayer1TurnState(ClassicMode mode) {
		this.mode = mode;
	}

	@Override
	public void onWeaponFired(int id, Weapon weapon) {
		mode.disablePlayer1Controller();
		weapon.attach(mode.getPlayer1TransitState());
		mode.setState(mode.getPlayer1TransitState());
	}

	@Override
	public void onWeaponFlightEnded(Weapon w) {

	}
}
