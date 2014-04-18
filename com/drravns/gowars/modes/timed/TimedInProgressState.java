package com.drravns.gowars.modes.timed;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class TimedInProgressState implements ITimedModeState {

	private TimedMode mode;
	private AtomicInteger time = new AtomicInteger(120);
	private AtomicBoolean expired = new AtomicBoolean(false);
	private Timer t = new Timer();

	public TimedInProgressState(TimedMode mode) {
		this.mode = mode;

	}

	private class ElapsedTime extends TimerTask {

		@Override
		public void run() {
			time.set(time.get() - 1);
			if (time.get() < 0) {
				t.cancel();
				onTimerExpired();
			} else {
				onTimerTick(time.get());
			}
		}

	}

	public void startTimer() {
		t.cancel();
		t.scheduleAtFixedRate(new ElapsedTime(), Calendar.getInstance()
				.getTime(), 1000);
	}

	@Override
	public void onTimerTick(int secondsLeft) {
		mode.getTicker().setTime(secondsLeft);
	}

	@Override
	public void onTimerExpired() {
		if (!expired.get()) {
			expired.set(true);
			mode.setState(mode.getTimedCompletedState());
			mode.endGame();
		}
	}

}
