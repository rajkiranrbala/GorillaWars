package com.drravns.gowars.actors;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import greenfoot.Actor;

import com.drravns.gowars.observers.DefaultSubject;
import com.drravns.gowars.observers.ISubject;
import com.drravns.gowars.observers.IWeaponObserver;
import com.drravns.gowars.weapons.ConcreteDecoratorBanana;
import com.drravns.gowars.weapons.ConcreteDecoratorFireBall;
import com.drravns.gowars.weapons.ConcreteDecoratorPickAxe;
import com.drravns.gowars.weapons.ConcreteWeapon;
import com.drravns.gowars.weapons.IWeaponDecorator;

public class Weapon extends Actor implements ISubject<IWeaponObserver> {

	private IWeaponDecorator weaponDecorator;
	private DefaultSubject<IWeaponObserver> weaponSubject = new DefaultSubject<IWeaponObserver>();

	public Weapon(IWeaponDecorator weaponDecorator) {
		this.weaponDecorator = weaponDecorator;
		setImage(weaponDecorator.getImage());
	}

	@Override
	public void act() {
		setRotation((getRotation() + 10) % 360);
	}

	@Override
	public void attach(IWeaponObserver observer) {
		weaponSubject.attach(observer);

	}

	@Override
	public void detach(IWeaponObserver observer) {
		weaponSubject.detach(observer);
	}

	private static String[] weaponNames = { "Banana", "Pick Axe", "Fire Ball" };

	public static String[] getWeapons() {
		return weaponNames;
	}

	public static IWeaponDecorator createWeapon(String name, int velocity,
			int angle, boolean reverse) {
		ConcreteWeapon w = new ConcreteWeapon(velocity, angle, reverse);
		if (name.equals(weaponNames[0])) {
			return new ConcreteDecoratorBanana(w);
		} else if (name.equals(weaponNames[1])) {
			return new ConcreteDecoratorPickAxe(w);
		} else if (name.equals(weaponNames[2])) {
			return new ConcreteDecoratorFireBall(w);
		} else {
			return w;
		}
	}

	private static java.lang.Class[] weaponClasses = {
			ConcreteDecoratorBanana.class, ConcreteDecoratorPickAxe.class,
			ConcreteDecoratorFireBall.class, };

	private static IWeaponDecorator createWeapon(java.lang.Class c,
			int velocity, int angle, boolean reverse)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		return (IWeaponDecorator) c.getConstructor(ConcreteWeapon.class)
				.newInstance(new ConcreteWeapon(velocity, angle, reverse));
	}
}
