package com.drravns.gowars.weapons;

import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WeaponBase here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class ConcreteWeapon implements IWeaponDecorator {

	public ConcreteWeapon() {

	}

	@Override
	public String getImage() {
		return "weapon";
	}

	@Override
	public float getDamage() {
		return 0.1F;
	}

	@Override
	public float getError() {
		return 0.0F;
	}
}
