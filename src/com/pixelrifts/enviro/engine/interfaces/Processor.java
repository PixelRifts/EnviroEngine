package com.pixelrifts.enviro.engine.interfaces;

@FunctionalInterface
public interface Processor<T> {
	public void process(T t);
}
