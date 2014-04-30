package com.drravns.gowars.modes.timed;

import com.drravns.gowars.weapons.Weapon;
import greenfoot.Actor;

import com.drravns.gowars.observers.IPlayerObserver;

// (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LifeTracker here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class PointsTracker extends Actor implements IPlayerObserver {
	private int points = 0;
	private boolean changed = false;

	public PointsTracker() {

	}

	public void act() {
		if (changed) {
			changed = false;
		}
	}

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
		points++;
		changed = true;

	}

	@Override
	public void onWeaponFired(int id, Weapon weapon) {
		// TODO Auto-generated method stub

	}
}
