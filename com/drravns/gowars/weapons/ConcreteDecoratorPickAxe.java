package com.drravns.gowars.weapons;

import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ConcreteDecoratorPickAxe extends AbstractWeaponDecorator {
	public ConcreteDecoratorPickAxe(ConcreteWeapon w) {
		super(w);
	}

	@Override
	public float getDamage() {
		return super.getDamage() + 0.15F;
	}

	@Override
	public float getError() {
		return super.getError() + 0.15F;
	}

	@Override
	public String getImage() {
		return "images/weapon_pickaxe.png";
	}

	@Override
	public String toString() {
		return "PickAxe";
	}
}
