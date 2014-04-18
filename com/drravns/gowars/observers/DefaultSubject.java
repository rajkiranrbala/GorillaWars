package com.drravns.gowars.observers;

import java.util.ArrayList;
import java.util.Collection;

public class DefaultSubject<T extends IObserver> implements ISubject<T> {
	private ArrayList<T> observers = new ArrayList<T>();

	@Override
	public void attach(T observer) {
		if (!observers.contains(observer)) {
			observers.add(observer);
		}
	}

	@Override
	public void detach(T observer) {
		if (observers.contains(observer)) {
			observers.remove(observer);
		}
	}

	public Collection<T> getObservers() {
		return observers;
	}

}
