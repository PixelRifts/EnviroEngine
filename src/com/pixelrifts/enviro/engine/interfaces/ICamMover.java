package com.pixelrifts.enviro.engine.interfaces;

import com.pixelrifts.enviro.engine.math.CamTransfer;

@FunctionalInterface
public interface ICamMover {
	public CamTransfer move();
}
