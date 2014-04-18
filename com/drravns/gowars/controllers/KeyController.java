package com.drravns.gowars.controllers;
import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Hashtable;
import java.lang.Integer;

public abstract class KeyController extends Actor {
	private IPlayerControlAdapter controller;
	protected Hashtable<Integer, String> keyMap = new Hashtable<Integer, String>();
	public final Integer VELOCITY_UP = 1;
	public final Integer VELOCITY_DOWN = 2;
	public final Integer ANGLE_UP = 3;
	public final Integer ANGLE_DOWN = 4;
	public final Integer WEAPON_NEXT = 5;
	public final Integer WEAPON_PREV = 6;
	public final Integer WEAPON_LAUNCH = 7;

	private boolean enabled = false;
	
	public KeyController(IPlayerControlAdapter controller) {
		this.controller = controller;
		initializeKeys();
	}
	
	public void enableContoller()
	{
		this.enabled = true;
	}
	
	public void disableController()
	{
		this.enabled = false;
	}

	protected abstract void initializeKeys();

	public void act() {
		if(this.enabled){
		if (Greenfoot.isKeyDown(keyMap.get(VELOCITY_UP))) {
			controller.increaseVelocity();
		} else if (Greenfoot.isKeyDown(keyMap.get(VELOCITY_DOWN))) {
			controller.decreaseVelocity();
		} else if (Greenfoot.isKeyDown(keyMap.get(ANGLE_UP))) {
			controller.increaseAngle();
		} else if (Greenfoot.isKeyDown(keyMap.get(ANGLE_DOWN))) {
			controller.decreaseAngle();
		} else if (Greenfoot.isKeyDown(keyMap.get(WEAPON_NEXT))) {
			controller.nextWeapon();
		} else if (Greenfoot.isKeyDown(keyMap.get(WEAPON_PREV))) {
			controller.previousWeapon();
		} else if (Greenfoot.isKeyDown(keyMap.get(WEAPON_LAUNCH))) {
			controller.launchWeapon();
		}
		}
	}
}
