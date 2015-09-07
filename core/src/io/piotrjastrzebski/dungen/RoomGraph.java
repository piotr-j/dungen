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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * Created by PiotrJ on 03/09/15.
 */
public class RoomGraph {
	Array<RoomEdge> edges = new Array<>();
	ObjectMap<Room, RoomNode> roomToNode = new ObjectMap<>();
	Array<RoomNode> nodes = new Array<>();

	public RoomGraph () {
	}

	public void add (RoomEdge edge) {
		add(edge.roomA, edge.roomB);
	}

	public void add (Room roomA, Room roomB) {
		RoomEdge edge = new RoomEdge();
		edge.set(roomA, roomB);
		if (!edges.contains(edge, false)) {
			edges.add(edge);
		}
		addNode(roomA, roomB);
		addNode(roomB, roomA);
	}

	private void addNode (Room roomA, Room roomB) {
		RoomNode nodeA = roomToNode.get(roomA);
		if (nodeA == null) {
			nodeA = new RoomNode();
			nodeA.room = roomA;
			roomToNode.put(roomA, nodeA);
			nodes.add(nodeA);
		}
		nodeA.add(roomB);
	}

	public void render (ShapeRenderer renderer) {
		if (edges.size == 0)
			return;
		for (RoomEdge e : edges) {
			if (e.recon) {
				renderer.setColor(Color.CYAN);
//			renderer.line(e.ax(), e.ay(), e.bx(), e.by());
			} else if (e.mst) {
				renderer.setColor(Color.YELLOW);
//			renderer.line(e.ax(), e.ay(), e.bx(), e.by());
			} else {
//					renderer.setColor(Color.GREEN);
//				renderer.setColor(Color.CLEAR);
			}
		}
	}

	public void clear () {
		edges.clear();
		roomToNode.clear();
		nodes.clear();
	}

	public Array<RoomEdge> getEdges () {
		return edges;
	}

	Array<RoomNode> open = new Array<>();
	Array<RoomNode> closed = new Array<>();

	public boolean isConnected (RoomEdge edge) {
		if (edges.size == 0)
			return false;
		// find if there is an existing path between edge.roomA and edge.roomB
		Room start = edge.roomA;
		Room target = edge.roomB;
		RoomNode sNode = roomToNode.get(start);
		// start not yet in graph
		if (sNode == null)
			return false;

		closed.clear();
		open.clear();
		open.add(sNode);
		while (open.size > 0) {
			RoomNode first = open.get(0);
			closed.add(first);
			open.removeIndex(0);
			for (RoomEdge e : first.edges) {
				RoomNode node = roomToNode.get(e.roomB);
				if (node.room == target) {
					return true;
				}
				if (!closed.contains(node, true)) {
					open.add(node);
				}
			}
		}
		return false;
	}
}
