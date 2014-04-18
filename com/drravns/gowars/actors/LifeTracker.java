package com.drravns.gowars.actors;

import greenfoot.Actor;

import com.drravns.gowars.observers.IPlayerObserver;

// (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LifeTracker here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class LifeTracker extends Actor implements IPlayerObserver {
	private double life = 100.00;
	private boolean changed = false;

	public LifeTracker() {

	}

	public void act() {
		if (changed) {
			changed = false;
		}
	}

	@Override
	public void onVelocityChanaged(int id, int velocity) {
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
		life -= value;
		if (life < 0) {
			life = 0;
		}
		changed = true;

	}

	@Override
	public void onWeaponFired(int id, Weapon weapon) {
		// TODO Auto-generated method stub

	}

}
