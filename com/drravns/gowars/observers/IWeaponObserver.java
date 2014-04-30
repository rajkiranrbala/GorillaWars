package com.drravns.gowars.observers;

import com.drravns.gowars.weapons.Weapon;

public interface IWeaponObserver extends IObserver {

	void onWeaponFlightEnded(Weapon w);
}
