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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getError() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
