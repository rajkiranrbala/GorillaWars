package com.drravns.gowars.weapons;

public abstract class AbstractWeaponDecorator implements IWeaponDecorator {

	private ConcreteWeapon w;

	public AbstractWeaponDecorator(ConcreteWeapon w) {
		this.w = w;
	}

	@Override
	public float getDamage() {
		return w.getDamage();
	}

	@Override
	public float getError() {
		return w.getError();
	}

	@Override
	public String getImage() {
		return w.getImage();
	}
}
