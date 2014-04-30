package com.drravns.gowars.actors;

import com.drravns.gowars.observers.IPlayerObserver;

import com.drravns.gowars.weapons.Weapon;
import greenfoot.Actor;

public class PlayerControlViewer extends Actor implements IPlayerObserver {

	@Override
	public void onVelocityChanged(int id, int velocity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAngleChanged(int id, int angle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onWeaponChanged(int id, String weapon) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDamageReceived(int id, double value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onWeaponFired(int id, Weapon weapon) {
		// TODO Auto-generated method stub

	}

}
