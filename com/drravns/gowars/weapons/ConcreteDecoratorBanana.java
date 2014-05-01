package com.drravns.gowars.weapons;

import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ConcreteDecoratorBanana extends AbstractWeaponDecorator {
	public ConcreteDecoratorBanana(ConcreteWeapon w) {
		super(w);
	}

	@Override
	public float getDamage() {
		return super.getDamage();
	}

	@Override
	public float getError() {
		return super.getError();
	}

	@Override
	public String getImage() {
		return "images/weapon_banana.png";
	}

	@Override
	public String toString() {
		return "Banana";
	}

}
