package com.ribdum.battlepets.event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ribdum.battlepets.event.listener.EventListener;

public class EventDispatcher implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7056024069766280089L;
	private static Map<EventType, List<EventListener>> eventListeners = new HashMap<EventType, List<EventListener>>();

	public static void registerListener(EventListener eventListener) {
		// Remove duplicates if listener is OverwriteSimilar
		if (eventListener instanceof OverwriteSimilar) {
			for (List<EventListener> list : eventListeners.values()) {
				list.remove(eventListener);
			}
		}

		for (EventType eventType : eventListener.getListenedEvents()) {
			List<EventListener> listeners = eventListeners.get(eventType);
			if (listeners == null) {
				listeners = new ArrayList<EventListener>();
				eventListeners.put(eventType, listeners);
			}
			if (!(eventListener instanceof SkipIfSimilar)
					|| !(listeners.contains(eventListener))) {
				listeners.add(eventListener);
			}
		}
	}

	public static void fireEvent(Event event) {
		List<EventListener> listeners = eventListeners
				.get(event.getEventType());
		if (listeners != null) {
			for (int i = 0; i < listeners.size(); i++) {
				EventListener eventListener = listeners.get(i);
				if (!eventListener.isExpired()) {
					eventListener.execute(event);
				}
				if (eventListener.isExpired()) {
					listeners.remove(i--);
				}
			}
		}
	}

}
