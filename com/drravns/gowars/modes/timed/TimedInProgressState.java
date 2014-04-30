package com.drravns.gowars.modes.timed;


public class TimedInProgressState implements ITimedModeState {

	private TimedMode mode;

	public TimedInProgressState(TimedMode mode) {
		this.mode = mode;
	}

	@Override
	public void onTimerExpired() {
		mode.setState(mode.getTimedCompletedState());
		mode.endGame();
	}

}
