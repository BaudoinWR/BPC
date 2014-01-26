package com.ribdum.battlepets.event.listener;

import java.io.Serializable;

import com.ribdum.battlepets.event.Event;
import com.ribdum.battlepets.event.EventType;

public abstract class EventListener implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4294756576814034834L;
	protected boolean expired = false;

	public abstract void execute(Event event);

	public boolean isExpired() {
		return this.expired;
	}

	public abstract EventType[] getListenedEvents();
}
