package com.drravns.gowars.modes.timed;

public interface ITimedModeState {

	void onTimerTick(int secondsLeft);

	void onTimerExpired();

}
