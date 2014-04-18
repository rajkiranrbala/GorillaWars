package com.drravns.gowars.observers;

import java.util.Collection;

public interface ISubject<T extends IObserver> {
	void attach(T observer);

	void detach(T observer);
}
