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

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by PiotrJ on 03/09/15.
 */
public class Room {
	public int id;
	public Rectangle bounds = new Rectangle();
	public Body body;
	public float gridSize;
	public boolean isMain;
	public boolean isHallway;
	public boolean isExtra;

	public Room (int id, float gridSize) {
		this.id = id;
		this.gridSize = gridSize;
	}

	public void update () {
		if (body == null)
			return;
		Vector2 pos = body.getPosition();
		bounds.setPosition(Utils.roundToSize(pos.x - bounds.width / 2, gridSize),
			Utils.roundToSize(pos.y - bounds.height / 2, gridSize));
	}

	public boolean isSleeping () {
		if (body == null)
			return false;
		return !body.isAwake();
	}

	public void draw (ShapeRenderer renderer) {
		float s = gridSize * 0.16f;
		renderer.rect(bounds.x + s, bounds.y + s, bounds.width - 2 * s, bounds.height - 2 * s);
	}

	public void set (float x, float y, float w, float h) {
		if (w < 0)
			w = -w;
		if (h < 0)
			h = -h;
		bounds.set(x, y, w, h);
	}

	public float cx () {
		return bounds.x + bounds.width / 2;
	}

	public float cy () {
		return bounds.y + bounds.height / 2;
	}

	@Override public String toString () {
		return "Room{" +
			"id=" + id +
			'}';
	}

	@Override public boolean equals (Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Room room = (Room)o;

		if (id != room.id)
			return false;
		if (Float.compare(room.gridSize, gridSize) != 0)
			return false;
		if (isMain != room.isMain)
			return false;
		if (bounds != null ? !bounds.equals(room.bounds) : room.bounds != null)
			return false;
		return !(body != null ? !body.equals(room.body) : room.body != null);

	}

	@Override public int hashCode () {
		int result = id;
		result = 31 * result + (bounds != null ? bounds.hashCode() : 0);
		result = 31 * result + (body != null ? body.hashCode() : 0);
		result = 31 * result + (gridSize != +0.0f ? Float.floatToIntBits(gridSize) : 0);
		result = 31 * result + (isMain ? 1 : 0);
		return result;
	}
}
