package com.pixelrifts.enviro.engine.quads;

import org.joml.Vector2f;

import com.pixelrifts.enviro.engine.base.Loader;
import com.pixelrifts.enviro.engine.rendering.Texture;
import com.pixelrifts.enviro.engine.util.TextureUtils;
import com.pixelrifts.enviro.engine.util.ArrayUtils;
import com.pixelrifts.enviro.engine.vertex.TexturedVertex2D;

public class TexturedQuad extends Quad {
	private Texture t;
	
	private TexturedVertex2D tl;
	private TexturedVertex2D bl;
	private TexturedVertex2D br;
	private TexturedVertex2D tr;

	public TexturedQuad(float x, float y, float width, float height, Texture t) {
		this(x, y, width, height, new Vector2f(0, 0), new Vector2f(0, 1), new Vector2f(1, 1), new Vector2f(1, 0), t);
	}

	protected TexturedQuad(float x, float y, float width, float height, Vector2f uvtl, Vector2f uvbl, Vector2f uvbr, Vector2f uvtr, Texture t) {
		super(x, y, width, height);
		
		this.t = t;

		tl = new TexturedVertex2D(new Vector2f(x, y + height), uvtl);
		bl = new TexturedVertex2D(new Vector2f(x, y), uvbl);
		br = new TexturedVertex2D(new Vector2f(x + width, y), uvbr);
		tr = new TexturedVertex2D(new Vector2f(x + width, y + height), uvtr);
		
		float[] vertices = ArrayUtils.concat(tl.getOrderedPositions(), ArrayUtils.concat(bl.getOrderedPositions(), ArrayUtils.concat(br.getOrderedPositions(), tr.getOrderedPositions())));
		float[] uvs = ArrayUtils.concat(tl.getOrderedUVs(), ArrayUtils.concat(bl.getOrderedUVs(), ArrayUtils.concat(br.getOrderedUVs(), tr.getOrderedUVs())));
		super.mesh = Loader.loadQuadToVAO(vertices, null, uvs, quadIndices);
	}

	@Override
	public void bind() {
		super.bind();
		TextureUtils.ActivateBank(1);
		t.bind();
	}

	@Override
	public void unbind() {
		super.unbind();
		t.unbind();
	}
}
