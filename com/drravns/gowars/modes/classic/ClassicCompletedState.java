package com.drravns.gowars.modes.classic;

import com.drravns.gowars.actors.Weapon;

public class ClassicCompletedState implements IClassicState {

	private ClassicMode mode;

	public ClassicCompletedState(ClassicMode classicMode) {
		this.mode = classicMode;
	}

	@Override
	public void onWeaponFlightEnded(Weapon w) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onWeaponFired(int id, Weapon weapon) {
		// TODO Auto-generated method stub

	}

}
