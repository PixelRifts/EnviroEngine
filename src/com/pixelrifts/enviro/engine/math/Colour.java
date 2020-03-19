package com.pixelrifts.enviro.engine.math;

public class Colour {
	public final float r;
	public final float g;
	public final float b;
	public final float a;

	public Colour(final float r, final float g, final float b, final float a) {
		this.r = Mathf.Clamp(r, 0, 1);
		this.g = Mathf.Clamp(g, 0, 1);
		this.b = Mathf.Clamp(b, 0, 1);
		this.a = Mathf.Clamp(a, 0, 1);
	}

	public Colour(final float r, final float g, final float b) {
		this(r, g, b, 1);
	}

	public Colour(final float rgb) {
		this(rgb, rgb, rgb, 1);
	}

	public Colour() {
		this(1);
	}

	public Colour(float r, float g, Object b) {
		this(r, g, (float) b);
	}

	public static Colour Black() {
		return new Colour(0, 0, 0);
	}

	public static Colour Grey() {
		return new Colour(0.5f, 0.5f, 0.5f);
	}

	public static Colour DarkGrey() {
		return new Colour(0.2f, 0.2f, 0.2f);
	}

	public static Colour White() {
		return new Colour(1, 1, 1);
	}

	public static Colour Red() {
		return new Colour(1, 0, 0);
	}

	public static Colour Green() {
		return new Colour(0, 1, 0);
	}

	public static Colour Lime() {
		return new Colour(0, 1, 0);
	}

	public static Colour Wine() {
		return new Colour(0.5f, 0, 0);
	}

	public static Colour Forest() {
		return new Colour(0, 0.5f, 0);
	}

	public static Colour Blue() {
		return new Colour(0, 0, 0.5f);
	}

	public static Colour Yellow() {
		return new Colour(1, 1, 0);
	}

	public static Colour Cyan() {
		return new Colour(0, 1, 1);
	}

	public static Colour Magenta() {
		return new Colour(1, 0, 1);
	}
	
	public static Colour Peach() {
		return new Colour(1, 0.796078431372549f, 0.6431372549019608f);
	}
	
	public static Colour Purple() {
		return new Colour(0.4156862745098039f, 0.0509803921568627f, 0.6784313725490196f);
	}
	
	public static Colour Brown() {
		return new Colour(0.5882352941176471f, 0.2941176470588235f, 0);
	}

	public boolean compare(final Colour c) {
		return c.r == r && c.g == g && c.b == b && c.a == a;
	}

	@Override
	public String toString() {
		return "(" + String.valueOf(r) + ", " + String.valueOf(g) + ", " + String.valueOf(b) + ", " + String.valueOf(a)
				+ "";
	}
}
