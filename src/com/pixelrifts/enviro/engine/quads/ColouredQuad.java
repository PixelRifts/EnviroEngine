package com.pixelrifts.enviro.engine.quads;

import org.joml.Vector2f;

import com.pixelrifts.enviro.engine.base.Loader;
import com.pixelrifts.enviro.engine.math.Colour;
import com.pixelrifts.enviro.engine.util.ArrayUtils;
import com.pixelrifts.enviro.engine.vertex.ColouredVertex2D;

public class ColouredQuad extends Quad {
	private ColouredVertex2D tl;
	private ColouredVertex2D bl;
	private ColouredVertex2D br;
	private ColouredVertex2D tr;
	
	public ColouredQuad(float x, float y, float width, float height, Colour c) {
		this(x, y, width, height, c, c, c, c);
	}

	public ColouredQuad(float x, float y, float width, float height, Colour ctl, Colour cbl, Colour cbr, Colour ctr) {
		super(x, y, width, height);
		tl = new ColouredVertex2D(new Vector2f(x, y + height), ctl);
		bl = new ColouredVertex2D(new Vector2f(x, y), cbl);
		br = new ColouredVertex2D(new Vector2f(x + width, y), cbr);
		tr = new ColouredVertex2D(new Vector2f(x + width, y + height), ctr);
		
		float[] positions = ArrayUtils.concat(tl.getOrderedPositions(), ArrayUtils.concat(bl.getOrderedPositions(), ArrayUtils.concat(br.getOrderedPositions(), tr.getOrderedPositions())));
		float[] colours = ArrayUtils.concat(tl.getOrderedColours(), ArrayUtils.concat(bl.getOrderedColours(), ArrayUtils.concat(br.getOrderedColours(), tr.getOrderedColours())));
		super.mesh = Loader.loadQuadToVAO(positions, colours, null, quadIndices);
	}
}
