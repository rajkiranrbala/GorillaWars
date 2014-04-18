package com.drravns.gowars.observers;

import com.drravns.gowars.actors.Weapon;

public interface IWeaponObserver extends IObserver {

	void onWeaponFlightEnded(Weapon w);
}
