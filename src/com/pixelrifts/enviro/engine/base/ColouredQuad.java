package com.pixelrifts.enviro.engine.base;

import static org.lwjgl.opengl.GL20.*;

import org.joml.Vector2f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import com.pixelrifts.enviro.engine.interfaces.IBindable;
import com.pixelrifts.enviro.engine.interfaces.IRenderable;
import com.pixelrifts.enviro.engine.util.Colour;
import com.pixelrifts.enviro.engine.util.Utils;
import com.pixelrifts.enviro.engine.vertex.ColouredVertex2D;

public class ColouredQuad implements IRenderable, IBindable {
	private RawMesh mesh;
	@SuppressWarnings("unused")
	private float x;
	@SuppressWarnings("unused")
	private float y;
	@SuppressWarnings("unused")
	private float width;
	@SuppressWarnings("unused")
	private float height;
	private ColouredVertex2D tl;
	private ColouredVertex2D bl;
	private ColouredVertex2D br;
	private ColouredVertex2D tr;
	
	public ColouredQuad(float x, float y, float width, float height, Colour c) {
		this(x, y, width, height, c, c, c, c);
	}

	public ColouredQuad(float x, float y, float width, float height, Colour ctl, Colour cbl, Colour cbr, Colour ctr) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		tl = new ColouredVertex2D(new Vector2f(x, y + height), ctl);
		bl = new ColouredVertex2D(new Vector2f(x, y), cbl);
		br = new ColouredVertex2D(new Vector2f(x + width, y), cbr);
		tr = new ColouredVertex2D(new Vector2f(x + width, y + height), ctr);
		
		float[] positions = Utils.concat(tl.getOrderedPositions(), Utils.concat(bl.getOrderedPositions(), Utils.concat(br.getOrderedPositions(), tr.getOrderedPositions())));
		float[] colours = Utils.concat(tl.getOrderedColours(), Utils.concat(bl.getOrderedColours(), Utils.concat(br.getOrderedColours(), tr.getOrderedColours())));
		int[] indices = { 0, 1, 2, 3, 0, 2 };
		
		this.mesh = Loader.loadColouredQuadToVAO(positions, colours, indices);
	}

	@Override
	public void render() {
		GL15.glDrawElements(GL11.GL_TRIANGLES, mesh.vertexCount, GL11.GL_UNSIGNED_INT, 0);
	}

	@Override
	public void bind() {
		mesh.bind();
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
	}

	@Override
	public void unbind() {
		mesh.unbind();
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(0);
	}
}
