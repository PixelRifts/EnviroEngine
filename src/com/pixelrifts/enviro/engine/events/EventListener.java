package com.pixelrifts.enviro.engine.events;

@FunctionalInterface
public interface EventListener
{
    void eventOccurred(final EventData p0);
}
