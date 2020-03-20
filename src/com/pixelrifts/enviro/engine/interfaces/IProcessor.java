package com.pixelrifts.enviro.engine.interfaces;

@FunctionalInterface
public interface IProcessor<T> {
	public void process(T t);
}
