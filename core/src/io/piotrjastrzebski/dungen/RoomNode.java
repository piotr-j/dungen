package io.piotrjastrzebski.dungen;

import com.badlogic.gdx.utils.Array;

/**
 * Created by PiotrJ on 03/09/15.
 */
public class RoomNode {
	public Room room;
	public Array<RoomEdge> edges = new Array<>();

	public void add (Room add) {
		RoomEdge edge = new RoomEdge();
		edge.roomA = room;
		edge.roomB = add;
		edges.add(edge);
	}

	public void add (RoomEdge add) {
		RoomEdge edge = new RoomEdge();
		if (add.roomA == room) {
			edge.roomA = add.roomA;
			edge.roomB = add.roomB;
		} else {
			edge.roomA = add.roomB;
			edge.roomB = add.roomA;
		}
		edges.add(edge);
	}

	@Override public boolean equals (Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		RoomNode roomNode = (RoomNode)o;

		if (room != null ? !room.equals(roomNode.room) : roomNode.room != null)
			return false;
		return !(edges != null ? !edges.equals(roomNode.edges) : roomNode.edges != null);

	}

	@Override public int hashCode () {
		int result = room != null ? room.hashCode() : 0;
		result = 31 * result + (edges != null ? edges.hashCode() : 0);
		return result;
	}

	@Override public String toString () {
		return "RoomNode{" +
			"room=" + room +
			'}';
	}
}
