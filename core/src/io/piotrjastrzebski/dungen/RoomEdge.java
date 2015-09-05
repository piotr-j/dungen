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
