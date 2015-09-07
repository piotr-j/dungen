/*******************************************************************************
 * Copyright 2015 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/

package io.piotrjastrzebski.dungen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by PiotrJ on 04/09/15.
 */
public class Grid {
	float size;
	float w, h;
	Color color = new Color(0.25f, 0.25f, 0.25f, 1f);

	public void render (ShapeRenderer renderer) {
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

	public void resize (int width, int height) {
		w = width;
		h = height;
	}
}
