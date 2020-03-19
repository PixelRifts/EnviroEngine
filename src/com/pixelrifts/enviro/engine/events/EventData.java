package com.pixelrifts.enviro.engine.events;

public class EventData
{
	public final Object object;
	public final int count;

	public EventData()
	{
		this.count = 0;
		this.object = null;
	}

	public EventData(final Object object)
	{
		this.count = 0;
		this.object = object;
	}

	public EventData(final Object object, final int count)
	{
		this.count = count;
		this.object = object;
	}
}
