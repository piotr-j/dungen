package io.piotrjastrzebski.dungen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by PiotrJ on 04/09/15.
 */
public class Grid {
	float size;
	float w, h;
	Color color = new Color(0.25f, 0.25f, 0.25f, 0.25f);

	public void draw (ShapeRenderer renderer) {
		renderer.setColor(color);
		int hSegments = (int)(w / size);
		for (int i = 0; i < hSegments / 2; i++) {
			float x = i * size;
			renderer.line(x, -h / 2, x, h / 2);
			renderer.line(-x, -h / 2, -x, h / 2);
		}
		int vSegments = (int)(h / size);
		for (int i = 0; i < vSegments / 2; i++) {
			float y = i * size;
			renderer.line(-w / 2, y, w / 2, y);
			renderer.line(-w / 2, -y, w / 2, -y);
		}
	}

	public Grid setSize (float size) {
		this.size = size;
		return this;
	}

	public Grid setColor (float r, float g, float b, float a) {
		this.color.set(r, g, b, a);
		return this;
	}

	public void setViewPort (int width, int height) {
		w = width;
		h = height;
	}
}
