package com.drravns.gowars.weapons;

import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FireBall here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class ConcreteDecoratorFireBall extends AbstractWeaponDecorator {

	public ConcreteDecoratorFireBall(ConcreteWeapon w) {
		super(w);
	}

	@Override
	public float getDamage() {
		return super.getDamage() + 0.3F;
	}

	@Override
	public float getError() {
		return super.getError() + 0.3F;
	}

	@Override
	public String getImage() {
		return "fireballweapon";
	}

	@Override
	public String toString() {
		return "Fireball";
	}
}
