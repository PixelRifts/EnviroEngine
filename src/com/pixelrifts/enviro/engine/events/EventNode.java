package com.pixelrifts.enviro.engine.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventNode {
	public final EventNode parent;
	private List<EventListener> listeners;
	private Map<String, EventNode> leafChildren;

	public EventNode(final EventNode parent) {
		this.listeners = new ArrayList<EventListener>();
		this.leafChildren = new HashMap<String, EventNode>();
		this.parent = parent;
	}

	public void registerEvent(final EventData data, final String... eventKeys) {
		this.registerEvent(0, eventKeys, data);
	}

	public void addListener(final EventListener listener, final String... eventKeys) {
		this.addListener(0, eventKeys, listener);
	}

	private void registerEvent(final int index, final String[] eventKey, final EventData data) {
		if (index == eventKey.length) {
			this.notifyAllListeners(data);
			return;
		}
		final EventNode child = this.leafChildren.get(eventKey[index]);
		if (child == null) {
			this.notifyAllListeners(data);
		} else {
			child.registerEvent(index + 1, eventKey, data);
		}
	}

	private void addListener(final int index, final String[] childEventKey, final EventListener listener) {
		if (index == childEventKey.length) {
			this.listeners.add(listener);
			return;
		}
		EventNode child = this.leafChildren.get(childEventKey[index]);
		if (child == null) {
			child = new EventNode(this);
			this.leafChildren.put(childEventKey[index], child);
		}
		child.addListener(index + 1, childEventKey, listener);
	}

	private void notifyAllListeners(final EventData data) {
		this.notifyListeners(data);
		if (this.parent != null) {
			this.parent.notifyAllListeners(data);
		}
	}

	private void notifyListeners(final EventData data) {
		for (final EventListener listener : this.listeners) {
			listener.eventOccurred(data);
		}
	}
}
