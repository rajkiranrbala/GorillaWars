package com.drravns.gowars.modes.classic;

import com.drravns.gowars.weapons.Weapon;
import com.drravns.gowars.observers.IWeaponObserver;

public interface IClassicState extends IWeaponObserver {
	void onWeaponFired(int id, Weapon weapon);
}
