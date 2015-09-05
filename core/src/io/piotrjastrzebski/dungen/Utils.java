package io.piotrjastrzebski.dungen;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Random;

/**
 * Created by PiotrJ on 04/09/15.
 */
public class Utils {

	public static Vector2 pointInCircle(float radius, Vector2 out) {
		return pointInEllipse(radius * 2, radius * 2, out);
	}

	public static Vector2 pointInEllipse (float width, float height, Vector2 out) {
		float t = (float)(MathUtils.random() * Math.PI * 2);
		float u = MathUtils.random() + MathUtils.random();
		float r = (u > 1)?(2 - u):u;
		out.set(
			width * r * MathUtils.cos(t) / 2,
			height * r * MathUtils.sin(t) / 2
		);
		return out;
	}

	public static Vector2 roundedPointInEllipse(float width, float height, float size, Vector2 out) {
		Utils.pointInEllipse(width, height, out);
		out.x = roundToSize(out.x, size);
		out.y = roundToSize(out.y, size);
		return out;
	}

	public static float roundToSize(float value, float size) {
		return ((Math.round(value / size)) * size);
	}

	public static Vector2 roundToSize(Vector2 value, float size) {
		value.x = roundToSize(value.x, size);
		value.y = roundToSize(value.y, size);
		return value;
	}

	private static Random rng = new Random(TimeUtils.millis());
	public static float rngFloat() {
		return (float)(rng.nextGaussian());
	}

	public static float rngFloat(float max) {
		return rngFloat() * max;
	}

	public static float roundedRngFloat (float scale, float size) {
		return Utils.roundToSize(Utils.rngFloat(scale), size);
	}

	public static float rngFloat(float min ,float max) {
		return min + rngFloat() * (max - min);
	}
}
