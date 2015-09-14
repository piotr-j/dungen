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
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Array;

/**
 * Created by PiotrJ on 03/09/15.
 */
public class HallwayPath {
	private static int ID = 0;
	public int id;
	public Vector2 start = new Vector2();
	public Vector2 bend = new Vector2();
	public Vector2 end = new Vector2();
	public Rectangle hallA = new Rectangle();
	public Rectangle hallB = new Rectangle();
	public boolean hasBend;
	public boolean recon;
	public int width = 1;
	public Room roomA;
	public Room roomB;
	public Array<Room> overlap = new Array<>();
	private float gridSize;

	public HallwayPath (float gridSize, int width) {
		this.gridSize = gridSize;
		this.width = width;
		this.id = ID++;
	}

	Vector2 tmp = new Vector2();
	public void draw (ShapeRenderer renderer) {
		if (recon) {
			renderer.setColor(Color.ORANGE);
		} else {
			renderer.setColor(Color.LIME);
		}
		if (hasBend) {
			renderer.rectLine(start.x, start.y, bend.x, bend.y, gridSize * 0.33f);
			renderer.rectLine(bend.x, bend.y, end.x, end.y, gridSize * 0.33f);
		} else {
			renderer.rectLine(start.x, start.y, end.x, end.y, gridSize * 0.33f);
		}
	}

	private Rectangle setPathRect(Vector2 start, Vector2 end, Rectangle rect) {
		tmp.set(end).sub(start);
		float grid = gridSize * width;
		float sx = start.x > end.x? end.x:start.x;
		float sy = start.y > end.y? end.y:start.y;
		float width = grid - gridSize / 2;
		float height = grid - gridSize / 2;
		if (MathUtils.isZero(tmp.x)) {
			// horizontal path
			sx = sx - grid / 2 + gridSize / 4;
			sy = sy - grid / 2 + gridSize / 4;
			height = Math.abs(end.y - start.y) + grid - gridSize / 2;
			rect.set(sx, sy, width, height);
		} else {
			// vertical path
			sx = sx - grid / 2 + gridSize / 4;
			sy = sy - grid / 2 + gridSize / 4;
			width = Math.abs(end.x - start.x) + grid - gridSize / 2;
			rect.set(sx, sy, width, height);
		}
		return rect;
	}

	public void set (float sx, float sy, float ex, float ey) {
		start.set(sx, sy);
		end.set(ex, ey);
		hasBend = false;
		setPathRect(start, end, hallA);
	}

	public void set (float sx, float sy, float bx, float by, float ex, float ey) {
		start.set(sx, sy);
		bend.set(bx, by);
		end.set(ex, ey);
		hasBend = true;
		setPathRect(start, bend, hallA);
		setPathRect(bend, end, hallB);
	}

	public boolean intersects (Room room) {
		Rectangle b = room.bounds;
		if (hasBend) {
			return b.overlaps(hallA) || b.overlaps(hallB);
		} else {
			return b.overlaps(hallA);
		}
	}

	@Override public String toString () {
		return "Hallway{" +
			"id=" + id +
			'}';
	}
}
