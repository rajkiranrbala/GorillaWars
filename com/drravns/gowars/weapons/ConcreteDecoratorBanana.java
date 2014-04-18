package com.drravns.gowars.weapons;
import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ConcreteDecoratorBanana extends AbstractWeaponDecorator
{
	public ConcreteDecoratorBanana(ConcreteWeapon w)
	{
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
