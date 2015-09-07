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

import com.badlogic.gdx.math.Vector2;

/**
 * Created by PiotrJ on 03/09/15.
 */
public class RoomEdge {
	private static Vector2 tmp = new Vector2();
	public Room roomA;
	public Room roomB;
	public float len;
	public boolean mst;
	public boolean recon;

	public void set (Room roomA, Room roomB) {
		this.roomA = roomA;
		this.roomB = roomB;
		len = tmp.set(roomA.cx(), roomA.cy()).sub(roomB.cx(), roomB.cy()).len();
	}

	public float ax () {
		return roomA.cx();
	}

	public float ay () {
		return roomA.cy();
	}

	public float by () {
		return roomB.cy();
	}

	public float bx () {
		return roomB.cx();
	}

	@Override public boolean equals (Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		RoomEdge edge = (RoomEdge)o;
		if (edge.roomA == roomA && edge.roomB == roomB)
			return true;
		if (edge.roomA == roomB && edge.roomB == roomA)
			return true;
		return false;
	}

	@Override public int hashCode () {
		int result = roomA != null ? roomA.hashCode() : 0;
		result = 31 * result + (roomB != null ? roomB.hashCode() : 0);
		result = 31 * result + (len != +0.0f ? Float.floatToIntBits(len) : 0);
		return result;
	}

	@Override public String toString () {
		return "RoomEdge{" +
			"roomA=" + roomA +
			", roomB=" + roomB +
			", mst=" + mst +
			'}';
	}
}
