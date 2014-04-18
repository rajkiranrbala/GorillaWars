package com.drravns.gowars.actors;

import greenfoot.Actor;

public class Ticker extends Actor {

	private int seconds;

	public Ticker(int seconds) {
		this.seconds = seconds;
	}

	private boolean update = false;

	public void setTime(int seconds) {
		this.seconds = seconds;
		update = true;
	}

	@Override
	public void act() {
		if (update) {
			update = false;
		}
	}

}
