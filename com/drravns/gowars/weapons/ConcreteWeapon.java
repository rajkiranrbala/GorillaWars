package com.drravns.gowars.weapons;

import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WeaponBase here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class ConcreteWeapon implements IWeaponDecorator {

	protected int velocity;
	protected int angle;
	protected boolean reverse;

	public ConcreteWeapon(int velocity, int angle, boolean reverse) {
		this.velocity = velocity;
		this.angle = angle;
		this.reverse = reverse;
	}

	@Override
	public String getImage() {
		return "";
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
}
