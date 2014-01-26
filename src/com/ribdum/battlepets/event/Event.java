package com.ribdum.battlepets.event;

import java.io.Serializable;

public class Event implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7810013843756133136L;
	private final EventType eventType;
	private Object source;

	public Event(EventType eventType, Object source) {
		this.eventType = eventType;
		this.source = source;
	}

	public EventType getEventType() {
		return this.eventType;
	}

	public Object getSource() {
		return this.source;
	}

}
