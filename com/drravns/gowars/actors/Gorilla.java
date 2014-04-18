package com.drravns.gowars.actors;

import greenfoot.Actor;

import com.drravns.gowars.controllers.IPlayerControlAdapter;
import com.drravns.gowars.observers.DefaultSubject;
import com.drravns.gowars.observers.IPlayerObserver;
import com.drravns.gowars.observers.ISubject;
import com.drravns.gowars.weapons.IWeaponDecorator;

// (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Gorilla here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Gorilla extends Actor implements ISubject<IPlayerObserver>,
		IPlayerControlAdapter {

	private static int count = 0;
	private int id;
	private int angle;
	private int velocity;
	private boolean reverse;
	private int selectedWeaponIndex = 0;

	DefaultSubject<IPlayerObserver> playerSubject = new DefaultSubject<IPlayerObserver>();

	public Gorilla(boolean reverse) {
		id = ++count;
		this.reverse = reverse;
	}

	public int getId() {
		return this.id;
	}

	public void act() {

	}

	@Override
	public void attach(IPlayerObserver observer) {
		playerSubject.attach(observer);
	}

	@Override
	public void detach(IPlayerObserver observer) {
		playerSubject.detach(observer);

	}

	@Override
	public void increaseVelocity() {
		setVelocity(velocity + 1);

	}

	@Override
	public void decreaseVelocity() {
		setVelocity(velocity - 1);

	}

	@Override
	public void increaseAngle() {
		setAngle(angle + 1);
	}

	@Override
	public void decreaseAngle() {
		setAngle(angle - 1);
	}

	@Override
	public void setAngle(int angle) {
		if (angle <= 180 && angle >= 0) {
			this.angle = angle;
			for (IPlayerObserver o : playerSubject.getObservers()) {
				o.onAngleChanged(this.id, angle);
			}
		}
	}

	@Override
	public void setVelocity(int v) {
		if (v <= 100 && v >= 1) {
			this.velocity = v;
			for (IPlayerObserver o : playerSubject.getObservers()) {
				o.onVelocityChanaged(this.id, v);
			}
		}
	}

	@Override
	public void nextWeapon() {
		selectedWeaponIndex = (selectedWeaponIndex + 1)
				% Weapon.getWeapons().length;
		for (IPlayerObserver o : playerSubject.getObservers()) {
			o.onWeaponChanged(id, Weapon.getWeapons()[selectedWeaponIndex]);
		}
	}

	@Override
	public void previousWeapon() {
		selectedWeaponIndex = (selectedWeaponIndex - 1)
				% Weapon.getWeapons().length;
		for (IPlayerObserver o : playerSubject.getObservers()) {
			o.onWeaponChanged(id, Weapon.getWeapons()[selectedWeaponIndex]);
		}

	}

	@Override
	public void launchWeapon() {
		IWeaponDecorator d = Weapon.createWeapon(
				Weapon.getWeapons()[selectedWeaponIndex], velocity, angle,
				reverse);
		Weapon w = new Weapon(d);
		//TODO: Place weapon
		for (IPlayerObserver o : playerSubject.getObservers()) {
			o.onWeaponFired(id, w);
		}
	}

	public void inflictDamage(double damage) {
		for (IPlayerObserver o : playerSubject.getObservers()) {
			o.onDamageReceived(id, damage);
		}
	}

}
