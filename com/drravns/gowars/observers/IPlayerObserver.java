package com.drravns.gowars.observers;

import com.drravns.gowars.weapons.Weapon;

public interface IPlayerObserver extends IObserver {
	void onVelocityChanged(int id, int velocity);

	void onAngleChanged(int id, int angle);

	void onWeaponChanged(int id, String weapon);

	void onDamageReceived(int id, double value);

	void onWeaponFired(int id, Weapon weapon);
}
